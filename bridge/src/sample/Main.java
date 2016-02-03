//INSTRUCTIONS OMITTED BUT CAN BE FOUND IN CARDS FOLDER
package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
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
import javafx.scene.text.FontPosture;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.InputStream;
import java.util.ArrayList;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }//launches start

    @Override
    public void start(Stage primaryStage) throws Exception {
//        region Score testing
//        Bid bid = new Bid ( );
//        bid.slevel = "2";
//        bid.nsuit = 1;
//        bid.x = false;
//        bid.xx = false;
//        Score score = new Score (bid, 8);
//        ScoreStage.display (score.calculate (),bid);
//        endregion
        switch (MenuStage.display()) {//launch according to menu return
            case 2: {
                int rngdir1 = (int) (Math.random() * 100 % 4);//get direction
                Deck masterDeck = new Deck();//get complete deck
                Deck players[] = new Deck[4];//initialize players' hands
                for (int i = 0; i < 4; i++) players[i] = new Deck(i, masterDeck);//initialize players' hands

                //initializes stages
                new DeckStage();
                new BidStage();
                Stage stage = new Stage();//get new stage
                stage.setOpacity(0.75);//opacity is .75

                new BidStage(masterDeck, players, stage, new Stage(), rngdir1, 1, 7, 0, 4);//start bidding

                break;
            }
            default:
                break;
        }
    }
}

class BidStage {
    public static Auction auction;//container that stores bids
    private static boolean show;//if true show screen, prevent reopening to prevent flickering

    public BidStage() {
        initAuction();
    }//default constructor initializes static fields

    public BidStage(Deck masterDeck, DeckStage deckStage, Deck[] decksDeckStage, Stage stage, Stage stage1,
                    String sdirection, int startLevel, int endLevel, int startSuit, int endSuit) {
        start(masterDeck, deckStage, decksDeckStage, stage, stage1,
                Bid.sDirectiontonDirection(sdirection), sdirection,
                startLevel, endLevel, startSuit, endSuit);//call main method
        BidStage.show = false;//set show to false to prevent flickering
    }

    public BidStage(Deck masterDeck, Deck[] decksDeckStage, Stage stage, Stage stage1, int ndirection,
                    int startLevel, int endLevel, int startSuit, int endSuit) {
        new BidStage(masterDeck,
                new DeckStage(decksDeckStage, ndirection, true, true),
                decksDeckStage, stage, stage1,
                Bid.nDirectiontosDirection(ndirection),
                startLevel, endLevel, startSuit, endSuit);//call main method
        BidStage.show = false;//set show to false
        DeckStage.show = false;//deckstage set show to false
    }

    public static void initAuction() {
        BidStage.show = true;//show the stage
        auction = new Auction();//initialize auction
    }

    private static void start(Deck masterDeck, DeckStage deckStage, Deck[] decksDeckStage, Stage stage, Stage stage1,
                              int ndirection, String sdirection, int startLevel, int endLevel, int startSuit, int endSuit) {
        //init
        GridPane gridPane = new GridPane();//new layout
        gridPane.getChildren().addAll(getGridePane(masterDeck, deckStage, decksDeckStage, stage, stage1, ndirection,
                10, startLevel, endLevel, startSuit, endSuit, 5, 10, 5, 10));
        Scene primaryScene = new Scene(gridPane);//set scene to layout
        stage.setTitle("Bid from " + sdirection);//set title
        stage.setScene(primaryScene);//set stage to scene

        //reaction to close button
        stage.setOnCloseRequest(event -> {
            event.consume();//override close
            if (ConfirmBox.display("Confirm", "End Auction Prematurely?")) {//if confirm is true
                stage.close();
                stage1.close();
            }
        });

        //init layout for record of bids
        GridPane gridPane1 = new GridPane();//layout for record of bids
        gridPane1.setPadding(new Insets(10, 10, 10, 10));//border spacing

        //labels
        Label north = getLabel("North");
        Label east = getLabel("East");
        Label south = getLabel("South");
        Label west = getLabel("West");

        //set label pos
        gridPane1.add(north, 0, 0);
        gridPane1.add(east, 1, 0);
        gridPane1.add(south, 2, 0);
        gridPane1.add(west, 3, 0);

        //insert content into layout
        int row = 1;//initialize row counter
        for (Bid bid : auction.auction)
            gridPane1.add(translateToLabel(bid), bid.ndirection, (bid.ndirection == 3 ? row++ : row));//if last col, goto next row
        Scene secondaryScene = new Scene(gridPane1);//set scene to layout
        secondaryScene.setFill(Color.TRANSPARENT);//set scene to be transparent
        stage1.setScene(secondaryScene);//set stage to scene
        stage1.setTitle("Bid History");//set title

        //init of stage use only
        if (show) {
            stage1.initStyle(StageStyle.TRANSPARENT);//set stage to transparent
            stage1.setAlwaysOnTop(true);//set always on top
            stage1.show();//open window
            stage.setResizable(false);//not resizable
            stage.setAlwaysOnTop(true);//set on top
            stage.show();//open window
        }
    }

    private static Label getLabel(String name) {//get label, customize to prigram standards
        Label label = new Label(name);//set label to name
        label.setPadding(new Insets(10, 10, 10, 10));//set border spacing
        label.setFont(Font.font("Verdana", 20));//set font and size
        return label;
    }

    private static Label translateToLabel(Bid bid) {//bid to label
        //check if pass, double, or redouble, print adaptively
        return getLabel(!bid.slevel.equals("Aux") ? bid.slevel + bid.ssuit : bid.ssuit);
    }

    private static void eventHandler(Deck masterDeck, DeckStage deckStage, Deck[] decksDeckStage, Stage stage, Stage stage1, int ndirection, String name) {
        Bid bid = new Bid(name, ndirection);//push back bid and the direction of player who bid it
        if (ConfirmBox.display("Confirm", "Is " + bid.svalue + " your Final Bid?")) {//confirm
            //counters and switches
            switch (name.charAt(0)) {
                case 'P':
                    ++auction.npass;//increment pass counter
                    break;
                case 'D':
                    auction.x = true;//double switch on
                    auction.npass = 0;//reset pass counter
                    break;
                case 'R':
                    auction.xx = true;//redouble switch on
                    auction.npass = 0;//reset pass counter
                    break;
                default:
                    break;
            }

            //updating
            auction.push_back(bid.nvalue, ndirection);//insert bid into auction container
            ++ndirection;//move to next container
            ndirection %= 4;//keep counter in range
            ++auction.nbid;//add counter to number of bids

            //end and continue auction scenarios
            if ((auction.nbid == 4 && auction.npass == 4)) {//pass out
                //close windows
                stage.close();
                stage1.close();
                DeckStage.stage.close();
                AlertBox.display("Auction", "Auction Passed Out.");//alert players
            } else if (auction.bcontract && auction.npass >= 3 && auction.nbid >= 4) {
                //close windows
                stage.close();
                stage1.close();
                DeckStage.stage.close();
                //prepare deck stage
                DeckStage.show = true;
                DeckStage.initDeckStage();
                //call deck stage
                new DeckStage(decksDeckStage, auction.getContractBid().ndirection + 1,
                        auction.getContractBid().ndirection + 2, auction.getContractBid(), false);
            } else {
                if (bid.nvalue >= 35) {//aux bid
                    Bid lastContractBid = new Bid();
                    boolean found = false;
                    for (int i = auction.auction.size() - 1; i >= 0; i--)//find last contract bid
                        if (auction.auction.get(i).nvalue < 35) {
                            lastContractBid = auction.auction.get(i);
                            found = true;
                            break;
                        }
                    if (found) {//set limitations to last contract bid
                        new BidStage(masterDeck, decksDeckStage, stage, stage1, ndirection, lastContractBid.nlevel + 1, 7, lastContractBid.nsuit + 1, 4);
                    } else {//set limitations to default bid due to lack of contract bid
                        new BidStage(masterDeck, decksDeckStage, stage, stage1, ndirection, 1, 7, 0, 4);
                    }
                } else {//initialize next bid stage with updated values
                    new BidStage(masterDeck, decksDeckStage, stage, stage1, ndirection, bid.nlevel + 1, 7, bid.nsuit + 1, 4);
                }
            }
        }
    }

