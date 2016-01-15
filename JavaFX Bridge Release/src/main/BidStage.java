package main;

import helpers.AlertBox;
import helpers.ConfirmBox;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import logic.Auction;
import logic.Bid;
import logic.Deck;

/**
 * Created by Adam on 1/7/2016.
 */
public class BidStage {
    public static Auction auction;

    public BidStage(Deck[] decksDeckStage, String sdirection, int startLevel, int endLevel, int startSuit, int endSuit) {
        Stage stage = new Stage ( );
        start (decksDeckStage, stage, Bid.sDirectiontonDirection (sdirection), sdirection, startLevel, endLevel, startSuit, endSuit);
    }

    public BidStage(Deck[] decksDeckStage, int ndirection, int startLevel, int endLevel, int startSuit, int endSuit) {
        new DeckStage(decksDeckStage,ndirection);
        new BidStage (decksDeckStage, Bid.nDirectiontosDirection (ndirection), startLevel, endLevel, startSuit, endSuit);
    }

    public static void initAuction() {
        auction = new Auction();
    }

    private void start(Deck[] decksDeckStage, Stage stage, int ndirection,
                       String sdirection, int startLevel, int endLevel, int startSuit, int endSuit) {
        GridPane gridPane = new GridPane ( );
        gridPane.getChildren ( ).addAll (getGridePane (decksDeckStage,stage, ndirection,
                10, startLevel, endLevel, startSuit, endSuit, 5, 10, 5, 10));
        Scene primaryScene = new Scene (gridPane);
        stage.setTitle ("Bid from " + sdirection);
        stage.setScene (primaryScene);
        stage.setResizable (false);
        stage.setFullScreen (false);
        stage.setFullScreenExitHint ("Bid is now full screen. Exit full screen (ESC).");
        stage.setOnCloseRequest (event -> {
            event.consume ( );
            if (ConfirmBox.display ("Confirm", "End Auction Prematurely?")) stage.close ( );
        });
        stage.show ( );
    }

//    private void start(int ndirection, int startLevel, int endLevel, int startSuit, int endSuit) {
//        GridPane gridPane = new GridPane ( );
//        gridPane.getChildren ( ).addAll (getGridePane (10, startLevel, endLevel, startSuit, endSuit, 5, 10, 5, 10));
//        Scene primaryScene = new Scene (gridPane);
//        stage = new Stage ( );
//        stage.setTitle ("Bid from " + sdirection);
//        stage.setScene (primaryScene);
//        stage.setResizable (false);
//        stage.setFullScreen (false);
//        stage.setFullScreenExitHint ("Bid is now full screen. Exit full screen (ESC).");
//        stage.setOnCloseRequest (event -> {
//            event.consume ( );
//            if (ConfirmBox.display ("Confirm", "End Auction Prematurely?")) stage.close ( );
//        });
//        stage.show ( );
//    }

    private void eventHandler(Deck[] decksDeckStage, Stage stage, int ndirection, String name) {
        Bid bid = new Bid (name, ndirection);
        if (ConfirmBox.display ("Confirm",
                "Is " + bid.svalue + " your Final Bid?")) {
//            bid.Print ( );
            auction.push_back (bid.nvalue, ndirection);
            stage.close ( );
            ++ndirection;
            ndirection %= 4;
            ++auction.nbid;
            if ((auction.nbid == 4 && auction.npass == 4)){
                stage.close ();
            }
            if (auction.bcontract && auction.npass >= 3 && auction.nbid >= 4) {
                stage.close ( );
                new DeckStage(decksDeckStage, auction.getContractBid ().ndirection + 1, auction.getContractBid ().ndirection + 2);
            } else {
                if (bid.nvalue >= 35) {
                    Bid lastCotnractBid = new Bid ( );
                    boolean found = false;
                    for (int i = auction.auction.size ( ) - 1; i >= 0; i--)
                        if (auction.auction.get (i).nvalue < 35) {
                            lastCotnractBid = auction.auction.get (i);
                            found = true;
                            break;
                        }
                    if (found) {
                        new BidStage (decksDeckStage,ndirection, lastCotnractBid.nlevel + 1, 7, lastCotnractBid.nsuit + 1, 4);
                    } else {
                        new BidStage (decksDeckStage,ndirection, 1, 7, 0, 4);
                    }
                } else {
                    new BidStage (decksDeckStage,ndirection, bid.nlevel + 1, 7, bid.nsuit + 1, 4);
                }
            }
        }
    }

