package main;

import javafx.application.Application;
import javafx.stage.Stage;
import logic.Deck;

public class Main extends Application {
    public static void main(String[] args) {
        launch (args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        int rngdir1 = (int) (Math.random ( ) * 100 % 4);
        Deck masterDeck = new Deck();
        Deck players[] = new Deck[4];
        for (int i = 0; i < 4; i++) players[i] = new Deck (i, masterDeck);
        DeckStage.initDeckStage (BidStage.auction);
        BidStage.initAuction ();
        BidStage bidStage = new BidStage (players, rngdir1, 1, 7, 0, 4);
    }
}
