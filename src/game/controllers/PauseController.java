package game.controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * <h1>PauseController class.</h1>
 * Handles the Pause screen.
 *
 * @author Emil Jensen
 */
public class PauseController implements Initializable {

    /**
     * Holds {@link MainController} instance for parent fxml manipulation.
     */
    private MainController parentController;

    /**
     * Holds {@link GameController} instance for game start/stopping.
     */
    private GameController gameController;

    /**
     * Save game message label
     */
    @FXML
    private Label saveMessage;

    /**
     * Resumes game and removes pause screen.
     */
    @FXML
    private void handleResumeAction() {
        this.saveMessage.setText("Yo");
        gameController.runGame(false);
        parentController.removePauseChild();
    }

    /**
     * Saves the game to savefile.
     */
    @FXML
    private void handleSaveAction() {
        this.gameController.saveGame();
        this.saveMessage.setText("Game was saved");
    }

    /**
     * {@link ActionEvent} quits the application when button is clicked.
     * @param event A simple {@link ActionEvent}.
     */
    @FXML
    private void handleQuitAction(ActionEvent event) {
        System.exit(0);
    }

    /**
     * Initializes the controller. Overrides {@link Initializable#initialize(URL, ResourceBundle)}.
     * @param url location used to resolve relative paths for the root object
     * @param rb resources used to localize the root object
     * @see Initializable
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) { }

    /**
     * Sets the parent controller, the {@link MainController}.
     * @param parentController The parent {@link MainController} controller.
     */
    void setParentController(MainController parentController) {
        this.parentController = parentController;
    }

    /**
     * Sets the game controller.
     * @param gameController The {@link GameController}.
     */
    void setGameController(GameController gameController) {
        this.gameController = gameController;
    }
}
