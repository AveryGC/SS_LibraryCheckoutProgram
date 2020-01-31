package com.ss.training;


import com.ss.training.fileInterface.FileToMap;
import com.ss.training.resourceClasses.Author;
import com.ss.training.resourceClasses.Book;
import com.ss.training.resourceClasses.Publisher;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String args[]){
        //Declare vectors for Authors, Books, and Publisher
        Map<Integer, Author> authorMap = new HashMap<Integer, Author>();
        Map<Integer, Book>  bookMap = new HashMap<Integer,Book>();
        Map<Integer, Publisher> publisherMap = new HashMap<Integer,Publisher>();

        //Read files and setup initial HashMaps for authors, books and publishers
        boolean authorFileSuccess = FileToMap.readAuthor(authorMap);
        boolean publisherFileSuccess= FileToMap.readPublisher(publisherMap);
        boolean bookFileSuccess = FileToMap.readBook(bookMap);

        //Check
        if(authorFileSuccess && publisherFileSuccess && bookFileSuccess){
            System.out.println("File Reading Was a SUCCESS!!!!");
        }
        else{
            System.out.println("Please make sure that files are located in proper positions.");
            System.exit(0);
        }

        Scanner scanner = new Scanner(System.in);

//        Book.printMapToConsole(bookMap,authorMap,publisherMap);
//        Author.printMapToConsole(authorMap);
//        Publisher.printMapToConsole(publisherMap);


    }
}
