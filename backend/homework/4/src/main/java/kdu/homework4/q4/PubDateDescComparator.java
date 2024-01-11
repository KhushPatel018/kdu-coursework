package kdu.homework4.q4;

import java.util.Comparator;

public class PubDateDescComparator implements Comparator<Book> {

    /**
     *  compares books to set them in descending order
     *  comparison takes place in this priority order
     *  Year --> title
     * @param book
     * @param t1
     * @return
     */
    @Override
    public int compare(Book book, Book t1) {
        if(book.getYear() == t1.getYear())
        {
            return book.getTitle().compareTo(t1.getTitle());
        }
        return t1.getYear() - book.getYear();
    }
}
