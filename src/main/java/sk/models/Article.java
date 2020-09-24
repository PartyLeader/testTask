package sk.models;

import java.util.List;

public class Article {
    private String articleTitle;
    private String status;
    private String body;
    private List<String> tags;

    //todo: here can be wither or builder pattern
    public Article(String articleTitle, String status, String body, List<String> tags) {
        this.articleTitle = articleTitle;
        this.status = status;
        this.body = body;
        this.tags = tags;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public String getStatus() {
        return status;
    }

    public String getBody() {
        return body;
    }

    public List<String> getTags() {
        return tags;
    }
    public String getTagsAsString() {
        return String.join(",", tags);
    }

    //we use this function for Allure attachments
    @Override
    public String toString() {
        return "Article{" +
                "articleTitle='" + articleTitle + '\'' +
                ", status='" + status + '\'' +
                ", body='" + body + '\'' +
                ", tags=" + tags +
                '}';
    }
}
