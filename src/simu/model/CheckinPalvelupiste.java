package simu.model;

import eduni.distributions.ContinuousGenerator;
import simu.framework.Tapahtumalista;

public class CheckinPalvelupiste extends Palvelupiste {
	public CheckinPalvelupiste(ContinuousGenerator generator, Tapahtumalista tapahtumalista) {
		super(generator, tapahtumalista);
		this.tapahtumalista = tapahtumalista;
		this.generator = generator;
	}

	@Override
	public void aloitaPalvelu() {
		boolean hasMatkatavara = jono.peek().getMatkatavara();

		if (hasMatkatavara) {
			super.aloitaPalvelu(TapahtumanTyyppi.CHECKIN_END_LUGGAGE);
			return;
		}

		super.aloitaPalvelu(TapahtumanTyyppi.CHECKIN_END_SELF);

	}
}
