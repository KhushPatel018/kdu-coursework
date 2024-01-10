package org.example;

public class Book {
    private static LoggingSystem ls = new LoggingSystem();
    private String title;
    private String author;
    private int publicationYear;
    private double averageRating;
    private int ratingsCount;
    private String imageUrl;

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public void setRatingsCount(int ratingsCount) {
        this.ratingsCount = ratingsCount;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getRatingsCount() {
        return ratingsCount;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void printBook()
    {
        ls.logInfo("Book Details ");
        ls.logInfo("Title : " + this.getTitle());
        ls.logInfo("Author : "+this.getAuthor());
        ls.logInfo("Publication Year : " + this.getPublicationYear());
        ls.logInfo("Average Rating : " + this.getAverageRating());
        ls.logInfo("Ratings Count : " + this.getRatingsCount());
        ls.logInfo("Image Url : " + this.getImageUrl());
    }
}
