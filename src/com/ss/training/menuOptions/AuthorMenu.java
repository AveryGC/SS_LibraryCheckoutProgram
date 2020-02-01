package com.ss.training.menuOptions;

import com.ss.training.resourceClasses.Author;
import com.ss.training.resourceClasses.Book;

import java.util.Map;
import java.util.Scanner;


public class AuthorMenu {
    /**
     * displays the main author menu
     * @param scanner - set the in stream to system.io
     * @param bookMap - Map of all books
     * @param authorMap - map of all authors
     */
    public static void  mainAuthorMenu(Scanner scanner, Map<Integer,Book> bookMap, Map<Integer,Author> authorMap) {
        String line;
        int input = 40;
        while (input != 999){
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("+                                             Author Menu                                                +");
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("Enter 1 to add an author, 2 to delete an author, 3 to update an author, 4 to read all authors, or 999 to exit");
            line = scanner.nextLine();
            try {
                input = Integer.parseInt(line);
                switch (input) {
                    case 1:
                        addAuthor(scanner, authorMap);
                        break;
                    case 2:
                        deleteAuthor(scanner, bookMap, authorMap);
                        break;
                    case 3:
                        updateAuthor(scanner, authorMap);
                        break;
                    case 4:
                        readAllAuthors(authorMap);
                        break;
                    case 999:
                        System.out.println("Exiting to main.");
                        break;
                    default:
                        System.out.println("!!!Please enter proper input option!!!");
                        break;

                }
            } catch (NumberFormatException e) {System.out.println("!!!!!!!!!Improper Input Format!!!!!!!!!!!!!");}
        }
    }

    /**
     * Sent here to add author into author menu
     *
     * @param scanner    Scanner with the input stream set as System.in
     * @param authorMap- map of all authors
     * @return
     */
    public static int addAuthor(Scanner scanner, Map<Integer, Author> authorMap) {
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("Enter Name of Author you would like to add, or enter the number \"999\" to CANCEL.");
        String input = scanner.nextLine();
        try {
            int check = Integer.parseInt(input);
            if (check == 999)
                return 999;
            else
                throw new Exception();
        } catch (Exception e) {
            int newAuthor = Author.addToMap(authorMap, input);
            System.out.println("The following author has been added:");
            authorMap.get(newAuthor).printToConsole();
            return newAuthor;
        }
    }

    /**
     * After selecting delete from the main author menu you will be sent here
     *
     * @param scanner    - scanner is  setup using system.in is the io stream
     * @param bookMap    - map of all books
     * @param authorMap- map of all authors
     */
    public static void deleteAuthor(Scanner scanner, Map<Integer, Book> bookMap, Map<Integer, Author> authorMap) {
        Author.printMapToConsole(authorMap);
        System.out.println("Enter the Author ID of the author you would like to delete or type the number \"999\" to CANCEL.");
        String line = scanner.nextLine();
        try {
            int input = Integer.parseInt(line);
            if (input == 999)
                return;
            else {
                if (authorMap.containsKey(input)) {
                    Author deletedAuthor = new Author(authorMap.get(input).getKeyID(), authorMap.get(input).getFullName());
                    Author.deleteFromMap(authorMap, bookMap, input);
                    System.out.print("Succefully Deleted ");
                    deletedAuthor.printToConsole();
                    System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                } else {
                    System.out.println("!!!!!NO SUCH AUTHOR ID EXIST IN RECORDS!!!!");
                    deleteAuthor(scanner, bookMap, authorMap);
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("!!!!!!!!!!!!!!!!!Improper Input Format!!!!!!!!!!!!!!!!!!");
            deleteAuthor(scanner, bookMap, authorMap);
        }

    }

    /**
     * function to update author
     *
     * @param scanner    - Scanner with system.in as the input stream
     * @param authorMap- map of all authors in system
     */
    public static void updateAuthor(Scanner scanner, Map<Integer, Author> authorMap) {
        Author.printMapToConsole(authorMap);
        System.out.println("Enter the Author ID of the author you would like to update or type the number \"999\" to CANCEL.");
        String line = scanner.nextLine();
        try {
            int input = Integer.parseInt(line);
            if (input == 999)
                System.out.println("Author Update Canceled");
            else {
                if (authorMap.containsKey(input)) {
                    System.out.println("Enter the new name to replace the authors existing name.");
                    String newName = scanner.nextLine();
                    Author newAuthor = new Author(input, newName);
                    authorMap.replace(input, newAuthor);
                    System.out.println("Author successfully updated.");
                    newAuthor.printToConsole();
                } else {
                    System.out.println("Author ID doesn't exist");
                    updateAuthor(scanner, authorMap);
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("!!!!!!!!!!!!!!!!Improper Input Format!!!!!!!!!!!!!!!");
            updateAuthor(scanner, authorMap);
        }
    }

    /**
     * prints all authors to console
     *
     * @param authorMap - hashmap of all authors
     */
    public static void readAllAuthors(Map<Integer, Author> authorMap) {
        if (authorMap.isEmpty())
            System.out.println("There are currently NO AUTHORS stored in the system.");
        else
            Author.printMapToConsole(authorMap);
    }

    /**
     * user either selects an existing author or adds a new one
     * @param scanner - scanner with if stream set as system.io
     * @param authorMap - map of all authors
     * @return - the selected id of author or the new id created by author
     */
    public static int selectAnAuthor(Scanner scanner, Map<Integer, Author> authorMap) {
        Author.printMapToConsole(authorMap);
        System.out.println("Input the ID of the Author you would like to select or type the number \"999\" to CANCEL.");
        String line = scanner.nextLine();
        try {
            int input = Integer.parseInt(line);
            if (input == 999)
                return 999;
            else if (authorMap.containsKey(input))
                return input;
            else {
                System.out.println("!!!!!!!Selected Author does not exist!!!!!!!!");
                return selectAnAuthor(scanner, authorMap);
            }
        } catch (Exception e) {
            System.out.println("!!!!!!!Improper input format!!!!!!!");
            return selectAnAuthor(scanner, authorMap);
        }
    }
}