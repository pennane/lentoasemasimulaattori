package view;

import java.awt.Canvas;
import java.awt.Color;

import javafx.scene.canvas.GraphicsContext;

public class Visualisointi extends Canvas implements IVisualisointi {

	private GraphicsContext gc;

	double i = 0;
	double j = 10;

	public Visualisointi(int w, int h) {
		super(w, h);
		gc = this.getGraphicsContext2D();
		tyhjennaNaytto();
	}

	public void tyhjennaNaytto() {
		gc.setFill(Color.YELLOW);
		gc.fillRect(0, 0, this.getWidth(), this.getHeight());
	}

	public void uusiAsiakas() {
		gc.setFill(Color.RED);
		gc.fillOval(i, j, 10, 10);

		i = (i + 10 % this.getWidth());
		// j = (j + 12) % this.getHeight();
		if (i == 0)
			j += 10;
	}

}
