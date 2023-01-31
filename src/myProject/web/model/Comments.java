package myProject.web.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Comments {
    private Integer id;
    private String text;
    private Integer authorId;
    private Integer newsId;
}
