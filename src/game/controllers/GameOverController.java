package game.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * <h1>GameOverController class.</h1>
 * Handles the "Game Over" screen.
 *
 * @author Emil Jensen
 */
public class GameOverController implements Initializable {

    /**
     * Holds {@link MainController} instance for parent fxml manipulation.
     */
    private MainController parentController;

    /**
     * Holds {@link GameController} instance for game start/stopping.
     */
    private GameController gameController;

    /**
     * An {@link ActionEvent} for fxml button.
     * @param event A simple {@link ActionEvent}.
     */
    @FXML
    private void tryAgain(ActionEvent event) {
        gameController.restart();
        gameController.runGame(false);
        parentController.removeGameOverChild();
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
