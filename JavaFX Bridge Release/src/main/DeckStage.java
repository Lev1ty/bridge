package main;

import helpers.ConfirmBox;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.Deck;
import logic.ImageDeck;
import logic.ImageWrapper;

/**
 * Created by Adam on 1/7/2016.
 */
public class DeckStage {
    public static Deck deck = new Deck ( );
    public Deck[] players;

    public DeckStage() {
        Stage stage = new Stage ( );
        players = new Deck[4];
        for (int i = 0; i < 4; i++) players[i] = new Deck (i, new Deck ( ));
        start (stage, players, 10);
    }

    private void start(Stage stage, Deck[] players,
                       int padding) {
        ImageDeck.initDeck ();
//        for (ImageWrapper imageWrapper :
//                ImageDeck.imageDeck) {
//            HBox hBox = new HBox (imageWrapper.getImageView ( ));
//            Group group = new Group (hBox);
//            Scene scene = new Scene (group);
//            Stage stage1 = new Stage ( );
//            stage1.setScene (scene);
//            stage1.show ( );
//        }
        BorderPane borderPane = new BorderPane (null, getRow (players[0], padding),
                getColumn (players[1], padding), getRow (players[2], padding), getColumn (players[3], padding));
        // TODO: 1/12/2016 Figure out operations with center
        Scene primaryScene = new Scene (borderPane);
        stage.setTitle ("Bridge");
        stage.setScene (primaryScene);
        stage.setResizable (true);
        stage.setFullScreen (false);
        stage.setFullScreenExitHint ("Bridge is now full screen. Exit full screen (ESC).");
        stage.setOnCloseRequest (event -> {
            event.consume ( );
            if (ConfirmBox.display ("Confirm", "End Game Prematurely?")) stage.close ( );
        });
        stage.show ( );
    }

    private Group getColumn(Deck deck, int padding) {
        VBox vBox = new VBox (padding, ImageDeck.getImageViewDeck (deck));
//        VBox vBox = new VBox (padding);
//        vBox.getChildren ( ).addAll (ImageDeck.getImageViewDeck (deck));
        Group group = new Group (vBox);
        return group;
    }

    private Group getRow(Deck deck, int padding) {
        HBox hBox = new HBox (padding, ImageDeck.getImageViewDeck (deck));
        Group group = new Group (hBox);
        return group;
    }
}
