package com.domain.wiseSaying;

import com.AppContext;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WiseSaying {
    private int id;
    private String content;
    private String author;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private DateTimeFormatter formatter = AppContext.forPrintDateTimeFormatter;

    public WiseSaying (String content, String author) {
        LocalDateTime now = LocalDateTime.now();
        this.content = content;
        this.author = author;
        this.createdDate = now;
        this.modifiedDate = now;
    }

    public boolean isNew() {
        return getId() == 0;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    public String getCreatedDate() {
        return createdDate.format(formatter);
    }

    public String getModifiedDate() {
        return modifiedDate.format(formatter);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setModifiedDate(LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}
