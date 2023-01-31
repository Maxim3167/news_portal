package myProject.web.service;

import myProject.exception.BanException;
import myProject.exception.CensorshipException;
import myProject.mapper.CommentsDtoMapper;
import myProject.validator.NewCommentsValidationResult;
import myProject.validator.NewCommentsValidator;
import myProject.web.dao.CommentsDao;
import myProject.web.dao.UserDao;
import myProject.web.dto.CommentsDto;
import myProject.web.model.Comments;
import myProject.web.model.User;

import java.time.LocalDate;
import java.util.Optional;

public class CommentsService {
    private final CommentsDao commentsDao = CommentsDao.getInstance();
    private final CommentsDtoMapper commentsDtoMapper = CommentsDtoMapper.getInstance();
    private final NewCommentsValidator newCommentsValidator = NewCommentsValidator.getInstance();
    private final UserDao userDao = UserDao.getInstance();
    private static final CommentsService INSTANCE = new CommentsService();

    public Comments create(CommentsDto commentsDto) {
        if(!checkUser(commentsDto)){
          throw new BanException();
        }
        NewCommentsValidationResult newCommentsValidationResult = newCommentsValidator.isValidComment(commentsDto);
        if (!newCommentsValidationResult.isValid()) {
            invalidAction(commentsDto);
            throw new CensorshipException();
        }
        Comments comments = commentsDtoMapper.mapToEntity(commentsDto);
        return commentsDao.create(comments);
    }

    private boolean checkUser(CommentsDto commentsDto){
        Optional<User> optionalUser = userDao.findById(commentsDto.getAuthorId());
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            return !user.getIsBanned();
        }
        return true;
    }

    private void invalidAction(CommentsDto commentsDto) {
        Optional<User> userOptional = userDao.findById(commentsDto.getAuthorId());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setNotice(user.getNotice() + 1);
            if (user.getNotice() == 2) {
                user.setIsBanned(true);
                user.setBanDate(LocalDate.now());
            }
        }
    }
    public static CommentsService getInstance() {
        return INSTANCE;
    }
}
