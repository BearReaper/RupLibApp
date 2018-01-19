package com.example.nenezoid.ruplibapp;

/**
 * Created by Nenezoid on 1/9/2018.
 */

public class Book {

    public String title,author,id,description;
    public boolean available;
    public String imageUrl;

    public Book(String id_, String title_, String author_, String url,String description_,boolean available_){
        this.id=id_;
        this.title=title_;
        this.author=author_;
        this.description=description_;
        this.available=available_;
        imageUrl=url;
    }

    public Book(){}
    public boolean contains(String searchStr)
    {
        String str=searchStr.toLowerCase();
        try {
            return title.toLowerCase().contains(str) || author.toLowerCase().contains(str) || description.toLowerCase().contains(str);
        }catch (Exception e)
        {
            return false;
        }

    }
    public String toString()
    {
        return "Title: "+title+"\nAuthor: "+author+"\nLocation: "+id+"\nIs available: "+(available?"yes":"no");
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getId() {
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

    public String getDescription(){return description;}
}
