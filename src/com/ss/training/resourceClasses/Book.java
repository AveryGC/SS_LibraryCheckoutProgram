package com.ss.training.resourceClasses;

import java.util.Map;

public class Book {
    //Declare Variables
    private int bookID;
    private String bookName;
    private int authorID;
    private int publisherID;

    //The static counter to keep track of the next
    public static int counter= 0;

    /**
     * Constructor for book
     * @param bookID
     * @param bookName
     * @param authorID
     * @param publisherID
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

    public int getPublisherID() {
        return publisherID;
    }

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
    static public int addToMap(Map<Integer,Book> bookMap, String bookName, int authorID, int publisherID ) {
        Book b = new Book(Book.counter,bookName,authorID,publisherID);
        bookMap.put(b.bookID,b);
        Book.counter++;
        return b.bookID;
    }

    static public void deleteFromMap(Map<Integer, Book> bookMap, int bookID) {
        bookMap.remove(bookID);
    }

    static public void printMapToConsole(Map<Integer, Book> book,Map<Integer, Author> author, Map<Integer, Publisher> publisher) {
        System.out.println("Full List of Books \n++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        book.forEach((k,v)-> v.printToConsole(author, publisher));
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");


    }

    static public void printMapToTxtFile() {

    }


}
