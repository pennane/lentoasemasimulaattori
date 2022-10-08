package simu.view.entitys;

import javafx.scene.canvas.Canvas;

public class VisualizableAirplane extends VisualizablePoint {
	private static double ANIMATION_DURATION = 3000;

	public VisualizableAirplane(Canvas canvas, double baseX, double baseY) {
		super(ANIMATION_DURATION, baseX, baseY, canvas.getWidth(), canvas.getHeight() / 3);
	}
}
