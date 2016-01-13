package main;

import javafx.application.Application;
import javafx.stage.Stage;
import logic.Deck;
import logic.ImageWrapper;

public class Main extends Application {
    public static void main(String[] args) {
        launch (args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        int rngdir1 = (int) (Math.random ( ) * 100 % 4);
        new BidStage (rngdir1, 1, 7, 0, 4);
        new DeckStage ();
    }
}
