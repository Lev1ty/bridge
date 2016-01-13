package logic;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Created by Adam on 1/12/2016.
 */
public class ImageDeck {
    public static ImageWrapper imageDeck[];

    public static void initDeck() {
        imageDeck = new ImageWrapper[52];
        for (int i = 0; i < 52; i++) imageDeck[i] = new ImageWrapper (i + 1);
        for (ImageWrapper imageWrapper :
                ImageDeck.imageDeck) {
            HBox hBox = new HBox (imageWrapper.getImageView ( ));
            Group group = new Group (hBox);
            Scene scene = new Scene (group);
            Stage stage1 = new Stage ( );
            stage1.setScene (scene);
            stage1.show ( );
        }
    }

    public static ImageView[] getImageViewDeck(Deck deck) {
        ImageView imageView[] = new ImageView[deck.deck.length];
        for (int i = 0; i < deck.deck.length; i++) {
//            ImageWrapper imageWrapper = new ImageWrapper (deck.deck[i].nvalue + 1);
//            imageView[i] = new ImageWrapper (
//                    deck.deck[i].nvalue + 1).getImageView (
////                    100, 100, true, true
//            );
            imageView[i] = imageDeck[deck.deck[i].nvalue].getImageView ();
            HBox hBox = new HBox (imageDeck[deck.deck[i].nvalue].getImageView ());
            Group group = new Group (hBox);
            Scene scene = new Scene (group);
            Stage stage = new Stage ( );
            stage.setScene (scene);
            stage.show ( );
        }
//        HBox hBox = new HBox (10);
//        hBox.getChildren ( ).addAll (imageView);
//        Group group = new Group (hBox);
//        Scene scene = new Scene (group);
//        Stage stage = new Stage ( );
//        stage.setScene (scene);
//        stage.show ( );
        return imageView;
    }
}
