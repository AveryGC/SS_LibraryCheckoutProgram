package com.ss.training.resourceClasses;

import java.util.Map;

public class Author {
    //Variables
    private int keyID;
    private String fullName;
    //counter is used to keep track of the highest keyID for any instance of Author
    //  for use of assigning new keyID to new instances of Author
    public static int counter= 0;

    /**
     *
     * @param fullName- full name of the author
     * Creates an instance of Author with a key ID equal to the counter then updates
     *                the new live count.
     */
    public Author(String fullName) {
        this.fullName = fullName;
        this.keyID= Author.counter;
        Author.counter++;
    }

    /**
     * Constructor for Author that takes a keyID and fullName
     * @param keyID
     * @param fullName
     */
    public Author(int keyID, String fullName) {
        this.keyID = keyID;
        this.fullName = fullName;
    }

    //Getter and Setters
    public int getKeyID() {
        return keyID;
    }

    public void setKeyID(int keyID) {
        this.keyID = keyID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    //Prints to console
    public void printToConsole(){
        System.out.println("ID: " + this.keyID + " Name : " + this.fullName);
    }

    //Necessary map functionality
    static public int addToMap(Map<Integer, Author> mapAuthor, String authorName) {
        Author a = new Author(Author.counter, authorName);
        mapAuthor.put(Author.counter, a);
        Author.counter++;
        return Author.counter;
    }

    static public void deleteFromMap() {

    }

    static public void printMapToConsole(Map<Integer, Author> mapAuthor) {
        System.out.println("Full List of Authors \n+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        mapAuthor.forEach((k,v)-> v.printToConsole());
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
    }

    static public void printMapToTxtFile() {

    }
}
