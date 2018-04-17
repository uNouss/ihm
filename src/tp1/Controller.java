package tp1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Controller extends Application {

    public void start(Stage stage) {
        /*VBox root = new VBox();
        Label msg = new Label("Hello JavaFX");
        root.getChildren().add(msg);

        Scene scene = new Scene(root, 300, 50);
        stage.setScene(scene);
        //stage.initStyle(StageStyle.UTILITY);
        stage.setTitle("Hello JavaFX");

        stage.show();

        //Fenêtre modale est lié de manière à la fenêtre principale en lui prenant le focus.
        Stage myFen = new Stage();
        HBox  myHbox = new HBox();
        TextArea monText = new TextArea("un champ de text");
        myHbox.getChildren().add(monText);
        Scene mySen = new Scene(myHbox, 200, 150);
        myFen.setScene(mySen);

        myFen.initOwner(stage);
        myFen.initModality(Modality.WINDOW_MODAL);

        myFen.setY(stage.getY()); // même Y
        myFen.setX(stage.getX()+stage.getWidth()); // x2 = coord en X de stage et

        myFen.show();*/


        /*FlowPane fp = new FlowPane();
        Button b;
        Insets marge = new Insets(10,5,10,5);
        for(int i = 1; i < 11; i++){
            b = new Button("Bouton "+i);
            fp.getChildren().add(b);
            FlowPane.setMargin(b, marge);
        }
        Scene scene = new Scene(fp, 500,300);
        stage.setScene(scene);
        stage.show();*/

        Slider[] slider = new Slider[]{
                new Slider(),
                new Slider(),
                new Slider()
        };
        TextField[] text = new TextField[]{
                new TextField("203"),
                new TextField("103"),
                new TextField("10")
        };

        GridPane grid = new GridPane();
        for(int i = 0; i < slider.length; i++) {
            grid.add(slider[i], 0, i, 2, 1);
        }
        for(int i = 0; i < text.length; i++) {
            grid.add(text[i], 2, i);
        }

        Region r1 = new Region();
        Region r2 = new Region();

        TextField t = new TextField("#001122");



        VBox gauche = new VBox();
        VBox droite = new VBox();

        droite.getChildren().add(t);

        gauche.getChildren().add(r1);
        gauche.getChildren().add(grid);
        gauche.getChildren().add(r2);

        VBox.setVgrow(r1, Priority.ALWAYS);
        VBox.setVgrow(r2, Priority.ALWAYS);

        Insets mVBox1 = new Insets(5,10,5,5);
        VBox.setMargin(gauche, mVBox1);
        HBox box = new HBox();

        box.getChildren().add(gauche);
        box.getChildren().add(droite);

        Scene scene = new Scene(box, 500,200);

        stage.setTitle("RGBColor");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
