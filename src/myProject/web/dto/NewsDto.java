package myProject.web.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class NewsDto {
    Integer id;
    String name;
    String text;
    Integer authorId;
}
