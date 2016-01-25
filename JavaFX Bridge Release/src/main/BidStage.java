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
                Bid.sDirectiontonDirection (sdirection), sdirection, startLevel, endLevel, startSuit, endSuit);//call main method
        BidStage.show = false;//set show to false to prevent flickering
    }

    public BidStage(Deck masterDeck, Deck[] decksDeckStage, Stage stage, Stage stage1, int ndirection,
                    int startLevel, int endLevel, int startSuit, int endSuit) {
        new BidStage (masterDeck, new DeckStage (decksDeckStage, ndirection, true, true), decksDeckStage, stage, stage1,
                Bid.nDirectiontosDirection (ndirection), startLevel, endLevel, startSuit, endSuit);//call main method
        BidStage.show = false;//set show to false
        DeckStage.show = false;//deckstage set show to false
    }

    public static void initAuction() {
        BidStage.show = true;//show the stage
        auction = new Auction ( );//initialize auction
    }

    private static void start(Deck masterDeck, DeckStage deckStage, Deck[] decksDeckStage, Stage stage, Stage stage1,
                              int ndirection, String sdirection, int startLevel, int endLevel, int startSuit, int endSuit) {
        GridPane gridPane = new GridPane ( );//new layout
        gridPane.getChildren ( ).addAll (getGridePane (masterDeck, deckStage, decksDeckStage, stage, stage1, ndirection,
                10, startLevel, endLevel, startSuit, endSuit, 5, 10, 5, 10));
        Scene primaryScene = new Scene (gridPane);//set scene to layout
        stage.setTitle ("Bid from " + sdirection);//set title
        stage.setScene (primaryScene);//set stage to scene
        stage.setOnCloseRequest (event -> {
            event.consume ( );//override close
            if (ConfirmBox.display ("Confirm", "End Auction Prematurely?")) {//if confirm is true
                stage.close ( );
                stage1.close ( );
            }
        });
        GridPane gridPane1 = new GridPane ( );//layout for record of bids
        gridPane1.setPadding (new Insets (10, 10, 10, 10));//border spacing
        //labels
        Label north = getLabel ("North");
        Label east = getLabel ("East");
        Label south = getLabel ("South");
        Label west = getLabel ("West");
        //set label pos
        gridPane1.add (north, 0, 0);
        gridPane1.add (east, 1, 0);
        gridPane1.add (south, 2, 0);
        gridPane1.add (west, 3, 0);
        int row = 1;//initialize row counter
        for (Bid bid : auction.auction)
            gridPane1.add (translateToLabel (bid), bid.ndirection, (bid.ndirection == 3 ? row++ : row));//if last col, goto next row
        Scene secondaryScene = new Scene (gridPane1);//set scene to layout
        secondaryScene.setFill (Color.TRANSPARENT);//set scene to be transparent
        stage1.setScene (secondaryScene);//set stage to scene
        stage1.setTitle ("Bid History");//set title
        if (show) {
            stage1.initStyle (StageStyle.TRANSPARENT);//set stage to transparent
            stage1.setAlwaysOnTop (true);//set always on top
            stage1.show ( );//open window
            stage.setResizable (false);//not resizable
            stage.setAlwaysOnTop (true);//set on top
            stage.show ( );//open window
        }
    }

    private static Label getLabel(String name) {//get label, customize to prigram standards
        Label label = new Label (name);//set label to name
        label.setPadding (new Insets (10, 10, 10, 10));//set border spacing
        label.setFont (Font.font ("Verdana", 20));//set font and size
        return label;
    }

    private static Label translateToLabel(Bid bid) {//bid to label
        //check if pass, double, or redouble, print adaptively
        return getLabel (!bid.slevel.equals ("Aux") ? bid.slevel + bid.ssuit : bid.ssuit);
    }

    private static void eventHandler(Deck masterDeck, DeckStage deckStage, Deck[] decksDeckStage, Stage stage, Stage stage1, int ndirection, String name) {
        Bid bid = new Bid (name, ndirection);//push back bid and the direction of player who bid it
        if (ConfirmBox.display ("Confirm",
                "Is " + bid.svalue + " your Final Bid?")) {//confirm
            switch (name.charAt (0)) {
                case 'P':
                    ++auction.npass;//increment pass counter
                    break;
                case 'D':
                    auction.x = true;//double switch on
                    auction.npass = 0;//reset pass counter
                    break;
                case 'R':
                    auction.xx = true;//redouble switch on
                    auction.npass = 0;//reset pass counter
                    break;
                default:
                    break;
            }
            auction.push_back (bid.nvalue, ndirection);//insert bid into auction container
            ++ndirection;//move to next container
            ndirection %= 4;//keep counter in range
            ++auction.nbid;//add counter to number of bids
            if ((auction.nbid == 4 && auction.npass == 4)) {//pass out
                //close windows
                stage.close ( );
                stage1.close ( );
                DeckStage.stage.close ( );
                AlertBox.display ("Auction", "Auction Passed Out.");//alert players
            } else if (auction.bcontract && auction.npass >= 3 && auction.nbid >= 4) {
                //close windows
                stage.close ( );
                stage1.close ( );
                DeckStage.stage.close ( );
                //prepare deck stage
                DeckStage.show = true;
                DeckStage.initDeckStage ( );
                //call deck stage
                new DeckStage (decksDeckStage, auction.getContractBid ( ).ndirection + 1,
                        auction.getContractBid ( ).ndirection + 2, auction.getContractBid ( ), false);
            } else {
                if (bid.nvalue >= 35) {//aux bid
                    Bid lastContractBid = new Bid ( );
                    boolean found = false;
                    for (int i = auction.auction.size ( ) - 1; i >= 0; i--)//find last contract bid
                        if (auction.auction.get (i).nvalue < 35) {
                            lastContractBid = auction.auction.get (i);
                            found = true;
                            break;
                        }
                    if (found) {//set limitations to last contract bid
                        new BidStage (masterDeck, decksDeckStage, stage, stage1, ndirection, lastContractBid.nlevel + 1, 7, lastContractBid.nsuit + 1, 4);
                    } else {//set limitations to default bid due to lack of contract bid
                        new BidStage (masterDeck, decksDeckStage, stage, stage1, ndirection, 1, 7, 0, 4);
                    }
                } else {//initialize next bid stage with updated values
                    new BidStage (masterDeck, decksDeckStage, stage, stage1, ndirection, bid.nlevel + 1, 7, bid.nsuit + 1, 4);
                }
            }
        }
    }

    private static GridPane getGridePane(Deck masterDeck, DeckStage deckStage, Deck[] decksDeckStage, Stage stage, Stage stage1, int ndirection,
                                         int padding, int startLevel, int endLevel, int startSuit, int endSuit,
                                         int top, int right, int bottom, int left) {
        GridPane gridPane = new GridPane ( );//new grid pane
        gridPane.setPadding (new Insets (top, right, bottom, left));//set border spacing
        Button auxBid[] = new Button[3];//initialize auxillary bid buttons
        //new aux buttons and set event handlers corresponding to those
        auxBid[0] = new Button ("Pass");
        auxBid[0].setOnAction (event -> eventHandler (masterDeck, deckStage, decksDeckStage, stage, stage1, ndirection, "Pass"));
        auxBid[1] = new Button ("Double");
        auxBid[1].setOnAction (event -> {
            if (auction.bcontract)//range checking
                eventHandler (masterDeck, deckStage, decksDeckStage, stage, stage1, ndirection, "Double");
            else AlertBox.display ("Double", "Cannot double before a contract bid. (e.g. 1NT)");
        });
        auxBid[2] = new Button ("Redouble");
        auxBid[2].setOnAction (event -> {
            if (auction.x)//range checking
                eventHandler (masterDeck, deckStage, decksDeckStage, stage, stage1, ndirection, "Redouble");
            else AlertBox.display ("Redouble", "Cannot redouble before double.");
        });
        HBox hBoxArr[] = new HBox[endLevel - startLevel + 2];//adjust to limitations on bidding
        hBoxArr[0] = new HBox (1.825 * padding);//horizontal spacing
        hBoxArr[0].getChildren ( ).addAll (auxBid);//add aux bid
        hBoxArr[0].setPadding (new Insets (top, right, bottom, left));//set padding
        GridPane.setConstraints (hBoxArr[0], 0, 0);//set positions of buttons for auz
        //region Modified getBidRow
        Button buttonFirstRow[] = new Button[endSuit - startSuit + 1];//button array for first row, row that's not standard
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
            buttonFirstRow[j] = new Button (name);//set button
            final String finalName = name;//set name of button
            buttonFirstRow[j].setOnAction (event -> {//set event handler for button
                auction.npass = 0;
                auction.bcontract = true;
                eventHandler (masterDeck, deckStage, decksDeckStage, stage, stage1, ndirection, finalName);
            });
        }
        //endregion
        if (hBoxArr.length >= 2) {//if bidding requires standard button rows
            hBoxArr[1] = new HBox (padding);
            hBoxArr[1].setPadding (new Insets (top, right, bottom, left));//set border spacing
            hBoxArr[1].getChildren ( ).addAll (buttonFirstRow);//add buttons to hbox
            GridPane.setConstraints (hBoxArr[1], 0, 1);//set positions
            for (int i = startLevel + 1, j = 2; i <= endLevel; i++, j++) {
                hBoxArr[j] = getBidRow (masterDeck, deckStage, decksDeckStage, stage, stage1,
                        ndirection, i, padding, top, right, bottom, left);//get standard rows (rows that are complete)
                GridPane.setConstraints (hBoxArr[j], 0, j + 1);//set positions
            }
        }
        gridPane.getChildren ( ).addAll (hBoxArr);//add hbox array to grid pane
        return gridPane;
    }

    private static HBox getBidRow(Deck masterDeck, DeckStage deckStage, Deck[] decksDeckStage,
                                  Stage stage, Stage stage1, int ndirection, int level, int padding,
                                  int top, int right, int bottom, int left) {
        Button arr[] = new Button[5];//new standard button row
        HBox hBox = new HBox (padding);//set horizontal spacing
        hBox.setPadding (new Insets (top, right, bottom, left));//set border spacing
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
            arr[i] = new Button (name);//set button's name
            final String finalName = name;
            arr[i].setOnAction (event -> {
                auction.npass = 0;//reset pass counter
                auction.bcontract = true;//set switch for contract to be true
                eventHandler (masterDeck, deckStage, decksDeckStage, stage, stage1, ndirection, finalName);//call event handler
            });
        }
        hBox.getChildren ( ).addAll (arr);//add button array to hbox
        return hBox;
    }
}
