package ys.gc.slider;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

public class SliderControl implements Initializable, ChangeListener, MapChangeListener {


	@FXML
	private AnchorPane background;

	@FXML
	private Slider red;

	@FXML
	private Slider green;

	@FXML
	private Slider blue;

	@FXML
	private TextField valueRed;

	@FXML
	private TextField valueGreen;

	@FXML
	private TextField valueBlue;

	@FXML
	private Rectangle previewColor;

	@FXML
	private Rectangle previewGray;

	@FXML
	private TextField hexaColor;

	@FXML
	private Button addButton;

	@FXML
	private AnchorPane dcolor;

	@FXML
	private PieChart pcColors;

	@FXML
	private AnchorPane dgray;

	@FXML
	private PieChart pcGray;


	private ObservableList<Rectangle> shapes = FXCollections.observableArrayList();
	private ObservableList<Color> colors = FXCollections.observableArrayList();

	@Override
	public void changed(ObservableValue observableValue, Object o, Object t1) {
		Double r = red.getValue();
		Double g = green.getValue();
		Double b = blue.getValue();

		Color color = Color.rgb(r.intValue(), g.intValue(), b.intValue());

		previewColor.setFill(color);
		previewGray.setFill(niveauDeGris(r,g,b));

		valueRed.setText(String.valueOf(r.intValue()));
		valueGreen.setText(String.valueOf(g.intValue()));
		valueBlue.setText(String.valueOf(b.intValue()));

		hexaColor.setText(String.format("#%02x%02x%02x", r.intValue(), g.intValue(), b.intValue()));
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		red.valueProperty().addListener(this);
		green.valueProperty().addListener(this);
		blue.valueProperty().addListener(this);
	}


	@FXML
	void cliked(MouseEvent event) {
		//System.out.println("add "+ hexaColor.getText());
		System.out.println(shapes.size());

		Double w = dcolor.getWidth()/(shapes.size()+1);
		Rectangle rect = new Rectangle(w,dcolor.getHeight(), Color.valueOf(hexaColor.getText()));
		dcolor.getChildren().add(rect);
		shapes.add(rect);
		System.out.println(shapes.size()+"   "+w);
		for(int i = 0; i < shapes.size(); i++){
			shapes.get(i).setWidth(w);
		}

		//rect = new Rectangle(w,dcolor.getHeight(), Color.valueOf(hexaColor.getText()));
		//dgray.getChildren().add(rect);


		colors.add(Color.valueOf(hexaColor.getText()));
		//System.out.println(colors.size()+" et "+shapes.size());
	}

	private Color niveauDeGris(Double r, Double v, Double b) {
		//NiveauGris = 0.3   Rouge + 0.59   Vert + 0.11   Bleu
		return Color.grayRgb((int)(0.3*r + 0.59*v + 0.11*b));
	}

	@Override
	public void onChanged(Change change) {
		dcolor.getProperties().addListener(this);
		dgray.getProperties().addListener(this);
	}
}
