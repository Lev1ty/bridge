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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import logic.Bid;
import logic.Card;
import logic.Deck;
import logic.Score;

/**
 * Created by Adam on 1/7/2016.
 */
public class DeckStage {
    public static Stage stage;//window of deckstage
    public static boolean show;//show deckstage
    public static Bid contractBid;//game binding bid
    public static Deck deckHistory;//played cards
    public static Deck winningTricks;//winning tricks
    private static Image images[];//array of images of cards
    private static GridPane gridPaneCenter;//center layout

    public DeckStage() {
        initDeckStage ( );
    }//init deckstage

    public DeckStage(Deck players[], int currentDirection, boolean preplay, boolean showdummy) {
        start (players, currentDirection, currentDirection, -1, preplay, showdummy);//call main method
        DeckStage.show = false;//set to false to avoid flickering
    }

    public DeckStage(Deck players[], int currentDirection, int dummyDirection, boolean showdummy) {
        dummyDirection %= 4;//keep dummy in rnage
        currentDirection %= 4;//keep current direction in range
        start (players, currentDirection, currentDirection, dummyDirection, false, showdummy);//call main method
        DeckStage.show = false;//set to false to avoid flickering
    }

    public DeckStage(Deck players[], int currentDirection, int dummyDirection, Bid contractBid, boolean showdummy) {
        DeckStage.contractBid = contractBid;//set global
        for (int i = 0; i < 4; i++)//comb through decks and set priority suit aka trump suit
            for (int j = 0; j < players[i].deck.length; j++)
                players[i].deck[j].nSuittoprioritySuit (contractBid.nsuit);
        for (int i = 0; i < 4; i++) players[i].deck = Deck.sortBySuit (players[i].deck);//sort all decks by priority suit
        new DeckStage (players, currentDirection, dummyDirection, showdummy);//get another version of deckstage to start
    }

    public static void initDeckStage() {
        //deckstage stage
        DeckStage.stage = new Stage ( );//initialize to new stage
        DeckStage.stage.setTitle ("Bridge");
        DeckStage.stage.setOpacity (0.9);
        DeckStage.stage.setMinHeight (675);
        DeckStage.stage.setMinWidth (1200);
        DeckStage.stage.sizeToScene ( );//size stage to scene
        DeckStage.stage.setOnCloseRequest (event -> {
            event.consume ( );//consume overrides closing
            if (ConfirmBox.display ("Close", "End game?")) stage.close ( );//if confirm to close, close
        });

        DeckStage.show = true;//show deckstage for the first time

        DeckStage.images = new Image[52];//initialize image deck
        for (int i = 0; i < 52; i++) DeckStage.images[i] = DeckStage.getImage (i + 1);//initialize images

        gridPaneCenter = new GridPane ( );//intialize center layout
        gridPaneCenter.setAlignment (Pos.CENTER);

        deckHistory = new Deck ( );//init deck history
        deckHistory.deck = Deck.resize (deckHistory.deck, 0);//empty container

        winningTricks = new Deck ( );//init winning tricks
        winningTricks.deck = Deck.resize (winningTricks.deck, 0);//empty container
    }

    public static Image getImage(int nvalue) {
        return getImage (String.valueOf (nvalue));//translate to string and call method
    }

    public static Image getImage(String svalue) {
        Image image = null;
        try {
            image = new Image ("cards\\" + svalue + ".gif");//get with gif ending
        } catch (Exception e) {
            try {
                image = new Image ("cards\\" + svalue + ".jpg");//get with jpg ending
            } catch (Exception e1) {
                e1.printStackTrace ( );//print error stack
                AlertBox.display ("Error", "Image " + svalue + " not found.");//display alert
            }
        }
        return image;
    }

