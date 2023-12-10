package org.example;

import java.util.Scanner;

public class Worker implements Person{
    String firstname;
    String lastname;
    String id;
    @Override
    public String First_Name() {
        return this.firstname;
    }

    @Override
    public String Last_Name() {
        return lastname;
    }

    @Override
    public String ID() {
        return this.id;
    }
    public Worker(){}
//worker constructor
    public Worker(String firstname, String lastname, String id) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.id = id;
    }
//setters and getters for the fields
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setId(String id) {
        this.id = id;
    }
    // a method to add a book to the library
    public void addBook(Library lib, Book b){
        if (lib.addBook(b,this)){
            System.out.println("book added");
        }
        else{
            System.out.println("Can't add book. Worker doesn't work in this library");
        }
    }
    // gets a worker's full name
    public String getFullName(){
        return  First_Name()+" " + Last_Name();
    }
    //updates a book's price by locating it by id
    public void Updatebook(String id){
        Library lb=Library.getInstance();
        Scanner sc=new Scanner(System.in);
        Book toBeUpdated=(Book)lb.searchbookById(id);
        System.out.println("Enter new Price");
        int price= sc.nextInt();
        if(price>0 && toBeUpdated!=null){
            lb.UpdatePrice(this,toBeUpdated,price);
        }
        else {
            System.out.println("Price can't be negative or book not found");
        }


    }
    //removes a book from the books vector in the library
    public void RemoveBook(String id){
        Library lb = Library.getInstance();
        Book b=   lb.searchbookById(id);
        if(b!=null){
            lb.RemoveBook(this,b);
        }

    }
    // compares one worker to another by id
    public boolean compare(Worker worker) {
        return this.ID().equals(worker.ID());
    }
    //gets the type/ privilage of the worker
    public String getType(){
        return "Worker";
    }

}
