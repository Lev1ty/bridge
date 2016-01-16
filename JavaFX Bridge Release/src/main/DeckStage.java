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
import logic.Bid;
import logic.Card;
import logic.Deck;

/**
 * Created by Adam on 1/7/2016.
 */
public class DeckStage {
    public static Stage stage = new Stage ( );
    private static Image images[] = new Image[52];
    private static Bid contractBid;

    public DeckStage(Deck players[], int currentDirection) {
        start (players, currentDirection, currentDirection, -1);
    }

    public DeckStage(Deck players[], int currentDirection, int dummyDirection) {
        dummyDirection %= 4;
        currentDirection %= 4;
        start (players, currentDirection, currentDirection, dummyDirection);
    }

    public DeckStage(Deck players[], int currentDirection, int dummyDirection, Bid contractBid) {
        DeckStage.contractBid = contractBid;
        new DeckStage (players, currentDirection, dummyDirection);
    }

    public static void initDeckStage() {
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
                       int currentDirection, int dummyDirection) {
        BorderPane borderPane = new BorderPane ( );
        int temp = currentDirection + 2;
        HBox topCards = getHBoxCards (dummyDirection, temp %= 4, players,
                players[(temp %= 4)], images, 100, false, (temp == revealDirection || temp == dummyDirection));
        ++temp;
        topCards.setAlignment (Pos.CENTER);
        VBox rightCards = temp == dummyDirection ?
                getVBoxDummyCards (dummyDirection, temp %= 4, players, players[(temp %= 4)], images, 100, true, true, true)
                : getVBoxCards (players[(temp %= 4)], images, true, 100, temp == revealDirection, temp %= 4);
        ++temp;
        rightCards.setAlignment (Pos.CENTER);
        HBox bottomCards = getHBoxCards (dummyDirection, temp %= 4, players,
                players[(temp %= 4)], images, 100, true, (temp == revealDirection || temp == dummyDirection));
        ++temp;
        bottomCards.setAlignment (Pos.CENTER);
        VBox leftCards = temp == dummyDirection ?
                getVBoxDummyCards (dummyDirection, temp %= 4, players, players[(temp % 4)], images, 100, false, true, true)
                : getVBoxCards (players[(temp %= 4)], images, false, 100, temp == revealDirection, temp % 4);
        leftCards.setAlignment (Pos.CENTER);
        Group rightGroup = new Group (rightCards);
        Group leftGroup = new Group (leftCards);
        Group topGroup = new Group (topCards);
        Group bottomGroup = new Group (bottomCards);
        HBox alignTop = new HBox (topGroup);
        alignTop.getChildren ( ).add (new Label ("   "));
        Label topLabel = new Label (Bid.nDirectiontolsDirection ((currentDirection + 2) % 4));
        topLabel.setRotate (180);
        alignTop.getChildren ( ).add (topLabel);
        alignTop.setAlignment (Pos.CENTER);
        alignTop.setRotate (180);
        VBox alignRight = new VBox (rightGroup);
        alignRight.setAlignment (Pos.CENTER);
        HBox alignBottom = new HBox (bottomGroup);
        alignBottom.getChildren ( ).add (new Label ("   "));
        Label bottomLabel = new Label (Bid.nDirectiontolsDirection ((currentDirection + 4) % 4));
        bottomLabel.setRotate (180);
        alignBottom.getChildren ( ).add (bottomLabel);
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

    private Button getClickableImage(int dummyDirection, int currentDirection, Deck players[],
                                     Image image, Card card, int height) {
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
        if (reveal) if (clickable) for (Card card :
                deck.deck) {
            Image image = images[card.nvalue];
            hBox.getChildren ( ).add (getClickableImage (dummyDirection, currentDirection, players, image, card, height));
        }
        else for (Card card :
                    deck.deck) {
                Image image = images[card.nvalue];
                ImageView imageView = new ImageView (image);
                imageView.setPreserveRatio (true);
                imageView.setFitHeight (height);
                hBox.getChildren ( ).add (imageView);
            }
        else for (Card aDeck : deck.deck) {
            ImageView imageView = new ImageView (getImage ("B"));
            hBox.getChildren ( ).add (imageView);
        }
//        Label direction = new Label (Bid.nDirectiontolsDirection (currentDirection));
//        direction.setRotate (180);
//        hBox.getChildren ( ).add (0, direction);
//        hBox.getChildren ().add (1,new Label ());
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
        else for (Card aDeck : deck.deck) {
            ImageView imageView = new ImageView (getImage ("B"));
            if (right) imageView.setRotate (90);
            else imageView.setRotate (-90);
            vBox.getChildren ( ).add (imageView);
        }
        Label direction = new Label (Bid.nDirectiontolsDirection (currentDirection));
        if (!right) direction.setRotate (180);
        vBox.getChildren ( ).add (0, direction);
        return vBox;
    }

    private VBox getVBoxDummyCards(int dummyDirection, int currentDirection, Deck players[],
                                   Deck deck, Image[] images, int height, boolean right,
                                   boolean clickable, boolean reveal) {
        Deck deckSeperatedBySuit[] = Deck.seperateBySuit (deck);
        VBox vBox = new VBox ( );
        for (int i = 3; i >= 0; i--)
            vBox.getChildren ( ).add (getHBoxCards (dummyDirection, currentDirection,
                    players, deckSeperatedBySuit[i], images, height, clickable, reveal));
        if (right) vBox.setRotate (180);
        return vBox;
    }
}
