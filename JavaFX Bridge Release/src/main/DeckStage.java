package main;

import helpers.AlertBox;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import logic.Auction;
import logic.Card;
import logic.Deck;

/**
 * Created by Adam on 1/7/2016.
 */
public class DeckStage {
    private static Stage stage = new Stage ( );
    private static Image images[] = new Image[52];
    private static Auction auction;

    public DeckStage(Deck players[], int revealDirection) {
        start (players,revealDirection,0,2,true);
    }

    public DeckStage(Deck players[], int currentDirection, int dummyDirection) {
        currentDirection %= 4;
        start (players, currentDirection, currentDirection, dummyDirection, false);
    }

    public static void initDeckStage(Auction auction) {
        DeckStage.auction = auction;
        DeckStage.stage.setTitle ("Bridge");
        for (int i = 0; i < 52; i++) DeckStage.images[i] = DeckStage.getImage (i + 1);
    }

    public static Image getImage(int nvalue) {
        Image image = null;
        try {
            image = new Image ("cards\\" + nvalue + ".gif");
        } catch (Exception e) {
            AlertBox.display ("Error", "Image " + nvalue + " not found.");
        }
        return image;
    }

    public static Image getImage(String svalue) {
        Image image = null;
        try {
            image = new Image ("cards\\" + svalue + ".gif");
        } catch (Exception e) {
            AlertBox.display ("Error", "Image " + svalue + " not found.");
        }
        return image;
    }

    private void start(Deck players[], int revealDirection,
                       int currentDirection, int dummyDirection,
                       boolean simplyShow) {
        BorderPane borderPane = new BorderPane ( );
        int temp = currentDirection;
        if (!simplyShow) temp += 2;
        HBox topCards = getHBoxCards (dummyDirection, temp%=4, players, players[(temp %= 4)], images, 100, false, temp==revealDirection);
        ++temp;
        topCards.setAlignment (Pos.CENTER);
        VBox rightCards = getVBoxCards (players[(temp %= 4)], images, true, 100, temp==revealDirection,temp%=4);
        ++temp;
        rightCards.setAlignment (Pos.CENTER);
        HBox bottomCards = getHBoxCards (dummyDirection, temp%=4, players, players[(temp %= 4)], images, 100, true, temp==revealDirection);
        ++temp;
        bottomCards.setAlignment (Pos.CENTER);
        VBox leftCards = getVBoxCards (players[(temp %= 4)], images, true, 100, temp==revealDirection,temp%=4);
        leftCards.setAlignment (Pos.CENTER);
        Group rightGroup = new Group (rightCards);
        Group leftGroup = new Group (leftCards);
        Group topGroup = new Group (topCards);
        Group bottomGroup = new Group (bottomCards);
        HBox alignTop = new HBox (topGroup);
        alignTop.setAlignment (Pos.CENTER);
        VBox alignRight = new VBox (rightGroup);
        alignRight.setAlignment (Pos.CENTER);
        HBox alignBottom = new HBox (bottomGroup);
        alignBottom.setAlignment (Pos.CENTER);
        alignBottom.setRotate (180);
        VBox alignLeft = new VBox (leftGroup);
        alignLeft.setRotate (180);
        alignLeft.setAlignment (Pos.CENTER);
        GridPane gridPaneCenter = new GridPane ( );
        gridPaneCenter.setMinSize (400, 400);
        borderPane.setCenter (gridPaneCenter);
        borderPane.setTop (alignTop);
        borderPane.setRight (alignRight);
        borderPane.setBottom (alignBottom);
        borderPane.setLeft (alignLeft);
        Scene scene = new Scene (borderPane);
        stage.setScene (scene);
        stage.setResizable (false);
        stage.show ( );
    }

    private void EventHandler(int dummyDirection, int currentDirection, Deck players[], Card card) {
        card.Print ( );
        players[currentDirection].remove (card.nvalue);
        new DeckStage (players, ++currentDirection, dummyDirection);
    }

    private Button getClickableImage(int dummyDirection, int currentDirection, Deck players[], Image image, Card card, int height) {
        Button button = new Button ( );
        button.setMinSize (height * 17 / 22, height);
        BackgroundImage backgroundImage = new BackgroundImage (image,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background background = new Background (backgroundImage);
        button.setBackground (background);
        button.setOnAction (event -> EventHandler (dummyDirection, currentDirection, players, card));
        return button;
    }

    private HBox getHBoxCards(int dummyDirection, int currentDirection, Deck players[],
                              Deck deck, Image[] images,
                              int height, boolean clickable, boolean reveal) {
        HBox hBox = new HBox (-1 * height);
        if (reveal) if (clickable) {
            for (Card card :
                    deck.deck) {
                Image image = images[card.nvalue];
                hBox.getChildren ( ).add (getClickableImage (dummyDirection, currentDirection, players, image, card, height));
            }
        } else {
            for (Card card :
                    deck.deck) {
                Image image = images[card.nvalue];
                ImageView imageView = new ImageView (image);
                imageView.setPreserveRatio (true);
                imageView.setFitHeight (height);
                hBox.getChildren ( ).add (imageView);
            }
        }
        else for (int i = 0; i < deck.deck.length; i++) {
            ImageView imageView = new ImageView ( getImage ( "B" ) );
            hBox.getChildren().add(imageView);
        }
        hBox.getChildren ().add ( 0,new Label (String.valueOf ( currentDirection )) );
        return hBox;
    }

    private VBox getVBoxCards(Deck deck, Image[] images,
                              boolean right, int height,
                              boolean reveal, int currentDirection) {
        VBox vBox = new VBox (-1 * height * 17 / 14);
        if (reveal) for (Card card :
                deck.deck) {
            Image image = images[card.nvalue];
            ImageView imageView = new ImageView (image);
            if (right) imageView.setRotate (90);
            else imageView.setRotate (-90);
            vBox.getChildren ( ).add (imageView);
        }
        else for (int i = 0; i < deck.deck.length; i++) {
            ImageView imageView = new ImageView ( getImage ( "B" ) );
            if (right) imageView.setRotate (90);
            else imageView.setRotate (-90);
            vBox.getChildren().add(imageView);
        }
        vBox.getChildren ().add ( 0,new Label (String.valueOf ( currentDirection )) );
        return vBox;
    }
}
