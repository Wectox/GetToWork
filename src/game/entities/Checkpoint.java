package game.entities;

/**
 * <h1>Checkpoint class.</h1>
 * Checkpoint is used to take the player to the next level.
 * Just another entity.
 *
 * @author Emil Jensen
 */
public class Checkpoint extends Entity {

    /**
     * Checkpoint constructor.
     * @param positionX {@link Entity#positionX}
     * @param positionY {@link Entity#positionY}
     * @param width {@link Entity#width}
     * @param height {@link Entity#height}
     */
    public Checkpoint(int positionX, int positionY, int width, int height) {
        super(positionX, positionY, width, height);
    }
}
