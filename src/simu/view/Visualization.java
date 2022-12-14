package simu.view;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import simu.model.FlightType;
import simu.view.entitys.GUIClock;
import simu.view.entitys.VisualizableAirplane;
import simu.view.entitys.VisualizableCustomer;

/**
 * Animation central of the simulation
 * 
 * Animate the simulation by visualizing arriving customers, departing airplanes
 * and running time
 * 
 * Heart of the visualization is an AnimationTimer which runs constantly and
 * listens for calls from the motor
 * 
 * @author arttupennanen
 *
 */
public class Visualization implements IVisualization {

	private static final int CUSTOMER_HEIGHT = 8;
	private static final int CUSTOMER_WIDTH = 1;

	private Image airportImage;
	private Image planeImage;
	private double airportImageX;
	private double airportImageY;
	private double planeImageBaseX;
	private double planeImageBaseY;
	private double customerBaseY;

	private Image plane2Image;

	private GUIClock clock;
	private ArrayList<VisualizableAirplane> airplanes;
	private ArrayList<VisualizableCustomer> customers;

	private GraphicsContext ctx;
	private Canvas canvas;

	public Visualization(Canvas canvas) {
		this.canvas = canvas;
		ctx = canvas.getGraphicsContext2D();

		airportImage = new Image(getClass().getResourceAsStream("images/airport.png"));
		planeImage = new Image(getClass().getResourceAsStream("images/plane.png"));
		plane2Image = new Image(getClass().getResourceAsStream("images/plane2.png"));
		airplanes = new ArrayList<>();
		customers = new ArrayList<>();

		airportImageX = canvas.getWidth() / 2 - airportImage.getWidth();
		airportImageY = canvas.getHeight() / 2 - airportImage.getHeight();
		planeImageBaseX = airportImageX + airportImage.getWidth() - planeImage.getWidth();
		planeImageBaseY = airportImageY + airportImage.getHeight() - planeImage.getHeight();
		customerBaseY = canvas.getHeight() / 2 - CUSTOMER_HEIGHT;

		clock = new GUIClock(0, 10, 10);

		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				drawBackground();
				for (VisualizableAirplane airplane : airplanes) {
					if (airplane.getType() == FlightType.International) {
						ctx.drawImage(plane2Image, airplane.getX().doubleValue(), airplane.getY().doubleValue());
					} else {
						ctx.drawImage(planeImage, airplane.getX().doubleValue(), airplane.getY().doubleValue());
					}

				}
				ctx.setFill(Color.BLACK);
				for (VisualizableCustomer customer : customers) {
					ctx.fillRect(customer.getX().doubleValue(), customer.getY().doubleValue(), CUSTOMER_WIDTH,
							CUSTOMER_HEIGHT);
				}
				drawOverlay();
			}
		};
		timer.start();
	}

	private void drawClock() {
		clock.draw(ctx);
	}

	private void drawBackground() {
		ctx.setFill(Color.GREEN);
		ctx.fillRect(0, canvas.getHeight() / 2, canvas.getWidth(), canvas.getHeight());
		ctx.setFill(Color.BLUE);
		ctx.fillRect(0, 0, canvas.getWidth(), canvas.getHeight() / 2);
	}

	private void drawOverlay() {
		ctx.drawImage(airportImage, airportImageX, airportImageY);
		drawClock();
	}

	@Override
	public void setSimulationTimeSeconds(long timeStampSeconds) {
		clock.setSimulationTimeSeconds(timeStampSeconds);
	}

	@Override
	public void newCustomer() {
		VisualizableCustomer customer = new VisualizableCustomer(0, airportImageX, customerBaseY);
		customers.add(customer);
		customer.getTimeline().setOnFinished(event -> customers.remove(customer));
		customer.getTimeline().play();
	}

	public void summonPlane(FlightType type) {
		VisualizableAirplane plane = new VisualizableAirplane(canvas, planeImageBaseX, planeImageBaseY, type);
		airplanes.add(plane);
		plane.getTimeline().setOnFinished(event -> airplanes.remove(plane));
		plane.getTimeline().play();
	}

	@Override
	public void shengeDepart() {
		summonPlane(FlightType.Shengen);
	}

	@Override
	public void internationalDepart() {
		summonPlane(FlightType.International);
	}

	/**
	 * This gets called when the simulation finishes. There has not yet been a
	 * vision for what we could want to visualize in those cases, though.
	 */
	@Override
	public void finish() {
		// TODO Auto-generated method stub
	}

}
