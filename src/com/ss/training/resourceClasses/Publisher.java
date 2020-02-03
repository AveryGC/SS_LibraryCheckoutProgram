package com.ss.training.resourceClasses;

import java.util.Map;
import java.util.Vector;

//Main Class file for publisher
public class Publisher {
    //Publisher variables
    private int keyID;
    private String publisherName;
    private String publisherAddress;

    //counter is used to keep track of the highest keyID for any instance of Publisher
    //  for use of assigning new keyID to new instances of Publisher
    public static int counter = 0;

    /**
     * @param name - name of publisher
     *             Takes a name of a publisher and creates and instance of it.
     *             Gives it the id number from the counter
     *             Updates the new counter
     */
    public Publisher(String name) {
        this.publisherName = name;
        this.keyID = Publisher.counter;
        Publisher.counter++;
    }

    public Publisher(int keyID, String publisherName, String publisherAddress) {
        this.keyID = keyID;
        this.publisherName = publisherName;
        this.publisherAddress = publisherAddress;
    }

    //Set up get and set for variables
    public int getKeyID() {
        return keyID;
    }

    public void setKeyID(int keyID) {
        this.keyID = keyID;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public String getPublisherAddress() { return publisherAddress; }

    public void setPublisherAddress(String publisherAddress) {this.publisherAddress = publisherAddress; }

    //prints to console
    public void printToConsole() {
        System.out.println("ID: " + this.keyID + " Name : " + this.publisherName + "_______Address: " + this.publisherAddress);
    }


    //Necessary map functionality

    /**
     * Adds a new instance of a publisher to a map
     *
     * @param pubMap-       hashmap of all publishers
     * @param publisherName - name of publisher to be added
     * @return - returns the keyID of publisher added
     */
    static public int addToMap(Map<Integer, Publisher> pubMap, String publisherName, String publisherAddress) {
        Publisher p = new Publisher(Publisher.counter, publisherName, publisherAddress);
        pubMap.put(p.keyID, p);
        Publisher.counter++;
        return p.keyID;
    }

    /**
     * deletes particular publisher from publisher map
     * then checks and deletes any book with the same publisherID as the deleted publisher
     *
     * @param publisherMap-  map of all authors
     * @param bookMap     - map of all books
     * @param publisherID - ID of publisher to be deleted
     */
    static public void deleteFromMap(Map<Integer, Publisher> publisherMap, Map<Integer, Book> bookMap, int publisherID) {
        Vector<Integer> toBeDeletedBooks = new Vector<>();
        bookMap.forEach((k, v) -> {
            if (v.getPublisherID() == publisherID) {
                toBeDeletedBooks.add(v.getBookID());
            }
        });
//        bookMap.forEach((k,v)->
//                {if(v.getPublisherID()==publisherID)
//                    Book.deleteFromMap(bookMap,v.getBookID());}
//        );
        toBeDeletedBooks.forEach(e->Book.deleteFromMap(bookMap,e));
//        for (int i : toBeDeletedBooks)
//            Book.deleteFromMap(bookMap, i);
//        authorMap.remove(publisherID);
        publisherMap.remove(publisherID);
    }

    /**
     * Prints all publishers in map to console
     *
     * @param pubMap- map of publishers
     */
    static public void printMapToConsole(Map<Integer, Publisher> pubMap) {
        System.out.println("Full List of Publishers \n+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        pubMap.forEach((k, v) -> v.printToConsole());
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

    }
}
