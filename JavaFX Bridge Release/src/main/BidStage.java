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

/**
 * Created by Adam on 1/7/2016.
 */
public class BidStage {
    public static Auction auction = new Auction ();
    public boolean inputAlert;
    private String sdirection;
    private int ndirection;
    private Stage stage;

    public BidStage(String sdirection, int startLevel, int endLevel, int startSuit, int endSuit) {
        this.sdirection = sdirection;
        start ( sdirection, startLevel, endLevel, startSuit, endSuit );
    }

    public BidStage(int ndirection, int startLevel, int endLevel, int startSuit, int endSuit) {
        this.ndirection = ndirection;
        sdirection = Bid.nDirectiontosDirection ( ndirection );
        start ( ndirection, startLevel, endLevel, startSuit, endSuit );
    }

    private void start(String sdirection, int startLevel, int endLevel, int startSuit, int endSuit) {
        GridPane gridPane = new GridPane ();
        gridPane.getChildren ().addAll ( getGridePane ( 10, startLevel, endLevel, startSuit, endSuit, 5, 10, 5, 10 ) );
        Scene primaryScene = new Scene ( gridPane );
        stage = new Stage ();
        stage.setTitle ( "Bid from " + sdirection );
        stage.setScene ( primaryScene );
        stage.setFullScreen ( false );
        stage.setFullScreenExitHint ( "Bid is now full screen. Exit full screen (ESC)." );
        stage.setOnCloseRequest ( event -> {
            event.consume ();
            if (ConfirmBox.display ( "Confirm", "End Auction Prematurely?" )) stage.close ();
        } );
        stage.show ();
    }

    private void start(int ndirection, int startLevel, int endLevel, int startSuit, int endSuit) {
        GridPane gridPane = new GridPane ();
        gridPane.getChildren ().addAll ( getGridePane ( 10, startLevel, endLevel, startSuit, endSuit, 5, 10, 5, 10 ) );
        Scene primaryScene = new Scene ( gridPane );
        stage = new Stage ();
        stage.setTitle ( "Bid from " + sdirection );
        stage.setScene ( primaryScene );
        stage.setFullScreen ( false );
        stage.setFullScreenExitHint ( "Bid is now full screen. Exit full screen (ESC)." );
        stage.setOnCloseRequest ( event -> {
            event.consume ();
            if (ConfirmBox.display ( "Confirm", "End Auction Prematurely?" )) stage.close ();
        } );
        stage.show ();
    }

    private void eventHandler(String name) {
        Bid bid = new Bid ( name, ndirection );
        if (ConfirmBox.display ( "Confirm",
                "Is " + bid.svalue + " your Final Bid?" )) {
            bid.Print ();
            auction.push_back ( bid.nvalue, ndirection );
            inputAlert = true;
            stage.close ();
            ++ndirection;
            ndirection %= 4;
            ++auction.nbid;
            if (auction.nbid == 4 && auction.npass == 4) {
                stage.close ();
            } else if (auction.bcontract && auction.npass >= 3 && auction.nbid >= 4) {
                stage.close ();
            } else {
                if (bid.nvalue >= 35) {
                    Bid lastCotnractBid = new Bid ();
                    boolean found = false;
                    for (int i = auction.auction.size () - 1; i >= 0; i--)
                        if (auction.auction.get ( i ).nvalue < 35) {
                            lastCotnractBid = auction.auction.get ( i );
                            found = true;
                            break;
                        }
                    if (found) {
                        BidStage bidStage = new BidStage ( ndirection, lastCotnractBid.nlevel + 1, 7, lastCotnractBid.nsuit + 1, 4 );
                    } else {
                        BidStage bidStage = new BidStage ( ndirection, 1, 7, 0, 4 );
                    }
                } else {
                    BidStage bidStage = new BidStage ( ndirection, bid.nlevel + 1, 7, bid.nsuit + 1, 4 );
                }
                auction.printField ();
            }
        }
    }

    private GridPane getGridePane(int padding, int startLevel, int endLevel, int startSuit, int endSuit,
                                  int top, int right, int bottom, int left) {
        GridPane gridPane = new GridPane ();
        gridPane.setPadding ( new Insets ( top, right, bottom, left ) );
        Button auxBid[] = new Button[3];
        auxBid[0] = new Button ( "Pass" );
        auxBid[0].setOnAction ( event -> {
            ++auction.npass;
            eventHandler ( "Pass" );
        } );
        auxBid[1] = new Button ( "Double" );
        auxBid[1].setOnAction ( event -> {
            auction.x = true;
            auction.npass = 0;
            eventHandler ( "Double" );
        } );
        auxBid[2] = new Button ( "Redouble" );
        auxBid[2].setOnAction ( event -> {
            if (!auction.x) AlertBox.display ( "Redouble", "Cannot redouble before double." );
            else {
                auction.xx = true;
                auction.npass = 0;
                eventHandler ( "Redouble" );
            }
        } );
        HBox hBoxArr[] = new HBox[endLevel - startLevel + 2];
        hBoxArr[0] = new HBox ( 1.825 * padding );
        hBoxArr[0].getChildren ().addAll ( auxBid );
        hBoxArr[0].setPadding ( new Insets ( top, right, bottom, left ) );
        GridPane.setConstraints ( hBoxArr[0], 0, 0 );
        //region Modified getBidRow
        Button buttonFirstRow[] = new Button[endSuit - startSuit + 1];
        for (int i = startSuit, j = 0; i <= endSuit; i++, j++) {
            String name = String.valueOf ( startLevel );
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
            buttonFirstRow[j] = new Button ( name );
            final String finalName = name;
            buttonFirstRow[j].setOnAction ( event -> {
                auction.npass = 0;
                auction.bcontract = true;
                eventHandler ( finalName );
            } );
        }
        //endregion
        if (hBoxArr.length >= 2) {
            hBoxArr[1] = new HBox ( padding );
            hBoxArr[1].setPadding ( new Insets ( top, right, bottom, left ) );
            hBoxArr[1].getChildren ().addAll ( buttonFirstRow );
            GridPane.setConstraints ( hBoxArr[1], 0, 1 );
            for (int i = startLevel + 1, j = 2; i <= endLevel; i++, j++) {
                hBoxArr[j] = getBidRow ( i, padding, top, right, bottom, left );
                GridPane.setConstraints ( hBoxArr[j], 0, j + 1 );
            }
        }
        gridPane.getChildren ().addAll ( hBoxArr );
        return gridPane;
    }

//    private static HBox getAuxBidRow(int padding) {
//        Button auxBid[] = new Button[3];
//        auxBid[0] = new Button("Pass");
//        auxBid[1] = new Button("Double");
//        auxBid[2] = new Button("Redouble");
//        HBox hBox = new HBox(padding);
//        hBox.getChildren().addAll(auxBid);
//        return hBox;
//    }

    private HBox getBidRow(int level, int padding,
                           int top, int right, int bottom, int left) {
        Button arr[] = new Button[5];
        HBox hBox = new HBox ( padding );
        hBox.setPadding ( new Insets ( top, right, bottom, left ) );
        for (int i = 0; i < 5; i++) {
            String name = String.valueOf ( level );
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
            arr[i] = new Button ( name );
            final String finalName = name;
            arr[i].setOnAction ( event -> {
                auction.npass = 0;
                auction.bcontract = true;
                eventHandler ( finalName );
            } );
        }
        hBox.getChildren ().addAll ( arr );
        return hBox;
    }
}
