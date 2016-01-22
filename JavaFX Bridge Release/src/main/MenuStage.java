package main;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.awt.*;

/**
 * Created by Adam on 1/21/2016.
 */
public class MenuStage {
    private static Image backgroundImage = getImage ("CardTower", "jpg");
    private static GridPane gridPane = new GridPane ( );
    private static StackPane stackPane = new StackPane (gridPane);
    private static Scene scene = new Scene (stackPane);
    private static Stage stage = new Stage ( );
    private static int result;

    public static int display() {
        gridPane.setBackground (new Background (new BackgroundImage (backgroundImage,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
        gridPane.setPadding (new Insets (50, 200, 200, 150));
        Label title = new Label ("Bridge");
        int row = 0, col = 0;
        gridPane.add (title, row, col++);
        gridPane.add (getButton (0, getButton ( )), row, col++);
        gridPane.add (getButton ("Start Bridge", getButton ( )), row, col++);
        gridPane.add (getButton (0, getButton ( )), row, col++);
        gridPane.add (getButton ("Instructions", getButton ()), row, col++);
        gridPane.add (getButton (0, getButton ( )), row, col++);
        gridPane.add (getButton ("Exit", getButton ( )), row, col);
        stage.setTitle ("Menu");
        stage.setScene (scene);
        stage.setResizable (false);
        stage.showAndWait ( );
        stage.setOnCloseRequest ( event -> result = 1 );
        return result;
    }

    private static void eventHandler(String name) {
        stage.close ();
        switch (name.charAt (0)) {
            case 'S': result = 2; break;
            case 'E': result = 1; break;
            default: result = 0; break;
        }
    }

    private static Button getButton() {
        Button button = new Button ( );
        button.setPadding (new Insets (10, 10, 10, 10));
        return button;
    }

    private static Button getButton(String name, Button button) {
        button.setText (name);
        button.setOnAction (event -> eventHandler(name));
        return button;
    }

    private static Button getButton(int opacity, Button button) {
        button.setOpacity (opacity);
        return button;
    }

    private static Image getImage(String name, String ending) {
        Image image = null;
        try {
            image = new Image ("cards\\" + name + "." + ending);
        } catch (Exception e) {
            System.out.println ("Image not Found in MenuStage getImage.");
        }
        return image;
    }
}
