package com.ss.training;

import com.ss.training.fileInterface.FileToMap;
import com.ss.training.menuOptions.AuthorMenu;
import com.ss.training.menuOptions.BookMenu;
import com.ss.training.menuOptions.PublisherMenu;
import com.ss.training.resourceClasses.Author;
import com.ss.training.resourceClasses.Book;
import com.ss.training.resourceClasses.Publisher;

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

        //Check to make sure file upload was succeful before running rest of program
        if(authorFileSuccess && publisherFileSuccess && bookFileSuccess){
            System.out.println("File Reading Was a SUCCESS!!!!");
        }
        else{
            System.out.println("Please make sure that files are located in proper positions.");
            System.exit(0);
        }
        //Program header
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("+                                 SmoothStack File Management System                                     +");
        System.out.println("+                                       Coded by: Avery Corbett                                          +");

        int input = 40;
        String line;
        Scanner scanner = new Scanner(System.in);
        while (input != 999){
            System.out.println("+                                              Main Menu                                                 +");
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("Enter 1 to travel to the Author menu, 2 for the Book Menu, or 3 for the Publisher Menu.\nOr Enter \"999\" to exit program.");
            line = scanner.nextLine();
            try{
                input = Integer.parseInt(line);
                switch (input){
                    case 1:
                        AuthorMenu.mainAuthorMenu(scanner,bookMap,authorMap);
                        break;
                    case 2:
                        BookMenu.mainBookMenu(scanner,bookMap,authorMap,publisherMap);
                        break;
                    case 3:
                        PublisherMenu.mainPublisherMenu(scanner,bookMap,publisherMap);
                        break;
                    case 999:
                        System.out.println("Program is being terminated");
                        break;
                    default:
                        System.out.println("!!!Please enter proper input option!!!");
                }
            }
            catch(NumberFormatException e){ System.out.println("!!!!!!!!!Improper Input Format!!!!!!!!!!!!!");}
        }




    }
}
