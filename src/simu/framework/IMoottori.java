package simu.framework;

public interface IMoottori {

	// Kontrolleri käyttää tätä rajapintaa

	public void setSimulointiaika(double aika);

	public void setViive(long aika);

	public long getViive();
}
