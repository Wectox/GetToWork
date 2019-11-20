package game.controllers;

import game.entities.Mob;
import game.entities.Player;
import game.levels.Level;
import game.levels.LevelFactory;
import game.levels.Levels;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.File;
import java.net.URL;
import java.util.*;

import javafx.scene.paint.Paint;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 * <h1>GameController class</h1>
 * Handles the game mechanics such as: gameloop, drawing, logic, levels, etc.
 *
 * @author Emil Jensen
 */
public class GameController implements Initializable {

    /**
     * The final save file url.
     */
    private final String saveFileUrl = "./saveData.xml";

    /**
     * Holds {@link MainController} instance for parent fxml manipulation.
     */
    private MainController parentController;

    /**
     * The {@link Node} for pauseScreen used when changing to pause menu.
     * Node is added to parent fxml.
     */
    private Node pauseScreen;

    /**
     * The {@link Node} for gameOverScreen used when changing to game-over menu.
     * Node is added to parent fxml.
     */
    private Node gameOverScreen;

    /**
     * The {@link Node} for gameFinishedScreen used when changing to game-finished menu.
     * Node is added to parent fxml.
     */
    private Node gameFinishedScreen;

    /**
     * The {@link AnimationTimer} used as gameloop for the game.
     */
    private AnimationTimer animationTimer;

    /**
     * An {@link ArrayList<String>} that holds all the current input data from the user.
     */
    private ArrayList<String> input = new ArrayList<>();

    /**
     * The {@link GraphicsContext} used to draw to canvas.
     */
    private GraphicsContext gc;

    /**
     * Current {@link Level} that is active.
     */
    private Level currentLevel;

    /**
     * Holds the current {@link Levels} enum.
     */
    private Levels currentLevelEnum;

    /**
     * First {@link Level} represented by {@link Levels}. Aka., startlevel.
     */
    private Levels firstLevel = Levels.SUBWAY;

    /**
     * The {@link Canvas} which we draw our game-objects to using {@link GameController#gc}.
     */
    @FXML
    private Canvas gameCanvas;

    /**
     * The {@link Integer} width of {@link GameController#gameCanvas}.
     */
    private int gameCanvasWidth = 600;

    /**
     * The {@link Integer} height of {@link GameController#gameCanvas}.
     */
    private int gameCanvasHeight = 480;

    /**
     * Initializes the controller. Overrides {@link Initializable#initialize(URL, ResourceBundle)}.
     * @param url location used to resolve relative paths for the root object
     * @param rb resources used to localize the root object
     * @see Initializable
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gameCanvas.setFocusTraversable(true);
        gameCanvas.setWidth(gameCanvasWidth);
        gameCanvas.setHeight(gameCanvasHeight);
        gameCanvas.setLayoutX(0);
        gameCanvas.setLayoutY(0);

        gc = gameCanvas.getGraphicsContext2D();

        this.currentLevelEnum = firstLevel;
        this.currentLevel = LevelFactory.buildLevel(firstLevel, gameCanvasWidth, gameCanvasHeight);

    }

    /**
     * {@link KeyEvent} that handles the keyPresses of the user.
     * Adds {@link KeyCode} to {@link GameController#input}.
     * @param keyEvent
     */
    @FXML
    private void handleKeyPressed(KeyEvent keyEvent) {
        String code = keyEvent.getCode().toString();
        if (!input.contains(code)) {
            input.add(code);
        }
    }

    /**
     * {@link KeyEvent} that handles the keyReleases of the user.
     * Removes {@link KeyCode} from {@link GameController#input}.
     * @param keyEvent
     */
    @FXML
    private void handleKeyReleased(KeyEvent keyEvent) {
        String code = keyEvent.getCode().toString();
        input.remove(code);
    }

    /**
     * Clears an initializes what is necessary every game-tick.
     */
    private void gameInit() {
        gc.clearRect(0,0,gameCanvasWidth, gameCanvasHeight);
        gc.fillRect(0,0, gameCanvasWidth, gameCanvasHeight);
        gc.setFill(Paint.valueOf("GREEN"));
    }

    /**
     * Updates everything necessary every game-tick.
     */
    private void gameUpdate() {
        currentLevel.updatePlayer();
        currentLevel.handleInputActions(input);

        if (input.contains(KeyCode.ESCAPE.toString())) {
            this.pauseGame();
            this.activatePauseScreen();
        }

        currentLevel.updateMobs();
        currentLevel.moveMobs();

        currentLevel.checkPlayerMobCollision();
        if (currentLevel.playerIsDead()) {
            this.pauseGame();
            this.activateGameOverScreen();
        }

        if (currentLevel.checkPlayerCheckpointCollision()) {
            this.currentLevelEnum = currentLevel.getNextLevel();
            if (this.currentLevelEnum == null) {
                this.pauseGame();
                this.input.clear();
                this.parentController.clearMainPaneChildren();
                this.parentController.addMainPaneChild(gameFinishedScreen);
            } else {
                this.currentLevel = LevelFactory.buildLevel(currentLevel.getNextLevel(), gameCanvasWidth, gameCanvasHeight);
            }
        }
    }

    /**
     * Renders/Draws game-objects every game-tick.
     */
    private void gameRender() {
        currentLevel.drawBackgroundImage(gc);
        currentLevel.drawCheckpoint(gc);
        currentLevel.drawMobs(gc);
        currentLevel.drawPlayer(gc);
        currentLevel.drawLives(gc);
    }

