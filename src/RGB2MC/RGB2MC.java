package RGB2MC;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class RGB2MC extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception{
		Parent root = FXMLLoader.load(getClass().getResource("RGB2MC.fxml"));


		Scene scene = new Scene(root);

		stage.setScene(scene);
		stage.setTitle("RGB2MC");
		stage.getIcons().add(new Image("file:icon.png"));
		stage.show();
	}


}
