package view;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Visualization implements IVisualization {

	private Image airportImage;
	private Image planeImage;
	private double airportImageX;
	private double airportImageY;
	private double planeImageBaseX;
	private double planeImageBaseY;

	private ArrayList<VisualizationPlane> planes = new ArrayList<>();

	private GraphicsContext ctx;
	private Canvas canvas;

	public Visualization(Canvas canvas) {
		this.canvas = canvas;
		ctx = canvas.getGraphicsContext2D();

		airportImage = new Image(getClass().getResourceAsStream("images/airport.png"));
		planeImage = new Image(getClass().getResourceAsStream("images/plane.png"));

		airportImageX = canvas.getWidth() / 2 - airportImage.getWidth();
		airportImageY = canvas.getHeight() / 2 - airportImage.getHeight();
		planeImageBaseX = airportImageX + airportImage.getWidth() - planeImage.getWidth();
		planeImageBaseY = airportImageY + airportImage.getHeight() - planeImage.getHeight();

		drawBase();

		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				drawBackground();
				for (VisualizationPlane plane : planes) {
					ctx.drawImage(planeImage, plane.getX().doubleValue(), plane.getY().doubleValue());
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
	public void displayTime(long timeStampSeconds) {
		// TODO Auto-generated method stub
	}

	@Override
	public void newCustomer() {
		// TODO Auto-generated method stub
	}

	public void summonPlane() {
		VisualizationPlane plane = new VisualizationPlane(canvas, planeImageBaseX, planeImageBaseY);
		planes.add(plane);
		plane.getTimeline().setOnFinished(event -> planes.remove(plane));
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
