package simu.model;

import eduni.distributions.ContinuousGenerator;
import simu.framework.Tapahtumalista;

public class SecurityPalvelupiste extends Palvelupiste {

	public SecurityPalvelupiste(ContinuousGenerator generator, Tapahtumalista tapahtumalista) {
		super(generator, tapahtumalista);
	}

	@Override
	public void aloitaPalvelu() {
		FlightType flightType = jono.peek().getFlightType();

		if (flightType == FlightType.Shengen) {
			super.aloitaPalvelu(TapahtumanTyyppi.SECURITYCHECK_END_SCHENGE);
			return;
		}

		super.aloitaPalvelu(TapahtumanTyyppi.SECURITYCHECK_END_INTERNATIONAL);

	}
}
