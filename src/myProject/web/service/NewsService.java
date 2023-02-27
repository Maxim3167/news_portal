package myProject.web.service;

import myProject.exception.BanException;
import myProject.exception.CensorshipException;
import myProject.mapper.NewsDtoMapper;
import myProject.validator.NewPublicationValidationResult;
import myProject.validator.NewPublicationValidator;
import myProject.web.dao.NewsDao;
import myProject.web.dao.UserDao;
import myProject.web.dto.NewsDto;
import myProject.web.model.News;
import myProject.web.model.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class NewsService {
    private final NewsDao newsDao = NewsDao.getInstance();
    private final NewsDtoMapper newsDtoMapper = NewsDtoMapper.getInstance();
    private final NewPublicationValidator newPublicationValidator= NewPublicationValidator.getInstance();
    private final UserDao userDao = UserDao.getInstance();
    private static final NewsService INSTANCE = new NewsService();

    public List<NewsDto> findAllNews(){
        List<News> newsList = newsDao.findAll();
        return newsList.stream().map(news ->
                NewsDto.builder()
                        .name(news.getName())
                        .text(news.getText())
                        .id(news.getId())
                        .build()).toList();
    }

    public static NewsService getInstance(){
        return INSTANCE;
    }

    public void create(NewsDto newsDto) {
        Optional<User> optionalUser = userDao.findById(newsDto.getAuthorId());
        if(!checkUser(optionalUser)){
            throw new BanException("Ваш аккаунт заблокирован. Вы не можете публиковать новости и оставлять комментарии");
        }
        NewPublicationValidationResult newNewsValidationResult = newPublicationValidator.isValidNews(newsDto);
        if (newNewsValidationResult.isValid()) {
            banAction(optionalUser,newsDto);
        }
        News news = newsDtoMapper.mapToEntity(newsDto);
        newsDao.create(news);
    }

    public Optional<NewsDto> findById(String id){
        return newsDao.findById(id).map(news -> NewsDto.builder()
                .id(news.getId())
                .name(news.getName())
                .text(news.getText())
                .build());
    }

    private boolean checkUser(Optional<User> optionalUser){
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            return !userDao.getIsBanned(user.getId()) || userDao.getTurnOffBanDate(user.getId()).isEqual(userDao.getBanDate(user.getId()).plusDays(1));
        }
        return true;
    }

    private void banAction(Optional<User> userOptional,NewsDto newsDto) {
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (!userDao.getNotice(user.getId())) {
                userDao.setNotice(user.getId(),true);
                throw new CensorshipException("Вам вынесено предупреждение за ненормативную лексику. В следующий раз вы будете забанены");
            }
            else{
                userDao.setIsBanned(user.getId(), true);
                userDao.setBanDate(user.getId(), LocalDateTime.now());
                throw new BanException("Вы забанены за неоднократное нарушение правил портала");
            }
        }
    }

    public boolean update(String newName,String newContent,String id){
        if(newName != null && newContent != null){
           return newsDao.updateAll(newName,newContent,id);
        } else if (newName == null) {
           return newsDao.updateContent(newContent,id);
        }
        else {
           return newsDao.updateName(newName,id);
        }
    }
}
