package com.ss.training.resourceClasses;

import java.util.Map;

public class Book {
    //Declare Variables
    private int bookID;
    private String bookName;
    private int authorID;
    private int publisherID;

    //The static counter to keep track of the next available bookID
    public static int counter= 0;

    /**
     * Constructor for book
     * @param bookID- ID of bookk
     * @param bookName - Nanme of book
     * @param authorID - ID of author that already exist in system
     * @param publisherID - ID of publisher that already exist in system/
     */
    public Book(int bookID, String bookName, int authorID, int publisherID) {
        this.bookID = bookID;
        this.bookName = bookName;
        this.authorID = authorID;
        this.publisherID = publisherID;
    }

    //Setup Setter and getters
    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public int getAuthorID() {
        return authorID;
    }

    public void setAuthorID(int authorID) {
        this.authorID = authorID;
    }

    public int getPublisherID() { return publisherID; }

    public void setPublisherID(int publisherID) {
        this.publisherID = publisherID;
    }

    /**
     * Prints a singles instance of
     * @param author is the hashmap containing all of the authors
     * @param publisher is the hashmap containing all of the publishers
     */
    public void printToConsole(Map<Integer, Author> author, Map<Integer, Publisher> publisher){
        System.out.println("Book ID: " + this.bookID + "  Book Name : " + this.bookName + " Written By: " + author.get(this.authorID).getFullName() + " Published By: " + publisher.get(this.publisherID).getPublisherName());
    }


    //
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
    static public int addToMap(Map<Integer,Book> bookMap, String bookName, int authorID, int publisherID ) {
        Book b = new Book(Book.counter,bookName,authorID,publisherID);
        bookMap.put(b.bookID,b);
        Book.counter++;
        return b.bookID;
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
    static public void printMapToConsole(Map<Integer, Book> book,Map<Integer, Author> author, Map<Integer, Publisher> publisher) {
        System.out.println("Full List of Books \n++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        book.forEach((k,v)-> v.printToConsole(author, publisher));
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");


    }

    static public void printMapToTxtFile() {

    }


}
