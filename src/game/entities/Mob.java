package game.entities;

/**
 * <h1>Mob class.</h1>
 * The class of the mob character. Extends Character class.
 * The mobs got two {@link Integer} properties representing coordinates it should
 * move between.
 *
 * @author Emil Jensen, Kevin Ngo
 */
public class Mob extends Character {

    /**
     * The {@link Integer} representing the first move-point coordinate for the mob.
     */
    private int yPositionOne;
    /**
     * The {@link Integer} representing the second move-point coordinate for the mob.
     */
    private int yPositionTwo;

    /**
     * Mob constructor. Will super variables to Character.
     * @param positionX {@link Entity#positionX}
     * @param positionY {@link Entity#positionY}
     * @param width {@link Entity#width}
     * @param height {@link Entity#height}
     * @param yPositionOne {@link Mob#yPositionOne}
     * @param yPositionTwo {@link Mob#yPositionTwo}
     * @param mobSpeed {@link Character#characterSpeed}
     */
    public Mob(int positionX, int positionY, int width, int height, int yPositionOne, int yPositionTwo, int mobSpeed) {
        super(positionX, positionY, width, height, mobSpeed);
        this.yPositionOne = yPositionOne;
        this.yPositionTwo = yPositionTwo;
    }

    /**
     * Moves the mob between the {@link Mob#yPositionOne} and {@link Mob#yPositionTwo}.
     */
    public void moveMobBetweenPositions() {
        if ((this.positionY - characterSpeed) < this.yPositionOne) {
            moveDown();
        } else if (this.positionY > (this.yPositionTwo - characterSpeed)) {
            moveUp();
        }
    }

}
