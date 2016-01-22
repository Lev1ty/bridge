package main;

import helpers.AlertBox;
import helpers.ConfirmBox;
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
    public static Stage stage;
    public static boolean show;
    public static Bid contractBid;
    public static Deck deckHistory;
    public static Deck winningTricks;
    private static Image images[];
    private static GridPane gridPaneCenter;

    public DeckStage() {
        initDeckStage ( );
    }

    public DeckStage(Deck players[], int currentDirection, boolean preplay, boolean showdummy) {
        start (players, currentDirection, currentDirection, -1, preplay, showdummy);
        DeckStage.show = false;
    }

    public DeckStage(Deck players[], int currentDirection, int dummyDirection, boolean showdummy) {
        dummyDirection %= 4;
        currentDirection %= 4;
        start (players, currentDirection, currentDirection, dummyDirection, false, showdummy);
        DeckStage.show = false;
    }

    public DeckStage(Deck players[], int currentDirection, int dummyDirection, Bid contractBid, boolean showdummy) {
        DeckStage.contractBid = contractBid;
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < players[i].deck.length; j++)
                players[i].deck[j].nSuittoprioritySuit (contractBid.nsuit);
        for (int i = 0; i < 4; i++) players[i].deck = Deck.sortBySuit (players[i].deck);
        new DeckStage (players, currentDirection, dummyDirection, showdummy);
    }

    public static void initDeckStage() {
        DeckStage.stage = new Stage ( );
        DeckStage.stage.setTitle ("Bridge");
        DeckStage.stage.setOpacity (0.9);
        DeckStage.stage.setMinHeight (675);
        DeckStage.stage.setMinWidth (1200);
        DeckStage.stage.sizeToScene ( );
        DeckStage.stage.setOnCloseRequest (event -> {
            event.consume ( );
            if (ConfirmBox.display ("Close", "End game?")) stage.close ( );
        });
        DeckStage.show = true;
        DeckStage.images = new Image[52];
        for (int i = 0; i < 52; i++) DeckStage.images[i] = DeckStage.getImage (i + 1);
        gridPaneCenter = new GridPane ( );
        gridPaneCenter.setAlignment (Pos.CENTER);
        deckHistory = new Deck ( );
        deckHistory.deck = Deck.resize (deckHistory.deck, 0);
        winningTricks = new Deck ( );
        winningTricks.deck = Deck.resize (winningTricks.deck, 0);
    }

    public static Image getImage(int nvalue) {
        return getImage (String.valueOf (nvalue));
    }

    public static Image getImage(String svalue) {
        Image image = null;
        try {
            image = new Image ("cards\\" + svalue + ".gif");
        } catch (Exception e) {
            try {
                image = new Image ("cards\\" + svalue + ".jpg");
            } catch (Exception e1) {
                e1.printStackTrace ( );
                AlertBox.display ("Error", "Image " + svalue + " not found.");
            }
        }
        return image;
    }

    private static void start(Deck players[], int revealDirection,
                              int currentDirection, int dummyDirection,
                              boolean preplay, boolean showdummy) {
        BorderPane borderPane = new BorderPane ( );
        int temp = currentDirection + 2;
        temp %= 4;
        HBox topCards = getHBoxCards (dummyDirection, temp, players,
                players[temp], images, 100, false, (temp == revealDirection || temp == dummyDirection));
        ++temp;
        temp %= 4;
        topCards.setAlignment (Pos.CENTER);
        VBox rightCards = temp == dummyDirection ?
                getVBoxDummyCards (dummyDirection, temp, players, players[temp], images, 100,
                        true, currentDirection == dummyDirection, true)
                : getVBoxCards (players[(temp %= 4)], images, true, 100, temp == revealDirection, temp %= 4);
        ++temp;
        temp %= 4;
        rightCards.setAlignment (Pos.CENTER);
        HBox bottomCards = getHBoxCards (dummyDirection, temp, players,
                players[(temp)], images, 100, !preplay, (temp == revealDirection || temp == dummyDirection));
        ++temp;
        temp %= 4;
        bottomCards.setAlignment (Pos.CENTER);
        VBox leftCards = (temp == dummyDirection && showdummy) ?
                getVBoxDummyCards (dummyDirection, temp, players, players[(temp % 4)], images, 100,
                        false, currentDirection == dummyDirection, true)
                : getVBoxCards (players[temp], images, false, 100, temp == revealDirection, temp % 4);
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
        borderPane.setCenter (gridPaneCenter);
        borderPane.setTop (alignTop);
        borderPane.setRight (alignRight);
        borderPane.setBottom (alignBottom);
        borderPane.setLeft (alignLeft);
        BackgroundImage backgroundImage = new BackgroundImage (getImage ("background"), BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize (
                BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, false, true
        ));
        Background background = new Background (backgroundImage);
        borderPane.setBackground (background);
        borderPane.setMinSize (1200, 675);
        stage.setScene (new Scene (borderPane));
        if (show) stage.show ( );
    }

    private static void EventHandler(int dummyDirection, int currentDirection, Deck players[], Card card) {
        if (isLegalMove (players, card)) {
            players[currentDirection].remove (card.nvalue);
            deckHistory.push_back (card);
            if (gridPaneCenter.getChildren ( ).size ( ) == 4) {
                gridPaneCenter.getChildren ( ).removeAll (gridPaneCenter.getChildren ( ));
                gridPaneCenter.getChildren ( ).add (new ImageView (images[card.nvalue]));
                GridPane.setConstraints (gridPaneCenter.getChildren ( ).get (0),
                        getCenterPosCol (currentDirection), getCenterPosRow (currentDirection));
            } else if (gridPaneCenter.getChildren ( ).size ( ) > 0) {
                gridPaneCenter.getChildren ( ).add (0, new ImageView (images[card.nvalue]));
                GridPane.setConstraints (gridPaneCenter.getChildren ( ).get (0),
                        getCenterPosCol (currentDirection), getCenterPosRow (currentDirection));
                if (gridPaneCenter.getChildren ( ).size ( ) == 4) {
                    Card winner = compare (deckHistory.deck[deckHistory.deck.length - 1],
                            deckHistory.deck[deckHistory.deck.length - 2],
                            deckHistory.deck[deckHistory.deck.length - 3],
                            deckHistory.deck[deckHistory.deck.length - 4]);
                    winningTricks.push_back (winner);
                    winner.Print ( );
                    new DeckStage (players, winner.ndirection, dummyDirection, contractBid, true);
                }
            } else {
                gridPaneCenter.getChildren ( ).add (new ImageView (images[card.nvalue]));
                GridPane.setConstraints (gridPaneCenter.getChildren ( ).get (0),
                        getCenterPosCol (currentDirection), getCenterPosRow (currentDirection));
            }
            if (isPlayersEmpty (players)) {
                // TODO: 1/18/2016 start scoring phase
            }
            if (gridPaneCenter.getChildren ( ).size ( ) < 4)
                new DeckStage (players, ++currentDirection, dummyDirection, contractBid, true);
        } else {
            AlertBox.display ("Illegal Move", card.lssuit + " does not trump or follow suit.");
        }
    }

    private static boolean isLegalMove(Deck players[], Card card) {
        if (deckHistory.deck.length % 4 == 0) return true;
        else {
            int nsuit = deckHistory.deck[(deckHistory.deck.length / 4) * 4].nsuit;
            if (card.nsuit == nsuit) {
                return true;
            } else if (!Deck.isThereSuit (players[card.ndirection], nsuit)) {
                return true;
            }
        }
        return false;
    }

    private static Card compare(Card... cards) {
        Card maxCard = cards[0];
        int auctionBidSuit = 3 - contractBid.nsuit;
        for (Card card : cards) {
            if (maxCard.nsuit == auctionBidSuit) {
                if (card.nsuit == auctionBidSuit && card.nrank >= maxCard.nrank) {
                    maxCard = card;
                }
            } else if (maxCard.nsuit < auctionBidSuit || maxCard.nsuit > auctionBidSuit) {
                if (card.nsuit == auctionBidSuit) {
                    maxCard = card;
                } else if (card.nrank >= maxCard.nrank) {
                    maxCard = card;
                }
            }
        }
        return maxCard;
    }

    //region Unused printTakenTricks
    //    private static void printTakenTricks(int takenTricks[][][]) {