    private GridPane getGridePane(Deck[] decksDeckStage, Stage stage, int ndirection,
                                  int padding, int startLevel, int endLevel, int startSuit, int endSuit,
                                  int top, int right, int bottom, int left) {
        GridPane gridPane = new GridPane ( );
        gridPane.setPadding (new Insets (top, right, bottom, left));
        Button auxBid[] = new Button[3];
        auxBid[0] = new Button ("Pass");
        auxBid[0].setOnAction (event -> {
            ++auction.npass;
            eventHandler (decksDeckStage,stage, ndirection, "Pass");
        });
        auxBid[1] = new Button ("Double");
        auxBid[1].setOnAction (event -> {
            auction.x = true;
            auction.npass = 0;
            eventHandler (decksDeckStage,stage, ndirection, "Double");
        });
        auxBid[2] = new Button ("Redouble");
        auxBid[2].setOnAction (event -> {
            if (!auction.x) AlertBox.display ("Redouble", "Cannot redouble before double.");
            else {
                auction.xx = true;
                auction.npass = 0;
                eventHandler (decksDeckStage,stage, ndirection, "Redouble");
            }
        });
        HBox hBoxArr[] = new HBox[endLevel - startLevel + 2];
        hBoxArr[0] = new HBox (1.825 * padding);
        hBoxArr[0].getChildren ( ).addAll (auxBid);
        hBoxArr[0].setPadding (new Insets (top, right, bottom, left));
        GridPane.setConstraints (hBoxArr[0], 0, 0);
        //region Modified getBidRow
        Button buttonFirstRow[] = new Button[endSuit - startSuit + 1];
        for (int i = startSuit, j = 0; i <= endSuit; i++, j++) {
            String name = String.valueOf (startLevel);
            switch (i) {
                case 0:
                    name += "C";
                    break;
                case 1:
                    name += "D";
                    break;
                case 2:
                    name += "H";
                    break;
                case 3:
                    name += "S";
                    break;
                case 4:
                    name += "NT";
                    break;
                default:
                    break;
            }
            buttonFirstRow[j] = new Button (name);
            final String finalName = name;
            buttonFirstRow[j].setOnAction (event -> {
                auction.npass = 0;
                auction.bcontract = true;
                eventHandler (decksDeckStage,stage, ndirection, finalName);
            });
        }
        //endregion
        if (hBoxArr.length >= 2) {
            hBoxArr[1] = new HBox (padding);
            hBoxArr[1].setPadding (new Insets (top, right, bottom, left));
            hBoxArr[1].getChildren ( ).addAll (buttonFirstRow);
            GridPane.setConstraints (hBoxArr[1], 0, 1);
            for (int i = startLevel + 1, j = 2; i <= endLevel; i++, j++) {
                hBoxArr[j] = getBidRow (decksDeckStage,stage, ndirection, i, padding, top, right, bottom, left);
                GridPane.setConstraints (hBoxArr[j], 0, j + 1);
            }
        }
        gridPane.getChildren ( ).addAll (hBoxArr);
        return gridPane;
    }

    private HBox getBidRow(Deck[] decksDeckStage, Stage stage, int ndirection,
                           int level, int padding,
                           int top, int right, int bottom, int left) {
        Button arr[] = new Button[5];
        HBox hBox = new HBox (padding);
        hBox.setPadding (new Insets (top, right, bottom, left));
        for (int i = 0; i < 5; i++) {
            String name = String.valueOf (level);
            switch (i) {
                case 0:
                    name += "C";
                    break;
                case 1:
                    name += "D";
                    break;
                case 2:
                    name += "H";
                    break;
                case 3:
                    name += "S";
                    break;
                case 4:
                    name += "NT";
                    break;
                default:
                    break;
            }
            arr[i] = new Button (name);
            final String finalName = name;
            arr[i].setOnAction (event -> {
                auction.npass = 0;
                auction.bcontract = true;
                eventHandler (decksDeckStage,stage, ndirection, finalName);
            });
        }
        hBox.getChildren ( ).addAll (arr);
        return hBox;
    }
}
