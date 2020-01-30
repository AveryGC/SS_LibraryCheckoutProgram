package com.ss.training;


import com.ss.training.fileInterface.FileToMap;
import com.ss.training.resourceClasses.Author;
import com.ss.training.resourceClasses.Book;
import com.ss.training.resourceClasses.Publisher;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String args[]){
        //Declare vectors for Authors, Books, and Publisher
        Map<Integer, Author> authorMap = new HashMap<Integer, Author>();
        Map<Integer, Book>  bookMap = new HashMap<Integer,Book>();
        Map<Integer, Publisher> publisherMap = new HashMap<Integer,Publisher>();

        FileToMap.readAuthor(authorMap);
        FileToMap.readPublisher(publisherMap);
        FileToMap.readBook(bookMap);


        if(authorMap.isEmpty()||bookMap.isEmpty()||publisherMap.isEmpty()){
            System.out.println("Please make sure that files are located in proper positions.");
            System.exit(0);
        }


        Book.printMapToConsole(bookMap,authorMap,publisherMap);
        Author.printMapToConsole(authorMap);
        Publisher.printMapToConsole(publisherMap);



    }
}
