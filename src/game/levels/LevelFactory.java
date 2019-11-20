package game.levels;

/**
 * <h1>LevelFactory class.</h1>
 * This class uses the factory design pattern to create levels identified by an enum variable.
 *
 * @author Kevin Ngo, Emil Jensen
 */
public class LevelFactory {
    public static Level buildLevel(Levels currentLevel, int gameCanvasWidth, int gameCanvasHeight) {
        switch (currentLevel) {
            case SUBWAY:
                return new SubwayLevel(gameCanvasWidth, gameCanvasHeight);
            case STREET:
                return new StreetLevel(gameCanvasWidth, gameCanvasHeight);
            case OFFICESTREET:
                return new OfficeStreetLevel(gameCanvasWidth, gameCanvasHeight);
            case LOBBY:
                return new LobbyLevel(gameCanvasWidth, gameCanvasHeight);
            case OFFICE:
                return new OfficeLevel(gameCanvasWidth, gameCanvasHeight);
            default:
                return null;
        }
    }
}
