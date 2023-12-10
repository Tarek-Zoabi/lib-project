package org.example;

public class Book {
    private String bookCode;
    private String title;
    private String writer;
    private int yearOfpup;
    private int numOfPages;

    private int price;
    private int amount=1;

    public Book(String bookCode, String title, String writer, int yearOfpup, int numOfPages) {
        this.bookCode = bookCode;
        this.title = title;
        this.writer = writer;
        this.yearOfpup = yearOfpup;
        this.numOfPages = numOfPages;
    }



    public String getBookCode() {
        return bookCode;
    }

    public void setBookCode(String bookCode) {
        this.bookCode = bookCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public int getYearOfpup() {
        return yearOfpup;
    }

    public void setYearOfpup(int yearOfpup) {
        this.yearOfpup = yearOfpup;
    }

    public int getNumOfPages() {
        return numOfPages;
    }

    public void setNumOfPages(int numOfPages) {

        if(numOfPages>0){
            this.numOfPages = numOfPages;
            System.out.println("Num of pages changes successfully");
        }
        else {
            System.out.println("Cannot set a book with a zero or below num of pages");
        }

    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public boolean isEqual(Book b){
        if (bookCode==b.bookCode && this.title==b.title && this.writer==b.writer && this.yearOfpup==b.yearOfpup && this.numOfPages==b.numOfPages)
            return true;
        else return false;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookCode=" + bookCode +
                ", title='" + title + '\'' +
                ", writer='" + writer + '\'' +
                ", yearOfpup=" + yearOfpup +
                ", numOfPages=" + numOfPages +
                '}';
    }

    public boolean compare(Book b) {
        if (this.getBookCode() == b.getBookCode()) {
            return true;
        }
        return false;
    }
}

