package com.ss.training.fileInterface;

import com.ss.training.resourceClasses.Author;
import com.ss.training.resourceClasses.Book;
import com.ss.training.resourceClasses.Publisher;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Map;

/**
 * Class used for reading the initial text files and inputing them into the proper Maps
 */
public class FileToMap {

    /**
     *
     * @param authorMap- HashMap of all the books
     * @return returns true if files was succesfully read and false if file could not be read
     */
    public static boolean readAuthor(Map<Integer, Author> authorMap){
        try(BufferedReader bufferedreader = new BufferedReader(new FileReader("resources/Author.txt"))){
            String line = bufferedreader.readLine();

            /**
             * While the file is not empty it reads the file line by line and creates an instance
             * of Author with the new lines variables and puts it into a vector at the position of
             * the Authors key ID
             * Sets the Author counter to be 1 above the highest current Author ID
             */
            while (line != null) {
                String splitLine[] = line.split(",");
                Author a = new Author(Integer.parseInt(splitLine[0]),splitLine[1]);
                authorMap.put(a.getKeyID(),a);
                if(Integer.parseInt(splitLine[0])>=Author.counter)
                    Author.counter = Integer.parseInt(splitLine[0]) +1;
                line = bufferedreader.readLine();
            }
            return true;
        }
        catch(Exception e){ System.out.println("FILE Author.txt NOT FOUND!!!!");}
        return false;
    }

    /**
     *
     * @param publisherMap-HashMap of all the publishers
     * @return returns true if files was succesfully read and false if file could not be read
     */
    public static boolean readPublisher(Map<Integer, Publisher> publisherMap){
        //Try and open text file
        try(BufferedReader bufferedreader = new BufferedReader(new FileReader("resources/Publisher.txt"))){
            String line = bufferedreader.readLine();

            /**
             * While the file is not empty it reads the file line by line and creates an instance
             * of Publisher with the new lines variables and puts it into a HashMap at the position of
             * the Publisher key ID
             * Sets the Author counter to be 1 above the highest current Author ID
             */
            while (line != null) {
                String splitLine[] = line.split(",");
                Publisher a = new Publisher(Integer.parseInt(splitLine[0]),splitLine[1]);
                publisherMap.put(a.getKeyID(),a);
                if(Integer.parseInt(splitLine[0])>=Publisher.counter)
                    Publisher.counter = Integer.parseInt(splitLine[0]) +1;
                line = bufferedreader.readLine();
            }
            return true;
        }
        catch(Exception e){ System.out.println("FILE Publisher.txt NOT FOUND!!!!");}
        return false;
    }


    /**
     *
     * @param bookMap-HashMap of all the books
     * @return returns true if files was succesfully read and false if file could not be read
     */
    public static boolean readBook(Map<Integer, Book> bookMap ){
        //Try and open text filebook
        try(BufferedReader bufferedreader = new BufferedReader(new FileReader("resources/Book.txt"))){
            String line = bufferedreader.readLine();

            /**
             * While the file is not empty it reads the file line by line and creates an instance
             * of Publisher with the new lines variables and puts it into a HashMap at the position of
             * the Publisher key ID
             * Sets the Author counter to be 1 above the highest current Author ID
             */
            while (line != null) {
                String splitLine[] = line.split(",");
                Book a = new Book(Integer.parseInt(splitLine[0]),splitLine[1],Integer.parseInt(splitLine[2]),Integer.parseInt(splitLine[3]));
                bookMap.put(a.getBookID(), a);
                if(Integer.parseInt(splitLine[0])>=Book.counter)
                    Book.counter = Integer.parseInt(splitLine[0]) +1;
                line = bufferedreader.readLine();
            }
            return true;
        }
        catch(Exception e){ System.out.println("FILE Book.txt NOT FOUND!!!!");}
        return false;
    }
}
