package game;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Main extends Application {

    private static final int WIDTH = 600;
    private static final int HEIGHT = 480;

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Get to Work!");
        stage.setWidth(WIDTH + 6);
        stage.setHeight(HEIGHT + 29);
        stage.setResizable(false);

        FXMLLoader mainLoader = new FXMLLoader();
        mainLoader.setLocation(getClass().getResource("/views/main.fxml"));
        Pane mainPane = mainLoader.load();
        Scene mainScene = new Scene(mainPane, WIDTH, HEIGHT);

        stage.setScene(mainScene);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
