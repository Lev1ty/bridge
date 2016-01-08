package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import javafx.scene.control.Button;

import java.util.Stack;

public class Main extends Application/* implements EventHandler<ActionEvent>*/ {

    Scene scene1, scene2;
    Stage window;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage window) throws Exception {
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
//        window.setTitle("Hello World");
//        window.setScene(new Scene(root, 300, 275));
//        window.show();

        //Label1
        this.window = window;
        Label label1 = new Label("First Screen");

        //Button1
        Button button1 = new Button("Click me");
        button1.setOnAction(e -> {
            System.out.println("Hello World");
            window.setScene(scene2);
        });

        //Layout1
        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(label1, button1);
        scene1 = new Scene(layout1, 200, 200);

        //Button2
        Button button2 = new Button("Scene 1");
        button2.setOnAction(e -> window.setScene(scene1));

        //Button3
        Button button3 = new Button("Alert Box");
        button3.setOnAction(e -> AlertBox.display("Alert Box", "2nd argument"));

        //Button4
        Button button4 = new Button("Confirm Box");
        button4.setOnAction(e -> {
            boolean result = ConfirmBox.display("Confirm Box", "Areyou sure you want to send that pic?");
            System.out.println(result);
        });

        //Button5
        Button button5 = new Button("Close and Delete History");
        button5.setOnAction(e -> {
            e.consume();
            closeProgram();
        });

        //Gridpane
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(10);
        grid.setHgap(10);
        Label user = new Label("Username: ");
        GridPane.setConstraints(user, 0, 0);
        TextField userin = new TextField();
        userin.setPromptText("Username");
        GridPane.setConstraints(userin, 1, 0);
        Label pswrd = new Label("Password: ");
        GridPane.setConstraints(pswrd, 0, 1);
        TextField pswrdin = new TextField();
        pswrdin.setPromptText("Password");
        GridPane.setConstraints(pswrdin, 1, 1);
        Button login = new Button("Log In");
        GridPane.setConstraints(login, 1, 2);
        grid.getChildren().addAll(user, pswrd, userin, pswrdin, login);
        Scene scene4 = new Scene(grid);

        //Button6
        Button button6 = new Button("Access Login");
        button6.setOnAction(e->{
            window.setScene(scene4);
        });

        //Layout2
        VBox layout2 = new VBox();
        layout2.getChildren().addAll(button2, button3, button4, button5, button6);
        scene2 = new Scene(layout2, 600, 300);

        //BorderPlane embedded layouts
        BorderPane cluster = new BorderPane();
        cluster.setLeft(layout1);
        cluster.setRight(layout2);
        Scene scene3 = new Scene(cluster, 2000, 1500);

        //Scenes
//        StackPane layout = new StackPane();
//        Scene scene1 = new Scene(layout, 1500, 1000);
//        layout.getChildren().add(button1);

        //Stage
        window.setOnCloseRequest(e -> closeProgram());

        window.setTitle("Bridge");
        window.setFullScreen(false);
        window.setFullScreenExitHint("Bridge is now full screen. Exit full screen (Esc).");
        window.setScene(scene3);
        window.show();
    }

    //    @Override
//    public void handle(ActionEvent event){
//        if (event.getSource()==button)
//            System.out.println("Hello World");
//    }
    private void closeProgram() {
        if (ConfirmBox.display("Close", "Sure?")) {
            System.out.println("History Deleted.");
            window.close();
        }
    }
}
