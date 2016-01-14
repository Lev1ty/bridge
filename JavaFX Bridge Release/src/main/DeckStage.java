package main;

import helpers.AlertBox;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Box;
import javafx.stage.Stage;
import logic.Card;
import logic.Deck;

/**
 * Created by Adam on 1/7/2016.
 */
public class DeckStage {

    public DeckStage() {
        Stage stage = new Stage ( );
        stage.setTitle ( "Bridge" );
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
        HBox topCards = getHBoxCards ( players[0],images,100 );
        topCards.setAlignment ( Pos.CENTER );
        VBox rightCards = getVBoxCards ( players[1],images,true,100 );
        rightCards.setAlignment ( Pos.CENTER );
        HBox bottomCards = getHBoxCards ( players[2],images,100 );
        bottomCards.setAlignment ( Pos.CENTER );
        VBox leftCards = getVBoxCards ( players[3],images,true,100 );
        leftCards.setAlignment ( Pos.CENTER );
        Group rightGroup = new Group ( rightCards );
        Group leftGroup = new Group ( leftCards );
        Group topGroup = new Group (topCards);
        Group bottomGroup = new Group (bottomCards);
        HBox alignTop = new HBox ( topGroup );
        alignTop.setAlignment ( Pos.CENTER );
        VBox alignRight = new VBox(rightGroup);
        alignRight.setAlignment ( Pos.CENTER );
        HBox alignBottom = new HBox(bottomGroup);
        alignBottom.setAlignment ( Pos.CENTER );
        alignBottom.setRotate ( 180 );
        VBox alignLeft = new VBox ( leftGroup );
        alignLeft.setRotate ( 180 );
        alignLeft.setAlignment ( Pos.CENTER );
        Box box = new Box();
        box.setHeight ( 400 );
        box.setWidth ( 400 );
        borderPane.setCenter(box);
        borderPane.setTop( alignTop );
        borderPane.setRight ( alignRight );
        borderPane.setBottom ( alignBottom );
        borderPane.setLeft ( alignLeft );
        scene = new Scene ( borderPane );
        stage.setScene ( scene );
        stage.setResizable ( false );
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

    private void EventHandler(int nvalue) {
        System.out.println (nvalue);
    }

    private StackPane getClickableImage(Image image, Card card, int height, int rotate) {
        ImageView imageView = new ImageView ( image );
        imageView.setPreserveRatio ( true );
        imageView.setFitHeight ( height );
        if (rotate!=0) imageView.setRotate ( rotate );
        Button button = new Button (card.srank+card.ssuit);
        button.setMinHeight ( height );
        button.setMinWidth ( height*17/22 );
        if (rotate!=0)button.setRotate ( rotate );
        button.setOnAction ( event -> EventHandler ( card.nvalue ) );
        StackPane stackPane = new StackPane (imageView,button);
        return stackPane;
    }

    private HBox getHBoxCards(Deck deck, Image[] images,
                              int height) {
        HBox hBox = new HBox (-1*height);
        for (Card card :
                deck.deck) {
            Image image = images[card.nvalue];
            hBox.getChildren ().add ( getClickableImage ( image,card,height,0 ) );
        }
        return hBox;
    }

    private VBox getVBoxCards(Deck deck, Image[] images,
                              boolean right, int height) {
        VBox vBox = new VBox ( -1*height*17/14 );
        for (Card card :
                deck.deck) {
            Image image = images[card.nvalue];
            if (right) vBox.getChildren ().add ( getClickableImage ( image,card,height,90 ) );
            else vBox.getChildren ().add ( getClickableImage ( image,card,height,-90 ) );
        }
        return vBox;
    }
}