    private static GridPane getGridePane(Deck masterDeck, DeckStage deckStage, Deck[] decksDeckStage, Stage stage, Stage stage1, int ndirection,
                                         int padding, int startLevel, int endLevel, int startSuit, int endSuit,
                                         int top, int right, int bottom, int left) {
        //init
        GridPane gridPane = new GridPane();//new grid pane
        gridPane.setPadding(new Insets(top, right, bottom, left));//set border spacing
        Button auxBid[] = new Button[3];//initialize auxillary bid buttons

        //new aux buttons and set event handlers corresponding to those
        auxBid[0] = new Button("Pass");
        auxBid[0].setOnAction(event -> eventHandler(masterDeck, deckStage, decksDeckStage, stage, stage1, ndirection, "Pass"));
        auxBid[1] = new Button("Double");
        auxBid[1].setOnAction(event -> {
            if (auction.bcontract)//range checking
                eventHandler(masterDeck, deckStage, decksDeckStage, stage, stage1, ndirection, "Double");
            else AlertBox.display("Double", "Cannot double before a contract bid. (e.g. 1NT)");
        });
        auxBid[2] = new Button("Redouble");
        auxBid[2].setOnAction(event -> {
            if (auction.x)//range checking
                eventHandler(masterDeck, deckStage, decksDeckStage, stage, stage1, ndirection, "Redouble");
            else AlertBox.display("Redouble", "Cannot redouble before double.");
        });

        //add aux buttons
        HBox hBoxArr[] = new HBox[endLevel - startLevel + 2];//adjust to limitations on bidding
        hBoxArr[0] = new HBox(1.825 * padding);//horizontal spacing
        hBoxArr[0].getChildren().addAll(auxBid);//add aux bid
        hBoxArr[0].setPadding(new Insets(top, right, bottom, left));//set padding
        GridPane.setConstraints(hBoxArr[0], 0, 0);//set positions of buttons for auz

        //region Modified getBidRow
        Button buttonFirstRow[] = new Button[endSuit - startSuit + 1];//button array for first row, row that's not standard
        for (int i = startSuit, j = 0; i <= endSuit; i++, j++) {
            String name = String.valueOf(startLevel);
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
            buttonFirstRow[j] = new Button(name);//set button
            final String finalName = name;//set name of button
            buttonFirstRow[j].setOnAction(event -> {//set event handler for button
                auction.npass = 0;
                auction.bcontract = true;
                eventHandler(masterDeck, deckStage, decksDeckStage, stage, stage1, ndirection, finalName);
            });
        }
        //endregion

        //rest of rows
        if (hBoxArr.length >= 2) {//if bidding requires standard button rows
            //incomplete row
            hBoxArr[1] = new HBox(padding);
            hBoxArr[1].setPadding(new Insets(top, right, bottom, left));//set border spacing
            hBoxArr[1].getChildren().addAll(buttonFirstRow);//add buttons to hbox
            GridPane.setConstraints(hBoxArr[1], 0, 1);//set positions

            //complete rows
            for (int i = startLevel + 1, j = 2; i <= endLevel; i++, j++) {
                hBoxArr[j] = getBidRow(masterDeck, deckStage, decksDeckStage, stage, stage1,
                        ndirection, i, padding, top, right, bottom, left);//get standard rows (rows that are complete)
                GridPane.setConstraints(hBoxArr[j], 0, j + 1);//set positions
            }
        }
        gridPane.getChildren().addAll(hBoxArr);//add hbox array to grid pane
        return gridPane;
    }

    private static HBox getBidRow(Deck masterDeck, DeckStage deckStage, Deck[] decksDeckStage,
                                  Stage stage, Stage stage1, int ndirection, int level, int padding,
                                  int top, int right, int bottom, int left) {
        Button arr[] = new Button[5];//new standard button row
        HBox hBox = new HBox(padding);//set horizontal spacing
        hBox.setPadding(new Insets(top, right, bottom, left));//set border spacing
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

            //add reaction to button
            arr[i] = new Button(name);//set button's name
            final String finalName = name;
            arr[i].setOnAction(event -> {
                auction.npass = 0;//reset pass counter
                auction.bcontract = true;//set switch for contract to be true
                eventHandler(masterDeck, deckStage, decksDeckStage, stage, stage1, ndirection, finalName);//call event handler
            });
        }
        hBox.getChildren().addAll(arr);//add button array to hbox
        return hBox;
    }
}

class DeckStage {
    public static Stage stage;//window of deckstage
    public static boolean show;//show deckstage
    public static Bid contractBid;//game binding bid
    public static Deck deckHistory;//played sample.cards
    public static Deck winningTricks;//winning tricks
    private static Image images[];//array of images of sample.cards
    private static GridPane gridPaneCenter;//center layout

    public DeckStage() {
        initDeckStage();
    }//init deckstage

    public DeckStage(Deck players[], int currentDirection, boolean preplay, boolean showdummy) {
        start(players, currentDirection, currentDirection, -1, preplay, showdummy);//call main method
        DeckStage.show = false;//set to false to avoid flickering
    }

    public DeckStage(Deck players[], int currentDirection, int dummyDirection, boolean showdummy) {
        dummyDirection %= 4;//keep dummy in rnage
        currentDirection %= 4;//keep current direction in range
        start(players, currentDirection, currentDirection, dummyDirection, false, showdummy);//call main method
        DeckStage.show = false;//set to false to avoid flickering
    }

