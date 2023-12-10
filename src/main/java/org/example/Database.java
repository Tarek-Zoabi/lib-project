package org.example;

import java.io.*;
import java.util.Vector;

public class Database {
    private Vector<Worker> workers;
    private Vector<Book> books;

    private static  Database db;

    //this method gets the instance of the db class as it is a singleton meaning that
    //one instance only can be generated and used across the entire project
    public static  Database getInstance(){
        if(db==null){
            return db=new Database();
        }
        return db;
    }
    // this method fetches the workers from the WorkerDB text file and puts them into the worker vector
    public void FetchWorker(String filename) throws IOException {
        workers = new Vector<>();
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        String[] tokens;

        while ((line = reader.readLine()) != null) {
             tokens = line.split(",");
            System.out.println(tokens[0]+" " +tokens[1]+tokens[2]+" " +tokens[3]);
            String id = tokens[0];
            String firstname = tokens[1];
            String lastname = tokens[2];
            String position =tokens[3];
            switch (position){
                case  "Worker" :

                    workers.add(new Worker(firstname, lastname, id));
                    break;
                case "Manger":{

                    workers.add(new Manger(firstname,lastname,id));
                    break;
                }
                case "Adminstrator":{

                    workers.add(new Adminstrator(firstname,lastname,id));
                    break;
                }
                default :{
                    System.out.println("invalid worker type");
                    break;
                }
            }


        }
        reader.close();

    }

    //this method transfers the workers from the WorkerDB text file to the worker vector in the Library
    public Vector getAllWorkers(){
        try {
            this.FetchWorker("WorkerDB.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("_________________________");
        for (Worker worker:workers){
            System.out.println(worker.getType()+" "+ worker.id);
        }
        return workers;
    }

    //this method saves all the workers from the library to the WorkerDB file
    public void saveWorkerToFile() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("WorkerDB.txt"));
            boolean anyWorkersWritten = false; // flag to keep track of whether any workers were written to the file
            for (int i = 0; i < workers.size(); i++) {
                if (i !=0){
                    writer.newLine();
                }

                String line = workers.get(i).ID() + "," + workers.get(i).First_Name() + "," + workers.get(i).Last_Name() + "," + workers.get(i).getClass().getSimpleName();

                writer.write(line);


                anyWorkersWritten = true; // set the flag to true if a worker was written to the file
            }
            writer.close();
            if (!anyWorkersWritten) { // if no workers were written to the file, print a message to the console
                System.out.println("No new workers added to file");
            }
        } catch (IOException e) {
            System.out.println("Error saving database to file: " + e.getMessage());
        }
    }






    //this method checks if a book exists in the book vector
    public boolean checkkBookExistance(Book stored){
        String id=stored.getBookCode();
        for (Book b : books) {
            if (id.equals(b.getBookCode()) && stored.compare(b)) {
                return true;
            }
        }
        return false;
    }
    //this method checks if a worker exists in the worker vector
    public boolean checkWorkerExistence(Worker worker) {
        String id = worker.ID();
        System.out.println(id);
        for (int i=0;i<  workers.size();i++) {

            if (workers.get(i).ID()==id) {
                return true;
            }
        }
        return false;
    }

    //Books Database
    //saves all books to BookDB
    public void saveBookToFile(){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("BookDB.txt"));
            for (Book book : books) {

                String line = book.getBookCode() + "," + book.getTitle() + "," + book.getWriter() + ","+ book.getYearOfpup()+ ","+book.getPrice()+","+book.getAmount() + ","+book.getNumOfPages();
                writer.write(line);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving database to file: " + e.getMessage());
        }
    }
    //Fetches all Books From BookDB
    public void FetchBook(String filename)throws IOException
    {
        books = new Vector<>();
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] tokens = line.split(",");
            String id = tokens[0];
            String title = tokens[1];
            String writer = tokens[2];
            String yearOfpub = tokens[3];
            String numOfpages= tokens[4];
            books.add(new Book(id, title, writer,Integer.parseInt(yearOfpub),Integer.parseInt(numOfpages)));
        }
        reader.close();
    }
    // gets all books organizes them to transfer them to the book vector in library
    public Vector<Book> getAllBooks(){
        try {
            this.FetchBook("BookDB.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return books;
    }

    //Methods for getting the workers and books from the library in order to save them

    public void getWorkersFromLib(Vector<Worker> workers){
        this.workers=workers;
        this.saveWorkerToFile();
    }
    public void getBooksFromLib(Vector<Book> books){
        this.books=books;
        this.saveBookToFile();
    }
}
