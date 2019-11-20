package game.levels;

import game.entities.Checkpoint;
import game.entities.Mob;
import game.entities.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>Level class.</h1>
 * All level maps should be extended from this class.
 * Level is used to handle inputActions, update players and mobs, detect player collisions etc.
 *
 * @author Kevin Ngo, Emil Jensen
 */
public class Level {

    private Image backgroundImage;
    private Player player;
    private List<Mob> mobs;
    private Checkpoint checkpoint;
    private Levels nextLevel;

    void setLevelVariables(Image backgroundImage, Player player, List<Mob> mobs, Checkpoint checkpoint, Levels nextLevel) {
        this.backgroundImage = backgroundImage;
        this.player = player;
        this.mobs = mobs;
        this.checkpoint = checkpoint;
        this.nextLevel = nextLevel;
    }

    public Player getPlayer() {
        return player;
    }

    public List<Mob> getMobs() {
        return mobs;
    }

    public void drawBackgroundImage(GraphicsContext gc) {
        gc.drawImage(backgroundImage, 0, 0);
    }

    public void updatePlayer() {
        player.update();
    }

    public void drawPlayer(GraphicsContext gc) {
        player.drawEntity(gc);
    }

    public void drawLives(GraphicsContext gc) {
        player.drawLives(gc);
    }

    public boolean playerIsDead() {
        return player.getLives() == 0;
    }

    public void updateMobs() {
        for (Mob mob : mobs) {
            mob.update();
        }
    }

    public void moveMobs() {
        for (Mob mob : mobs) {
            mob.moveMobBetweenPositions();
        }
    }

    public void drawMobs(GraphicsContext gc) {
        for (Mob mob : mobs) {
            mob.drawEntity(gc);
        }
    }

    public void checkPlayerMobCollision() {
        for (Mob mob : mobs) {
            if (player.checkEntityCollision(mob)) {
                player.setPosition(140, 200);
                player.removeLife();
            }
        }
    }

    public void drawCheckpoint(GraphicsContext gc) {
        checkpoint.drawEntity(gc);
    }

    public boolean checkPlayerCheckpointCollision() {
        return player.checkEntityCollision(checkpoint);
    }

    public void handleInputActions(ArrayList<String> input) {
        if (input.contains("LEFT") && player.insideWindowLeft()) {
            player.moveLeft();
        } else if (input.contains("RIGHT") && player.insideWindowRight()) {
            player.moveRight();
        } else if (input.contains("UP") && player.insideWindowTop()) {
            player.moveUp();
        } else if (input.contains("DOWN") && player.insideWindowBottom()) {
            player.moveDown();
        } else {
            player.moveHalt();
        }
    }

    public Levels getNextLevel() {
        return nextLevel;
    }
}
