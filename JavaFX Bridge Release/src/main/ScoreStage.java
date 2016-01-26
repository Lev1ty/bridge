package main;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import logic.Bid;

/**
 * Created by Adam on 1/21/2016.
 */
public class ScoreStage {
    private static StackPane stackPane;//root layout
    private static Scene scene;//content of stage
    private static Stage stage = new Stage ( );//window
    private static Image image;//backgroudn image

    public static int display(int score, Bid contractBid) {
        //init background image
        image = MenuStage.getImage ("StephCurry", "gif");
        Image image = ScoreStage.image;

        //Label for points and direction
        Label label = new Label (contractBid.sdirection + ": " + score + " Points");
        label.setFont (Font.font ("Verdana", 100));
        label.setTextFill (Color.WHITE);
        label.setAlignment (Pos.CENTER);

        //exit button due to lack of window buttona and thus close button
        Button exit = new Button (contractBid.lsdirection + (score > 0 ? " wins!" : " loses!"));
        exit.setOnAction (event -> stage.close ( ));

        //horizontally layed out
        HBox hBox = new HBox (90);
        hBox.getChildren ( ).addAll (label, exit);//add label and exit button to layout
        hBox.setAlignment (Pos.CENTER);

        //set root layout and background image
        stackPane = new StackPane (hBox);
        stackPane.setAlignment (Pos.CENTER);
        stackPane.setBackground (new Background (new BackgroundImage (image, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

        //set scene to root layout stackpane and stage to scene
        scene = new Scene (stackPane);
        stage.setScene (scene);
        stage.initStyle (StageStyle.TRANSPARENT);//make window borders disappear
        stage.showAndWait ( );
        return score;
    }
}
