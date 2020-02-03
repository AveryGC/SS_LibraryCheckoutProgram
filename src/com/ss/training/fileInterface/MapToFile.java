package com.ss.training.fileInterface;

import com.ss.training.resourceClasses.Author;
import com.ss.training.resourceClasses.Book;
import com.ss.training.resourceClasses.Publisher;

import java.io.File;
import java.io.FileWriter;
import java.util.Map;


public class MapToFile {


    //write authorMap to file
    public static void saveAuthor(Map<Integer, Author> authorMap){
        try(FileWriter writer = new FileWriter(new File("resources/Author.txt"))){
//            Set<Integer> keySet= authorMap.keySet();
            for(int i : authorMap.keySet()){
                writer.write(authorMap.get(i).getKeyID()+"@@"+authorMap.get(i).getFullName()+"\n");
            }
            System.out.println("Authors successfully saved.");
//            authorMap.forEach((k,v)-> writer.write(v.getKeyID()+","+v.getFullName()+"\n"));
        }
        catch (Exception e){ System.out.println("Author File did not write!!!!");}
    }

    //writes bookMap to file
    public static void saveBook(Map<Integer,Book> bookMap){
        try (FileWriter writer = new FileWriter(new File("resources/Book.txt"))){
//            Set<Integer> keySet = bookMap.keySet();
            for(int i : bookMap.keySet()){
                writer.write(bookMap.get(i).getBookID()+ "@@"  + bookMap.get(i).getBookName()+"@@"
                        +bookMap.get(i).getAuthorID()+"@@"+bookMap.get(i).getPublisherID()+"\n");
            }
            System.out.println("Books successfully saved.");
        }
        catch (Exception e){
            System.out.println("Books file did not write!!!!");
        }
    }


    //writes publisherMap to file
    public static void savePublisehr(Map<Integer, Publisher> publisherMap){
        try(FileWriter writer = new FileWriter(new File("resources/Publisher.txt"))){
//            Set<Integer> keySet = publisherMap.keySet();
            for(int i : publisherMap.keySet()){
                writer.write(publisherMap.get(i).getKeyID()+"@@"+publisherMap.get(i).getPublisherName() + "@@"
                        + publisherMap.get(i).getPublisherAddress()+"\n");
            }
            System.out.println("Publishers successfully saved.");
        }
        catch (Exception e){
            System.out.println("Publisher file did not write!!!");
        }
    }
}
