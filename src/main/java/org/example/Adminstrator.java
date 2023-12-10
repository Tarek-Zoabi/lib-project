package org.example;

public class Adminstrator extends Manger{
    public Adminstrator(){

    }
    public  Adminstrator(String first_name, String last_name, String id){
        this.setId(id);
        this.setFirstname(first_name);
        this.setLastname(last_name);
    }
    //adds worker to the lib worker vector
    public void addWorker(Library lib,Worker w){
        lib.addWorkerOrManager(this,w);
    }
    //gets the type/ privilage of the worker
    @Override
    public String getType() {
        return "Adminstrator";
    }
}
