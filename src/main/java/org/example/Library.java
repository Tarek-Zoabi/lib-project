package org.example;

//import java.awt.print.Book;
import java.io.IOException;
import java.time.chrono.IsoChronology;
import java.util.Scanner;
import java.util.Vector;

public class Library {
    Vector<Book> books= new Vector<Book>();;
     static Vector<Worker> workers= new Vector<Worker>();
    Database db=Database.getInstance();

    static Library library;

    private Library() {
        books=new Vector<Book>();
        workers=new Vector<>();

    }
    // a constructor that initiates all the fields takes an admin so we can start building a library
    private Library(Adminstrator m){
        books=new Vector<Book>();
        workers=new Vector<Worker>();
       // workers = db.getAllWorkers();
        workers.add(m);
        db=Database.getInstance();
    }
    // the library class is a singleton this means thatany library pointer points to the same instance of the library class
    // and there can only be one instance of the library class across the entire project hence the private constructors
    public static Library getInstance(){
        Adminstrator adminstrator=new Adminstrator("Juan","Doe","admin");
        if (library==null){
            library=new Library(adminstrator);
        }


        return library;
    }



    // toString method to print the library to console with
    @Override
    public String toString() {
        return "Library{" +
                "books=" + books +
                "workers"+ workers+
                '}';
    }
    //adds book to the books vector
    public boolean addBook(Book b,Worker adder)
    {
        if(this.ContainsWorker(adder)){

            if(!ContainsBook(b.getBookCode())){
                books.add(b);
                this.saveBookToDB();
                return true;
            }
            else {
                for (int i=0; i<books.size();i++){
                    if(b.getBookCode()==books.get(i).getBookCode()){
                        books.get(i).setAmount(books.get(i).getAmount()+1);
                        this.saveBookToDB();
                        return true;
                    }
                }
            }


        }
        else {

            return false;
        }


        return false;
    }
    //returns the size of the book vector irrelevant for this project
    public int amount() {
        return books.size();
    }
    //returns the index of a given book using its' book code
    public int indexOf(String bookCode) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getBookCode()==bookCode);
            return i;
        }


        return -1;
    }
    //checks if the book vector is empty
    public boolean isempty() {
        if (books.size()==0)
            return true;
        else return false;
    }
    //adds worker manager or admin got lazy and didn't change the name because it was used in many already
    //howver to add an admin the adder must be an admin too
    public void addWorkerOrManager(Manger m, Worker ToAdd){ //this adds a worker or a manger
        if (m instanceof Manger && !(m instanceof  Adminstrator)){
            if(!(ToAdd instanceof Adminstrator) && this.ContainsWorker(m) && !(this.ContainsWorker(ToAdd))){

                workers.add(ToAdd);
                this.saveWorkersToDB();
                System.out.println("worker added successfully");
            } else if (m instanceof Adminstrator && (this.ContainsWorker(m)) && !(this.ContainsWorker(ToAdd))) {
                workers.add(ToAdd);
                this.saveWorkersToDB();
                System.out.println("worker added successfully");
            } else{
                System.out.println("Cannot add admin with manager privilege");
            }
        }
        else {
            if (this.ContainsWorker(m) &&  !(this.ContainsWorker(ToAdd))) {
                workers.add(ToAdd);
                this.saveWorkersToDB();
            }
            if (this.ContainsWorker(m) &&  !(this.ContainsWorker(ToAdd))) {
                workers.add(ToAdd);
                this.saveWorkersToDB();
            }

        }

    }
    //fires a worker admin or manager
    //only admins have the ability to fire managers and other admins
    //no manager or admin can fire himself
    public boolean Fire(Manger m,Worker toFire){
        if(!this.ContainsWorker(toFire)){
            System.out.println("worker does not work in this library");
            return false;
        }
        if(!(toFire instanceof Adminstrator)&& !(toFire instanceof Manger)){
            if( this.ContainsWorker(m)&& m.id!=toFire.id){
                workers.remove(toFire);
                this.saveWorkersToDB();
                System.out.println("worker removed successfully");

                return true;
            }
            else{
                System.out.println("a worker can  not be removed | unauthorized access");
                return false;
            }
        } else if (m instanceof Adminstrator && this.ContainsWorker(m) && m.id!=toFire.id) {
            workers.remove(toFire);
            this.saveWorkersToDB();
            System.out.println("worker removed successfully");
            return true;
        }
        return false;
    }

   static boolean ContainsWorker(Worker w){
        for (int i=0;i<workers.size(); i++){
            if(workers.get(i).id==w.id){
                return true;
            }
            else {
                continue;
            }
        }
        return false;
    }
    private boolean ContainsBook(String bookCode){
        for(int i=0; i<books.size();i++){
            if (bookCode ==books.get(i).getBookCode())
                return true;
        }
        return false;
    }
    public void UpdatePrice(Worker w,Book b, int price){
        if(this.ContainsWorker(w)){
            for(int i=0; i<books.size();i++){
                if (books.get(i).isEqual(b)){
                    books.get(i).setPrice(price);
                    this.saveBookToDB();
                    System.out.println("Book price updated successfully");
                    break;
                }
                else {
                    System.out.println("book does not exist in this library");
                }
            }
        }
        else{
            System.out.println("worker does not work here");
        }

    }
    public void RemoveBook(Worker w,Book b){
        if(!this.ContainsWorker(w)){
            System.out.println("Worker doesn't work here");
            this.saveBookToDB();
            return;
        }
        if(this.ContainsWorker(w)) {
            for (int i = 0; i < books.size(); i++) {
                if (books.get(i).isEqual(b)) {
                    books.remove(books.get(i));
                    this.saveBookToDB();
                    System.out.println("Book removed successfully");
                    break;
                } else {
                    continue;
                }
            }

        }else {
            System.out.println("Book does not exist in this library");
        }

    }
    //searches up a worker by id
    public Worker SearchByID(String id){

        id=id.trim();
        Worker w;
        for(Worker worker:workers){
            System.out.println(worker.id + " "+ worker.getType());
        }
        System.out.println(workers.size());
        for(int i=0; i<workers.size();i++){
            if(workers.get(i).ID().equals(id)){
                return w=workers.get(i);
            }
        }
        return null;
    }
    // looks for a book by id
    public Book searchbookById(String id){
        for(int i=0; i<books.size();i++){
            if(books.get(i).getBookCode().equals(id)){
                return books.get(i);
            }
        }
        return null;
    }
    //looks for a book by name
    public Book SearchBookByName(String name){
        for (int i=0;i< books.size(); i++){
            if(books.get(i).getTitle()==name){
                return books.get(i);
            }
        }
        return null;
    }
    //looks for a worker by name
    public Worker SearchWorkerByName(String name){
        for (int i=0;i< workers.size(); i++){
            if(workers.get(i).getFullName()==name){
                return workers.get(i);
            }
        }
        return null;
    }

    //Methods for getting and setting the database vectors
    //exports books and workers from lib
    public  void saveWorkersToDB(){
        db.getWorkersFromLib(this.workers);
    }

    public void saveBookToDB(){
        db.getBooksFromLib(this.books);
    }
    //import books and workers to lib
    public void putBooksInLib(){
        this.books=db.getAllBooks();
    }
    public void putWorkersInLib(){
        this.workers=db.getAllWorkers();
    }

}