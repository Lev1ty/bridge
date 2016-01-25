package helpers;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by Adam on 1/7/2016.
 */
public class AlertBox {
    public static void display(String title, String message) {
        Stage window = new Stage ( );

        //Block events to other windows
        window.setAlwaysOnTop (true);
        window.initModality (Modality.APPLICATION_MODAL);//Block input to other windows
        window.setTitle (title);
        window.setMinWidth (250);

        Label label = new Label ( );//Message
        label.setText (message);//Set label to arg2
        label.setFont(Font.font("Verdana",20));//font
        label.setTextFill ( Color.ORANGERED );//colour

        VBox layout = new VBox (10);//layout
        layout.getChildren ( ).addAll (label);//add label to layout
        layout.setAlignment (Pos.CENTER);//align

        //Display and wait for it to be closed
        Scene scene = new Scene (layout);//set layout to scene
        window.setScene (scene);//include scene into window
        window.setResizable (false);//disable resizing
        window.showAndWait ( );//wait for action on window before continuing on thread
    }
}
