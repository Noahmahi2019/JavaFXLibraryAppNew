package com.mahderawod.notes;

import com.mahderawod.notes.model.BookService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
        primaryStage.setTitle("Notes");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }
    @Override
    public void init(){
     BookService.getInstance().getBooksFromFile();

    }

    @Override
    public void stop() {
        BookService.getInstance().addBooksToFile();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
