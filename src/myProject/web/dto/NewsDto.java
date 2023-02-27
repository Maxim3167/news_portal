package myProject.web.dto;

public final class NewsDto {
    private final Integer id;
    private final String name;
    private final String text;
    private final Integer authorId;

    NewsDto(Integer id, String name, String text, Integer authorId) {
        this.id = id;
        this.name = name;
        this.text = text;
        this.authorId = authorId;
    }

    public static NewsDtoBuilder builder() {
        return new NewsDtoBuilder();
    }

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getText() {
        return this.text;
    }

    public Integer getAuthorId() {
        return this.authorId;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof NewsDto)) return false;
        final NewsDto other = (NewsDto) o;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$text = this.getText();
        final Object other$text = other.getText();
        if (this$text == null ? other$text != null : !this$text.equals(other$text)) return false;
        final Object this$authorId = this.getAuthorId();
        final Object other$authorId = other.getAuthorId();
        if (this$authorId == null ? other$authorId != null : !this$authorId.equals(other$authorId)) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $text = this.getText();
        result = result * PRIME + ($text == null ? 43 : $text.hashCode());
        final Object $authorId = this.getAuthorId();
        result = result * PRIME + ($authorId == null ? 43 : $authorId.hashCode());
        return result;
    }

    public String toString() {
        return "NewsDto(id=" + this.getId() + ", name=" + this.getName() + ", text=" + this.getText() + ", authorId=" + this.getAuthorId() + ")";
    }

    public static class NewsDtoBuilder {
        private Integer id;
        private String name;
        private String text;
        private Integer authorId;

        NewsDtoBuilder() {
        }

        public NewsDtoBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public NewsDtoBuilder name(String name) {
            this.name = name;
            return this;
        }

        public NewsDtoBuilder text(String text) {
            this.text = text;
            return this;
        }

        public NewsDtoBuilder authorId(Integer authorId) {
            this.authorId = authorId;
            return this;
        }

        public NewsDto build() {
            return new NewsDto(id, name, text, authorId);
        }

        public String toString() {
            return "NewsDto.NewsDtoBuilder(id=" + this.id + ", name=" + this.name + ", text=" + this.text + ", authorId=" + this.authorId + ")";
        }
    }
}
