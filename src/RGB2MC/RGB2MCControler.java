package RGB2MC;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;


public class RGB2MCControler implements Initializable, ChangeListener {

	int DATA_SIZE = 1000;
	int data[] = new int[DATA_SIZE];
	int[] group = new int[10];

	@FXML
	private Label hexa;


	@FXML
	private Button addCol;

	@FXML
	private ImageView add;

	@FXML
	private Button delCol;

	@FXML
	private ImageView del;

	@FXML
	private BarChart<String, Number> hist1;

	@FXML
	private BarChart<String, Number> hist2;

	@FXML
	private Circle gCircleColor;

	@FXML
	private Circle circleColor;

	@FXML
	private Slider rouge;

	@FXML
	private Slider vert;

	@FXML
	private Slider bleu;

	@FXML
	private TextField vRed;

	@FXML
	private TextField vGreen;

	@FXML
	private TextField vBlue;

	@FXML
	void cliked(MouseEvent event) {
		prepareData();
		groupData();

		System.out.println("Pressed");
		System.out.println("j'ai cliqué sur " + event.getTarget());
		if(event.getTarget() == addCol) System.out.println("j'ajoute ");
		else if (event.getTarget() == delCol ) System.out.println("je supprime");


		System.out.println(data);
		String hCol = hexa.getText();
		histo();
	}



	@Override
	public void changed(ObservableValue observableValue, Object o, Object t1) {
		int r = (int) rouge.getValue();
		int v = (int) vert.getValue();
		int b = (int) bleu.getValue();

		vRed.setText(String.format("%3d", r));
		vGreen.setText(String.format("%3d", v));
		vBlue.setText(String.format("%3d", b));


		Color color = Color.rgb(r, v, b);

		circleColor.setFill(color);
		circleColor.setStroke(niveauDeGris(r,v,b));
		gCircleColor.setFill(niveauDeGris(r,v,b));
		gCircleColor.setStroke(color);

		hexa.setText(String.format("#%02x%02x%02x", r, v, b));
	}

	private Color niveauDeGris(int r, int v, int b) {
		//NiveauGris = 0.3   Rouge + 0.59   Vert + 0.11   Bleu
		return Color.grayRgb((int)(0.3*r + 0.59*v + 0.11*b));
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		rouge.valueProperty().addListener(this);
		vert.valueProperty().addListener(this);
		bleu.valueProperty().addListener(this);

		hist1.getData().addAll(histo());
		hist2.getData().addAll(histo());
	}

	public XYChart.Series histo() {
		prepareData();
		groupData();

		XYChart.Series series1 = new XYChart.Series();
		series1.setName("Histogramme");
		series1.getData().add(new XYChart.Data("0-10", group[0]));
		series1.getData().add(new XYChart.Data("10-20", group[1]));
		series1.getData().add(new XYChart.Data("20-30", group[2]));
		series1.getData().add(new XYChart.Data("30-40", group[3]));
		series1.getData().add(new XYChart.Data("40-50", group[4]));

		series1.getData().add(new XYChart.Data("50-60", group[5]));
		series1.getData().add(new XYChart.Data("60-70", group[6]));
		series1.getData().add(new XYChart.Data("70-80", group[7]));
		series1.getData().add(new XYChart.Data("80-90", group[8]));
		series1.getData().add(new XYChart.Data("90-100", group[9]));

		return series1;
	}


	private void prepareData(){

		Random random = new Random();
		for(int i=0; i<DATA_SIZE; i++){
			data[i] = random.nextInt(100);
		}
	}

	private void groupData(){
		for(int i=0; i<10; i++){
			group[i]=0;
		}
		for(int i=0; i<DATA_SIZE; i++){
			if(data[i]<=10){
				group[0]++;
			}else if(data[i]<=20){
				group[1]++;
			}else if(data[i]<=30){
				group[2]++;
			}else if(data[i]<=40){
				group[3]++;
			}else if(data[i]<=50){
				group[4]++;
			}else if(data[i]<=60){
				group[5]++;
			}else if(data[i]<=70){
				group[6]++;
			}else if(data[i]<=80){
				group[7]++;
			}else if(data[i]<=90){
				group[8]++;
			}else if(data[i]<=100){
				group[9]++;
			}
		}
	}
}
