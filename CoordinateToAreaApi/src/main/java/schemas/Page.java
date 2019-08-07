package schemas;

import java.util.Date;

public class Page {
    private Date pulishedDate;
    private String author;
    private String content;

    public Page(Date pulishedDate, String author, String content) {
        this.pulishedDate = pulishedDate;
        this.author = author;
        this.content = content;
    }

    public Date getPulishedDate() {
        return pulishedDate;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }
}
