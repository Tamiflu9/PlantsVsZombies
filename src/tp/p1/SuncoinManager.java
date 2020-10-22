package tp.p1;

public class SuncoinManager {

	private int sunCoins;
	
	public SuncoinManager() {
		this.sunCoins = 50;
	}
	
	public void acumularSunCoins(int sc) {
		sunCoins += sc;
	}

	public int getSunCoins() {
		return sunCoins;
	}

	public void setSunCoins(int sunCoins) {
		this.sunCoins = sunCoins;
	}
	
	
}
