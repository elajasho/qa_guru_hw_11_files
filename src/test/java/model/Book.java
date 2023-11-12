package model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;

public class Book {
    private String title;
    private String author;
    private int year;

    @JsonCreator
    public Book(
            @JsonProperty("title") String title,
            @JsonProperty("author") String author,
            @JsonProperty("year") int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("author")
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @JsonProperty("year")
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}