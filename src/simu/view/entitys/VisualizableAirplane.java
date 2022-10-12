package simu.view.entitys;

import javafx.scene.canvas.Canvas;
import simu.model.FlightType;

/**
 * Airplane that can be animated into the visualization canvas
 * 
 * @author arttupennanen
 *
 */
public class VisualizableAirplane extends VisualizablePoint {
	private static double ANIMATION_DURATION = 3000;

	FlightType type;

	public VisualizableAirplane(Canvas canvas, double baseX, double baseY, FlightType type) {
		super(ANIMATION_DURATION, baseX, baseY, canvas.getWidth(), canvas.getHeight() / 3);
		this.type = type;
	}

	public FlightType getType() {
		return type;
	}
}
