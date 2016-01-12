package main;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch (args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        int rngdir1 = (int) (Math.random ( ) * 100 % 4);
        BidStage bidStage = new BidStage (rngdir1, 1, 7, 0, 4);
    }
}
