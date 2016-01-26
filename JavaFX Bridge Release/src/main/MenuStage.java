package main;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Created by Adam on 1/21/2016.
 */
public class MenuStage {
    private static Image backgroundImage = getImage ("CardTower", "jpg");//background image
    private static GridPane gridPane = new GridPane ( );//get grid pane layout
    private static StackPane stackPane = new StackPane (gridPane);//root grid pane lyout to stackPane
    private static Scene scene = new Scene (stackPane);//set scene to format and content of stackPane
    private static Stage stage = new Stage ( );//set content of stage to scene
    private static int result;//choice result to be used in MAIN

    public static int display() {
        //background image
        gridPane.setBackground (new Background (new BackgroundImage (backgroundImage,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
        gridPane.setPadding (new Insets (100, 200, 100, 100));

        //title
        Label title = new Label ("Bridge");
        title.setFont (Font.font ("Verdana", FontPosture.ITALIC, 50));
        title.setTextFill (Color.ORANGERED);

        //buttons on menu stage
        int row = 0, col = 0;
        gridPane.add (title, row, col++);
        gridPane.add (getButton (0, getButton ( )), row, col++);
        gridPane.add (getButton ("Start", getButton ( )), row, col++);
        gridPane.add (getButton (0, getButton ( )), row, col++);
        gridPane.add (getButton ("Exit", getButton ( )), row, col);

        //set stage title
        stage.setTitle ("Menu");
        stage.setScene (scene);
        stage.setResizable (false);
        stage.initStyle (StageStyle.TRANSPARENT);//remove borders
        stage.showAndWait ( );//pause thread for input
        return result;
    }

    private static void eventHandler(String name) {
        stage.close ( );//close stage and then decide return result based on name of button
        switch (name.charAt (0)) {
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
        Button button = new Button ( );
        button.setPadding (new Insets (10, 10, 10, 10));//border spacing
        button.setPrefWidth ( 90 );//uniform button width
        return button;
    }

    private static Button getButton(String name, Button button) {
        button.setText (name);//set button display name
        button.setOnAction (event -> eventHandler (name));//set reaction to click
        return button;
    }

    private static Button getButton(int opacity, Button button) {
        button.setOpacity (opacity);//customize button opactiy
        return button;
    }

    public static Image getImage(String name, String ending) {
        Image image = null;
        try {
            image = new Image ("cards\\" + name + "." + ending);//get image from cards folder
        } catch (Exception e) {
            System.out.println ("Image not Found in MenuStage getImage.");//error msg
        }
        return image;
    }
}
