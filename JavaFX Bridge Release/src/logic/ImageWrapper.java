package logic;

import helpers.AlertBox;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Created by Adam on 1/11/2016.
 */
public class ImageWrapper {
    private Image image;

    public ImageWrapper(int address) {
        String sval = String.valueOf (address);
        new ImageWrapper (sval);
    }

    public ImageWrapper(String address) {
        try {
            image = new Image ("cards\\" + address + ".gif");
        } catch (Exception e) {
            AlertBox.display ("Error", "Image " + address + " not found.");
        }
//        HBox hBox = new HBox (getImageView ( ));
//        Group group = new Group (hBox);
//        Scene scene = new Scene (group);
//        Stage stage = new Stage ( );
//        stage.setScene (scene);
//        stage.show ( );
    }

    public Image getImage() {
        return image;
    }

    public ImageView getImageView() {
        return new ImageView (getImage ( ));
    }

    public ImageView getImageView(int width, int height,
                                  boolean preserveRatio, boolean setWidth) {
        ImageView imageView = getImageView ( );
        imageView.setSmooth (true);
        imageView.setCache (true);
        if (preserveRatio) {
            imageView.setPreserveRatio (true);
            if (setWidth) imageView.setFitWidth (width);
            else imageView.setFitHeight (height);
        } else {
            imageView.setFitHeight (height);
            imageView.setFitWidth (width);
        }
        return imageView;
    }
}
