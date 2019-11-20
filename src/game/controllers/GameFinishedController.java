package game.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * <h1>GameFinishedController class.</h1>
 * Handles the "Game Finished" screen.
 *
 * @author Emil Jensen
 */
public class GameFinishedController implements Initializable {

    /**
     * Holds {@link MainController} instance for parent fxml manipulation.
     */
    private MainController parentController;

    /**
     * Holds {@link GameController} instance for game start/stopping.
     */
    private GameController gameController;

    /**
     * Holds the Menu Screen.
     */
    private Node menuScreen;

    /**
     * An {@link ActionEvent} for fxml button.
     * @param event A simple {@link ActionEvent}.
     */
    @FXML
    private void playAgain(ActionEvent event) {
        gameController.restart();
        parentController.clearMainPaneChildren();
        parentController.addMainPaneChild(menuScreen);
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

    /**
     * Sets the menu screen.
     * @param menuScreen A {@link Node} object.
     */
    void setMenuScreen(Node menuScreen) {
        this.menuScreen = menuScreen;
    }
}