//        for (int[][] level1 :
//                takenTricks) {
//            System.out.println ();
//            for (int[] level2 :
//                    level1) {
//                for (int i :
//                        level2) {
//                    System.out.print ( i );
//                    System.out.print ( ' ' );
//                }
//                System.out.println ();
//            }
//            System.out.println ();
//        }
//    }
//
//    private static int[][][] takenTricksResize(int takenTricks[][][], int index1, int index2, Card card, int length) {
//        takenTricks[index1][index2] = resize ( takenTricks[index1][index2], length );
//        return takenTricks;
//    }
//
//    private static int[] resize(int resize[], int length) {
//        int retDeck[] = new int[length];
//        for (int i = 0; i < (length >= resize.length ? resize.length : length); i++) retDeck[i] = resize[i];
//        return retDeck;
//    }
    //endregion

    private static boolean isPlayersEmpty(Deck players[]) {
        for (Deck aDeck : players)
            if (aDeck.deck.length > 0)
                return false;
        return true;
    }

    private static void setCenterBacks() {
        for (int i = gridPaneCenter.getChildren ( ).size ( ); i < 4; i++) {
            gridPaneCenter.getChildren ( ).add (0, new ImageView (getImage ("b")));
            GridPane.setConstraints (gridPaneCenter.getChildren ( ).get (0), getCenterPosCol (i), getCenterPosRow (i));
        }
    }

    private static int getCenterPosRow(int currentDirection) {
        switch (currentDirection) {
            case 0:
                return 0;
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 1;
            default:
                assert (currentDirection >= 0 && currentDirection <= 3);
                break;
        }
        return -1;
    }

    private static int getCenterPosCol(int currentDirection) {
        switch (currentDirection) {
            case 0:
                return 1;
            case 1:
                return 2;
            case 2:
                return 1;
            case 3:
                return 0;
            default:
                assert (currentDirection >= 0 && currentDirection <= 3);
                break;
        }
        return -1;
    }

    private static Button getClickableImage(int dummyDirection, int currentDirection, Deck players[],
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

    private static HBox getHBoxCards(int dummyDirection, int currentDirection, Deck players[],
                                     Deck deck, Image[] images, int height,
                                     boolean clickable, boolean reveal) {
        HBox hBox = new HBox (-1 * height);
        if (reveal) {
            if (clickable) for (Card card :
                    deck.deck) {
                Image image = images[card.nvalue];
                hBox.getChildren ( ).add (getClickableImage (dummyDirection, currentDirection, players, image,
                        card, height));
            }
            else for (Card card :
                    deck.deck) {
                Image image = images[card.nvalue];
                ImageView imageView = new ImageView (image);
                imageView.setPreserveRatio (true);
                imageView.setFitHeight (height);
                hBox.getChildren ( ).add (imageView);
            }
        } else {
            for (Card aDeck : deck.deck) {
                ImageView imageView = new ImageView (getImage ("b"));
                hBox.getChildren ( ).add (imageView);
            }
        }
//        Label direction = new Label (Bid.nDirectiontolsDirection (currentDirection));
//        direction.setRotate (180);
//        hBox.getChildren ( ).add (0, direction);
//        hBox.getChildren ().add (1,new Label ());
        return hBox;
    }

    private static VBox getVBoxCards(Deck deck, Image[] images,
                                     boolean right, int height,
                                     boolean reveal, int currentDirection) {
        VBox vBox = new VBox (-1 * height * 9 / 7);
        if (reveal) for (Card card :
                deck.deck) {
            Image image = images[card.nvalue];
            ImageView imageView = new ImageView (image);
            if (right) imageView.setRotate (90);
            else imageView.setRotate (-90);
            vBox.getChildren ( ).add (imageView);
        }
        else for (Card aDeck : deck.deck) {
            ImageView imageView = new ImageView (getImage ("b"));
            if (right) imageView.setRotate (90);
            else imageView.setRotate (-90);
            vBox.getChildren ( ).add (imageView);
        }
        Label direction = new Label (Bid.nDirectiontolsDirection (currentDirection));
        if (!right) direction.setRotate (180);
        vBox.getChildren ( ).add (0, direction);
        return vBox;
    }

    private static VBox getVBoxDummyCards(int dummyDirection, int currentDirection, Deck players[],
                                          Deck deck, Image[] images, int height, boolean right,
                                          boolean clickable, boolean reveal) {
        Deck seperated[] = Deck.seperateBySuit (players[dummyDirection]);
        VBox vBox = new VBox ( );
        for (int i = 3; i >= 0; i--)
            vBox.getChildren ( ).add (getHBoxCards (dummyDirection, currentDirection,
                    players, seperated[i], images, height, clickable, reveal));
        Label direction = new Label (Bid.nDirectiontolsDirection (currentDirection));
        if (!right) direction.setRotate (180);
        vBox.getChildren ( ).add (0, direction);
        if (right) vBox.setRotate (180);
        return vBox;
    }
}
