package logic;

import javafx.scene.image.Image;

/**
 * Created by Adam on 1/11/2016.
 */
public class ImageWrapper {
    public Image image;

    public ImageWrapper(int val) {
        String sval = String.valueOf (val);
        new ImageWrapper (sval);
    }

    public ImageWrapper(String val) {
        image = new Image (val + ".png");
    }

    public Image getImage() {
        return image;
    }
}
