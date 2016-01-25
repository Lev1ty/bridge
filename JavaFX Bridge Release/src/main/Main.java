package main;

import javafx.application.Application;
import javafx.stage.Stage;
import logic.Bid;
import logic.Deck;
import logic.Score;

public class Main extends Application {
    public static void main(String[] args) {
        launch (args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
//        region Score testing
//        Bid bid = new Bid ( );
//        bid.slevel = "2";
//        bid.nsuit = 1;
//        bid.x = false;
//        bid.xx = false;
//        Score score = new Score (bid, 8);
//        ScoreStage.display (score.calculate (),bid);
//        endregion
        switch (MenuStage.display ( )) {
            case 3:

            case 2: {
                int rngdir1 = (int) (Math.random ( ) * 100 % 4);
                Deck masterDeck = new Deck ( );
                Deck players[] = new Deck[4];
                for (int i = 0; i < 4; i++) players[i] = new Deck (i, masterDeck);
                new DeckStage ( );
                new BidStage ( );
                Stage stage = new Stage ( );
                stage.setOpacity (0.75);
                BidStage bidStage = new BidStage (masterDeck, players, stage, new Stage(), rngdir1, 1, 7, 0, 4);
                break;
            }
            default:
                break;
        }
    }
}