    public DeckStage(Deck players[], int currentDirection, int dummyDirection, Bid contractBid, boolean showdummy) {
        DeckStage.contractBid = contractBid;//set global
        for (int i = 0; i < 4; i++)//comb through decks and set priority suit aka trump suit
            for (int j = 0; j < players[i].deck.length; j++)
                players[i].deck[j].nSuittoprioritySuit(contractBid.nsuit);
        for (int i = 0; i < 4; i++) players[i].deck = Deck.sortBySuit(players[i].deck);//sort all decks by priority suit
        new DeckStage(players, currentDirection, dummyDirection, showdummy);//get another version of deckstage to start
    }

    public static void initDeckStage() {
        //deckstage stage
        DeckStage.stage = new Stage();//initialize to new stage
        DeckStage.stage.setTitle("Bridge");
        DeckStage.stage.setOpacity(0.9);
        DeckStage.stage.setMinHeight(675);
        DeckStage.stage.setMinWidth(1200);
        DeckStage.stage.sizeToScene();//size stage to scene
        DeckStage.stage.setOnCloseRequest(event -> {
            event.consume();//consume overrides closing
            if (ConfirmBox.display("Close", "End game?")) stage.close();//if confirm to close, close
        });

        DeckStage.show = true;//show deckstage for the first time

        DeckStage.images = new Image[52];//initialize image deck
        for (int i = 0; i < 52; i++) DeckStage.images[i] = DeckStage.getImage(i + 1);//initialize images

        gridPaneCenter = new GridPane();//intialize center layout
        gridPaneCenter.setAlignment(Pos.CENTER);

        deckHistory = new Deck();//init deck history
        deckHistory.deck = Deck.resize(deckHistory.deck, 0);//empty container

        winningTricks = new Deck();//init winning tricks
        winningTricks.deck = Deck.resize(winningTricks.deck, 0);//empty container
    }

    public static Image getImage(int nvalue) {
        return getImage(String.valueOf(nvalue));//translate to string and call method
    }

    public static Image getImage(String svalue) {
        Image image = null;
        try {
            //for gif
            InputStream inputStream = MenuStage.class.getResourceAsStream(svalue + ".gif");
            image = new Image(inputStream);
        } catch (Exception e) {
            try {
                //for jpg
                InputStream inputStream = MenuStage.class.getResourceAsStream(svalue + ".jpg");
                image = new Image(inputStream);
            } catch (Exception e1) {
                e1.printStackTrace();//print error stack
                AlertBox.display("Error", "Image " + svalue + " not found.");//display alert
            }
        }
        return image;
    }

    private static void start(Deck players[], int revealDirection,
                              int currentDirection, int dummyDirection,
                              boolean preplay, boolean showdummy) {
        //WARNING BOILERPLATE CODE BELOW
        BorderPane borderPane = new BorderPane();//init layout for scene
        int temp = currentDirection + 2;//currentdirection is across from bottom, displayed direction

        temp %= 4;//keep in range
        HBox topCards = getHBoxCards(dummyDirection, temp, players,//get hand
                players[temp], images, 100, false, (temp == revealDirection || temp == dummyDirection));

        ++temp;
        temp %= 4;
        topCards.setAlignment(Pos.CENTER);
        VBox rightCards = temp == dummyDirection ?//get dummy or normal sample.cards
                getVBoxDummyCards(dummyDirection, temp, players, players[temp], images, 100,
                        true, currentDirection == dummyDirection, true)
                : getVBoxCards(players[(temp %= 4)], images, true, 100, temp == revealDirection, temp %= 4);

        ++temp;
        temp %= 4;
        rightCards.setAlignment(Pos.CENTER);
        HBox bottomCards = getHBoxCards(dummyDirection, temp, players,//get hand
                players[(temp)], images, 100, !preplay, (temp == revealDirection || temp == dummyDirection));

        ++temp;
        temp %= 4;
        bottomCards.setAlignment(Pos.CENTER);
        VBox leftCards = (temp == dummyDirection && showdummy) ?//if hide dummy
                getVBoxDummyCards(dummyDirection, temp, players, players[(temp % 4)], images, 100,
                        false, currentDirection == dummyDirection, true)
                : getVBoxCards(players[temp], images, false, 100, temp == revealDirection, temp % 4);//normal sample.cards
        leftCards.setAlignment(Pos.CENTER);

        //Below code use is to
        //Set sample.cards into groups and then an hbox <- layouts
        //to align the sample.cards properly
        //incorporate directional names with final layout
        Group rightGroup = new Group(rightCards);
        Group leftGroup = new Group(leftCards);
        Group topGroup = new Group(topCards);
        Group bottomGroup = new Group(bottomCards);

        //top sample.cards
        HBox alignTop = new HBox(topGroup);
        alignTop.getChildren().add(new Label("   "));
        Label topLabel = new Label(" " + Bid.nDirectiontolsDirection((currentDirection + 2) % 4));
        topLabel.setRotate(180);
        topLabel.setFont(Font.font("Verdana", 20));
        topLabel.setTextFill(Color.ORANGERED);
        alignTop.getChildren().add(0, topLabel);
        alignTop.setAlignment(Pos.CENTER);
        alignTop.setRotate(180);

        //right sample.cards
        VBox alignRight = new VBox(rightGroup);
        alignRight.setAlignment(Pos.CENTER);

        //bottom sample.cards
        HBox alignBottom = new HBox(bottomGroup);
        alignBottom.getChildren().add(new Label("   "));
        Label bottomLabel = new Label(Bid.nDirectiontolsDirection((currentDirection + 4) % 4));
        bottomLabel.setRotate(180);
        bottomLabel.setFont(Font.font("Verdana", 20));
        bottomLabel.setTextFill(Color.ORANGERED);
        alignBottom.getChildren().add(bottomLabel);
        alignBottom.setAlignment(Pos.CENTER);
        alignBottom.setRotate(180);

        //left sample.cards
        VBox alignLeft = new VBox(leftGroup);
        alignLeft.setRotate(180);
        alignLeft.setAlignment(Pos.CENTER);

        //center gridpane & setting borderpane
        borderPane.setCenter(gridPaneCenter);
        borderPane.setTop(alignTop);
        borderPane.setRight(alignRight);
        borderPane.setBottom(alignBottom);
        borderPane.setLeft(alignLeft);
        BackgroundImage backgroundImage = new BackgroundImage(getImage("background"), BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(
                BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, false, true
        ));//set background image

        Background background = new Background(backgroundImage);
        borderPane.setBackground(background);//incorporate background image
        borderPane.setMinSize(1200, 675);
        stage.setScene(new Scene(borderPane));

        if (show) {//if show, display window
            stage.setResizable(false);
            stage.show();
        }
    }

