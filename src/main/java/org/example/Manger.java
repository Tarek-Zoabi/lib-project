package org.example;

public class Manger extends Worker{

    public Manger(){

    }
    public Manger(String first_name,String last_name,String id){
        this.id=id;
        this.firstname=first_name;
        this.lastname=last_name;
    }
    //hires worker
    public  void addWorker(Worker toAdd){
        Library lib= Library.getInstance();
        if(toAdd instanceof Adminstrator){
            System.out.println("a manager cannot add an admin");
        } else {
            lib.addWorkerOrManager(this,toAdd);
        }
    }
    //fires worker
    public void Fire(Library lib,Worker w){
       lib.Fire(this,w);
    }

    @Override
    public String getType() {
        return "Manger";
    }
}
