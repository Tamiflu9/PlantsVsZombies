package tp.p1;

import tp.p3.exception.CommandParseException;

public enum Level {	
	
	EASY("EASY", 3, 0.1),
	HARD("HARD",5, 0.2), 
	INSANE("INSANE",10, 0.3);
	
	/*FACIL("EASY", 3, 0.1), 
	MEDIO("HARD", 5, 0.2),
	DIFICIL("INSANE", 10, 0.3);
	*/

	private String nivel;
	private int numeroZombis;
	private double frecuencia;
	
//  private int numberOfZombies; 
//	private double zombieFrequency; 
	
	Level (String l, int n , double f) {
		this.nivel = l;
		this.numeroZombis = n;
		this.frecuencia = f;
	}
	
/*	private Level(int zombieNum, double zombieFreq){
		numberOfZombies = zombieNum;
		zombieFrequency = zombieFreq; 
	}*/ 
	
	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public int getNumeroZombis() {
		return numeroZombis;
	}

	public void setNumeroZombis(int numeroZombis) {
		this.numeroZombis = numeroZombis;
	}

	public double getFrecuencia() {
		return frecuencia;
	}

	public void setFrecuencia(double frecuencia) {
		this.frecuencia = frecuencia;
	}
	 
	/*public int getNumberOfZombies() {
		return numberOfZombies; 
	}*/ 
	 
	/*public double getZombieFrequency() { 
		return zombieFrequency; 
	}*/ 
	 
	public static Level parse(String inputString) throws CommandParseException {
		
		for (Level level : Level.values()) {
			if (level.name().equalsIgnoreCase(inputString)) {
				return level; 
			}
		}
		throw new CommandParseException("Usage: plantsVsZombies <level: "+inputString+"> [seed]: level must be one of: EASY, HARD, INSANE");
		
		//return null;
	} 
	
	public static String all(String separator) {
		StringBuilder sb = new StringBuilder();
		for (Level level : Level.values()) {
			sb.append(level.name() + separator);
		}
		String allLevels = sb.toString();
		int A = allLevels.length() - separator.length();
		return allLevels.substring(0, A);
	}
	
}
	
