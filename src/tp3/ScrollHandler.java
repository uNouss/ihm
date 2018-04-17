package tp3;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.ScrollEvent;

public class ScrollHandler implements EventHandler<ScrollEvent> {
    private final Label label;

    @Override
    public void handle(ScrollEvent event) {
        System.out.println(event.getDeltaY());
        label.setText(""+(event.getDeltaY() < 0 ? Integer.parseInt(label.getText())-1: Integer.parseInt(label.getText())+1));
    }

    public ScrollHandler(Label label) {
        this.label = label;
    }
}
