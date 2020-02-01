package com.ss.training.menuOptions;

import com.ss.training.resourceClasses.Author;
import com.ss.training.resourceClasses.Book;
import com.ss.training.resourceClasses.Publisher;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class BookMenu {

    public static void mainBookMenu(Scanner scanner, Map<Integer,Book> bookMap,Map<Integer,Author> authorMap, Map<Integer,Publisher> publisherMap){
        String line;
        int input = 40;
        while (input != 999){
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("+                                              Book Menu                                                 +");
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("Enter 1 to add a book, 2 to delete a book, 3 to update a book, 4 to read all books, or 999 to exit");
            line = scanner.nextLine();
            try {
                input = Integer.parseInt(line);
                switch (input) {
                    case 1:
                        addBooks(scanner,bookMap,authorMap,publisherMap);
                        break;
                    case 2 :
                        deleteBooks(scanner,bookMap,authorMap,publisherMap);
                        break;
                    case 3:
                        updateBooks(scanner,bookMap,authorMap,publisherMap);
                        break;
                    case 4:
                        readAllBooks(bookMap,authorMap,publisherMap);
                        break;
                    case 999:
                        System.out.println("Exiting to main.");
                        break;
                    default:
                        System.out.println("!!!Please enter proper input option!!!");
                        break;
                }
            }
            catch (NumberFormatException e) {System.out.println("!!!!!!!!!Improper Input Format!!!!!!!!!!!!!");}
        }
    }
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
            if(initialAuthorCounter==(Author.counter - 1)){
                authorMap.remove(initialAuthorCounter);
                System.out.println("No book, author, or publisher has been added. Operation canceled");
            }
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
        String line = scanner.nextLine();
        try {
            int input = Integer.parseInt(line);
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
                    deleteBooks(scanner,bookMap,authorMap,publisherMap);
                }
            }
        }
        catch (NumberFormatException e){
            System.out.println("Improper Input Format");
            deleteBooks(scanner,bookMap,authorMap,publisherMap);
        }

    }
    public static void updateBooks(Scanner scanner, Map<Integer,Book> bookMap, Map<Integer,Author> authorMap, Map<Integer,Publisher> publisherMap){
        if(bookMap.isEmpty()){
            System.out.println("!!!There are currently NO BOOKS saved in the system. No book can be updated!!!");
            return;
        }
        Book.printMapToConsole(bookMap,authorMap,publisherMap);
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("Enter the BookID of the book you would like to update or type the number \"999\" to CANCEL");
        String line = scanner.nextLine();
        try {
            int input = Integer.parseInt(line);
            if(input == 999)
                return;
            else{
                if(bookMap.containsKey(input)){
                    updateBooksSubMenu(scanner,input,bookMap,authorMap,publisherMap);
                }
                else {
                    System.out.println("!!!!!NO SUCH BOOK ID EXIST IN RECORDS!!!!");
                    updateBooks(scanner,bookMap,authorMap,publisherMap);
                }
            }
        }
        catch (NumberFormatException e){
            System.out.println("Improper Input Format");
            updateBooks(scanner,bookMap,authorMap,publisherMap);
        }
    }

    public static void updateBooksSubMenu(Scanner scanner,int updateBookID, Map<Integer,Book> bookMap, Map<Integer,Author> authorMap, Map<Integer,Publisher> publisherMap){
        System.out.println("Enter 1 to change the Author, 2 to Book change title, 3 to change the publisher title, or type the number \"999\" to CANCEL");
        String line = scanner.nextLine();
        try{
            int input = Integer.parseInt(line);
            if(input ==999)
                return;
            else if(input==1){
                int updatedAuthorID = getAuthorID(scanner,authorMap);
                if(updatedAuthorID==999)
                    return;
                else{
                    bookMap.get(updateBookID).setAuthorID(updatedAuthorID);
                    System.out.println("Book has been updated.");
                    bookMap.get(updateBookID).printToConsole(authorMap,publisherMap);
                }
            }
            else if (input == 2){
                System.out.println("Input the updated name of the book.");
                line = scanner.nextLine();
                bookMap.get(updateBookID).setBookName(line);
                System.out.println("Book has been updated.");
                bookMap.get(updateBookID).printToConsole(authorMap,publisherMap);
            }
            else if (input == 3){
                int updatedPublisherID = getPublisherID(scanner,publisherMap);
                if(updatedPublisherID == 999)
                    return;
                else{
                    bookMap.get(updateBookID).setPublisherID(updatedPublisherID);
                    System.out.println("Book has been updated.");
                    bookMap.get(updateBookID).printToConsole(authorMap,publisherMap);
                }
            }
            else{
                System.out.println("Press enter a valid input option");
                updateBooksSubMenu(scanner,updateBookID,bookMap,authorMap,publisherMap);
            }
        }
        catch(NumberFormatException e){
            System.out.println("Improper Input Format");
            updateBooksSubMenu(scanner, updateBookID, bookMap,authorMap,publisherMap);
        }
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
