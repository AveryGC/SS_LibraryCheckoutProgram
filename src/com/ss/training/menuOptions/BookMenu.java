package com.ss.training.menuOptions;

import com.ss.training.resourceClasses.Author;
import com.ss.training.resourceClasses.Book;
import com.ss.training.resourceClasses.Publisher;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class BookMenu {
    /**
     * adds a book to the system.
     * Gives choice off adding new author/publisher or assigning existing one
     * @param scanner- Scanner with if stream set to system.io
     * @param bookMap- hashmap of all books
     * @param authorMap - hashmap of all authors
     * @param publisherMap - hashmap of all publishers
     * @return - returns the BookID of newly added book or returns 999 if no book added
     */
    public static int addBooks(Scanner scanner, Map<Integer,Book> bookMap,Map<Integer,Author> authorMap, Map<Integer,Publisher> publisherMap){
        System.out.println("Enter the Name of the New Book or type the number \"999\" to RETURN to last page.");
        String name = scanner.nextLine();
        try{
            int input = Integer.parseInt(name);
            if(input==999)
                return 999;
        }
        catch (NumberFormatException e){}
        int initialAuthorCounter = Author.counter;
        int newAuthorID = getAuthorID(scanner,authorMap);
        if(newAuthorID==999)
            return 999;
        int newPublisherID = getPublisherID(scanner, publisherMap);
        if(newPublisherID==999){
            if(initialAuthorCounter==(Author.counter - 1))
                authorMap.remove(initialAuthorCounter);
            return 999;
        }
        int newBookNum = Book.addToMap(bookMap, name,newAuthorID,newPublisherID);
        System.out.println("The following book has been added:");
        bookMap.get(newBookNum).printToConsole(authorMap,publisherMap);
        return newBookNum;
    }

    /**Takes input from the user and trys to delete from the Books HashMap
     *
     * @param scanner - Scanner with the input stream set to System.io
     * @param bookMap - hashmap of all books
     * @param authorMap - hashmap of all authors
     * @param publisherMap - hashmap of all publishers
     */
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

    /**
     * Reads all books if books exist
     * @param bookMap- hashmap of all books
     * @param authorMap - hash map of all
     * @param publisherMap - hash map of all publishers
     */
    public static void readAllBooks(Map<Integer, Book> bookMap, Map<Integer, Author> authorMap, Map<Integer, Publisher> publisherMap){
        if(bookMap.isEmpty())
            System.out.println("There are currently NO BOOKS saved in the system.");
        else
            Book.printMapToConsole(bookMap,authorMap,publisherMap);
    }

    /**
     * either gets the ID as choosen by user
     * or allows user to create a new ID and returns that
     * @param scanner - Scanner with the if stream of system.io
     * @param authorMap - hashMap of all authors
     * @return the authorID of selected author or of created author
     */
    public static int getAuthorID(Scanner scanner, Map<Integer, Author> authorMap){
        System.out.println("Type 1 to assign new author to the book, press 2 too assign existing author or or type the number \"999\" to CANCEL.");
        String line =scanner.nextLine();
        try{
            int input = Integer.parseInt(line);
            if(input == 999)
                return 999;
            else if(input == 1)
                return AuthorMenu.addAuthor(scanner, authorMap);
            else if(input == 2 && authorMap.isEmpty()){
                System.out.println("Author Map is Empty.");
                return AuthorMenu.addAuthor(scanner,authorMap);
            }
            else if(input== 2 && !authorMap.isEmpty()){
               return AuthorMenu.selectAnAuthor(scanner,authorMap);
            }
            else{
                System.out.println("Improper Input, please select 1 or 2.");
                return getAuthorID(scanner,authorMap);
            }
        }
        catch (Exception e){
            System.out.println("!!!!!!!!!!Improper Input Format!!!!!!!!!!");
            return getAuthorID(scanner,authorMap);
        }
    }

    /**
     * either gets the ID as choosen by user
     * or allows user to create a new ID and returns that
     * @param scanner - Scanner with the if stream of system.io
     * @param publisherMap - map of all the publishers
     * @return - returns either the newly created publisher ID or the chosen existing publisher ID
     */
    public static int getPublisherID(Scanner scanner, Map<Integer, Publisher> publisherMap){
        System.out.println("Type 1 to assign new publisher to the book, press 2 too assign existing publisher or or type the number \"999\" to CANCEL.");
        String line =scanner.nextLine();
        try{
            int input = Integer.parseInt(line);
            if(input == 999)
                return 999;
            else if(input == 1)
                return PublisherMenu.addPublisher(scanner, publisherMap);
            else if(input == 2 && publisherMap.isEmpty()){
                System.out.println("Publisher Map is Empty.");
                return PublisherMenu.addPublisher(scanner,publisherMap);
            }
            else if(input== 2 && !publisherMap.isEmpty()){
                return PublisherMenu.selectAPublisher(scanner,publisherMap);
            }
            else{
                System.out.println("Improper Input, please select 1 or 2.");
                return getPublisherID(scanner,publisherMap);
            }
        }
        catch (Exception e){
            System.out.println("!!!!!!!!!!Improper Input Format!!!!!!!!!!");
            return getPublisherID(scanner,publisherMap);
        }
    }
}
