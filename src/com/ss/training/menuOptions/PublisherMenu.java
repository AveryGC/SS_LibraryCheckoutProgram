package com.ss.training.menuOptions;

import com.ss.training.resourceClasses.Book;
import com.ss.training.resourceClasses.Publisher;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class PublisherMenu {
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
                return newID = Publisher.addToMap(publisherMap,input);

            }
        }
        public static void deletePublisher(Scanner scanner, Map<Integer, Book> bookMap, Map<Integer, Publisher> publisherMap){
            Publisher.printMapToConsole(publisherMap);
            System.out.println("Enter the Publisher ID of the book you would like to delete or type the number \"999\" to RETURN to last page.");
            try {
                int input = scanner.nextInt();
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
                    }
                }
            }
            catch (InputMismatchException e){
                System.out.println("Improper Input Format");
            }

        }
        public static void updatePublishers(){

        }
        public static void readAllPublishers(Map<Integer, Publisher> publisherMap) {
            if(publisherMap.isEmpty())
                System.out.println("There are currently NO PUBLISHERS saved in the system.");
            else
                Publisher.printMapToConsole(publisherMap);
        }
}
