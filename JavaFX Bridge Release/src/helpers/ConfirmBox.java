package helpers;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by Adam on 1/7/2016.
 */
public class ConfirmBox {
    static boolean answer;

    public static boolean display(String title, String message) {
        Stage window = new Stage ( );//new window
        window.setAlwaysOnTop (true);
        window.initModality (Modality.APPLICATION_MODAL);//blocks input to other windows
        window.setTitle (title);//set title of window
        window.setMinWidth (250);
        Label label = new Label ( );//Label for message
        label.setText (message);//set label to display message
        label.setFont(Font.font("Verdana",20));//set font and size

        //Two choices
        Button yes = new Button ("Yes");
        Button no = new Button ("No");

        //Clicking reactions
        yes.setOnAction (e -> {
            answer = true;
            window.close ( );
        });
        no.setOnAction (e -> {
            answer = false;
            window.close ( );
        });

        VBox layout = new VBox (10);//layout to format buttons

        //Add buttons to layout and to scene to stage
        layout.getChildren ( ).addAll (label, yes, no);
        layout.setAlignment (Pos.CENTER);//align layout
        Scene scene = new Scene (layout);//set scene to layout
        window.setScene (scene);//set window to scene
        window.setOnCloseRequest (event -> answer = false);//set reaction on 'x' button
        window.setResizable (false);//disable resizing
        window.showAndWait ( );//pause thread until window is closed

        return answer;//return input from user
    }
}
