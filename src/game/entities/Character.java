package game.entities;

/**
 * <h1>Character class.</h1>
 * The base character class. All characters should extend from this, and
 * the character class extends from the {@link Entity} class.
 *
 * @author Emil Jensen, Kevin Ngo
 */
public class Character extends Entity {

    /**
     * The {@link Integer} x-velocity of the character.
     */
    private int velocityX = 0;

    /**
     * The {@link Integer} y-velocity of the character.
     */
    private int velocityY = 0;

    /**
     * The {@link Integer} representing the character speed.
     */
    protected int characterSpeed;

    /**
     * Constructor for the Character class
     * @param positionX {@link Entity#positionX}
     * @param positionY {@link Entity#positionY}
     * @param width {@link Entity#width}
     * @param height {@link Entity#height}
     * @param characterSpeed {@link Character#characterSpeed}
     */
    Character(int positionX, int positionY, int width, int height, int characterSpeed) {
        super(positionX, positionY, width, height);
        this.characterSpeed = characterSpeed;
    }

    /**
     * Get x velocity of character
     * @return {@link Character#velocityX}
     */
    private int getVelocityX() {
        return velocityX;
    }

    /**
     * Set x velocity of character
     * @param velocityX {@link Character#velocityX}
     */
    public void setVelocityX(int velocityX) {
        this.velocityX = velocityX;
    }

    /**
     * Get y velocity of character
     * @return {@link Character#velocityY}
     */
    private int getVelocityY() {
        return velocityY;
    }

    /**
     * Set y velocity of character
     * @param velocityY {@link Character#velocityY}
     */
    public void setVelocityY(int velocityY) {
        this.velocityY = velocityY;
    }

    /**
     * Sets the current velocity of the character.
     * @param velocityX {@link Character#velocityX}
     * @param velocityY {@link Character#velocityY}
     */
    public void setVelocity(int velocityX, int velocityY) {
        this.velocityX = velocityX;
        this.velocityY = velocityY;
    }

    /**
     * Updates the character position.
     */
    public void update() {
        positionY += velocityY;
        positionX += velocityX;
    }

    /**
     * Sets {@link Character#velocityX} to minus {@link Character#characterSpeed}.
     * Also sets {@link Character#velocityY} to 0.
     */
    public void moveLeft() {
        velocityX = -characterSpeed;
        velocityY = 0;
    }

    /**
     * Sets {@link Character#velocityX} to plus {@link Character#characterSpeed}.
     * Also sets {@link Character#velocityY} to 0.
     */
    public void moveRight() {
        velocityX = characterSpeed;
        velocityY = 0;
    }

    /**
     * Sets {@link Character#velocityY} to minus {@link Character#characterSpeed}.
     * Also sets {@link Character#velocityX} to 0.
     */
    public void moveUp() {
        velocityY = -characterSpeed;
        velocityX = 0;
    }

    /**
     * Sets {@link Character#velocityY} to plus {@link Character#characterSpeed}.
     * Also sets {@link Character#velocityX} to 0.
     */
    public void moveDown() {
        velocityY = characterSpeed;
        velocityX = 0;
    }

    /**
     * Sets both {@link Character#velocityX} and {@link Character#velocityY} to 0.
     */
    public void moveHalt() {
        velocityX = 0;
        velocityY = 0;
    }
}
