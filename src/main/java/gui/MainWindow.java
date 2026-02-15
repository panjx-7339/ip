package gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import penguin.Penguin;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Penguin penguin;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image penguinImage = new Image(this.getClass().getResourceAsStream("/images/Penguin.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Penguin instance */
    public void setPenguin(Penguin p) {
        penguin = p;

        // Show welcome message and loaded tasks at startup
        dialogContainer.getChildren().addAll(
                DialogBox.getPenguinDialog(penguin.welcome(), penguinImage),
                DialogBox.getPenguinDialog(penguin.showLoadedTasks(), penguinImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Penguin's reply and then appends them
     * to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String userText = userInput.getText();
        if (userText.equals("bye")) {
            Platform.exit(); // Quit application
        }
        String penguinText = penguin.respond(userInput.getText());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, userImage),
                DialogBox.getPenguinDialog(penguinText, penguinImage)
        );
        userInput.clear();
    }
}

