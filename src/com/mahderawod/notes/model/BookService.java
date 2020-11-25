package com.mahderawod.notes.model;

import javafx.collections.FXCollections;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class BookService {
    private static BookService instance = new BookService();
    private List<Book> books = new ArrayList<>();

    private BookService() {
    }


    public static BookService getInstance() {
        return instance;
    }

    public static void setInstance(BookService instance) {
        BookService.instance = instance;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public void addBooksToFile() {

        try {
            BufferedWriter bw = new BufferedWriter((new FileWriter("books.txt")));
            for (Book book : this.books) {
                bw.write(book.getTitle() + "\t" + book.getAuthor());
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            System.out.println("IO  Exception occurred when trying to write to books.text");
            e.printStackTrace();
        }
    }

    public void getBooksFromFile() {
        books = FXCollections.observableArrayList();

        try {
            BufferedReader br = new BufferedReader(new FileReader("books.txt"));
            String fileData;
            while ((fileData = br.readLine()) != null) {
                System.out.println(fileData);
                String[] fileLineArr = fileData.split("\t");
                String bookTitle = fileLineArr[0];
                String bookAuthor = fileLineArr[1];
                System.out.println(bookTitle);
                System.out.println(bookAuthor);
                Book book = new Book(bookTitle, bookAuthor);
                books.add(book);


            }
        } catch (IOException e) {
            System.out.println("IO  Exception occurred when trying to read to books.text");
            e.printStackTrace();
        }
    }

    public void addBooks(Book newBook) throws IOException {
        this.books.add(newBook);
    }
}
