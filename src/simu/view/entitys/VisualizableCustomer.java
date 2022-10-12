package simu.view.entitys;

/**
 * Customer that can be animated into the visualization canvas
 * 
 * @author arttupennanen
 *
 */
public class VisualizableCustomer extends VisualizablePoint {
	private static double ANIMATION_DURATION = 4000;

	public VisualizableCustomer(double baseX, double toX, double y) {
		super(ANIMATION_DURATION, baseX, y, toX, y);
	}
}
