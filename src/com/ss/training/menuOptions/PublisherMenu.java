package com.ss.training.menuOptions;

import com.ss.training.resourceClasses.Book;
import com.ss.training.resourceClasses.Publisher;

import java.util.Map;
import java.util.Scanner;

public class PublisherMenu {
    public static void mainPublisherMenu(Scanner scanner, Map<Integer,Book> bookMap, Map<Integer,Publisher> publisherMap) {
        String line;
        int input = 40;
        while (input != 999){
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("+                                            Publisher Menu                                              +");
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("Enter 1 to add a publisher, 2 to delete a publisher, 3 to update a publisher, 4 to read all publisher, or 999 to exit");
            line = scanner.nextLine();
            try {
                input = Integer.parseInt(line);
                switch (input) {
                    case 1:
                        addPublisher(scanner,publisherMap);
                        break;
                    case 2:
                        deletePublisher(scanner, bookMap, publisherMap);
                        break;
                    case 3:
                        updatePublishers(scanner, publisherMap);
                        break;
                    case 4:
                        readAllPublishers(publisherMap);
                        break;
                    case 999:
                        System.out.println("Exiting to main.");
                        break;
                    default:
                        System.out.println("!!!Please enter proper input option!!!");
                        mainPublisherMenu(scanner,bookMap,publisherMap);
                        break;
                }
            } catch (NumberFormatException e) {System.out.println("!!!!!!!!!Improper Input Format!!!!!!!!!!!!!");}
        }
    }
    public static int addPublisher(Scanner scanner, Map<Integer, Publisher> publisherMap){
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("Enter Name of Publisher you would like to add, or enter the number \"999\" into the console to go back.");
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
            int newPub = Publisher.addToMap(publisherMap,input);
            System.out.println("The following publisher has been successfully added:");
            publisherMap.get(newPub).printToConsole();
            return newPub;

        }
    }
    public static void deletePublisher(Scanner scanner, Map<Integer, Book> bookMap, Map<Integer, Publisher> publisherMap){
        Publisher.printMapToConsole(publisherMap);
        System.out.println("Enter the Publisher ID of the publisher you would like to delete or type the number \"999\" to RETURN to last page.");
        String line = scanner.nextLine();
        try {
            int input = Integer.parseInt(line);
            if(input == 999)
                return;
            else{
                if(publisherMap.containsKey(input)){
                    Publisher deletedPublisher = new Publisher(publisherMap.get(input).getKeyID(),publisherMap.get(input).getPublisherName());
                    Publisher.deleteFromMap(publisherMap,bookMap,input);
                    System.out.print("Succefully Deleted ");
                    deletedPublisher.printToConsole();
                    System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                }
                else {
                    System.out.println("!!!!!NO SUCH PUBLISHER ID EXIST IN RECORDS!!!!");
                    deletePublisher(scanner,bookMap,publisherMap);
                }
            }
        }
        catch (Exception e){
            System.out.println("Improper Input Format");
            deletePublisher(scanner,bookMap,publisherMap);
        }

    }
    public static void updatePublishers(Scanner scanner, Map<Integer, Publisher> publisherMap){
        Publisher.printMapToConsole(publisherMap);
        System.out.println("Enter the Publisher ID of the author you would like to update or type the number \"999\" to CANCEL.");
        String line = scanner.nextLine();
        try{
            int input = Integer.parseInt(line);
            if(input == 999)
                System.out.println("Publisher Update Canceled");
            else{
                if(publisherMap.containsKey(input)) {
                    System.out.println("Enter the new name to replace the Publisher's existing name.");
                    String newName = scanner.nextLine();
                    Publisher newPublisher = new Publisher(input, newName);
                    publisherMap.replace(input, newPublisher);
                    System.out.println("Publisher successfully updated.");
                    newPublisher.printToConsole();
                }
                else{
                    System.out.println("Publisher ID doesn't exist");
                    updatePublishers(scanner,publisherMap);
                }
            }
        }
        catch (NumberFormatException e){
            System.out.println("!!!!!!!!!!!!!!!!Improper Input Format!!!!!!!!!!!!!!!");
            updatePublishers(scanner,publisherMap);
        }
    }

    public static void readAllPublishers(Map<Integer, Publisher> publisherMap) {
        if(publisherMap.isEmpty())
            System.out.println("There are currently NO PUBLISHERS saved in the system.");
        else
            Publisher.printMapToConsole(publisherMap);
    }
    public static int selectAPublisher(Scanner scanner, Map<Integer, Publisher> publisherMap) {
        Publisher.printMapToConsole(publisherMap);
        System.out.println("Input the ID of the Publisher you would like to select or type the number \"999\" to CANCEL.");
        String line = scanner.nextLine();
        try {
            int input = Integer.parseInt(line);
            if (input == 999)
                return 999;
            else if (publisherMap.containsKey(input))
                return input;
            else {
                System.out.println("!!!!!!!Selected Publisher does not exist!!!!!!!!");
                return selectAPublisher(scanner, publisherMap);
            }
        } catch (Exception e) {
            System.out.println("!!!!!!!Improper input format!!!!!!!");
            return selectAPublisher(scanner, publisherMap);
        }
    }
}
