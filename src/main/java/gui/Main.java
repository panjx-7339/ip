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

    // Todo: show welcome and load tasks
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
//    /**
//     * Creates a dialog box containing user input, and appends it to
//     * the dialog container. Clears the user input after processing.
//     */
//    private void handleUserInput() {
//        String userText = userInput.getText();
//        // Quit application if user types "exit"
//        if (userText.equals("exit")) {
//            Platform.exit();
//        }
//        String penguinText = penguin.respond(userInput.getText());
//        dialogContainer.getChildren().addAll(
//                DialogBox.getUserDialog(userText, userImage),
//                DialogBox.getPenguinDialog(penguinText, penguinImage)
//        );
//        userInput.clear();
//    }
    //        // Show welcome message and loaded tasks
//        dialogContainer.getChildren().addAll(
//                DialogBox.getPenguinDialog(penguin.welcome(), penguinImage),
//                DialogBox.getPenguinDialog(penguin.showLoadedTasks(), penguinImage)
//        );


