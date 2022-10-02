package simu.model;

import eduni.distributions.ContinuousGenerator;
import simu.framework.Tapahtumalista;

public class CheckinRouter extends PalvelupisteRouter {
	public CheckinRouter(ContinuousGenerator generator, Tapahtumalista tapahtumalista, int amount, String tiedot) {
		super(generator, tapahtumalista, amount, tiedot);

	}

	@Override
	public void aloitaPalvelu() {
		boolean hasMatkatavarat = jono.peek().getHasMatkatavarat();

		if (hasMatkatavarat) {
			super.aloitaPalvelu(TapahtumanTyyppi.CHECKIN_END_BAGGAGE);
			return;
		}

		super.aloitaPalvelu(TapahtumanTyyppi.CHECKIN_END_SELF);
	}
}
