package org.kdu;

public class Book {
    private String title;
    private String isbn;
    private boolean checkedOut;


    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                ", checkedOut=" + checkedOut +
                ", genre='" + genre + '\'' +
                ", howmanyTimesCheckedOut=" + howmanyTimesCheckedOut +
                '}';
    }

    private String genre;
    private int howmanyTimesCheckedOut;
    private boolean isDue;

    public boolean isDue() {
        return isDue;
    }

    public void setDue(boolean due) {
        isDue = due;
    }

    public int getHowmanyTimesCheckedOut() {
        return howmanyTimesCheckedOut;
    }

    public void setHowmanyTimesCheckedOut(int howmanyTimesCheckedOut) {
        this.howmanyTimesCheckedOut = howmanyTimesCheckedOut;
    }

    public Book(String title, String isbn, boolean checkedOut, String genre) {
        this.title = title;
        this.isbn = isbn;
        this.checkedOut = checkedOut;
        this.genre = genre;
        this.howmanyTimesCheckedOut = 0;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public boolean isCheckedOut() {
        return checkedOut;
    }

    public void setCheckedOut(boolean checkedOut) {
        this.checkedOut = checkedOut;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int compareTo(Book book){
        return getHowmanyTimesCheckedOut() - book.getHowmanyTimesCheckedOut();
    }
    // to string implement ..

}
