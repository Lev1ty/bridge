package main;

import helpers.AlertBox;
import helpers.ConfirmBox;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import logic.Auction;
import logic.Bid;
import logic.Deck;

/**
 * Created by Adam on 1/7/2016.
 */
public class BidStage {
    public static Auction auction;//container that stores bids
    private static boolean show;//if true show screen, prevent reopening to prevent flickering

    public BidStage() {
        initAuction ( );
    }//default constructor initializes static fields

    public BidStage(Deck masterDeck, DeckStage deckStage, Deck[] decksDeckStage, Stage stage, Stage stage1,
                    String sdirection, int startLevel, int endLevel, int startSuit, int endSuit) {
        start (masterDeck, deckStage, decksDeckStage, stage, stage1,
                Bid.sDirectiontonDirection (sdirection), sdirection, startLevel, endLevel, startSuit, endSuit);
        BidStage.show = false;
    }

    public BidStage(Deck masterDeck, Deck[] decksDeckStage, Stage stage, Stage stage1, int ndirection,
                    int startLevel, int endLevel, int startSuit, int endSuit) {
        new BidStage (masterDeck, new DeckStage (decksDeckStage, ndirection, true, true), decksDeckStage, stage, stage1,
                Bid.nDirectiontosDirection (ndirection), startLevel, endLevel, startSuit, endSuit);
        BidStage.show = false;
        DeckStage.show = false;
    }

    public static void initAuction() {
        BidStage.show = true;
        auction = new Auction ( );
    }

    private static void start(Deck masterDeck, DeckStage deckStage, Deck[] decksDeckStage, Stage stage, Stage stage1,
                              int ndirection, String sdirection, int startLevel, int endLevel, int startSuit, int endSuit) {
        GridPane gridPane = new GridPane ( );
        gridPane.getChildren ( ).addAll (getGridePane (masterDeck, deckStage, decksDeckStage, stage, stage1, ndirection,
                10, startLevel, endLevel, startSuit, endSuit, 5, 10, 5, 10));
        Scene primaryScene = new Scene (gridPane);
        stage.setTitle ("Bid from " + sdirection);
        stage.setScene (primaryScene);
        stage.setOnCloseRequest (event -> {
            event.consume ( );
            if (ConfirmBox.display ("Confirm", "End Auction Prematurely?")) {
                stage.close ( );
                stage1.close ( );
            }
        });
        GridPane gridPane1 = new GridPane ( );
        gridPane1.setPadding (new Insets (10, 10, 10, 10));
        Label north = getLabel ("North");
        Label east = getLabel ("East");
        Label south = getLabel ("South");
        Label west = getLabel ("West");
        gridPane1.add (north, 0, 0);
        gridPane1.add (east, 1, 0);
        gridPane1.add (south, 2, 0);
        gridPane1.add (west, 3, 0);
        int row = 1;
        for (Bid bid : auction.auction)
            gridPane1.add (translateToLabel (bid), bid.ndirection, (bid.ndirection == 3 ? row++ : row));
        Scene secondaryScene = new Scene (gridPane1);
        secondaryScene.setFill (Color.TRANSPARENT);
        stage1.setScene (secondaryScene);
        stage1.setTitle ("Bid History");
        if (show) {
            stage1.initStyle (StageStyle.TRANSPARENT);
            stage1.setAlwaysOnTop (true);
            stage1.show ( );
            stage.setResizable (false);
            stage.setAlwaysOnTop (true);
            stage.show ( );
        }
    }

    private static Label getLabel(String name) {
        Label label = new Label (name);
        label.setPadding (new Insets (10, 10, 10, 10));
        label.setFont (Font.font ("Verdana", 20));
        return label;
    }

    private static Label translateToLabel(Bid bid) {
        return getLabel (!bid.slevel.equals ("Aux") ? bid.slevel + bid.ssuit : bid.ssuit);
    }

