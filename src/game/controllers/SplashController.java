package game.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;


/**
 * <h1>SplashController class.</h1>
 * Handles the menu screen.
 *
 * @author Emil Jensen
 */
public class SplashController implements Initializable {

    /**
     * Holds {@link MainController} instance for parent fxml manipulation.
     */
    private MainController parentController;

    /**
     * Holds {@link GameController} instance for game start/stopping.
     */
    private GameController gameController;

    /**
     * The {@link Node} instance representing the game screen.
     * Game screen is where the game is drawn and contains a canvas.
     */
    private Node gameScreen;

    /**
     * Save game message label
     */
    @FXML
    private Label saveMessage;

    /**
     * Clears the screen, starts the game and switches to the game screen.
     * @param event A simple {@link ActionEvent}.
     */
    @FXML
    private void handleButtonAction(ActionEvent event) {
        parentController.clearMainPaneChildren();
        gameController.runGame(false);
        parentController.addMainPaneChild(gameScreen);
    }

    /**
     * Loads the saved game.
     * @param event A simple {@link ActionEvent}.
     */
    @FXML
    private void editButtonAction(ActionEvent event) {

        if (gameController.checkSaveFileExists()) {
            parentController.clearMainPaneChildren();
            gameController.runGame(true);
            parentController.addMainPaneChild(gameScreen);
        } else {
            this.saveMessage.setText("No save exists!");
        }
    }

    /**
     * {@link ActionEvent} quits the application when button is clicked.
     * @param event A simple {@link ActionEvent}.
     */
    @FXML
    private void closeButtonAction(ActionEvent event) {
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

    /**
     * Sets the {@link SplashController#gameScreen}.
     * @param gameScreen A {@link Node} object.
     */
    void setGameScreen(Node gameScreen) {
        this.gameScreen = gameScreen;
    }
}
