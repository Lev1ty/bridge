package main;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import logic.Bid;

/**
 * Created by Adam on 1/21/2016.
 */
public class ScoreStage {
    private static StackPane stackPane;
    private static Scene scene;
    private static Stage stage = new Stage();

    public static int display(int score, Bid contractBid) {
        Label label = new Label(contractBid.sdirection + ": " + score );
        label.setAlignment ( Pos.CENTER );
        stackPane = new StackPane ( label );
        scene = new Scene ( stackPane );
        stage.setScene ( scene );
        stage.sizeToScene ();
        stage.showAndWait ();
        return score;
    }
}
