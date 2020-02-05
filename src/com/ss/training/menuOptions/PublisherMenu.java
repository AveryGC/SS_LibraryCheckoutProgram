package com.ss.training.menuOptions;

import com.ss.training.MapInterfaces.PublisherMapInterface;
import com.ss.training.resourceClasses.Book;
import com.ss.training.resourceClasses.Publisher;

import java.util.Map;
import java.util.Scanner;

public class PublisherMenu {
    /**
     * main menu of Publisher
     * @param scanner -Scanner set to system.in
     * @param bookMap - map of all books
     * @param publisherMap - map of all publishers
     */
    public void mainPublisherMenu(Scanner scanner, Map<Integer,Book> bookMap, Map<Integer,Publisher> publisherMap) {
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
                        break;
                }
            } catch (NumberFormatException e) {System.out.println("!!!!!!!!!Improper Input Format!!!!!!!!!!!!!");}
        }
    }

    /**
     * adds new publisher to publisher map
     * @param scanner - scanner set to system.in
     * @param publisherMap - map of all publisher
     * @return - the publisherID of book created or 999 if nothing is created
     */
    public int addPublisher(Scanner scanner, Map<Integer, Publisher> publisherMap){
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
            String publisherName = input;
            System.out.println("Enter the Address of the publisher");
            input = scanner.nextLine();
            PublisherMapInterface publishemapinterface = new PublisherMapInterface();
            int newPub = publishemapinterface.addToMap(publisherMap,publisherName,input);
            System.out.println("The following publisher has been successfully added:");
            publisherMap.get(newPub).printToConsole();
            return newPub;
        }
    }

    /**
     * delete publisher from publisher map
     * @param scanner - Scanner with ifstream of system.in
     * @param bookMap - map of all books
     * @param publisherMap - map of all publishers
     */
    public void deletePublisher(Scanner scanner, Map<Integer, Book> bookMap, Map<Integer, Publisher> publisherMap){
        PublisherMapInterface publishemapinterface = new PublisherMapInterface();
        publishemapinterface.printMapToConsole(publisherMap);
        System.out.println("Enter the Publisher ID of the publisher you would like to delete or type the number \"999\" to RETURN to last page.");
        String line = scanner.nextLine();
        try {
            int input = Integer.parseInt(line);
            if(input == 999)
                return;
            else{
                if(publisherMap.containsKey(input)){
                    Publisher deletedPublisher = new Publisher(publisherMap.get(input).getKeyID(),publisherMap.get(input).getPublisherName(),publisherMap.get(input).getPublisherAddress());
                    publishemapinterface.deleteFromMap(publisherMap,bookMap,input);
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

    /**
     * Allows user to update a particular publisher
     * @param scanner - scanner set to ifstream of system.in
     * @param publisherMap- map of all publishers
     */
    public void updatePublishers(Scanner scanner, Map<Integer, Publisher> publisherMap){
        PublisherMapInterface publishemapinterface = new PublisherMapInterface();
        publishemapinterface.printMapToConsole(publisherMap);
        String line;
        System.out.println("Enter the Publisher ID of the author you would like to update or type the number \"999\" to CANCEL.");
        line = scanner.nextLine();
        try{
            int input = Integer.parseInt(line);
            if(input == 999)
                System.out.println("Publisher Update Canceled");
            else{
                if(publisherMap.containsKey(input)) {
                    updatePublisherSubMenu(scanner, publisherMap, input);
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

    /**
     * used to make updating a publisher recursive when an input error is made
     * @param scanner - scanner set to system.in
     * @param publisherMap - map of all publishers
     * @param selectedID - the selected ID of publisher to update
     */
    public void updatePublisherSubMenu (Scanner scanner, Map<Integer, Publisher> publisherMap, int selectedID)
    {
        //Gives user the option of changing name or address
        System.out.println("Enter 1 to update the name, enter 2 to update the address, or enter 999 to exit.");
        String line =scanner.nextLine();
        try {
            int input = Integer.parseInt(line);
            //Updates selected authors name
            if(input==1) {
                System.out.println("Enter the new name to replace the Publisher's existing name.");
                String newName = scanner.nextLine();
                publisherMap.get(selectedID).setPublisherName(newName);
                System.out.println("Publisher successfully updated.");
                publisherMap.get(selectedID).printToConsole();;
            }
            //updates selected authors address
            else if(input==2){
                System.out.println("Enter the new name to replace the Publisher's address");
                String newAddress = scanner.nextLine();
//                Publisher newPublisher = new Publisher(selectedID, publisherMap.get(selectedID).getPublisherName(), newAddress);
//                publisherMap.replace(selectedID, newPublisher);
                publisherMap.get(selectedID).setPublisherAddress(newAddress);
                System.out.println("Publisher successfully updated.");
//                newPublisher.printToConsole();
                publisherMap.get(selectedID).printToConsole();
            }

            //exits  to main publisher menu
            else if(input==999){
                return;
            }
            //Input was invalid and re-runs the subMenu
            else{
                System.out.println("Input does not match available response options");
                updatePublisherSubMenu(scanner,publisherMap,selectedID);
            }
        }
        //Input was invalid and re-runs the subMenu
        catch (Exception e){
            System.out.println("!!Improper Input Format!!");
            updatePublisherSubMenu(scanner,publisherMap,selectedID);
        }
    }

    /**
     * prints all publishers to screen
     * @param publisherMap - map of all publishers
     */
    public void readAllPublishers(Map<Integer, Publisher> publisherMap) {
        PublisherMapInterface publishermapinterface = new PublisherMapInterface();
        if(publisherMap.isEmpty())
            System.out.println("There are currently NO PUBLISHERS saved in the system.");
        else
            publishermapinterface.printMapToConsole(publisherMap);
    }

    /**
     * select a particular existing publisher from publisher map
     * @param scanner- scanner set to system.in
     * @param publisherMap - map of all publishers
     * @return - returns the selected publisher ID or returns 999 if operation canceled
     */
    public int selectAPublisher(Scanner scanner, Map<Integer, Publisher> publisherMap) {
        PublisherMapInterface publishermapinterface = new PublisherMapInterface();
        publishermapinterface.printMapToConsole(publisherMap);
        System.out.println("Input the ID of the Publisher you would like to select or type the number \"999\" to CANCEL.");
        String line = scanner.nextLine();
        //Check to make sure the input was a valid publisher
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
