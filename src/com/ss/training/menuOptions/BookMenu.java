package com.ss.training.menuOptions;

import com.ss.training.resourceClasses.Author;
import com.ss.training.resourceClasses.Book;
import com.ss.training.resourceClasses.Publisher;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class BookMenu {
    public static int addBooks(){
        return 0;

    }

    //Takes input from the user and trys to delete from the Books HashMap
    public static void deleteBooks(Scanner scanner, Map<Integer,Book> bookMap, Map<Integer,Author> authorMap, Map<Integer,Publisher> publisherMap){
        Book.printMapToConsole(bookMap,authorMap,publisherMap);
        System.out.println("Enter the Book ID of the book you would like to delete or type the number \"999\" to RETURN to last page.");
        try {
            int input = scanner.nextInt();
            if(input == 999)
                return;
            else{
                if(bookMap.containsKey(input)){
                    Book deletedBook = new Book(bookMap.get(input).getBookID(),bookMap.get(input).getBookName(),bookMap.get(input).getAuthorID(),bookMap.get(input).getPublisherID());
                    Book.deleteFromMap(bookMap,input);
                    System.out.print("Succefully deleted ");
                    deletedBook.printToConsole(authorMap,publisherMap);
                    System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                }
                else {
                    System.out.println("!!!!!NO SUCH BOOK ID EXIST IN RECORDS!!!!");
                }
            }
        }
        catch (InputMismatchException e){
            System.out.println("Improper Input Format");
        }

    }
    public static void updateBooks(){

    }
    public static void readAllBooks(Map<Integer, Book> bookMap, Map<Integer, Author> authorMap, Map<Integer, Publisher> publisherMap){
        if(bookMap.isEmpty())
            System.out.println("There are currently NO BOOKS saved in the system.");
        else
            Book.printMapToConsole(bookMap,authorMap,publisherMap);
    }
}
