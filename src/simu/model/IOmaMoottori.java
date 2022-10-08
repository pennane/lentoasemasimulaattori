package simu.model;

public interface IOmaMoottori {

	// Kontrolleri käyttää tätä rajapintaa

	public void setSimulointiaika(long aika);

	public void setSettingsViive(long aika);

	public long getSettingsViive();
}