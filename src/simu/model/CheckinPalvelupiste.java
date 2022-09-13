package simu.model;

import eduni.distributions.ContinuousGenerator;
import simu.framework.Tapahtumalista;

public class CheckinPalvelupiste extends Palvelupiste {
	public CheckinPalvelupiste(ContinuousGenerator generator, Tapahtumalista tapahtumalista) {
		super(generator, tapahtumalista);

	}

	@Override
	public void aloitaPalvelu() {
		boolean hasMatkatavara = jono.peek().getMatkatavara();

		if (hasMatkatavara) {
			super.aloitaPalvelu(TapahtumanTyyppi.CHECKIN_END_BAGGAGE);
			return;
		}

		super.aloitaPalvelu(TapahtumanTyyppi.CHECKIN_END_SELF);

	}
}
