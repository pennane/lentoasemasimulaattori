package view;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import view.entitys.VisualizableAirplane;
import view.entitys.VisualizableCustomer;

public class Visualization implements IVisualization {

	private Image airportImage;
	private Image planeImage;
	private double airportImageX;
	private double airportImageY;
	private double planeImageBaseX;
	private double planeImageBaseY;
	private double customerBaseY;

	private long simulationTime;
	private ArrayList<VisualizableAirplane> airplanes = new ArrayList<>();
	private ArrayList<VisualizableCustomer> customers = new ArrayList<>();

	private GraphicsContext ctx;
	private Canvas canvas;

	public Visualization(Canvas canvas) {

		simulationTime = 0;

		this.canvas = canvas;
		ctx = canvas.getGraphicsContext2D();

		airportImage = new Image(getClass().getResourceAsStream("images/airport.png"));
		planeImage = new Image(getClass().getResourceAsStream("images/plane.png"));

		airportImageX = canvas.getWidth() / 2 - airportImage.getWidth();
		airportImageY = canvas.getHeight() / 2 - airportImage.getHeight();
		planeImageBaseX = airportImageX + airportImage.getWidth() - planeImage.getWidth();
		planeImageBaseY = airportImageY + airportImage.getHeight() - planeImage.getHeight();
		customerBaseY =canvas.getHeight() / 2 - 5;

		drawBase();

		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				drawBackground();
				for (VisualizableAirplane airplane : airplanes) {
					ctx.drawImage(planeImage, airplane.getX().doubleValue(), airplane.getY().doubleValue());
				}
				ctx.setFill(Color.BLACK);
				for (VisualizableCustomer customer : customers) {
					ctx.fillRect(customer.getX().doubleValue(), customer.getY().doubleValue(), 1, 5);
				}
				drawOverlay();
			}
		};
		timer.start();
	}

	private void drawBackground() {
		ctx.setFill(Color.GREEN);
		ctx.fillRect(0, canvas.getHeight() / 2, canvas.getWidth(), canvas.getHeight());
		ctx.setFill(Color.BLUE);
		ctx.fillRect(0, 0, canvas.getWidth(), canvas.getHeight() / 2);
	}

	private void drawOverlay() {
		ctx.drawImage(airportImage, airportImageX, airportImageY);
	}

	@Override
	public void drawBase() {
		drawBackground();
		drawOverlay();
	}

	@Override
	public void setSimulationTime(long timeStampSeconds) {
		simulationTime = timeStampSeconds;
	}

	@Override
	public void newCustomer() {
		VisualizableCustomer customer = new VisualizableCustomer(0, airportImageX, customerBaseY);
		customers.add(customer);
		customer.getTimeline().setOnFinished(event -> customers.remove(customer));
		customer.getTimeline().play();
	}

	public void summonPlane() {
		VisualizableAirplane plane = new VisualizableAirplane(canvas, planeImageBaseX, planeImageBaseY);
		airplanes.add(plane);
		plane.getTimeline().setOnFinished(event -> airplanes.remove(plane));
		plane.getTimeline().play();
	}

	@Override
	public void shengeDepart() {
		summonPlane();
	}

	@Override
	public void internationalDepart() {
		summonPlane();
	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub
	}

}
