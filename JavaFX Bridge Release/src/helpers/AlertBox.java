package helpers;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
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
        window.initModality (Modality.APPLICATION_MODAL);
        window.setTitle (title);
        window.setMinWidth (250);

        Label label = new Label ( );
        label.setText (message);
        label.setFont(Font.font("Verdana",20));

        VBox layout = new VBox (10);
        layout.getChildren ( ).addAll (label);
        layout.setAlignment (Pos.CENTER);

        //Display and wait for it to be closed
        Scene scene = new Scene (layout);
        window.setScene (scene);
        window.setResizable (false);
        window.showAndWait ( );
    }
}