    private static void EventHandler(int dummyDirection, int currentDirection, Deck players[], Card card) {
        if (isLegalMove(players, card)) {//check legal move
            players[currentDirection].remove(card.nvalue);//remove from hand
            deckHistory.push_back(card);//push back into history
            if (gridPaneCenter.getChildren().size() == 4) {//if trick is finished
                gridPaneCenter.getChildren().removeAll(gridPaneCenter.getChildren());//remove all elements
                gridPaneCenter.getChildren().add(new ImageView(images[card.nvalue]));//add image view of card
                GridPane.setConstraints(gridPaneCenter.getChildren().get(0),//set position of card
                        getCenterPosCol(currentDirection), getCenterPosRow(currentDirection));
            } else if (gridPaneCenter.getChildren().size() > 0) {//there are existing sample.cards in the center
                gridPaneCenter.getChildren().add(0, new ImageView(images[card.nvalue]));//add in card
                GridPane.setConstraints(gridPaneCenter.getChildren().get(0),//set position
                        getCenterPosCol(currentDirection), getCenterPosRow(currentDirection));
                if (gridPaneCenter.getChildren().size() == 4) {//if gridpane is full
                    Card winner = compare(deckHistory.deck[deckHistory.deck.length - 1],
                            deckHistory.deck[deckHistory.deck.length - 2],
                            deckHistory.deck[deckHistory.deck.length - 3],
                            deckHistory.deck[deckHistory.deck.length - 4]);//compare played sample.cards
                    winningTricks.push_back(winner);//add winning member to winnning tricks container
                    new DeckStage(players, winner.ndirection, dummyDirection, contractBid, true);//display new instance of deckstage
                }
            } else {//no sample.cards in center
                gridPaneCenter.getChildren().add(new ImageView(images[card.nvalue]));//add first card
                GridPane.setConstraints(gridPaneCenter.getChildren().get(0),
                        getCenterPosCol(currentDirection), getCenterPosRow(currentDirection));
            }
            if (isPlayersEmpty(players)) {//out of sample.cards
                stage.close();//close stage
//                System.out.println (getWinningTricksbyDirection ( ));
//                contractBid.Print ();
                ScoreStage.display(new Score(contractBid, getWinningTricksbyDirection()).calculate(), contractBid);//display score
            }
            if (gridPaneCenter.getChildren().size() < 4)//refresh window
                new DeckStage(players, ++currentDirection, dummyDirection, contractBid, true);
        } else {
            AlertBox.display("Illegal Move", card.lssuit + " does not follow suit " + "(" +//error in played card, display error
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
            } else if (!Deck.isThereSuit(players[card.ndirection], nsuit)) {//if out of sample.cards to fail follow suit
                return true;
            }
        }
        return false;
    }

