package tuto.address;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.*;

public class PieChartApp extends Application {
	private Map<String, Integer> hm = new HashMap<>();
	ObservableList<PieChart.Data> slices = FXCollections.observableArrayList();

	@Override
	public void start(Stage primaryStage) throws Exception {
		initMap();
		Set<Map.Entry<String, Integer>> setHm = hm.entrySet();

		Iterator<Map.Entry<String, Integer>> it = setHm.iterator();

		PieChart pieChart = new PieChart();

		while(it.hasNext()){
			Map.Entry<String, Integer> e = it.next();
			slices.add(new PieChart.Data(e.getKey(), e.getValue()));
		}

		pieChart.getData().addAll(slices);


		pieChart.setPrefSize(400, 300);

		//pieChart.setLegendSide(Side.LEFT);
		pieChart.setLegendVisible(false);
		pieChart.setStartAngle(30);

		final Label caption = new Label("");
		caption.setTextFill(Color.DARKGRAY);
		caption.setStyle("-fx-font: 12 arial;");

		int i = 0;
		for (PieChart.Data data : pieChart.getData()) {
			System.out.println(data);
			data.getNode().setStyle(
					"-fx-pie-color: "+data.getName()+ ";"
			);
			System.out.println(data.chartProperty().getBean());
			data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent e) {
					System.out.println("je delete " + e.getTarget());
					hm.remove(data.getName());
					slices.remove(data.chartProperty().getBean());
					System.out.println(hm.size());
					System.out.println(slices.size());
					caption.setTranslateX(e.getSceneX());
					caption.setTranslateY(e.getSceneY());

					caption.setText(String.valueOf(data.getPieValue()));
				}
			});
		}

		primaryStage.setTitle("");
		AnchorPane root = new AnchorPane();
		root.getChildren().addAll(pieChart,  caption); // si caption pas commenté le rajouter.


		Scene scene = new Scene(root, 500, 400);

		primaryStage.setScene(scene);

		primaryStage.show();
	}
	private void applyCustomColorSequence(
			ObservableList<PieChart.Data> pieChartData,
			String... pieColors) {
		int i = 0;
		for (PieChart.Data data : pieChartData) {
			data.getNode().setStyle(
					"-fx-pie-color: " + pieColors[i % pieColors.length] + ";"
			);
			i++;
		}
	}
	private void initMap() {
		Random random  = new Random();
		hm.put("#f0ff4f", random.nextInt(901)+100);
		hm.put("#fafafa", random.nextInt(901)+100);
		hm.put("#bfbfbf", random.nextInt(901)+100);
		hm.put("#cffcfc", random.nextInt(901)+100);
		hm.put("#dffdfd", random.nextInt(901)+100);
		hm.put("#efefef", random.nextInt(901)+100);
		hm.put("#6f6f6f", random.nextInt(901)+100);
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}