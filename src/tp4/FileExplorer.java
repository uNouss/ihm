package tp4;

import javafx.application.Application;
import javafx.collections.ListChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.File;

public class FileExplorer extends Application {
    Label label;
    ListView<String> list, files;
    final String PATHNAME = "/usr/include/";

    class MonListChangeListener implements ListChangeListener<String> {
        public void onChanged(Change<? extends String> c){
            label.setText("Selection de " + c.getList());
        }
    }

    class MonExplorer implements ListChangeListener<String> {
        public void onChanged(Change<? extends String> c){
            File courant = new File(PATHNAME +c.getList().get(0));
            list.getItems().clear();
            if(courant.isDirectory()){
                list.getItems().addAll(courant.list());
            }
            label.setText("Selection de " + c.getList());
        }
    }
    public void start(Stage stage) {
        File path = new File(PATHNAME);
        String[] filelist = path.list();

        label = new Label("Aucune selection");
        list= new ListView<String>();
        files = new ListView<String>();

        files.getItems().addAll(filelist);
        files.getSelectionModel().getSelectedItems().addListener(new MonExplorer());
        files.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> list) {
                return new MonRenduDeCellule();
            }
        });
        list.getSelectionModel().getSelectedItems().addListener(new MonListChangeListener());
        list.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> list) {
                return new MonRenduDeCellule();
            }
        });

        HBox root = new HBox();
        root.setAlignment(Pos.CENTER_LEFT);
        root.setSpacing(10.0);
        root.setPadding(new Insets(3, 3, 3, 3));
        root.getChildren().addAll(files,list,label);

        Scene scene = new Scene(root, 800, 350);
        stage.setTitle("File Explorer");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    class MonRenduDeCellule extends ListCell<String> {
        @Override
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            Canvas c = new Canvas(200, 20);
            GraphicsContext gc = c.getGraphicsContext2D();
            gc.fillText(item, 20, 10);

            Image f = new Image("https://www.iut-info.univ-lille1.fr/~casiez/M2105/TP/TP4EvenementsListView/file.png",
                    20,20,false, false);
            Image d = new Image("https://www.iut-info.univ-lille1.fr/~casiez/M2105/TP/TP4EvenementsListView/folder.png",
                    20,20,false, false);

            System.out.println(item);
            if(!empty && item != null){
                File courant = new File(PATHNAME+item);
                if(courant.isDirectory()){
                    gc.drawImage(d, 1, 0);
                }else{
                    gc.drawImage(f, 1, 0);
                }
            }
            setGraphic(c);
        }
    }
}
