package simu.model;


public class LentoasemaAsiakas extends Asiakas {
	private boolean matkatavara;   //muuttuja jolla ilmaistaan onko asiakkaalla matkatavaroita
	private int lentoid;           //TODO lento johon asiakas on menossa
	
	public LentoasemaAsiakas( boolean m) {
		this.matkatavara = m;
	}

}
