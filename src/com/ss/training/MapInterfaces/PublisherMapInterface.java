package com.ss.training.MapInterfaces;

import com.ss.training.resourceClasses.Book;
import com.ss.training.resourceClasses.Publisher;

import java.util.Map;
import java.util.Vector;

public class PublisherMapInterface {
    //Necessary map functionality

    /**
     * Adds a new instance of a publisher to a map
     *
     * @param pubMap-       hashmap of all publishers
     * @param publisherName - name of publisher to be added
     * @return - returns the keyID of publisher added
     */
    public int addToMap(Map<Integer, Publisher> pubMap, String publisherName, String publisherAddress) {
        Publisher p = new Publisher(Publisher.counter, publisherName, publisherAddress);
        pubMap.put(p.getKeyID(), p);
        Publisher.counter++;
        return p.getKeyID();
    }

    /**
     * deletes particular publisher from publisher map
     * then checks and deletes any book with the same publisherID as the deleted publisher
     *
     * @param publisherMap-  map of all authors
     * @param bookMap     - map of all books
     * @param publisherID - ID of publisher to be deleted
     */
    public void deleteFromMap(Map<Integer, Publisher> publisherMap, Map<Integer, Book> bookMap, int publisherID) {
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
        BookMapInterface bookmapinterface = new BookMapInterface();
        toBeDeletedBooks.forEach(e->bookmapinterface.deleteFromMap(bookMap,e));
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
    public void printMapToConsole(Map<Integer, Publisher> pubMap) {
        System.out.println("Full List of Publishers \n+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        pubMap.forEach((k, v) -> v.printToConsole());
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

    }
}
