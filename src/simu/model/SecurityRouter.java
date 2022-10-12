package simu.model;

import eduni.distributions.ContinuousGenerator;
import simu.framework.Tapahtumalista;

/**
 * PalvelupisteRouter subclass for the Securitycheck service point
 * 
 * @author arttupennanen
 *
 */
public class SecurityRouter extends PalvelupisteRouter {

	public SecurityRouter(ContinuousGenerator generator, Tapahtumalista tapahtumalista, int amount, String tiedot) {
		super(generator, tapahtumalista, amount, tiedot);
	}

	/**
	 * Starts different service based on what is the airplane the customer is headed
	 * towards
	 */
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
