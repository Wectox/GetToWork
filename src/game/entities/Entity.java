package game.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * <h1>Entity class.</h1>
 * All entities such as Mobs, Players, Map-Objects, etc. should
 * be extended from this class.
 *
 * @author Emil Jensen
 */
public class Entity {

    /**
     * The {@link Image} image of the entity.
     */
    private Image image;

    /**
     * The {@link Integer} width of the entity.
     */
    private int width;

    /**
     * The {@link Integer} height of the entity.
     */
    private int height;

    /**
     * The {@link Integer} x-position of the entity.
     */
    protected int positionX;

    /**
     * The {@link Integer} y-position of the entity.
     */
    protected int positionY;

    /**
     * Entity constructor.
     * @param positionX {@link Entity#positionX}
     * @param positionY {@link Entity#positionY}
     * @param width {@link Entity#width}
     * @param height {@link Entity#height}
     */
    Entity(int positionX, int positionY, int width, int height) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.width = width;
        this.height = height;
    }

    /**
     * Sets the Image for the entity.
     * @param entityImage {@link Entity#image}
     * @see Image
     */
    public void setImage(Image entityImage) {
        this.image = entityImage;
    }

    /**
     * Gets the entity width.
     * @return {@link Entity#width}
     */
    private int getWidth() {
        return width;
    }

    /**
     * Sets the entity width.
     * @param width {@link Entity#width}
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Gets the entity height.
     * @return {@link Entity#height}
     */
    private int getHeight() {
        return height;
    }

    /**
     * Sets the entity height.
     * @param height {@link Entity#height}
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Gets the entity x-position.
     * @return {@link Entity#positionX}
     */
    public int getPositionX() {
        return positionX;
    }

    /**
     * Sets the entity x-position.
     * @param positionX {@link Entity#positionX}
     */
    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    /**
     * Gets the entity y-position.
     * @return {@link Entity#positionY}
     */
    public int getPositionY() {
        return positionY;
    }

    /**
     * Sets the entity y-position.
     * @param positionY {@link Entity#positionY}
     */
    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    /**
     * Sets both the x- and y-position for the entity.
     * @param positionX {@link Entity#positionX}
     * @param positionY {@link Entity#positionY}
     */
    public void setPosition(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    /**
     * Draws the entity to a canvas using {@link GraphicsContext}.
     * @param gc {@link GraphicsContext}
     */
    public void drawEntity(GraphicsContext gc){
        gc.drawImage(image, positionX, positionY);
    }

    /**
     * Checks if an entity intersects with one other entity. Returns true if intersects, false if not.
     * @param entity {@link Entity}
     * @return {@link Boolean}
     */
    public boolean checkEntityCollision(Entity entity) {
        return (this.positionX < entity.getPositionX() + entity.getWidth() &&
                this.positionX + this.width > entity.getPositionX() &&
                this.positionY < entity.getPositionY() + entity.getHeight() &&
                this.positionY + this.height > entity.getPositionY());
    }
}
