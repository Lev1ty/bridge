package main;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Created by Adam on 1/7/2016.
 */
public class BidStage {
    public static void start(Stage bidStage, int startLevel, int endLevel) {
        GridPane gridPane = new GridPane();
        gridPane.getChildren().addAll(getGridePane(10, startLevel, endLevel, 5, 10, 5, 10));
        Scene primaryScene = new Scene(gridPane);
        bidStage.setTitle("Auction");
        bidStage.setScene(primaryScene);
        bidStage.show();
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

    private static GridPane getGridePane(int padding, int startLevel, int endLevel, int top, int right, int bottom, int left) {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(top, right, bottom, left));
        Button auxBid[] = new Button[3];
        auxBid[0] = new Button("Pass");
        auxBid[1] = new Button("Double");
        auxBid[2] = new Button("Redouble");
        HBox hBoxArr[] = new HBox[endLevel - startLevel + 2];
        hBoxArr[0] = new HBox(1.825 * padding);
        hBoxArr[0].getChildren().addAll(auxBid);
        hBoxArr[0].setPadding(new Insets(top, right, bottom, left));
        gridPane.setConstraints(hBoxArr[0], 0, 0);
        for (int i = startLevel, j = 1; i <= endLevel; i++, j++) {
            hBoxArr[j] = getBidRow(i, padding, top, right, bottom, left);
            gridPane.setConstraints(hBoxArr[j], 0, j + 1);
        }
        gridPane.getChildren().addAll(hBoxArr);
        return gridPane;
    }

    private static HBox getBidRow(int level, int padding, int top, int right, int bottom, int left) {
        Button arr[] = new Button[5];
        HBox hBox = new HBox(padding);
        hBox.setPadding(new Insets(top, right, bottom, left));
        for (int i = 0; i < 5; i++) {
            String name = String.valueOf(level);
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
            arr[i] = new Button(name);
        }
        hBox.getChildren().addAll(arr);
        return hBox;
    }
}
