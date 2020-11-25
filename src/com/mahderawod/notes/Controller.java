package com.mahderawod.notes;

import com.mahderawod.notes.model.Book;
import com.mahderawod.notes.model.BookService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Controller {
    public BorderPane mainBorderpane;
    @FXML
    private ListView<Book> booksListView;
    @FXML
    private ListView<Book> NotesListView;
    @FXML
    private TextArea booksTextArea;
    @FXML
    private Label createAnewBook;
    private List<Book> books = new ArrayList<>();

    public void initialize() {

        booksListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Book>() {
            @Override
            public void changed(ObservableValue<? extends Book> unusedValue1, Book unUsedValue2, Book book) {
                if (book != null) {
                    Book selectedBook = booksListView.getSelectionModel().getSelectedItem();
                    booksTextArea.setText(selectedBook.getTitle());
                    booksTextArea.setText(selectedBook.getAuthor());
                    createAnewBook.setText(selectedBook.getTitle());
                    createAnewBook.setText(selectedBook.getAuthor());
                }

            }
        });
        booksListView.getItems().setAll(BookService.getInstance().getBooks());

    }

    public void showCreateBookDialog(ActionEvent actionEvent) throws IOException {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainBorderpane.getScene().getWindow());
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("createBookDialog.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println("IOException: couldn't load New note Dialog");
            e.printStackTrace();
        }
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            DialogController controller = fxmlLoader.getController();
            controller.processNewBook();
            booksListView.getItems().setAll(BookService.getInstance().getBooks());

            System.out.println("New Book Created");

        } else System.out.println("new note canceled");


    }

    public void updateListView(MouseEvent mouseEvent) {
    }
}