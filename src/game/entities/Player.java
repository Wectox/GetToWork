package game.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 * <h1>Player class.</h1>
 * The class of the users player character. Extends Character class.
 *
 * @author Emil Jensen
 */
public class Player extends Character {

    /**
     * The {@link Integer} representing player lives.
     */
    private int lives;

    /**
     * The {@link Integer} representing the minimum player x coordinate.
     */
    private int windowBoundLeft;

    /**
     * The {@link Integer} representing the maximum player x coordinate.
     */
    private int windowBoundRight;

    /**
     * The {@link Integer} representing the minimum player y coordinate.
     */
    private int windowBoundTop;

    /**
     * The {@link Integer} representing the maximum player y coordinate.
     */
    private int windowBoundBottom;

    /**
     * Player constructor. Will super variables to Character.
     * @param positionX {@link Entity#positionX}
     * @param positionY {@link Entity#positionY}
     * @param width {@link Entity#width}
     * @param height {@link Entity#height}
     * @param playerSpeed {@link Character#characterSpeed}
     */
    public Player(int positionX, int positionY, int width, int height, int playerSpeed) {
        super(positionX, positionY, width, height, playerSpeed);
    }

    /**
     * Gets number of player lives.
     * @return {@link Player#lives}
     */
    public int getLives() {
        return lives;
    }

    /**
     * Sets number of player lives.
     * @param lives {@link Player#lives}
     */
    public void setLives(int lives) {
        this.lives = lives;
    }

    /**
     * Removes one {@link Player#lives} from the player.
     */
    public void removeLife() {
        lives -= 1;
    }

    /**
     * Draws a heart to the gameCanvas for every {@link Player#lives} using
     * {@link GraphicsContext}.
     * @param gc {@link GraphicsContext}
     */
    public void drawLives(GraphicsContext gc) {
        Image hearthImage = new Image(getClass().getResource("/images/hearth.png").toString());

        gc.setFill(Color.BLACK);
        gc.fillRect(5, 5, ((lives*20) + 5), 25);

        for (int i = 0; i < lives; i++) {
            gc.drawImage(hearthImage, (i*20) + 10,10, 15, 15);
        }
    }

    /**
     * Sets the window-bounds for the player.
     * aka. the minimum and maximum allowed player coordinates.
     * @param windowBoundLeft {@link Player#windowBoundLeft}
     * @param windowBoundRight {@link Player#windowBoundRight}
     * @param windowBoundTop {@link Player#windowBoundTop}
     * @param windowBoundBottom {@link Player#windowBoundBottom}
     */
    public void setWindowBounds(int windowBoundLeft, int windowBoundRight, int windowBoundTop, int windowBoundBottom) {
        this.windowBoundLeft = windowBoundLeft;
        this.windowBoundRight = windowBoundRight;
        this.windowBoundTop = windowBoundTop;
        this.windowBoundBottom = windowBoundBottom;
    }

    /**
     * Checks if the player is inside the left window bound.
     * @return {@link Boolean}
     */
    public boolean insideWindowLeft() {
        return this.positionX > windowBoundLeft;
    }

    /**
     * Checks if the player is inside the right window bound.
     * @return {@link Boolean}
     */
    public boolean insideWindowRight() {
        return this.positionX < windowBoundRight;
    }

    /**
     * Checks if the player is inside the top window bound.
     * @return {@link Boolean}
     */
    public boolean insideWindowTop() {
        return this.positionY > windowBoundTop;
    }

    /**
     * Checks if the player is inside the bottom window bound.
     * @return {@link Boolean}
     */
    public boolean insideWindowBottom() {
        return this.positionY < windowBoundBottom;
    }

}
