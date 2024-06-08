package org.kdu;

import java.util.*;

public class Library {
    private static final LoggingSystem ls = new LoggingSystem();
    private List<Book> books = new ArrayList<>();
    private List<Author> authors = new ArrayList<>();
    private List<Patron> patrons = new ArrayList<>(); // Patron is a borrower
    private List<Book> checkedOutBooks = new ArrayList<>(); // each Book can have multiple copies

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public List<Patron> getPatrons() {
        return patrons;
    }

    public void setPatrons(List<Patron> patrons) {
        this.patrons = patrons;
    }

    public List<Book> getCheckedOutBooks() {
        return checkedOutBooks;
    }

    public void setCheckedOutBooks(List<Book> checkedOutBooks) {
        this.checkedOutBooks = checkedOutBooks;
    }

    public Library(List<Book> books, List<Author> authors, List<Patron> patrons, List<Book> checkedOutBooks) {
        this.books = books;
        this.authors = authors;
        this.patrons = new ArrayList<>(patrons);
        this.checkedOutBooks = new ArrayList<>(checkedOutBooks);
    }

    @Override
    public String toString() {
        return "Library{" +
                "books=" + books +
                ", authors=" + authors +
                ", patrons=" + patrons +
                ", checkedOutBooks=" + checkedOutBooks +
                '}';
    }

    public void findAllAvailableBooks(){
        books.stream().filter(book -> !book.isCheckedOut()).forEach(book -> ls.logInfo(book.toString()));
    }
    public boolean checkOutBook(Book book, Patron patron){
        if(book.isCheckedOut()){
            ls.logInfo(book.getTitle() + "  is already checked out.");
            return false;
        }
        if(patron.getCheckoutLimit() == patron.getCheckedOutBooks().size())
        {
            ls.logInfo("Checkout Limit Reached");
            return false;
        }
        book.setCheckedOut(true);
        book.setHowmanyTimesCheckedOut(book.getHowmanyTimesCheckedOut() + 1);
        checkedOutBooks.add(book);
        if(!patrons.contains(patron)){
            patrons.add(patron);
        }
        patron.getCheckedOutBooks().add(book);
        ls.logInfo(book.getTitle() + " checked out successfully!");
        return true;
    }

    public List<Book> findBooksByAuthor(Author author){
        return author.getBooks();
    }

    public List<Book> findOverdueBooks(){
        return books.stream().filter(Book::isDue).toList();
    }

    public List<Book> findPopularBooks(int topN){
            return books.stream().sorted((book, t1) -> t1.compareTo(book)).limit(topN).toList();
    }

    public Map<String, List<Book>> groupBooksByGenre(){
        Map<String, List<Book>> genreWiseBooks = new HashMap<>();
        books.stream().forEach(book -> {
            if(genreWiseBooks.get(book.getGenre()) == null){
                List<Book> bookList = new ArrayList<>();
                bookList.add(book);
                genreWiseBooks.put(book.getGenre(),bookList);
            }else{
                genreWiseBooks.get(book.getGenre()).add(book);
            }
        });
        return genreWiseBooks;
    }

}
