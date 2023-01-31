package myProject.web.service;

import myProject.mapper.NewsDtoMapper;
import myProject.web.dao.NewsDao;
import myProject.web.dto.NewsDto;
import myProject.web.model.News;

import java.util.List;
import java.util.Optional;

public class NewsService {
    private final NewsDao newsDao = NewsDao.getInstance();
    private final NewsDtoMapper newsDtoMapper = NewsDtoMapper.getInstance();
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
        //валидация не нужна
        //просто мапим и все
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
