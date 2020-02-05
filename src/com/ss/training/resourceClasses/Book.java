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
        System.out.println("Book ID: " + this.bookID + "______Book Name : " + this.bookName + "______Written By: " + author.get(this.authorID).getFullName() + "_____Published By: " + publisher.get(this.publisherID).getPublisherName());
    }


    //

}
