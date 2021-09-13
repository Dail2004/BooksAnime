package com.example.home1.model;

import java.io.Serializable;

public class Book implements Serializable {
    private int image;
    private String title;

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public  Book(int image, String title, String description) {
        this.image = image;
        this.title = title;
        this.description = description;
    }

    private String description;
}
