package tp.p2;


public class GameObject{
	
	protected int posX;
	protected int posY;
	protected int resistencia;
	protected int daño;
	protected String nombre;
	protected String simbolo;
	protected int frecuencia;
	protected int coste;
	private int contadorFR;
	private String externalise;

	

	public GameObject(int posX, int posY, int resistencia, int daño, String nombre, String simbolo, int frecuencia, int coste) {
		this.posX = posX;
		this.posY = posY;
		this.resistencia = resistencia;
		this.daño = daño;
		this.nombre = nombre;
		this.simbolo = simbolo;
		this.frecuencia = frecuencia;
		this.coste = coste;
		this.contadorFR = frecuencia;
	}

	public String getNombre() {
		return nombre;
	}

	public String getSimbolo() {
		return simbolo;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public int getResistencia() {
		return resistencia;
	}

	public void setResistencia(int resistencia) {
		this.resistencia = resistencia;
	}

	public int getDaño() {
		return daño;
	}

	public int getFrecuencia() {
		return frecuencia;
	}

	public void setFrecuencia(int frecuencia) {
		this.frecuencia = frecuencia;
	}

	public int getContFrec() {
		return contadorFR;
	}
	
	public void setContFrec(int c) {
		contadorFR = c;
	}
	
	public void resetCont() {
		contadorFR = this.frecuencia;
	}
	
	public int getCoste() {
		return coste;
	}
	
	@Override
	public String toString() {
		return this.simbolo + "[" + this.resistencia + "]";
		
	}
	
	public String externalise(int i){
		return this.simbolo+":"+this.resistencia+":"+this.posX+":"+this.posY+":"+ this.frecuencia;
	}

	public String instanciaElemento() {
		return this.externalise;
	}
	
	public void resetFrectuencia() {
		setFrecuencia(2);
	}
	
	public String getObjectDebug() {
		return this.simbolo + "[l:" + this.resistencia + ", x: " + this.posX + ", y: " + this.posY + ", t:" + this.frecuencia + "]";
	}
	
	public boolean mov() {
		contadorFR--;
		if(contadorFR == 0) {
			contadorFR = frecuencia;
			return true;
		}else {
			return false;
		}
	}
	
}
