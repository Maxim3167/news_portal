package myProject.validator;

import lombok.SneakyThrows;
import myProject.web.dto.CommentsDto;
import myProject.web.dto.NewsDto;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class NewPublicationValidator implements ValidatorForPublication<NewPublicationValidationResult> {
    private static final Path ABSOLUTE_PATH = Path.of("C:\\Users\\Пользователь\\IdeaProjects\\http_course_dmdev\\resources\\нецензурные слова.txt");
    private static List<String> badWords;

    static {
        try {
            badWords = Files.readAllLines(ABSOLUTE_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static final NewPublicationValidator INSTANCE = new NewPublicationValidator();

    @SneakyThrows
    @Override
    public NewPublicationValidationResult isValidComment(CommentsDto commentsDto) {
        NewPublicationValidationResult newPublicationValidationResult = new NewPublicationValidationResult();
        for (String line : badWords) {
            if(commentsDto.getText().contains(line)){
                newPublicationValidationResult.setViolation(true);
                return newPublicationValidationResult;
            }
        }
        return newPublicationValidationResult;
    }
    @SneakyThrows
    @Override
    public NewPublicationValidationResult isValidNews(NewsDto newsDto) {
        NewPublicationValidationResult newPublicationValidationResult = new NewPublicationValidationResult();
        for (String line : Files.readAllLines(ABSOLUTE_PATH)) {
            if(newsDto.getText().contains(line)){
                newPublicationValidationResult.setViolation(true);
            }
        }
        return newPublicationValidationResult;
    }

    public static NewPublicationValidator getInstance(){
        return INSTANCE;
    }
}
