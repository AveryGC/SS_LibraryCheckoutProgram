package com.ss.training.resourceClasses;

import java.util.Map;
import java.util.Vector;

public class Author {
    //Variables
    private int keyID;
    private String fullName;
    //counter is used to keep track of the next keyID for any instance of Author
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
    /**
     * @param mapAuthor -map where all authors are saved
     * @param authorName - the name of author to be added to map
     * @return - returns the new authorID of the added quthor
     */
    static public int addToMap(Map<Integer, Author> mapAuthor, String authorName) {
        Author a = new Author(Author.counter, authorName);
        mapAuthor.put(a.keyID, a);
        Author.counter++;
        return a.keyID;
    }

    /**
     * deletes particular author from author map
     * then checks and deletes any book with the same AuthorID as the deleted author
     * @param authorMap- map of all authors
     * @param bookMap - map of all books
     * @param authorID - author ID to be deleted from authorMap
     */
    static public void deleteFromMap(Map<Integer,Author> authorMap, Map<Integer,Book> bookMap, int authorID){
//        bookMap.forEach((k,v)->
//            {if(v.getAuthorID()==authorID){
//                Book.deleteFromMap(bookMap,v.getBookID());}}
//                );
        Vector<Integer> toBeDeletedBooks = new Vector<>();
        bookMap.forEach((k,v)->{if(v.getAuthorID()==authorID){toBeDeletedBooks.add(v.getBookID());}});
        toBeDeletedBooks.forEach(e->Book.deleteFromMap(bookMap,e));
//        for(int i :toBeDeletedBooks)
//            Book.deleteFromMap(bookMap,i);
        authorMap.remove(authorID);
    }

    /**
     *Prints all authors stored in system to map
     * @param mapAuthor - map of all authors
     */
    static public void printMapToConsole(Map<Integer, Author> mapAuthor) {
        System.out.println("Full List of Authors \n+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        mapAuthor.forEach((k,v)-> v.printToConsole());
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
    }
}
