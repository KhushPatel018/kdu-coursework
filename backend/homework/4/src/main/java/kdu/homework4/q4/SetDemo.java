package kdu.homework4.q4;
import kdu.homework4.logging.LoggingSystem;

import java.util.*;

public class SetDemo {

    private static final LoggingSystem ls = new LoggingSystem();

    public static Set<Book> treeSetDemo(Comparator<Book> comparator) {
        Book book1 = new Book("Harry Potter", "J.K.Rowling", 1997);
        Book book2 = new Book("Harry Potter", "J.K.Rowling", 1997);
        Book book3 = new Book("Walden", "Henry David Thoreau", 1854);
        Book book4 = new Book("Effective Java", "Joshua Bloch", 2008);
        Book book5 = new Book("The Last Lecture", "Randy Pausch", 2008);

        Set<Book> books = new TreeSet<>(comparator);
        books.add(book1);
        books.add(book2);
        books.add(book3);
        books.add(book4);
        books.add(book5);

        for (Book book : books) {
            ls.logInfo(book.toString());
        }

        return books;
    }

    public static void main(String[] args) {
        ls.logInfo("default ordering : ");
        treeSetDemo(null);
        ls.logInfo("ascending : ");
    treeSetDemo(new PubDateAscComparator());
        ls.logInfo("descending : ");
    treeSetDemo(new PubDateDescComparator());
    }

}