    private static Card compare(Card... cards) {
        Card maxCard = cards[0];//init max card
        int auctionBidSuit = 3 - contractBid.nsuit;
        for (Card card : cards) {//comb through sample.cards to get max card
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
        Button button = new Button();
        button.setMinSize(height * 17 / 22, height);

        //set background of button to card so button is a clickable card
        BackgroundImage backgroundImage = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background background = new Background(backgroundImage);
        button.setBackground(background);

        //set button reaction
        button.setOnAction(event -> EventHandler(dummyDirection, currentDirection, players, card));//set click reaction
        return button;
    }

    private static HBox getHBoxCards(int dummyDirection, int currentDirection, Deck players[],
                                     Deck deck, Image[] images, int height,
                                     boolean clickable, boolean reveal) {
        HBox hBox = new HBox(-1 * height);
        if (reveal) {
            if (clickable) for (Card card :
                    deck.deck) {//get clickable image
                Image image = images[card.nvalue];
                hBox.getChildren().add(getClickableImage(dummyDirection, currentDirection, players, image,
                        card, height));
            }
            else for (Card card :
                    deck.deck) {//get boring pictures
                Image image = images[card.nvalue];
                ImageView imageView = new ImageView(image);
                imageView.setPreserveRatio(true);
                imageView.setFitHeight(height);
                hBox.getChildren().add(imageView);
            }
        } else {
            for (Card aDeck : deck.deck) {//card back
                ImageView imageView = new ImageView(getImage("b"));
                hBox.getChildren().add(imageView);
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
        VBox vBox = new VBox(-1 * height * 9 / 7);//set width spacing between sample.cards
        if (reveal) for (Card card :
                deck.deck) {
            Image image = images[card.nvalue];//get image with value
            ImageView imageView = new ImageView(image);
            if (right) imageView.setRotate(90);//right vs left rotation
            else imageView.setRotate(-90);
            vBox.getChildren().add(imageView);
        }
        else for (Card aDeck : deck.deck) {//get back
            ImageView imageView = new ImageView(getImage("b"));//set imag eto card back
            if (right) imageView.setRotate(90);//right vs left rotation
            else imageView.setRotate(-90);
            vBox.getChildren().add(imageView);
        }

        Label direction = new Label(Bid.nDirectiontolsDirection(currentDirection));//add direction to hbox
        direction.setFont(Font.font("Verdana", 20));
        direction.setTextFill(Color.GHOSTWHITE);
        if (!right) direction.setRotate(180);//patching for rotation
        vBox.getChildren().add(0, direction);

        return vBox;
    }

    private static VBox getVBoxDummyCards(int dummyDirection, int currentDirection, Deck players[],
                                          Deck deck, Image[] images, int height, boolean right,
                                          boolean clickable, boolean reveal) {
        Deck seperated[] = Deck.seperateBySuit(players[dummyDirection]);//get seperated by suit style
        VBox vBox = new VBox();

        for (int i = 3; i >= 0; i--)//add in order to be displayed
            vBox.getChildren().add(getHBoxCards(dummyDirection, currentDirection,
                    players, seperated[i], images, height, clickable, reveal));
        Label direction = new Label(Bid.nDirectiontolsDirection(currentDirection));//name the direction of player

        //patch for rotation
        if (!right) direction.setRotate(180);
        vBox.getChildren().add(0, direction);
        if (right) vBox.setRotate(180);
        return vBox;
    }
}

class MenuStage {
    private static Image backgroundImage = getImage("CardTower", "jpg");//background image
    private static GridPane gridPane = new GridPane();//get grid pane layout
    private static StackPane stackPane = new StackPane(gridPane);//root grid pane lyout to stackPane
    private static Scene scene = new Scene(stackPane);//set scene to format and content of stackPane
    private static Stage stage = new Stage();//set content of stage to scene
    private static int result;//choice result to be used in MAIN

    public static int display() {
        //background image
        gridPane.setBackground(new Background(new BackgroundImage(backgroundImage,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
        gridPane.setPadding(new Insets(100, 200, 100, 100));

        //title
        Label title = new Label("Bridge");
        title.setFont(Font.font("Verdana", FontPosture.ITALIC, 50));
        title.setTextFill(Color.ORANGERED);

        //buttons on menu stage
        int row = 0, col = 0;
        gridPane.add(title, row, col++);
        gridPane.add(getButton(0, getButton()), row, col++);
        gridPane.add(getButton("Start", getButton()), row, col++);
        gridPane.add(getButton(0, getButton()), row, col++);
        gridPane.add(getButton("Exit", getButton()), row, col);

        //set stage title
        stage.setTitle("Menu");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initStyle(StageStyle.TRANSPARENT);//remove borders
        stage.showAndWait();//pause thread for input
        return result;
    }

    private static void eventHandler(String name) {
        stage.close();//close stage and then decide return result based on name of button
        switch (name.charAt(0)) {
            case 'S':
                result = 2;
                break;
            case 'E':
                result = 1;
                break;
            case 'I':
                result = 3;
                break;
            default:
                result = 0;
                break;
        }
    }

    private static Button getButton() {
        //standard customizations to menu stage for every button
        Button button = new Button();
        button.setPadding(new Insets(10, 10, 10, 10));//border spacing
        button.setPrefWidth(90);//uniform button width
        return button;
    }

    private static Button getButton(String name, Button button) {
        button.setText(name);//set button display name
        button.setOnAction(event -> eventHandler(name));//set reaction to click
        return button;
    }

    private static Button getButton(int opacity, Button button) {
        button.setOpacity(opacity);//customize button opactiy
        return button;
    }

    public static Image getImage(String name, String ending) {
        Image image = null;
        try {
            InputStream inputStream = MenuStage.class.getResourceAsStream(name + "." + ending);
//            image = new Image("sample\\" + name + "." + ending);//get image from sample.cards folder
            image = new Image(inputStream);
        } catch (Exception e) {
            System.out.println("Image not Found in MenuStage getImage.");//error msg
            e.printStackTrace();
        }
        return image;
    }
}

class ScoreStage {
    private static StackPane stackPane;//root layout
    private static Scene scene;//content of stage
    private static Stage stage = new Stage();//window
    private static Image image;//backgroudn image

    public static int display(int score, Bid contractBid) {
        //init background image
        image = MenuStage.getImage("StephCurry", "gif");
        Image image = ScoreStage.image;

        //Label for points and direction
        Label label = new Label(contractBid.sdirection + ": " + score + " Points");
        label.setFont(Font.font("Verdana", 100));
        label.setTextFill(Color.WHITE);
        label.setAlignment(Pos.CENTER);

        //exit button due to lack of window buttona and thus close button
        Button exit = new Button(contractBid.lsdirection + (score > 0 ? " wins!" : " loses!"));
        exit.setOnAction(event -> stage.close());

        //horizontally layed out
        HBox hBox = new HBox(90);
        hBox.getChildren().addAll(label, exit);//add label and exit button to layout
        hBox.setAlignment(Pos.CENTER);

        //set root layout and background image
        stackPane = new StackPane(hBox);
        stackPane.setAlignment(Pos.CENTER);
        stackPane.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

        //set scene to root layout stackpane and stage to scene
        scene = new Scene(stackPane);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);//make window borders disappear
        stage.showAndWait();
        return score;
    }
}

class Auction {
    public ArrayList<Bid> auction;//container for bids
    public int nbid, npass;//counter for pass and bids
    public boolean bcontract, x, xx;//switches for contract, double and redouble

    public Auction() {//default initialization
        auction = new ArrayList<>();
        nbid = 0;
        npass = 0;
        bcontract = false;
        x = false;
        xx = false;
    }

    public Bid getContractBid() {
        int nvalue = -1;
        for (int i = auction.size() - 1; i >= 0; --i)//get auction suit and level
            if (auction.get(i).nvalue < 35) nvalue = auction.get(i).nvalue;
        if (nvalue > -1)//if found, get first instance of the bid with the suit and level
            for (Bid anAuction : auction)
                if (anAuction.nvalue == nvalue) {
                    anAuction.x = x;
                    anAuction.xx = xx;
                    return anAuction;
                }
        return null;//if not found return nothing
    }

    public void push_back(int nvalue, int ndirection) {//wrapper
        auction.add(new Bid(nvalue, ndirection));
    }

    public void printAuction() {
        auction.forEach(Bid::Print);//print auction using method in each bid
    }

    public void printField() {//print globals
        System.out.println("nbid: " + nbid +
                " npass: " + npass +
                " bcontract: " + bcontract +
                " x: " + x +
                " xx: " + xx
        );
    }
}

class Bid {
    // TODO: 1/5/2016 Make bid then make auction wrapper for bid
    public int nvalue, ndirection, nlevel, nsuit;//int data for computation
    public String slevel, ssuit, sdirection, svalue, lsdirection;//string data for display
    public boolean x, xx;//switches for double and redouble

    public Bid() {//default initialization
        nvalue = 0;
        nlevel = 0;
        nsuit = 0;
        ndirection = 0;
        x = false;
        xx = false;
    }

    public Bid(int nvalue, int ndirection) {
        new Bid();

        //set global values
        this.nvalue = nvalue;
        this.ndirection = ndirection;

        //translate
        nValuetonSuitLevel();
        nSuitLeveltosSuitLevel();
        nDirectiontosDirection();
        nDirectiontolsDirection();
    }

    public Bid(String svalue, String sdirection) {
        new Bid();

        //set globals
        this.sdirection = sdirection;
        sValuetonValue(svalue);
        ndirection = sDirectiontonDirection(sdirection);

        //translate
        nValuetonSuitLevel();
        nSuitLeveltosSuitLevel();
        nDirectiontosDirection();
        nDirectiontolsDirection();
    }

    public Bid(String svalue, int ndirection) {
        new Bid();

        //set globals
        this.ndirection = ndirection;

        //translate
        sValuetonValue(svalue);
        nValuetonSuitLevel();
        nSuitLeveltosSuitLevel();
        nDirectiontosDirection();
        nDirectiontolsDirection();
    }

    public static String nDirectiontosDirection(int ndirection) {
        String sdirection = "Error";
        //program internal range checking
        if (ndirection >= 0 && ndirection <= 3) switch (ndirection) {
            case 0:
                sdirection = "N";
                break;
            case 1:
                sdirection = "E";
                break;
            case 2:
                sdirection = "S";
                break;
            case 3:
                sdirection = "W";
                break;
            default:
                assert (ndirection >= 0 && ndirection <= 3);
                break;
        }
        else System.out.println("Range error at class Bid in nDirectiontosDirection.");
        return sdirection;
    }

    public static String nDirectiontolsDirection(int ndirection) {//full form directions
        String sdirection = "Error";
        //program internal range checking
        if (ndirection >= 0 && ndirection <= 3) switch (ndirection) {
            case 0:
                sdirection = "North";
                break;
            case 1:
                sdirection = "East";
                break;
            case 2:
                sdirection = "South";
                break;
            case 3:
                sdirection = "West";
                break;
            default:
                assert (ndirection >= 0 && ndirection <= 3);
                break;
        }
        else System.out.println("Range error at class Bid in nDirectiontolsDirection.");
        return sdirection;
    }

    public static int sDirectiontonDirection(String sdirection) {
        int ndirection = -1;//preset error
        switch (sdirection.charAt(0)) {
            case 'N':
                ndirection = 0;
                break;
            case 'E':
                ndirection = 1;
                break;
            case 'S':
                ndirection = 2;
                break;
            case 'W':
                ndirection = 3;
                break;
            default:
                break;
        }
        return ndirection;
    }

    public static String nSuittosSuit(int nsuit) {//overloaded non-static nSuittosSuit method for external usage
        String ssuit = null;
        //range checking
        if (nsuit >= -3 && nsuit <= 4) switch (nsuit) {
            case -3:
                ssuit = "XX";
                break;
            case -2:
                ssuit = "X";
                break;
            case -1:
                ssuit = "P";
                break;
            case 0:
                ssuit = "C";
                break;
            case 1:
                ssuit = "D";
                break;
            case 2:
                ssuit = "H";
                break;
            case 3:
                ssuit = "S";
                break;
            case 4:
                ssuit = "NT";
                break;
            default:
                assert (nsuit >= -3 && nsuit <= 4);
                break;
        }
        else System.out.println("Overwrite or Range error at class Bid nSuittosSuit.");
        return ssuit;
    }

    public void Print() {//print fields
        System.out.println(
                "nvalue: " + nvalue +
                        " ndirection: " + ndirection +
                        " nlevel: " + nlevel +
                        " nsuit: " + nsuit +
                        " svalue: " + svalue +
                        " slevel: " + slevel +
                        " ssuit: " + ssuit +
                        " sdirection: " + sdirection +
                        " double: " + x +
                        " redouble: " + xx +
                        " lsdireciton: " + lsdirection
        );
    }

    private void sValuetonValue(String svalue) {
        this.svalue = svalue;//set global
        int nlevel = 0, nsuit = 0;
        //auxillary bids
        if (svalue.length() > 3) {
            switch (svalue) {
                case "Pass":
                    nsuit = 35;
                    break;
                case "Double":
                    nsuit = 36;
                    break;
                case "Redouble":
                    nsuit = 37;
                    break;
            }//contract bids
        } else {
            nlevel = svalue.charAt(0) - '1';//get level
            switch (svalue.charAt(1)) {//get suit
                case 'C':
                    nsuit = 0;
                    break;
                case 'D':
                    nsuit = 1;
                    break;
                case 'H':
                    nsuit = 2;
                    break;
                case 'S':
                    nsuit = 3;
                    break;
                case 'N':
                    nsuit = 4;
                    break;
                default:
                    break;
            }
        }//set global nvalue
        this.nvalue = nlevel * 5 + nsuit;
    }

    private void nValuetonSuit() {
        //range checking, overwrite checking
        if (nsuit == 0 && nvalue >= 0 && nvalue <= 37) {
            //auxillary bids
            if (nvalue == 35) nsuit = -1;
            else if (nvalue == 36) nsuit = -2;
            else if (nvalue == 37) nsuit = -3;
                //contract bids
            else nsuit = nvalue % 5;
        } else System.out.println("Overwrite or Range error in class Bid nValuetonSuit.");
    }

    private void nValuetonLevel() {
        //range and overwrite checking
        if (nlevel == 0 && nvalue >= 0 && nvalue <= 37) {
//            if (nvalue >= 35) nlevel = -1;
//            else nlevel = nvalue / 5;
            nlevel = nvalue / 5;//get level
        } else System.out.println("Overwrite or Range error in class Bid nValuetonSuit.");
    }

    private void nValuetonSuitLevel() {//wrapper method
        nValuetonLevel();
        nValuetonSuit();
    }

    private void nSuittosSuit() {
        //overwrite and range checking
        if (ssuit == null && nsuit >= -3 && nsuit <= 4) switch (nsuit) {
            case -3:
                ssuit = "XX";
                break;
            case -2:
                ssuit = "X";
                break;
            case -1:
                ssuit = "P";
                break;
            case 0:
                ssuit = "C";
                break;
            case 1:
                ssuit = "D";
                break;
            case 2:
                ssuit = "H";
                break;
            case 3:
                ssuit = "S";
                break;
            case 4:
                ssuit = "NT";
                break;
            default:
                assert (nsuit >= -3 && nsuit <= 4);
                break;
        }
        else System.out.println("Overwrite or Range error at class Bid nSuittosSuit.");
    }

    private void nLeveltosLevel() {
        //overwrite and range checking
        if (slevel == null && nlevel >= 0 && nlevel <= 7) {
            if (nlevel == 7) slevel = "Aux";//aux level for pass, double, redouble
            else slevel = String.valueOf((char) (nlevel + 1 + '0'));//contract bid
        } else System.out.println("Overwrite or Range error at class Bid nLeveltosLevel.");
    }

    private void nSuitLeveltosSuitLevel() {//wrapper method
        nSuittosSuit();
        nLeveltosLevel();
    }

    private void nDirectiontosDirection() {
        //overwrite and range error checking
        if (sdirection == null && ndirection >= 0 && ndirection <= 3) switch (ndirection) {
            case 0:
                sdirection = "N";
                break;
            case 1:
                sdirection = "E";
                break;
            case 2:
                sdirection = "S";
                break;
            case 3:
                sdirection = "W";
                break;
            default:
                assert (ndirection >= 0 && ndirection <= 3);
                break;
        }
        else System.out.println("Overwrite or Range error at class Bid in nDirectiontosDirection.");
    }

    private void nDirectiontolsDirection() {
        //no overwrite or range checking
        switch (ndirection) {
            case 0:
                lsdirection = "North";
                break;
            case 1:
                lsdirection = "East";
                break;
            case 2:
                lsdirection = "South";
                break;
            case 3:
                lsdirection = "West";
                break;
            default:
                break;
        }
    }
}

class Card {
    //priority suit for arranging suits after trump suit is initialized
    public int nvalue, nsuit, nrank, ndirection, prioritysuit;//int for computation
    public String lssuit, ssuit, srank, sdirection;//string for output

    public Card() {//default
        nvalue = 0;
        nsuit = 0;
        nrank = 0;
        ndirection = 0;
    }

    public Card(int nvalue) {
        new Card();
        this.nvalue = nvalue;//set global

        //translate
        nValuetonSuitRank();
        nSuitRanktosSuitRank();
        nSuittolsSuit();
    }

    public void nDirection(int ndirection) {
        //range and overwrite checking
        if (this.ndirection == 0 && ndirection >= 0 && ndirection <= 3) {
            this.ndirection = ndirection;//set global
            nDirectiontosDirection();//translate
        } else System.out.println("Overwrite or Range error at class Card nDirection.");
    }

    public void Print() {//set fields
        System.out.println(
                "nvalue: " + nvalue +
                        " nrank: " + nrank +
                        " nsuit: " + nsuit +
//                " nindex: " + c.nindex +
                        " ndirection: " + ndirection +
                        " ssuit: " + ssuit +
                        " srank: " + srank +
                        " lssuit: " + lssuit +
                        " sdirection: " + sdirection +
                        " prioritysuit: " + prioritysuit
        );
    }

    //<editor-fold desc="nIndex"> //NOT USED
/*    public void nIndex(int nindex){
        if (nindex>=0&&nindex<=51) this.nindex = nindex;
        else System.out.println("Range error at class Card nIndex.");
    }*/
//</editor-fold>
    public void nSuittoprioritySuit(int bidsuit) {
        //get suit from Bid Class
        //3 - bidsuit because of reverse ordering of suit in Bid Class compared to Card Class
        //range checking
        bidsuit = 3 - bidsuit;
        if (bidsuit == -1) prioritysuit = nsuit;//case of NT, no trump needed
        else if (bidsuit >= 0 && bidsuit <= 3) {
            if (nsuit == bidsuit) prioritysuit = 0;//suit is trump suit, push to highest suit value, 0
                //push back or front according to position relative to trump suit
            else if (nsuit > bidsuit) prioritysuit = nsuit - 1;
            else if (nsuit < bidsuit) prioritysuit = nsuit + 1;
        } else System.out.println("Range error at class Card nSuittoprioritySuit.");
    }

    private void nValuetonSuitRank() {//wrapper method
        nValuetonSuit();
        nValuetonRank();
    }

    private void nValuetonSuit() {
        //range and overwrite checking
        if (nsuit == 0 && nvalue >= 0 && nvalue <= 51) nsuit = nvalue / 13;//13 sample.cards in each suit, so suit = */13
        else System.out.println("Overwrite or Range error at class Card nValuetoSuit.");
    }

    private void nValuetonRank() {
        //overwrite and range checking
        if (nrank == 0 && nvalue >= 0 && nvalue <= 51) nrank = nvalue % 13;//getting rank in a suit, so rank = *%13
        else System.out.println("Overwrite or Range error at class Card nValuetonRank.");
    }

    private void nSuitRanktosSuitRank() {//wrapper method
        nSuittosSuit();
        nRanktosRank();
    }

    private void nSuittosSuit() {
        //range and overwrite checking
        if (ssuit == null && nsuit >= 0 && nsuit <= 3) switch (nsuit) {
            case 0:
                ssuit = "S";
                break;
            case 1:
                ssuit = "H";
                break;
            case 2:
                ssuit = "D";
                break;
            case 3:
                ssuit = "C";
                break;
            default:
                assert (nsuit >= 0 && nsuit <= 3);
                break;
        }
        else System.out.println("Overwrite or Range error at class Card nSuittosSuit.");
    }

    private void nRanktosRank() {
        //range and overwrite checking
        if (srank == null && nrank >= 0 && nrank <= 12) {
            //1 digit char to int cases
            if (nrank <= 7) srank = String.valueOf((char) ('0' + nrank + 2));
            else switch (nrank) {//special cases
                case 8:
                    srank = "10";
                    break;
                case 9:
                    srank = "J";
                    break;
                case 10:
                    srank = "Q";
                    break;
                case 11:
                    srank = "K";
                    break;
                case 12:
                    srank = "A";
                    break;
                default:
                    assert (nrank >= 0 && nrank <= 12);
                    break;
            }
        } else System.out.println("Overwrite or Range error at class Card nRanktosRank.");
    }

    private void nSuittolsSuit() {
        //range and overwrite checking
        if (lssuit == null && nsuit >= 0 && nsuit <= 3) switch (nsuit) {
            case 0:
                lssuit = "Spade";
                break;
            case 1:
                lssuit = "Heart";
                break;
            case 2:
                lssuit = "Diamond";
                break;
            case 3:
                lssuit = "Club";
                break;
            default:
                assert (nsuit >= 0 && nsuit <= 3);
                break;
        }
        else System.out.println("Overwrite or Range error at class Card nSuittolsSuit.");
    }

    private void nDirectiontosDirection() {
        //range and overwrite checking
        if (sdirection == null && ndirection >= 0 && ndirection <= 3) switch (ndirection) {
            case 0:
                sdirection = "N";
                break;
            case 1:
                sdirection = "E";
                break;
            case 2:
                sdirection = "S";
                break;
            case 3:
                sdirection = "W";
                break;
            default:
                assert (ndirection >= 0 && ndirection <= 3);
                break;
        }
        else System.out.println("Overwrite or Range error in Card nDirectiontosDirection.");
    }
}

class Deck {
    public Card deck[];//array for sample.cards

    public Deck() {//default init
        initDeck();
        Shuffle();
        assignDirection();
    }

    public Deck(int ndirection, Deck parentDeck) {//for players' hands
        initDeck(ndirection, parentDeck);
        sortBySuit();
        sortByRank();
    }

    public static Deck[] seperateBySuit(Deck masterDeck) {//seperate hand into suit
        Deck deck[] = new Deck[4];//for 4 suita of a hand

        for (int i = 0; i < 4; i++) {//init deck array
            deck[i] = new Deck();
            deck[i].deck = new Card[0];
        }

        for (int i = 0; i < 4; i++) {//sort by suit foreach suit
            for (int j = 0; j < masterDeck.deck.length; j++) {//foreach card in hand
                if (masterDeck.deck[j].prioritysuit == i) {//if is current suit number, post trump
                    deck[i].push_back(masterDeck.deck[j]);
                }
            }
        }

        return deck;
    }

    public static Card[] sortBySuit(Card deck[]) {//sort by suit
        try {//for debugging
            for (int i = 0; i < deck.length - 1; i++) {//bubble sort through decks
                for (int j = 0; j < deck.length - 1; j++) {//foreach card in deck
                    if (deck[j].prioritysuit > deck[j + 1].prioritysuit) {
                        Card temp = deck[j];
                        deck[j] = deck[j + 1];
                        deck[j + 1] = temp;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("sortbysuit exception");
        }
        return deck;
    }

    public static Card[] resize(Card deck[], int length) {
        Card retDeck[] = new Card[length];//init resized deck
        for (int i = 0; i < (length >= deck.length ? deck.length : length)/*see which size is bigger*/; i++)
            retDeck[i] = deck[i];//copy elements
        return retDeck;
    }

    public static boolean isThereSuit(Deck deck, int nsuit) {
        for (Card card :
                deck.deck)
            if (card.nsuit == nsuit) return true;//if found
        return false;//else
    }

    public void push_back(Card card) {
        deck = resize(deck, deck.length + 1);//resize
        deck[deck.length - 1] = card;//insert to back
    }

    public Card remove(int nvalue) {
        int pos = -1;//init to error pos
        Card card = null;//init to null
        for (int i = 0; i < deck.length; i++)//find position
            if (deck[i].nvalue == nvalue) {
                pos = i;
                break;
            }
        if (pos != -1) {//if found
            card = deck[pos];//card of pos
            if (pos > 0) for (int i = pos + 1; i < deck.length; i++)//shift all elements up
                deck[i - 1] = deck[i];
            else for (int i = 1; i < deck.length; i++)//if pos is first element, unecessary microoptimization
                deck[i - 1] = deck[i];

            deck = resize(deck, deck.length - 1);//resize deck
        } else System.out.println("No card found that nvalue matches parameter");
        return card;//return card for debugging
    }

    public void printDeck() {//print all card fields
        for (Card c :
                deck)
            c.Print();
    }

    private void sortBySuit() {
//        for (int i = 0; i < size; i++) {
//            int lowPos = i;
//            for (int j = lowPos + 1; j < size; j++) {
//                if (deck[j].nsuit < deck[lowPos].nsuit) {
//                    lowPos = j;
//                }
//            }
//            Card temp = deck[lowPos];
//            deck[lowPos] = deck[i];
//            deck[i] = temp;
//        }
        for (int i = 0; i < deck.length - 1; i++)//bubble sort based on suit
            for (int j = 0; j < deck.length - 1; j++)
                if (deck[j].nsuit > deck[j + 1].nsuit) {
                    Card temp = deck[j];
                    deck[j] = deck[j + 1];
                    deck[j + 1] = temp;
                }
    }

    private void sortByRank() {
        for (int i = 0; i < deck.length - 1; i++)//bubble sort within suits by rank
            for (int j = 0; j < deck.length - 1; j++)
                if (deck[j].nsuit == deck[j + 1].nsuit &&//if next element is of the same suit
                        deck[j].nrank < deck[j + 1].nrank) {
                    Card temp = deck[j];
                    deck[j] = deck[j + 1];
                    deck[j + 1] = temp;
                }
    }

    private void Shuffle() {
        for (int i = deck.length - 1; i >= 1; --i) {
            int j = (int) (Math.random() * 100 % i);//generate random pos between current card and start of deck
            Card temp = deck[i];//swap
            deck[i] = deck[j];
            deck[j] = temp;
        }
    }

    private void initDeck() {
        deck = new Card[52];
        for (int i = 0; i < 52; i++) deck[i] = new Card(i);
    }

    private void initDeck(int ndirection, Deck parentDeck) {
        deck = new Card[13];
//        for (Card card :
//                parentDeck.deck) {
//            if (card.ndirection == ndirection) deck[it] = card;
//            ++it;
//        }
        for (int i = 0, j = 0; i < parentDeck.deck.length; i++)//insert into corresponding dck according to direction
            if (parentDeck.deck[i].ndirection == ndirection) deck[j++] = parentDeck.deck[i];
    }

    private void assignDirection() {
        int dir = 0, itCnt = 0;//counter and direction
        for (Card c :
                deck) {
            c.nDirection(dir);//set direction
            if (++itCnt % 13 == 0) ++dir;//if after 13 sample.cards in hand move on to next player
        }
    }
}

class Score {
    public static int totalTricks, made, level, suit, score, overtricks, undertricks;
    public static Bid contractBid;

    public Score(Bid contractBid, int totalTricks) {
        //init globals
        made = totalTricks - 6;//translate to by contract instead of raw total
        level = contractBid.slevel.charAt(0) - '0';//translate to int
        suit = contractBid.nsuit;//set
        overtricks = made - level;//difference if win, neg when lost
        undertricks = level - made;//difference if lost, neg when win
        Score.contractBid = contractBid;
        Score.totalTricks = totalTricks;
    }

    public static int calculate() {
        boolean win = made >= level;//check if win or loose
        if (win) {
            //base score
            score = 50 + level * (suit <= 1 ? 20 : 30);//50 base score, major suit or minor suit
            if (suit == 4) score += 10;//if notrump

            //redoubling
            if (contractBid.xx) score = score * 4 - 50 + overtricks * 200 +
                    //by redoubling: score*4 - base score of 50,
                    //is the suit levels below eligible for game bonus?
                    ((level == 1 && (suit == 2 || suit == 3 || suit == 4)) ||
                            (level == 2 && (suit == 0 || suit == 1)) ? 250 : 0);//for redoubling

                //doubling
            else if (contractBid.x) score = score * 2 + overtricks * 100 +
                    //by doubling: score*4 - base score of 50,
                    //is the suit levels below eligible for game bonus?
                    ((level == 2 && (suit == 2 || suit == 3 || suit == 4)) ||
                            (level == 3 && (suit == 0 || suit == 1)) ? 250 : 0);//for doubling

            //bonuses
            if (((suit == 2 || suit == 3) && level == 4) ||
                    ((suit == 0 || suit == 1) && level == 5) ||
                    (suit == 4 && level == 3)) score += 250;//game bonus
            else if (level == 6) score += 500;//small slam bonus
            else if (level == 7) score += 1000;//grand slam bonus
        } else {//lose
            //base score
            score = 50 * (contractBid.xx ? 4 : contractBid.x ? 2 : 1);//multiplier of 50 based on normal, x, and xx

            //additional tricks
            int i = undertricks;//number of undertricks
            for (; i >= 4; i--) score += (contractBid.xx ? 600 : contractBid.x ? 300 : 50);//additional tricks
            for (; i >= 2; i--)
                score += (contractBid.xx ? 400 : contractBid.x ? 200 : 50);//after 4 undertricks, calculation changes
        }
        return win ? score : (score *= -1);//negative score based on loss
    }
}

class AlertBox {
    public static void display(String title, String message) {
        Stage window = new Stage();

        //Block events to other windows
        window.setAlwaysOnTop(true);
        window.initModality(Modality.APPLICATION_MODAL);//Block input to other windows
        window.setTitle(title);
        window.setMinWidth(250);

        Label label = new Label();//Message
        label.setText(message);//Set label to arg2
        label.setFont(Font.font("Verdana", 20));//font
        label.setTextFill(Color.ORANGERED);//colour

        VBox layout = new VBox(10);//layout
        layout.getChildren().addAll(label);//add label to layout
        layout.setAlignment(Pos.CENTER);//align

        //Display and wait for it to be closed
        Scene scene = new Scene(layout);//set layout to scene
        window.setScene(scene);//include scene into window
        window.setResizable(false);//disable resizing
        window.showAndWait();//wait for action on window before continuing on thread
    }
}

class ConfirmBox {
    static boolean answer;

    public static boolean display(String title, String message) {
        Stage window = new Stage();//new window
        window.setAlwaysOnTop(true);
        window.initModality(Modality.APPLICATION_MODAL);//blocks input to other windows
        window.setTitle(title);//set title of window
        window.setMinWidth(250);
        Label label = new Label();//Label for message
        label.setText(message);//set label to display message
        label.setFont(Font.font("Verdana", 20));//set font and size

        //Two choices
        Button yes = new Button("Yes");
        Button no = new Button("No");

        //Clicking reactions
        yes.setOnAction(e -> {
            answer = true;
            window.close();
        });
        no.setOnAction(e -> {
            answer = false;
            window.close();
        });

        VBox layout = new VBox(10);//layout to format buttons

        //Add buttons to layout and to scene to stage
        layout.getChildren().addAll(label, yes, no);
        layout.setAlignment(Pos.CENTER);//align layout
        Scene scene = new Scene(layout);//set scene to layout
        window.setScene(scene);//set window to scene
        window.setOnCloseRequest(event -> answer = false);//set reaction on 'x' button
        window.setResizable(false);//disable resizing
        window.showAndWait();//pause thread until window is closed

        return answer;//return input from user
    }
}







