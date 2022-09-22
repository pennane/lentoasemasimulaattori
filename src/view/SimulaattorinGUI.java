package view;

import java.text.DecimalFormat;

import controller.IKontrolleriVtoM;
import controller.Kontrolleri;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import simu.framework.Trace;
import simu.framework.Trace.Level;

public class SimulaattorinGUI implements ISimulaattorinUI {

	// Kontrollerin esittely (tarvitaan käyttöliittymässä)
	private IKontrolleriVtoM kontrolleri;

	private SimulaattoriApplication application;

	// Käyttöliittymäkomponentit:
	private TextField aika;
	private TextField viive;
	private Label tulos;
	
	@FXML
	private Canvas simulationRoot; // Käyttöliittymäkomponentti

	@FXML
	private IVisualisointi visualization = null; // Työjuhta

	@FXML
	private Button startButton;
	@FXML
	private Button accellerateButton;
	@FXML
	private Button decelerateButton;

	public void handleStart() {
		Trace.setTraceLevel(Level.INFO);
		System.out.println("Start");
		if (visualization == null) {
			visualization = new Visualisointi(simulationRoot);
			visualization.tyhjennaNaytto();
		}
		if (kontrolleri == null) {
			kontrolleri = new Kontrolleri(this);
		}
	}
	
	public void handleSimuloi() {
		System.out.println("SIMULOIDAAAN :DD");
		startButton.setDisable(true);
		kontrolleri.kaynnistaSimulointi();
	}

	// Käyttöliittymän rajapintametodit (kutsutaan kontrollerista)

	@Override
	public double getAika() {
		return Double.parseDouble(aika.getText());
	}

	@Override
	public long getViive() {
		return Long.parseLong(viive.getText());
	}

	@Override
	public void setLoppuaika(double aika) {
		DecimalFormat formatter = new DecimalFormat("#0.00");
		this.tulos.setText(formatter.format(aika));
	}

	@Override
	public IVisualisointi getVisualisointi() {
		return visualization;
	}

	public void setApplication(SimulaattoriApplication application) {
		this.application = application;
	}

}
