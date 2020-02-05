package com.ss.training.MapInterfaces;

import com.ss.training.resourceClasses.Author;
import com.ss.training.resourceClasses.Book;
import com.ss.training.resourceClasses.Publisher;

import java.util.Map;

public class BookMapInterface {
    //NECESARRY MAP FUNCTIONALITY

    /**
     * Adds a new book to the map
     * Creates a new instance of a book with the inputed name, author ID, publisherID with the BookID equal the BOOK COUNTER
     * puts instatnce of book in map at KEY = BOOKID
     * moves counter up one for next time a book is added
     * @param bookMap- map of all books
     * @param bookName- name of new book
     * @param authorID- existing author ID for new book
     * @param publisherID- existing publisher ID for new book
     * @return -it returns the BookId for the added book
     */
    public static int addToMap(Map<Integer, Book> bookMap, String bookName, int authorID, int publisherID ) {
        Book b = new Book(Book.counter,bookName,authorID,publisherID);
        bookMap.put(b.getBookID(),b);
        Book.counter++;
        return b.getBookID();
    }

    /**
     * Deletes book with particular ID from the bookMap/system
     * @param bookMap-where all instance of Books are stored
     * @param bookID- the id of book to be deleted
     */
    static public void deleteFromMap(Map<Integer, Book> bookMap, int bookID) {
        bookMap.remove(bookID);
    }

    /**
     * @param book -map of allBooks stored in system
     * @param author - map of all author stored in system
     * @param publisher - map of all publishers stroed in system
     */
    static public void printMapToConsole(Map<Integer, Book> book, Map<Integer, Author> author, Map<Integer, Publisher> publisher) {
        System.out.println("Full List of Books \n++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        book.forEach((k,v)-> v.printToConsole(author, publisher));
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");


    }
}
