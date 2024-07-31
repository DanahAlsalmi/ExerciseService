package com.example.exerciseservice.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor

public class NewsArticle {
    @NotNull(message = "ID should not be NULL")
    private int id;

    @NotEmpty(message = "Title should not EMPTY")
    @Size(min = 4,max = 100,message = "Title should between 4-100 character")
    private String title;

    @NotEmpty(message = "Author should not EMPTY")
    @Size(min = 4,max = 20,message = "Author should between 4-20 character")
    private String author;

    @NotEmpty(message = "Content should not be EMPTY")
    @Size(min = 20,message = "Content should be more than 200 character")
    private String content;

    @NotEmpty(message = "Category cannot be null")
    @Pattern(regexp = "Politics|Sports|Technology", message = "Category must be either Politics, Sports or Technology")
    private String category;

    @NotEmpty(message = "Image URL cannot be null ")
    private String imageUrl;

    @AssertFalse
    private boolean isPublished;

    private LocalDate publishedDate;
}
