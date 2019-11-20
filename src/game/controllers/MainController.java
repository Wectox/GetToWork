package game.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * <h1>MainController class.</h1>
 *
 * MainController handles the interaction between all of the controllers and what
 * fxml that should be active in the main.fxml. This is done by changing the fxml children of main.
 *
 * @author Emil Jensen
 */
public class MainController implements Initializable {

    /**
     * The {@link Node} instance representing the pause screen.
     */
    private Node pauseScreen;

    /**
     * The {@link Node} instance representing the game-over screen.
     */
    private Node gameOverScreen;

    /**
     * The {@link Node} instance representing the game-finished screen.
     */
    private Node gameFinishedScreen;

    /**
     * The {@link Node} instance representing the splash screen.
     */
    private Node splashScreen;

    /**
     * The {@link Node} instance representing the game screen.
     * Game screen is where the game is drawn and contains a canvas.
     */
    private Node gameScreen;

    /**
     * The {@link Pane} parent that will contain the different screens as children.
     */
    @FXML
    private Pane mainPane;

    /**
     *  Initializes the controller. Overrides {@link Initializable#initialize(URL, ResourceBundle)}.
     * @param url location used to resolve relative paths for the root object
     * @param rb resources used to localize the root object
     * @see Initializable
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        FXMLLoader splashScreenFxml = new FXMLLoader(getClass().getResource("/views/splashscreen.fxml"));
        FXMLLoader gameFxml = new FXMLLoader(getClass().getResource("/views/game.fxml"));
        FXMLLoader pauseFxml = new FXMLLoader(getClass().getResource("/views/pause.fxml"));
        FXMLLoader gameOverFxml = new FXMLLoader(getClass().getResource("/views/gameover.fxml"));
        FXMLLoader gameFinishedFxml = new FXMLLoader(getClass().getResource("/views/gamefinished.fxml"));

        try {
            splashScreen = splashScreenFxml.load();
            gameScreen = gameFxml.load();
            pauseScreen = pauseFxml.load();
            gameOverScreen = gameOverFxml.load();
            gameFinishedScreen = gameFinishedFxml.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        SplashController splashController = splashScreenFxml.getController();
        GameController gameController = gameFxml.getController();
        PauseController pauseController = pauseFxml.getController();
        GameOverController gameOverController = gameOverFxml.getController();
        GameFinishedController gameFinishedController = gameFinishedFxml.getController();

        splashController.setParentController(this);
        splashController.setGameScreen(gameScreen);
        splashController.setGameController(gameController);

        gameController.setParentController(this);
        gameController.setPauseScreen(pauseScreen);
        gameController.setGameOverScreen(gameOverScreen);
        gameController.setGameFinishedScreen(gameFinishedScreen);

        pauseController.setParentController(this);
        pauseController.setGameController(gameController);

        gameOverController.setParentController(this);
        gameOverController.setGameController(gameController);

        gameFinishedController.setParentController(this);
        gameFinishedController.setGameController(gameController);
        gameFinishedController.setMenuScreen(splashScreen);

        mainPane.getChildren().add(splashScreen);
    }

    /**
     * Removes all children from the {@link MainController#mainPane}.
     */
    void clearMainPaneChildren() {
        mainPane.getChildren().clear();
    }

    /**
     * Removes the {@link MainController#pauseScreen} child from {@link MainController#mainPane}.
     */
    void removePauseChild() {
        mainPane.getChildren().remove(this.pauseScreen);
    }

    /**
     * Removes the {@link MainController#gameOverScreen} child from {@link MainController#mainPane}.
     */
    void removeGameOverChild() {
        mainPane.getChildren().remove(this.gameOverScreen);
    }

    /**
     *  Adds a child to {@link MainController#mainPane}.
     * @param child {@link Node} to add as a child to {@link MainController#mainPane}.
     */
    void addMainPaneChild(Node child) {
        mainPane.getChildren().add(child);
    }
}
