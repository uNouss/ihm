package tp3;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Convertisseur extends Application {
    private final double TAUX_CONVERSION = 0.8647;
    private TextField euro;
    private TextField livre;

    @Override
    public void start(Stage stage) throws Exception {
        euro = new TextField("1");
        livre = new TextField("" + (Integer.parseInt(euro.getText())) * TAUX_CONVERSION);
        String styleField = "-fx-background-color: white;"
                + " -fx-alignment: center;"
                + " -fx-font: 30px Verdana;"
                + " -fx-border-color: black;"
                + " -fx-border-style: dotted;";
        euro.setStyle(styleField);
        livre.setStyle(styleField);

        ActionSaisie as = new ActionSaisie();
        euro.setOnAction(as);
        livre.setOnAction(as);

        Label e = new Label("€");
        Label l = new Label("£");
        String styleLabel = " -fx-font-weight: bold;"
                + " -fx-font-size: 40px;"
                + " -fx-padding: 10px;";
        e.setStyle(styleLabel);
        l.setStyle(styleLabel);

        VBox vbox = new VBox();
        Button bE2L = new Button(" € -> £ ");
        Button bL2E = new Button(" £ -> € ");
        vbox.getChildren().addAll(bE2L, bL2E);
        vbox.setStyle(" -fx-alignment: center;");
        vbox.setPadding(new Insets(3, 6, 3, 0));

        HBox hbox = new HBox();
        hbox.setPadding(new Insets(3, 3, 3, 3));
        hbox.setAlignment(Pos.CENTER);

        hbox.getChildren().addAll(euro, e, vbox,  livre, l);

        Scene scene = new Scene(hbox);
        stage.setTitle("Convertisseur devise");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    private class ActionSaisie implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event) {
            if(event.getTarget() == euro ) {
                livre.setText(""+(Integer.parseInt(euro.getText())*TAUX_CONVERSION));
            }else{
                euro.setText(""+(Integer.parseInt(livre.getText())/TAUX_CONVERSION));
            }
        }
    }
}
