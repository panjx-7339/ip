package gui;

import java.io.IOException;
import java.nio.file.Paths;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import penguin.Penguin;

/**
 * A GUI for Penguin using FXML.
 */
public class Main extends Application {
    private Penguin penguin = new Penguin(Paths.get("data", "Penguin.txt"));

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setPenguin(penguin); // inject the Penguin instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


