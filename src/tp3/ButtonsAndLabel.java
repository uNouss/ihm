package tp3;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ButtonsAndLabel extends Application implements EventHandler<MouseEvent>{
    private Label label;
    private Button bmoins;
    private Button bplus;
    private double x0;

    public void start(Stage stage) {
        label = new Label("0");
        label.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        label.setStyle("-fx-background-color: lightblue;"
                + " -fx-alignment: center;"
                + " -fx-font: 30px Verdana;");
        label.setOnMousePressed(e-> {
            label.setText(""+(e.isPrimaryButtonDown() ? (Integer.parseInt(label.getText()) - 1) : (Integer.parseInt(label.getText()) + 1)));
            x0 = e.getX();
        });
        label.setOnMouseDragged(this);
        label.setOnScroll(new ScrollHandler(label));

        bmoins = new Button("  -  ");
        bplus = new Button("  +  ");
        ClickEvent ce = new ClickEvent();
        bmoins.setOnAction(ce);
        bplus.setOnAction(ce);

        VBox vbox = new VBox(3);
        vbox.setPadding(new Insets(3, 3, 3, 3));
        vbox.setAlignment(Pos.CENTER);
        HBox hbox = new HBox(3);
        hbox.getChildren().addAll(bmoins, bplus);
        vbox.getChildren().addAll(label, hbox);

        Scene scene = new Scene(vbox);
        stage.setTitle("Counter");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void handle(MouseEvent event) {
        double x1 = event.getX();
        label.setText(""+(Integer.parseInt(label.getText()) - (int)Math.round(x1 - x0)));
        x0 = x1;
    }

    private class ClickEvent implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            label.setText(""+(event.getTarget() == bmoins ? (Integer.parseInt(label.getText()) - 1) : (Integer.parseInt(label.getText()) + 1)));
        }
    }
}
