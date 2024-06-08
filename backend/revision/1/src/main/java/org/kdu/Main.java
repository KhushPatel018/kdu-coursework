package org.kdu;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Main {
    private static final LoggingSystem ls = new LoggingSystem();
    public static void main(String[] args) {

        Author author1 = new Author("Author1");
        Book book1 = new Book("Book1", "author1", false,"Genre1");
        Book book2 = new Book("Book2", "author1",false ,"Genre2");

        Author author2 = new Author("Author2");
        Book book3 = new Book("Book3", "author2", false,"Genre1");
        Book book4 = new Book("Book4", "author2", false,"Genre3");

        Patron patron1 = new Patron("Patron1",1);
        Patron patron2 = new Patron("Patron2",1);

        List<Book> books = Arrays.asList(book1, book2, book3, book4);
        List<Author> authors = Arrays.asList(author1, author2);
        List<Patron> patrons = Arrays.asList(patron1, patron2);
        List<Book> checkedOutBooks = Arrays.asList();

        // Create a library instance
        Library library = new Library(books, authors, patrons, checkedOutBooks);

        // Test methods
        ls.logInfo("All Available Books:");
        library.findAllAvailableBooks();

        ls.logInfo("Checking out Book1 and Book2 to Patron1:");
         library.checkOutBook(book1, patron1);
         library.checkOutBook(book2,patron1);
        ls.logInfo("Books checked out by Patron1:");
         patron1.getCheckedOutBooks().forEach(dummyBook -> ls.logInfo(dummyBook.toString()));

        ls.logInfo("All Available Books after checking out Book1:");
        library.findAllAvailableBooks();

        ls.logInfo("Books by Author1:");
        List<Book> booksByAuthor1 = library.findBooksByAuthor(author1);
        booksByAuthor1.forEach(dummyBook -> ls.logInfo(dummyBook.toString()));

        ls.logInfo("Overdue books : ");
        book1.setDue(true);
        book3.setDue(true);
        List<Book> overdueBooks = library.findOverdueBooks();
        overdueBooks.forEach(dummyBook -> ls.logInfo(dummyBook.toString()));

        ls.logInfo("Popular Books (Top 2):");
        List<Book> popularBooks = library.findPopularBooks(2);
        popularBooks.forEach(dummyBook -> ls.logInfo(dummyBook.toString()));

        ls.logInfo("Books Grouped by Genre:");
        Map<String, List<Book>> genreWiseBooks = library.groupBooksByGenre();
        genreWiseBooks.forEach((genre, bookList) -> {
            ls.logInfo(genre + ":");
            bookList.forEach(dummyBook -> ls.logInfo(dummyBook.toString()));
        });

    }
}