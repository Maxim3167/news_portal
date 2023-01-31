package myProject.validator;

import lombok.SneakyThrows;
import myProject.exception.CensorshipException;
import myProject.web.dto.CommentsDto;

import java.nio.file.Files;
import java.nio.file.Path;

public class NewCommentsValidator implements Validator<CommentsDto,NewCommentsValidationResult>{
    private static final Path ABSOLUTE_PATH = Path.of("C:\\Users\\Пользователь\\IdeaProjects\\http_course_dmdev\\resources\\нецензурные слова.txt");
    private static final NewCommentsValidator INSTANCE = new NewCommentsValidator();

    @SneakyThrows
    @Override
    public NewCommentsValidationResult isValidComment(CommentsDto commentsDto) {
        NewCommentsValidationResult newCommentsValidationResult = new NewCommentsValidationResult();
        for (String line : Files.readAllLines(ABSOLUTE_PATH)) {
            if(commentsDto.getText().contains(line)){
                newCommentsValidationResult.setViolation(true);
            }
        }
        return newCommentsValidationResult;
    }

    @Override
    public NewCommentsValidationResult isValidUser(CommentsDto commentsDto) {
        return null;
    }

    public static NewCommentsValidator getInstance(){
        return INSTANCE;
    }
}
