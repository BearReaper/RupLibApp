package com.example.nenezoid.ruplibapp;

/**
 * Created by Nenezoid on 1/9/2018.
 */

public class Book {

    private int id;
    private String title,author;
    private boolean isAviable;

    public Book(int id_, String title_, String author_, boolean isAviable_){
        this.id=id_;
        this.title=title_;
        this.author=author_;
        this.isAviable=isAviable_;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAviable() {
        return isAviable;
    }
}
