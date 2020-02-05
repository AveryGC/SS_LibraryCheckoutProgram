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



}
