package com.example.nenezoid.ruplibapp;

/**
 * Created by Nenezoid on 1/9/2018.
 */

public class Book {

    public int id;
    public String title,author;
    public boolean available;

    public Book(int id_, String title_, String author_, boolean available_){
        this.id=id_;
        this.title=title_;
        this.author=author_;
        this.available=available_;
    }

    public Book(){}

    public String toString()
    {
        return "Title: "+title+"\nAuthor: "+author+"\nIs available: "+available;
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

    public boolean getAvailable() {
        return available;
    }
}
