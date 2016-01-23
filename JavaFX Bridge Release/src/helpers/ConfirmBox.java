package helpers;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by Adam on 1/7/2016.
 */
public class ConfirmBox {
    static boolean answer;

    public static boolean display(String title, String message) {
        Stage window = new Stage ( );
        window.setAlwaysOnTop (true);
        window.initModality (Modality.APPLICATION_MODAL);
        window.setTitle (title);
        window.setMinWidth (250);
        Label label = new Label ( );
        label.setText (message);

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

        VBox layout = new VBox (10);

        //Add buttons to layout and to scene to stage
        layout.getChildren ( ).addAll (label, yes, no);
        layout.setAlignment (Pos.CENTER);
        Scene scene = new Scene (layout);
        window.setScene (scene);
        window.setOnCloseRequest (event -> answer = false);
        window.showAndWait ( );

        return answer;
    }
}
