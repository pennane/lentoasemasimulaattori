package simu.view.entitys;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.util.Duration;

/**
 * A thing that can be given into the Visualization and drawn into the canvas
 * every frame
 * 
 * @author arttupennanen
 *
 */
public abstract class VisualizablePoint {
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

	public VisualizablePoint(double duration, double fromX, double fromY, double toX, double toY) {
		x = new SimpleDoubleProperty();
		y = new SimpleDoubleProperty();
		timeline = new Timeline(new KeyFrame(Duration.seconds(0), new KeyValue(x, fromX), new KeyValue(y, fromY)),
				new KeyFrame(Duration.seconds(3), new KeyValue(x, toX), new KeyValue(y, toY)));
		timeline.setCycleCount(1);
	}
}
