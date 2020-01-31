package com.ss.training.menuOptions;

import com.ss.training.resourceClasses.Author;
import com.ss.training.resourceClasses.Book;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class AuthorMenu {
    public static int addAuthor(Scanner scanner,Map<Integer, Author> authorMap){
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("Enter Name of Author you would like to add, or enter the number \"999\" to RETURN to last page");
        String input = scanner.nextLine();
        try{
            int check = Integer.parseInt(input);
            if(check==999)
                return 999;
            else
                throw new Exception();
        }
        catch(Exception e)
        {
           return newID = Author.addToMap(authorMap,input);

        }
    }
    public static void deleteAuthor(Scanner scanner, Map<Integer, Book> bookMap, Map<Integer,Author> authorMap){
        Author.printMapToConsole(authorMap);
        System.out.println("Enter the Author ID of the book you would like to delete or type the number \"999\" to RETURN to last page.");
        try {
            int input = scanner.nextInt();
            if(input == 999)
                return;
            else{
                if(authorMap.containsKey(input)){
                    Author deletedAuthor = new Author(authorMap.get(input).getKeyID(),authorMap.get(input).getFullName());
                    Author.deleteFromMap(authorMap,bookMap,input);
                    System.out.print("Succefully Deleted ");
                    deletedAuthor.printToConsole();
                    System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                }
                else {
                    System.out.println("!!!!!NO SUCH AUTHOR ID EXIST IN RECORDS!!!!");
                }
            }
        }
        catch (InputMismatchException e){
            System.out.println("Improper Input Format");
        }

    }
    public static void updateAuthor(){

    }
    public static void readAllAuthors(Map<Integer, Author> authorMap){
        if(authorMap.isEmpty())
            System.out.println("There are currently NO AUTHORS stored in the system.");
        else
            Author.printMapToConsole(authorMap);
    }
}
