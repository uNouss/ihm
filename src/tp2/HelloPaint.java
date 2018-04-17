package tp2;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;

public class HelloPaint extends Application {
    private Canvas canvas;
    private ArrayList<Rectangle> rectangles;
    private GraphicsContext gc;
    private boolean isMoved;
    private Rectangle rectMoved;
    private Rectangle rectSurvole;


    /*********************************************
     * LES GESTIONNAIRES D'EVENEMENTS
     *********************************************/
    private class MyMouseDraged implements EventHandler<MouseEvent>{
        @Override
        public void handle(MouseEvent event) {
            System.out.println("je deplace "+rectMoved);
            gc.clearRect(0,0, canvas.getWidth(), canvas.getHeight());
            if(rectMoved != null && isMoved){
                rectMoved.setX(event.getSceneX() - rectMoved.getWidth()/2);
                rectMoved.setY(event.getSceneY() - rectMoved.getHeight()/2);
            }
            print();
        }
    }

    private class MyMousePressed implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            isMoved = true;
            rectMoved = getRect(event);

            if(event.isShiftDown()){
                System.out.println("SHIFT -> efface un rectangle contenant: X = " + event.getSceneX() + " Y = " + event.getSceneY());
                rectangles.remove(getRect(event));
            }
            else {
                int width = 20, height = 20;
                Rectangle rect = new Rectangle(event.getSceneX() - height / 2, event.getSceneY() - width / 2, width, height);
                rectangles.add(rect);
            }
            print();
        }
    }

    public void start(Stage stage) {
        rectangles = new ArrayList<Rectangle>();
        VBox root = new VBox();
        canvas = new Canvas (500, 400);
        gc = canvas.getGraphicsContext2D();

        MyMousePressed mh = new MyMousePressed();
        canvas.setOnMousePressed(mh);
        MyMouseDraged md = new MyMouseDraged();
        canvas.setOnMouseDragged(md);
        canvas.setOnMouseReleased(e->isMoved = false);
        canvas.setOnMouseMoved(e->{
            rectSurvole = getRect(e);
            print();
        });

        root.getChildren().add(canvas);

        Scene scene = new Scene(root);
        stage.setTitle("Hello Paint");
        stage.setScene(scene);
        stage.show();
    }

    private void updateRectContrast(Rectangle r) {

    }

    private void print(){
        gc.clearRect(0,0, canvas.getWidth(), canvas.getHeight());
        for(Rectangle rect: rectangles) {
            if(rectSurvole != null && rect == rectSurvole){
                gc.setFill(Color.ORANGE);
            }
            else {
                gc.setFill(Color.ORANGE);
            }
            gc.fillRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
            gc.setStroke(Color.BLACK);
            gc.strokeRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
        }
    }

    private Rectangle getRect(MouseEvent event){
        for(int idRect = 0; idRect < rectangles.size(); idRect++){
            if(rectangles.get(idRect).contains(event.getSceneX(), event.getSceneY())) {
                return rectangles.get(idRect);
            }
        }
        return null;
    }

    public static void main(String[] args) {
            Application.launch(args);
    }

}
