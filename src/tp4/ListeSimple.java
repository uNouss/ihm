package tp4;

import javafx.application.Application;
import javafx.collections.ListChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ListeSimple extends Application {
    Label label;

    class MonListChangeListener implements ListChangeListener<String> {
        public void onChanged(javafx.collections.ListChangeListener.Change<? extends String> c){
            label.setText("Selection de " + c.getList());
        }
    }

    public void start(Stage stage) {
        label = new Label("Aucune selection");
        ListView<String> list = new ListView<String>();
        list.getItems().addAll("Paris", "Berlin", "Londres", "Rome", "Lisbonne", "Madrid",
                "New York", "Tokyo", "Pekin");
        list.getSelectionModel().getSelectedItems().addListener(new MonListChangeListener());

        HBox root = new HBox();
        root.setAlignment(Pos.CENTER_LEFT);
        root.setSpacing(10.0);
        root.setPadding(new Insets(3, 3, 3, 3));
        root.getChildren().addAll(list,label);

        Scene scene = new Scene(root, 400, 150);
        stage.setTitle("Simple liste");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
