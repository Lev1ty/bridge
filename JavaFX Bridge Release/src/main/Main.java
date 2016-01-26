//INSTRUCTIONS OMITTED BUT CAN BE FOUND IN CARDS FOLDER
package main;

import javafx.application.Application;
import javafx.stage.Stage;
import logic.Bid;
import logic.Deck;
import logic.Score;

import java.io.File;

public class Main extends Application {
    public static void main(String[] args) {
        launch (args);
    }//launches start

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
        switch (MenuStage.display ( )) {//launch according to menu return
            case 2: {
                int rngdir1 = (int) (Math.random ( ) * 100 % 4);//get direction
                Deck masterDeck = new Deck ( );//get complete deck
                Deck players[] = new Deck[4];//initialize players' hands
                for (int i = 0; i < 4; i++) players[i] = new Deck (i, masterDeck);//initialize players' hands

                //initializes stages
                new DeckStage ( );
                new BidStage ( );
                Stage stage = new Stage ( );//get new stage
                stage.setOpacity (0.75);//opacity is .75

                new BidStage (masterDeck, players, stage, new Stage(), rngdir1, 1, 7, 0, 4);//start bidding

                break;
            }
            default:
                break;
        }
    }
}
