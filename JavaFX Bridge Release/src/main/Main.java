package main;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch (args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        BidStage bidStage;
        int rngdir1 = (int) (Math.random ( ) * 100 % 4);
        bidStage = new BidStage (rngdir1, 1, 7, 0, 4);
//        recursiveDisplayStage (bidStage, rngdir1);
    }

//    public void recursiveDisplayStage(BidStage bidStage, int rngdir1) {
//        bidStage = new BidStage (rngdir1, 1, 7);
//        ++rngdir1;
//        ++bidStage.auction.nbid;
//        rngdir1 %= 4;
//        if (!(bidStage.auction.bcontract && bidStage.auction.npass >= 3 && bidStage.auction.nbid >= 4)
//                && bidStage.inputAlert)
//            recursiveDisplayStage (bidStage, rngdir1);
//        else if (bidStage.auction.npass == 4 && bidStage.auction.nbid == 4) return;
//        else return;
//    }
}