    private static void eventHandler(Deck masterDeck, DeckStage deckStage, Deck[] decksDeckStage, Stage stage, Stage stage1, int ndirection, String name) {
        Bid bid = new Bid (name, ndirection);
        if (ConfirmBox.display ("Confirm",
                "Is " + bid.svalue + " your Final Bid?")) {
            switch (name.charAt (0)) {
                case 'P':
                    ++auction.npass;
                    break;
                case 'D':
                    auction.x = true;
                    auction.npass = 0;
                    break;
                case 'R':
                    auction.xx = true;
                    auction.npass = 0;
                    break;
                default:
                    break;
            }
            auction.push_back (bid.nvalue, ndirection);
            ++ndirection;
            ndirection %= 4;
            ++auction.nbid;
            if ((auction.nbid == 4 && auction.npass == 4)) {
                stage.close ( );
                stage1.close ( );
                DeckStage.stage.close ( );
                AlertBox.display ("Auction", "Auction Passed Out.");
            } else if (auction.bcontract && auction.npass >= 3 && auction.nbid >= 4) {
                stage.close ( );
                stage1.close ( );
                DeckStage.stage.close ( );
                DeckStage.show = true;
                DeckStage.initDeckStage ( );
                new DeckStage (decksDeckStage, auction.getContractBid ( ).ndirection + 1,
                        auction.getContractBid ( ).ndirection + 2, auction.getContractBid ( ), false);
            } else {
                if (bid.nvalue >= 35) {
                    Bid lastContractBid = new Bid ( );
                    boolean found = false;
                    for (int i = auction.auction.size ( ) - 1; i >= 0; i--)
                        if (auction.auction.get (i).nvalue < 35) {
                            lastContractBid = auction.auction.get (i);
                            found = true;
                            break;
                        }
                    if (found) {
                        new BidStage (masterDeck, decksDeckStage, stage, stage1, ndirection, lastContractBid.nlevel + 1, 7, lastContractBid.nsuit + 1, 4);
                    } else {
                        new BidStage (masterDeck, decksDeckStage, stage, stage1, ndirection, 1, 7, 0, 4);
                    }
                } else {
                    new BidStage (masterDeck, decksDeckStage, stage, stage1, ndirection, bid.nlevel + 1, 7, bid.nsuit + 1, 4);
                }
            }
        }
    }

    private static GridPane getGridePane(Deck masterDeck, DeckStage deckStage, Deck[] decksDeckStage, Stage stage, Stage stage1, int ndirection,
                                         int padding, int startLevel, int endLevel, int startSuit, int endSuit,
                                         int top, int right, int bottom, int left) {
        GridPane gridPane = new GridPane ( );
        gridPane.setPadding (new Insets (top, right, bottom, left));
        Button auxBid[] = new Button[3];
        auxBid[0] = new Button ("Pass");
        auxBid[0].setOnAction (event -> eventHandler (masterDeck, deckStage, decksDeckStage, stage, stage1, ndirection, "Pass"));
        auxBid[1] = new Button ("Double");
        auxBid[1].setOnAction (event -> {
            if (auction.bcontract)
                eventHandler (masterDeck, deckStage, decksDeckStage, stage, stage1, ndirection, "Double");
            else AlertBox.display ("Double", "Cannot double before a contract bid. (e.g. 1NT)");
        });
        auxBid[2] = new Button ("Redouble");
        auxBid[2].setOnAction (event -> {
            if (auction.x) eventHandler (masterDeck, deckStage, decksDeckStage, stage, stage1, ndirection, "Redouble");
            else AlertBox.display ("Redouble", "Cannot redouble before double.");
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
                eventHandler (masterDeck, deckStage, decksDeckStage, stage, stage1, ndirection, finalName);
            });
        }
        //endregion
        if (hBoxArr.length >= 2) {
            hBoxArr[1] = new HBox (padding);
            hBoxArr[1].setPadding (new Insets (top, right, bottom, left));
            hBoxArr[1].getChildren ( ).addAll (buttonFirstRow);
            GridPane.setConstraints (hBoxArr[1], 0, 1);
            for (int i = startLevel + 1, j = 2; i <= endLevel; i++, j++) {
                hBoxArr[j] = getBidRow (masterDeck, deckStage, decksDeckStage, stage, stage1,
                        ndirection, i, padding, top, right, bottom, left);
                GridPane.setConstraints (hBoxArr[j], 0, j + 1);
            }
        }
        gridPane.getChildren ( ).addAll (hBoxArr);
        return gridPane;
    }

    private static HBox getBidRow(Deck masterDeck, DeckStage deckStage, Deck[] decksDeckStage,
                                  Stage stage, Stage stage1, int ndirection, int level, int padding,
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
                eventHandler (masterDeck, deckStage, decksDeckStage, stage, stage1, ndirection, finalName);
            });
        }
        hBox.getChildren ( ).addAll (arr);
        return hBox;
    }
}