    private static void start(Deck players[], int revealDirection,
                              int currentDirection, int dummyDirection,
                              boolean preplay, boolean showdummy) {
        //WARNING BOILERPLATE CODE BELOW
        BorderPane borderPane = new BorderPane ( );//init layout for scene
        int temp = currentDirection + 2;//currentdirection is across from bottom, displayed direction

        temp %= 4;//keep in range
        HBox topCards = getHBoxCards (dummyDirection, temp, players,//get hand
                players[temp], images, 100, false, (temp == revealDirection || temp == dummyDirection));

        ++temp;
        temp %= 4;
        topCards.setAlignment (Pos.CENTER);
        VBox rightCards = temp == dummyDirection ?//get dummy or normal cards
                getVBoxDummyCards (dummyDirection, temp, players, players[temp], images, 100,
                        true, currentDirection == dummyDirection, true)
                : getVBoxCards (players[(temp %= 4)], images, true, 100, temp == revealDirection, temp %= 4);

        ++temp;
        temp %= 4;
        rightCards.setAlignment (Pos.CENTER);
        HBox bottomCards = getHBoxCards (dummyDirection, temp, players,//get hand
                players[(temp)], images, 100, !preplay, (temp == revealDirection || temp == dummyDirection));

        ++temp;
        temp %= 4;
        bottomCards.setAlignment (Pos.CENTER);
        VBox leftCards = (temp == dummyDirection && showdummy) ?//if hide dummy
                getVBoxDummyCards (dummyDirection, temp, players, players[(temp % 4)], images, 100,
                        false, currentDirection == dummyDirection, true)
                : getVBoxCards (players[temp], images, false, 100, temp == revealDirection, temp % 4);//normal cards
        leftCards.setAlignment (Pos.CENTER);

        //Below code use is to
        //Set cards into groups and then an hbox <- layouts
        //to align the cards properly
        //incorporate directional names with final layout
        Group rightGroup = new Group (rightCards);
        Group leftGroup = new Group (leftCards);
        Group topGroup = new Group (topCards);
        Group bottomGroup = new Group (bottomCards);

        //top cards
        HBox alignTop = new HBox (topGroup);
        alignTop.getChildren ( ).add (new Label ("   "));
        Label topLabel = new Label (" " + Bid.nDirectiontolsDirection ((currentDirection + 2) % 4));
        topLabel.setRotate (180);
        topLabel.setFont (Font.font ("Verdana", 20));
        topLabel.setTextFill (Color.ORANGERED);
        alignTop.getChildren ( ).add (0, topLabel);
        alignTop.setAlignment (Pos.CENTER);
        alignTop.setRotate (180);

        //right cards
        VBox alignRight = new VBox (rightGroup);
        alignRight.setAlignment (Pos.CENTER);

        //bottom cards
        HBox alignBottom = new HBox (bottomGroup);
        alignBottom.getChildren ( ).add (new Label ("   "));
        Label bottomLabel = new Label (Bid.nDirectiontolsDirection ((currentDirection + 4) % 4));
        bottomLabel.setRotate (180);
        bottomLabel.setFont (Font.font ("Verdana", 20));
        bottomLabel.setTextFill (Color.ORANGERED);
        alignBottom.getChildren ( ).add (bottomLabel);
        alignBottom.setAlignment (Pos.CENTER);
        alignBottom.setRotate (180);

        //left cards
        VBox alignLeft = new VBox (leftGroup);
        alignLeft.setRotate (180);
        alignLeft.setAlignment (Pos.CENTER);

        //center gridpane & setting borderpane
        borderPane.setCenter (gridPaneCenter);
        borderPane.setTop (alignTop);
        borderPane.setRight (alignRight);
        borderPane.setBottom (alignBottom);
        borderPane.setLeft (alignLeft);
        BackgroundImage backgroundImage = new BackgroundImage (getImage ("background"), BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize (
                BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, false, true
        ));//set background image

        Background background = new Background (backgroundImage);
        borderPane.setBackground (background);//incorporate background image
        borderPane.setMinSize (1200, 675);
        stage.setScene (new Scene (borderPane));

        if (show) {//if show, display window
            stage.setResizable (false);
            stage.show ( );
        }
    }

