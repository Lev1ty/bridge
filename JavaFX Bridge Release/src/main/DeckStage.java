package main;

import helpers.AlertBox;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.Card;
import logic.Deck;

/**
 * Created by Adam on 1/7/2016.
 */
public class DeckStage {

    public DeckStage() {
        Stage stage = new Stage ( );
        Scene scene = null;
        Deck players[] = new Deck[4];
        for (int i = 0; i < 4; i++) players[i] = new Deck (i, new Deck ( ));
        Image images[] = new Image[52];
        for (int i = 0; i < 52; i++) images[i] = getImage ( i + 1 );
        start(stage, scene, images, players);
}

    private void start(Stage stage, Scene scene,
                       Image images[], Deck players[]){
//        for (int i = 0; i < players.deck.length; i++) {
//            HBox hBox = new HBox ( new ImageView ( images[players.deck[i].nvalue] ) );
//            Scene scene = new Scene (hBox);
//            Stage stage1 = new Stage();
//            stage1.setScene ( scene );
//            stage1.show ();
//        }
//        Scene scene = new Scene (getHBoxCards ( players,images ));
//        Stage stage2 = new Stage();
//        stage2.setScene ( scene );
//        stage2.show ();
        BorderPane borderPane = new BorderPane ();
        HBox topCards = getHBoxCards ( players[0],images );
        topCards.setAlignment ( Pos.CENTER );
        VBox rightCards = getVBoxCards ( players[1],images,true );
        rightCards.setAlignment ( Pos.CENTER );
        HBox bottomCards = getHBoxCards ( players[2],images );
        bottomCards.setAlignment ( Pos.CENTER );
        VBox leftCards = getVBoxCards ( players[3],images,true );
        leftCards.setAlignment ( Pos.CENTER );
        Group rightGroup = new Group ( rightCards );
        Group leftGroup = new Group ( leftCards );
        borderPane.setTop(topCards);
        borderPane.setRight ( rightGroup );
        borderPane.setBottom ( bottomCards );
        borderPane.setLeft ( leftGroup );
        scene = new Scene ( borderPane );
        stage.setScene ( scene );
        stage.setMinHeight ( 500 );
        stage.show ();
    }

    private Image getImage(int nvalue) {
        Image image = null;
        try {
            image = new Image ("cards\\" + nvalue + ".gif");
        } catch (Exception e) {
            AlertBox.display ("Error", "Image " + nvalue + " not found.");
        }
        return image;
    }

    private HBox getHBoxCards(Deck deck, Image[] images) {
        HBox hBox = new HBox (-50);
        for (Card card :
                deck.deck) {
            ImageView imageView =  new ImageView ( images[card.nvalue] );
            imageView.setFitHeight ( 85 );
            imageView.setPreserveRatio ( true );
            hBox.getChildren ().add ( imageView );
        }
        return hBox;
    }

    private VBox getVBoxCards(Deck deck, Image[] images, boolean right) {
        VBox vBox = new VBox (-50);
        for (Card card :
                deck.deck) {
            ImageView imageView = new ImageView ( images[card.nvalue] );
            imageView.setFitHeight ( 85 );
            imageView.setPreserveRatio ( true );
            if (right) imageView.setRotate ( 90 );
            else imageView.setRotate ( -90 );
            vBox.getChildren ().add ( imageView );
        }
        return vBox;
    }
}
