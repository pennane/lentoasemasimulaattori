package view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Visualisointi implements IVisualisointi {

	private GraphicsContext ctx;
	private Canvas canvas;


	public Visualisointi(Canvas canvas) {
		this.canvas = canvas;
		ctx = canvas.getGraphicsContext2D();
		tyhjennaNaytto();
	}

	public void tyhjennaNaytto() {
		ctx.setFill(Color.BLACK);
		ctx.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
	}

	public void uusiAsiakas() {
		System.out.println("uusi asiakas");
	}
	
	public void lentoPoistuu() {
		System.out.println("lento poistuu");
	}

}
