package com.mahderawod.notes;

import com.mahderawod.notes.model.Book;
import com.mahderawod.notes.model.BookService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DialogController {

    @FXML
    private TextField newBookTitleTextField;
    @FXML
    private TextField newBookAuthorTextField;

    public void processNewBook() throws IOException {
            String title = newBookTitleTextField.getText();
            String author =newBookAuthorTextField.getText();




            Book newBook = new Book(title,author);
            BookService.getInstance().addBooks(newBook);
        }
}