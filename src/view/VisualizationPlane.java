package view;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.canvas.Canvas;
import javafx.util.Duration;

public class VisualizationPlane {
	public Timeline getTimeline() {
		return timeline;
	}

	public DoubleProperty getX() {
		return x;
	}

	public DoubleProperty getY() {
		return y;
	}

	private Timeline timeline;
	private DoubleProperty x;
	private DoubleProperty y;

	public VisualizationPlane(Canvas canvas, double baseX, double baseY) {
		x = new SimpleDoubleProperty();
		y = new SimpleDoubleProperty();
		timeline = new Timeline(new KeyFrame(Duration.seconds(0), new KeyValue(x, baseX), new KeyValue(y, baseY)),
				new KeyFrame(Duration.seconds(3), new KeyValue(x, canvas.getWidth()),
						new KeyValue(y, canvas.getHeight() / 3)));
		timeline.setCycleCount(1);
	}
}
