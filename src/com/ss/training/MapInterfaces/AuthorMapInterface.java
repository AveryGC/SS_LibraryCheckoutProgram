package com.ss.training.MapInterfaces;

import com.ss.training.resourceClasses.Author;
import com.ss.training.resourceClasses.Book;

import java.util.Map;
import java.util.Vector;

public class AuthorMapInterface {
    //Necessary map functionality
    /**
     * @param mapAuthor -map where all authors are saved
     * @param authorName - the name of author to be added to map
     * @return - returns the new authorID of the added quthor
     */
    public int addToMap(Map<Integer, Author> mapAuthor, String authorName) {
        Author a = new Author(Author.counter, authorName);
        mapAuthor.put(a.getKeyID(), a);
        Author.counter++;
        return a.getKeyID();
    }

    /**
     *Prints all authors stored in system to map
     * @param mapAuthor - map of all authors
     */
    public void printMapToConsole(Map<Integer, Author> mapAuthor) {
        System.out.println("Full List of Authors \n+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        mapAuthor.forEach((k,v)-> v.printToConsole());
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
    }
    /**
     * deletes particular author from author map
     * then checks and deletes any book with the same AuthorID as the deleted author
     * @param authorMap- map of all authors
     * @param bookMap - map of all books
     * @param authorID - author ID to be deleted from authorMap
     */
    public void deleteFromMap(Map<Integer, Author> authorMap, Map<Integer, Book> bookMap, int authorID){
//        bookMap.forEach((k,v)->
//            {if(v.getAuthorID()==authorID){
//                Book.deleteFromMap(bookMap,v.getBookID());}}
//                );
        Vector<Integer> toBeDeletedBooks = new Vector<>();
        BookMapInterface bookmapinterface = new BookMapInterface();
        bookMap.forEach((k,v)->{if(v.getAuthorID()==authorID){toBeDeletedBooks.add(v.getBookID());}});
        toBeDeletedBooks.forEach(e->bookmapinterface.deleteFromMap(bookMap,e));
//        for(int i :toBeDeletedBooks)
//            Book.deleteFromMap(bookMap,i);
        authorMap.remove(authorID);
    }


}
