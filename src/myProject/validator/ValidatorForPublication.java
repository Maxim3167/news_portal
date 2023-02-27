package myProject.validator;

import myProject.web.dto.CommentsDto;
import myProject.web.dto.NewsDto;

public interface ValidatorForPublication<R>{
    R isValidComment(CommentsDto commentsDto);

    R isValidNews(NewsDto newsDto);
}
