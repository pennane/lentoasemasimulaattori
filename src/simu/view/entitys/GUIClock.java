package simu.view.entitys;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import simu.constants.Constants;

/**
 * GUIClock entity of the simulation layout.
 * 
 * Computes hour hand angle which is memoized for performance
 * 
 * @author arttupennanen
 *
 */
public class GUIClock {
	private static final int DIAMETER = 62;
	private static final int ARROW_HAND_WIDTH = 3;
	private static final int ARROW_HAND_LENGTH = 28;

	private double maxClockSeconds;
	private long simulationTimeSeconds;
	private long lastSimulationTimeSeconds;
	private double lastAngle;

	private double x;
	private double y;
	private double lineOriginX;
	private double lineOriginY;

	public long getSimulationTimeSeconds() {
		return simulationTimeSeconds;
	}

	public void setSimulationTimeSeconds(long simulationTimeSeconds) {
		this.simulationTimeSeconds = simulationTimeSeconds;
	}

	public GUIClock(long initialSimulationTime, double x, double y) {
		maxClockSeconds = Constants.SECONDS_IN_HOUR * 12l;
		simulationTimeSeconds = initialSimulationTime;
		lastSimulationTimeSeconds = initialSimulationTime;
		lastAngle = computeAngle();

		this.x = x;
		this.y = y;
		lineOriginX = x + DIAMETER / 2;
		lineOriginY = y + DIAMETER / 2;
	}

	/**
	 * Compute Angle for the arrow hand given the stored ellapsed seconds
	 */
	private double computeAngle() {
		double clockSeconds = simulationTimeSeconds % maxClockSeconds;
		double angle = (clockSeconds / maxClockSeconds) * 365 - 90;
		return angle;
	}

	/**
	 * "Memoized" version of the computeAngle
	 * 
	 * Not really, but it does not recompute the angle if the seconds haven't
	 * changed
	 * 
	 * The storing does not necessarily make sense, as we anyway do expensive
	 * calculations in the draw method ":D"
	 * 
	 * @return angle
	 */
	private double findAngle() {
		if (simulationTimeSeconds == lastSimulationTimeSeconds)
			return lastAngle;

		lastAngle = computeAngle();
		lastSimulationTimeSeconds = simulationTimeSeconds;

		return lastAngle;
	}

	/**
	 * Draws the clock into a GraphicsContext
	 * 
	 * @param ctx
	 */
	public void draw(GraphicsContext ctx) {
		ctx.setFill(Color.WHITE);
		ctx.fillOval(x, y, DIAMETER, DIAMETER);

		ctx.setLineWidth(ARROW_HAND_WIDTH);
		ctx.strokeLine(lineOriginX, lineOriginY,
				lineOriginX + ARROW_HAND_LENGTH * Math.cos(Math.toRadians(findAngle())), // TODO: do not compute on
																							// every frame
				lineOriginY + ARROW_HAND_LENGTH * Math.sin(Math.toRadians(findAngle()))); // TODO: do not compute on
																							// every frame
	}

}
