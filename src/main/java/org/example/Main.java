package org.example;

import com.sun.source.util.TaskEvent;

import java.io.File;
import java.util.Scanner;
import java.util.Vector;

public class Main {
 static Library lib=Library.getInstance();

    public static void main(String[] args) {
//        Book book1= new Book("6805","tarek's life","tarek zoabi",2009,465);
//        Book book2=new Book("324","Lina Mata as a teacher","Lina Mata",2021,247);
//        Book book3=new Book("581","Messi","Lionel Messi",2022,770);
//        Book book4=new Book("741","tarek took hashad","Tarek Zoabi",2023,95);
//        Book book5=new Book("100","How to not take hashad","Alaa Zoabi",2020,421);
//        Vector<Book>books=new Vector<>();
//        Library library=new Library();
//        library.addBook(book2,100);
//        library.addBook(book3,150);
//        library.addBook(book5,403);
//        System.out.println(library);
//
//        System.out.println(library);
//
//        System.out.println(library);

        System.out.println(new File("WorkerDB.txt").canRead());

        lib.putBooksInLib();
        lib.putWorkersInLib();
        Prompt p =new Prompt();
        p.TakeID();
    }

}