    private static void EventHandler(int dummyDirection, int currentDirection, Deck players[], Card card) {
        if (isLegalMove (players, card)) {//check legal move
            players[currentDirection].remove (card.nvalue);//remove from hand
            deckHistory.push_back (card);//push back into history
            if (gridPaneCenter.getChildren ( ).size ( ) == 4) {//if trick is finished
                gridPaneCenter.getChildren ( ).removeAll (gridPaneCenter.getChildren ( ));//remove all elements
                gridPaneCenter.getChildren ( ).add (new ImageView (images[card.nvalue]));//add image view of card
                GridPane.setConstraints (gridPaneCenter.getChildren ( ).get (0),//set position of card
                        getCenterPosCol (currentDirection), getCenterPosRow (currentDirection));
            } else if (gridPaneCenter.getChildren ( ).size ( ) > 0) {//there are existing cards in the center
                gridPaneCenter.getChildren ( ).add (0, new ImageView (images[card.nvalue]));//add in card
                GridPane.setConstraints (gridPaneCenter.getChildren ( ).get (0),//set position
                        getCenterPosCol (currentDirection), getCenterPosRow (currentDirection));
                if (gridPaneCenter.getChildren ( ).size ( ) == 4) {//if gridpane is full
                    Card winner = compare (deckHistory.deck[deckHistory.deck.length - 1],
                            deckHistory.deck[deckHistory.deck.length - 2],
                            deckHistory.deck[deckHistory.deck.length - 3],
                            deckHistory.deck[deckHistory.deck.length - 4]);//compare played cards
                    winningTricks.push_back (winner);//add winning member to winnning tricks container
                    new DeckStage (players, winner.ndirection, dummyDirection, contractBid, true);//display new instance of deckstage
                }
            } else {//no cards in center
                gridPaneCenter.getChildren ( ).add (new ImageView (images[card.nvalue]));//add first card
                GridPane.setConstraints (gridPaneCenter.getChildren ( ).get (0),
                        getCenterPosCol (currentDirection), getCenterPosRow (currentDirection));
            }
            if (isPlayersEmpty (players)) {//out of cards
                stage.close ( );//close stage
//                System.out.println (getWinningTricksbyDirection ( ));
//                contractBid.Print ();
                ScoreStage.display (new Score (contractBid, getWinningTricksbyDirection ( )).calculate ( ), contractBid);//display score
            }
            if (gridPaneCenter.getChildren ( ).size ( ) < 4)//refresh window
                new DeckStage (players, ++currentDirection, dummyDirection, contractBid, true);
        } else {
            AlertBox.display ("Illegal Move", card.lssuit + " does not follow suit " + "(" +//error in played card, display error
                    deckHistory.deck[(deckHistory.deck.length / 4) * 4].lssuit + ").");
        }
    }

    private static int getWinningTricksbyDirection() {
        int dir[] = new int[4];//array of counters
        for (int i = 0; i < dir.length; i++) dir[i] = 0;//initialize array of counters
        for (Card card : winningTricks.deck) ++dir[card.ndirection];//update counters
        return dir[contractBid.ndirection] + dir[(contractBid.ndirection + 2) % 4];//return sum of pair of players
    }

    private static boolean isLegalMove(Deck players[], Card card) {
        if (deckHistory.deck.length % 4 == 0) return true;//first card of trick
        else {
            int nsuit = deckHistory.deck[(deckHistory.deck.length / 4) * 4].nsuit;
            if (card.nsuit == nsuit) {//if card matches suit
                return true;
            } else if (!Deck.isThereSuit (players[card.ndirection], nsuit)) {//if out of cards to fail follow suit
                return true;
            }
        }
        return false;
    }

