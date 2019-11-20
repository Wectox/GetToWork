package game.levels;

import game.entities.Checkpoint;
import game.entities.Mob;
import game.entities.Player;
import javafx.scene.image.Image;

import java.util.Arrays;
import java.util.List;

/**
 * <h1>OfficeLevel class.</h1>
 * The class of the level Office. Extends Level class.
 * This class builds and sets variables for player, mobs and checkpoint.
 *
 * @author Kevin Ngo
 */
class OfficeLevel extends Level {
    OfficeLevel(int gameCanvasWidth, int gameCanvasHeight) {
        // General Level variables
        final Levels nextLevel = null;

        // General character variables
        final int characterWidth = 23;
        final int characterHeight = 57;

        // Player variables
        final int playerSpeed = 5;
        final int playerLives = 3;
        final int playerStartPositionX = 10;
        final int playerStartPositionY = 180;

        // Level variables
        final int windowBoundLeft = 0;
        final int windowBoundRight = gameCanvasWidth - characterWidth;
        final int windowBoundTop = 175;
        final int windowBoundBottom = gameCanvasHeight - characterHeight;

        // Mob variables
        final int mobSpeed = 3;
        final int mobOneStartPositionX = 165;
        final int mobOneStartPositionY = 50;
        final int mobTwoStartPositionX = 400;
        final int mobTwoStartPositionY = 200;
        final int mobThreeStartPositionX = 300;
        final int mobThreeStartPositionY = 300;

        final int mobOnePositionYOne = 80;
        final int mobOnePositionYTwo = 400;
        final int mobTwoPositionYOne = 200;
        final int mobTwoPositionYTwo = 400;
        final int mobThreePositionYOne = 300;
        final int mobThreePositionYTwo = 400;

        // Checkpoint variables;
        final int checkpointWidth = 80;
        final int checkpointHeight = 50;
        final int checkpointPositionX = 500;
        final int checkpointPositionY = (gameCanvasHeight / 2) - (checkpointHeight / 2);

        // Build default level
        Image backgroundImage = new Image(getClass().getResource("/images/levelFive.png").toString());

        // Build player
        Player player = new Player(playerStartPositionX, playerStartPositionY, characterWidth, characterHeight, playerSpeed);
        player.setImage(new Image(getClass().getResource("/images/player.png").toString()));
        player.setWindowBounds(windowBoundLeft, windowBoundRight, windowBoundTop, windowBoundBottom);
        player.setLives(playerLives);

        // Build mobs
        Mob mob_one = new Mob(mobOneStartPositionX, mobOneStartPositionY, characterWidth, characterHeight, mobOnePositionYOne, mobOnePositionYTwo, mobSpeed);
        Mob mob_two = new Mob(mobTwoStartPositionX, mobTwoStartPositionY, characterWidth, characterHeight, mobTwoPositionYOne, mobTwoPositionYTwo, mobSpeed);
        Mob mob_three = new Mob(mobThreeStartPositionX, mobThreeStartPositionY, characterWidth, characterHeight, mobThreePositionYOne, mobThreePositionYTwo, mobSpeed);
        mob_one.setImage(new Image(getClass().getResource("/images/enemy.png").toString()));
        mob_two.setImage(new Image(getClass().getResource("/images/enemy.png").toString()));
        mob_three.setImage(new Image(getClass().getResource("/images/enemy.png").toString()));

        // Build checkpoint
        Checkpoint checkpoint = new Checkpoint(checkpointPositionX, checkpointPositionY, checkpointWidth, checkpointHeight);
        checkpoint.setImage(new Image(getClass().getResource("/images/finish.png").toString()));

        List<Mob> mobs = Arrays.asList(mob_one, mob_two, mob_three);

        this.setLevelVariables(backgroundImage, player, mobs, checkpoint, nextLevel);
    }
}
