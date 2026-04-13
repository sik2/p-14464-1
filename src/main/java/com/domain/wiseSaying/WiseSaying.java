package com.domain.wiseSaying;

import java.time.LocalDateTime;

public class WiseSaying {
    private final int id;
    private String content;
    private String author;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public WiseSaying (int id, String content, String author) {
        LocalDateTime now = LocalDateTime.now();
        this.id = id;
        this.content = content;
        this.author = author;
        this.createdDate = now;
        this.modifiedDate = now;
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

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
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