    private static Card compare(Card... cards) {
        Card maxCard = cards[0];//init max card
        int auctionBidSuit = 3 - contractBid.nsuit;
        for (Card card : cards) {//comb through cards to get max card
            if (maxCard.nsuit == auctionBidSuit) {
                if (card.nsuit == auctionBidSuit && card.nrank >= maxCard.nrank) {//trump vs nontrump
                    maxCard = card;
                }
            } else if (maxCard.nsuit < auctionBidSuit || maxCard.nsuit > auctionBidSuit) {//trump vs trump
                if (card.nsuit == auctionBidSuit) {
                    maxCard = card;
                } else if (card.nrank >= maxCard.nrank) {//vs
                    maxCard = card;
                }
            }
        }
        return maxCard;
    }

    private static boolean isPlayersEmpty(Deck players[]) {
        for (Deck aDeck : players)//check if there is a hand that's empty
            if (aDeck.deck.length > 0)
                return false;
        return true;
    }

    private static int getCenterPosRow(int currentDirection) {
        switch (currentDirection) {//return x alignment value for the center gridpane corresponding to direction
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
        switch (currentDirection) {//return corresponding y coordinate value for center grid pane
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

        //set background of button to card so button is a clickable card
        BackgroundImage backgroundImage = new BackgroundImage (image,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background background = new Background (backgroundImage);
        button.setBackground (background);

        //set button reaction
        button.setOnAction (event -> EventHandler (dummyDirection, currentDirection, players, card));//set click reaction
        return button;
    }

    private static HBox getHBoxCards(int dummyDirection, int currentDirection, Deck players[],
                                     Deck deck, Image[] images, int height,
                                     boolean clickable, boolean reveal) {
        HBox hBox = new HBox (-1 * height);
        if (reveal) {
            if (clickable) for (Card card :
                    deck.deck) {//get clickable image
                Image image = images[card.nvalue];
                hBox.getChildren ( ).add (getClickableImage (dummyDirection, currentDirection, players, image,
                        card, height));
            }
            else for (Card card :
                    deck.deck) {//get boring pictures
                Image image = images[card.nvalue];
                ImageView imageView = new ImageView (image);
                imageView.setPreserveRatio (true);
                imageView.setFitHeight (height);
                hBox.getChildren ( ).add (imageView);
            }
        } else {
            for (Card aDeck : deck.deck) {//card back
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
        VBox vBox = new VBox (-1 * height * 9 / 7);//set width spacing between cards
        if (reveal) for (Card card :
                deck.deck) {
            Image image = images[card.nvalue];//get image with value
            ImageView imageView = new ImageView (image);
            if (right) imageView.setRotate (90);//right vs left rotation
            else imageView.setRotate (-90);
            vBox.getChildren ( ).add (imageView);
        }
        else for (Card aDeck : deck.deck) {//get back
            ImageView imageView = new ImageView (getImage ("b"));//set imag eto card back
            if (right) imageView.setRotate (90);//right vs left rotation
            else imageView.setRotate (-90);
            vBox.getChildren ( ).add (imageView);
        }

        Label direction = new Label (Bid.nDirectiontolsDirection (currentDirection));//add direction to hbox
        direction.setFont (Font.font ("Verdana", 20));
        direction.setTextFill (Color.GHOSTWHITE);
        if (!right) direction.setRotate (180);//patching for rotation
        vBox.getChildren ( ).add (0, direction);

        return vBox;
    }

    private static VBox getVBoxDummyCards(int dummyDirection, int currentDirection, Deck players[],
                                          Deck deck, Image[] images, int height, boolean right,
                                          boolean clickable, boolean reveal) {
        Deck seperated[] = Deck.seperateBySuit (players[dummyDirection]);//get seperated by suit style
        VBox vBox = new VBox ( );

        for (int i = 3; i >= 0; i--)//add in order to be displayed
            vBox.getChildren ( ).add (getHBoxCards (dummyDirection, currentDirection,
                    players, seperated[i], images, height, clickable, reveal));
        Label direction = new Label (Bid.nDirectiontolsDirection (currentDirection));//name the direction of player

        //patch for rotation
        if (!right) direction.setRotate (180);
        vBox.getChildren ( ).add (0, direction);
        if (right) vBox.setRotate (180);
        return vBox;
    }
}