    /**
     * Used to delete game-objects every game-tick.
     */
    private void gameDelete() {}

    /**
     * Starts the {@link GameController#animationTimer} game-loop thread.
     * Thread runs the game functions.
     * @param fromSave A {@link Boolean} value that decides if the game should be run from save.
     * @see GameController#gameInit()
     * @see GameController#gameUpdate()
     * @see GameController#gameRender()
     * @see GameController#gameDelete()
     * @see AnimationTimer
     */
    void runGame(Boolean fromSave) {

        if (fromSave) {
            this.buildLevelFromSave();
        }

        this.animationTimer = new AnimationTimer() {

            private final long startNanoTime = System.nanoTime();

            @Override
            public void handle(long currentNanoTime) {
                double time = (currentNanoTime - startNanoTime) / 1000000000.0;

                gameInit();
                gameUpdate();
                gameRender();
                gameDelete();
            }
        };
        animationTimer.start();
    }

    /**
     * Pauses the {@link GameController#animationTimer}.
     */
    private void pauseGame() {
        animationTimer.stop();
    }

    /**
     * Saves the game to file.
     */
    void saveGame() {
        Player player = this.currentLevel.getPlayer();
        List<Mob> mobs = this.currentLevel.getMobs();

        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

            Document doc = documentBuilder.newDocument();
            Element rootElement = doc.createElement("game");
            doc.appendChild(rootElement);

            Element levelElement = doc.createElement("level");
            rootElement.appendChild(levelElement);

            Attr attr = doc.createAttribute("name");
            attr.setValue(currentLevelEnum.name());
            levelElement.setAttributeNode(attr);

            Element playerElement = doc.createElement("player");
            rootElement.appendChild(playerElement);

            attr = doc.createAttribute("x-position");
            attr.setValue(Integer.toString(player.getPositionX()));
            playerElement.setAttributeNode(attr);

            attr = doc.createAttribute("y-position");
            attr.setValue(Integer.toString(player.getPositionY()));
            playerElement.setAttributeNode(attr);

            attr = doc.createAttribute("lives");
            attr.setValue(Integer.toString(player.getLives()));
            playerElement.setAttributeNode(attr);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(saveFileUrl));

            transformer.transform(source, result);
        } catch (ParserConfigurationException|TransformerException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Builds level from saveFile.
     */
    private void buildLevelFromSave() {
        try {
            File saveFile = new File(saveFileUrl);
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(saveFile);

            document.getDocumentElement().normalize();

            NodeList nodeList = document.getElementsByTagName("game");

            for (int i = 0; i < nodeList.getLength(); i++) {
                org.w3c.dom.Node nNode = nodeList.item(i);

                if (nNode.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
                    Element element = (Element) nNode;

                    String levelName = element.getElementsByTagName("level")
                            .item(0).getAttributes().getNamedItem("name").getNodeValue();
                    this.currentLevel = LevelFactory.buildLevel(Levels.valueOf(levelName), gameCanvasWidth, gameCanvasHeight);

                    org.w3c.dom.Node player = element.getElementsByTagName("player").item(0);
                    int playerLives = Integer.parseInt(player.getAttributes().getNamedItem("lives").getNodeValue());
                    int playerPositionX = Integer.parseInt(player.getAttributes().getNamedItem("x-position").getNodeValue());
                    int playerPositionY = Integer.parseInt(player.getAttributes().getNamedItem("y-position").getNodeValue());

                    this.currentLevel.getPlayer().setLives(playerLives);
                    this.currentLevel.getPlayer().setPosition(playerPositionX, playerPositionY);
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Checks if saveFile exists.
     * @return {@link Boolean}
     */
    public Boolean checkSaveFileExists() {
        File file = new File(saveFileUrl);
        return (file.exists() && !file.isDirectory());
    }

    /**
     * Adds {@link GameController#pauseScreen} to {@link MainController#mainPane}.
     */
    private void activatePauseScreen() {
        this.parentController.addMainPaneChild(pauseScreen);
    }

    /**
     * Adds {@link GameController#gameOverScreen} to {@link MainController#mainPane}.
     */
    private void activateGameOverScreen() {
        this.parentController.addMainPaneChild(gameOverScreen);
    }

    /**
     * Sets the parent controller, the {@link MainController}.
     * @param parentController The parent {@link MainController} controller.
     */
    void setParentController(MainController parentController) {
        this.parentController = parentController;
    }

    /**
     * Sets {@link GameController#pauseScreen}.
     * @param pauseScreen A {@link Node} object.
     */
    void setPauseScreen(Node pauseScreen) {
        this.pauseScreen = pauseScreen;
    }

    /**
     * Sets {@link GameController#gameOverScreen}.
     * @param gameOverScreen A {@link Node} object.
     */
    void setGameOverScreen(Node gameOverScreen) {
        this.gameOverScreen = gameOverScreen;
    }

    /**
     * Sets {@link GameController#gameFinishedScreen}.
     * @param gameFinishedScreen A {@link Node} object.
     */
    void setGameFinishedScreen(Node gameFinishedScreen) {
        this.gameFinishedScreen = gameFinishedScreen;
    }

    /**
     * Restart function. Sets the {@link GameController#currentLevel} to the first desired level.
     */
    public void restart() {
        currentLevel = LevelFactory.buildLevel(firstLevel, gameCanvasWidth, gameCanvasHeight);
    }
}
