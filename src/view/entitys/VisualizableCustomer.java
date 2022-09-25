package view.entitys;

public class VisualizableCustomer extends VisualizableEntity {
	private static double ANIMATION_DURATION = 4000;

	public VisualizableCustomer(double baseX, double toX, double y) {
		super(ANIMATION_DURATION, baseX, y, toX, y);
	}
}
