package tp.p1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

import tp.p2.GameObject;
import tp.p2.GameObjectList;
import tp.p2.Zombies.ZombieFactory;
import tp.p3.exception.CommandExecuteException;
import tp.p3.exception.CommandParseException;
import tp.p3.exception.FileContentsException;

public class Game {

	private GameObjectList lista;
	private String[][]tablero;
	private ZombieManager zomMng;
	private String[] nombreZombie = {"zombieComun", "zombieDeportista", "zombieCaracubo"};
	private SuncoinManager suncoinMng = new SuncoinManager();
	private int ciclo = 0;
	private int seed;
	private Scanner scn;
	private String nvl;
	private Random posAleatorio = new Random(seed);
	private boolean finished = false;


	public Game(String nivel, int seed) throws CommandParseException {
		super();
		lista = new GameObjectList();
		this.zomMng =  new ZombieManager(nivel);
		this.seed = seed;
		this.nvl = nivel;
	}

	public String getN() {
		return this.nvl;
	}

	public void setNievel(String nivel) {
		this.nvl = nivel;
	}

	public int getNumZ() {
		return this.lista.getTamZ();
	}

	public int getNumP() {
		return this.lista.getTamP();
	}

	public int getPosPlanta(int f, int c) {
		return this.lista.posPlanta(f, c);
	}

	public int getPosZombie(int f, int c) {
		return lista.posZombie(c,f);
	}

	public String getPintaP (int i) {
		return lista.pintaP(i);
	}

	public String getPintaZ (int i) {
		return lista.pintaZ(i);
	}
	public String getPintaPDebug (int i) {
		return lista.posPlantaDe(i);
	}

	public String getPintaZDebug (int i) {
		return lista.posZombieDe(i);
	}

	public boolean addPlant(GameObject planta, int x, int y) throws CommandExecuteException{
		boolean a = false;
		if (x < 4 && y < 8) {
			if (vacio(this.tablero[x][y]) && suficientesCoins(planta)) {
				lista.añadirPlanta(planta);
				this.tablero[x][y] = planta.toString();
				int c = suncoinMng.getSunCoins() - planta.getCoste();
				suncoinMng.setSunCoins(c);
				a = true;
			}else {
				throw new CommandExecuteException("Failed to add Sunflower: position (" +x +", " +y+") is already occupied");
			}
		}
		return a;
	} 

	private boolean vacio(String string) {
		if(string.equals("    ") || string.equals("")){
			return true;
		}
		return false;
	}

	public void addZombie() {

		int posNomb = (int) Math.round(Math.random()*2);
		int y = 7;
		int x = posAleatorio.nextInt(4);


		if(zomMng.isZombieAdded()) {
			String nombre = nombreZombie[posNomb];
			GameObject z = ZombieFactory.getZombie(nombre, x, y);
			if(this.tablero[x][y].equals("    ")){
				this.tablero[x][y] = z.toString();
				lista.añadirZombie(z);
				zomMng.restar();
			}
		}
	}

	public void contadorCiclos() {
		ciclo++;
	}

	public boolean suficientesCoins (GameObject planta) throws CommandExecuteException {
		boolean suf = false;
		if (planta.getNombre().equals("sunflower")) {
			if(suncoinMng.getSunCoins() >= 20) {
				suf= true;
			}
			else {
				throw new CommandExecuteException("Failed to add " + planta.getNombre() +": not enough suncoins to buy it"+'\n');
			}
		}
		else if(planta.getNombre().equals("peashooter") ||planta.getNombre().equals("petacereza") || planta.getNombre().equals("nuez")) {
			if(suncoinMng.getSunCoins() >= 50) {
				suf= true;
			}
			else {
				throw new CommandExecuteException("Failed to add " + planta.getNombre() +": not enough suncoins to buy it");
			}
		}
		return suf;
	}

	// para ver si tiene que generar soles
	public void updateS(int p) {
		int frec = lista.getListaPlant()[p].getFrecuencia();
		if(frec == 1) { // ha terminado y se reinica el ciclo
			suncoinMng.acumularSunCoins(10);
			lista.getListaPlant()[p].setFrecuencia(2);
		}
		else {
			lista.getListaPlant()[p].setFrecuencia(frec-1);
		}
	}

	// para ver si tiene que explotar
	public boolean updatePC(int p) {
		int frec = lista.getListaPlant()[p].getFrecuencia();
		if(frec == 0) {
			return true;
		}else {
			lista.getListaPlant()[p].setFrecuencia(frec-1);
			return false;
		}
	}

	public boolean update() {
		for (int p = 0; p < lista.getTamP(); p++){
			for(int z = 0; z < lista.getTamZ(); z++) {
				if(lista.getListaPlant()[p].getNombre().equals("sunflower")) {
					// misma fila
					if(lista.getListaPlant()[p].getPosX() == lista.getListaZombie()[z].getPosX()) {
						// a la derecha hay zombie
						if(lista.getListaPlant()[p].getPosY() == lista.getListaZombie()[z].getPosY()-1) {
							hacerDañoAPlanta(p,z);
						}
					}
				}				
				if(lista.getListaPlant()[p].getNombre().equals("petacereza")) {
					// misma fila
					if(lista.getListaPlant()[p].getPosX() == lista.getListaZombie()[z].getPosX()) {
						// a la derecha
						if(lista.getListaPlant()[p].getPosY() == lista.getListaZombie()[z].getPosY()-1) {
							if (updatePC(p)) { // si explota 
								hacerDañoAZombie(p,z);
								eliminarPlanta(p);
							}
							else {
								hacerDañoAPlanta(p, z);
							}
						}
					}
					// arriba o abajo				
					else if(lista.getListaPlant()[p].getPosY() == lista.getListaZombie()[z].getPosY()) {
						if(lista.getListaPlant()[p].getPosX() == lista.getListaZombie()[z].getPosX()-1 || lista.getListaPlant()[p].getPosX() == lista.getListaZombie()[z].getPosX()+1) {
							if (updatePC(p)) { // cada dos ciclos explota
								hacerDañoAZombie(p,z);
								eliminarPlanta(p);
							}
						}
					}
					// donde sea
					else {
						if (updatePC(p)) { // cada dos ciclos explota
							eliminarPlanta(p);
						}
					}
				}
				// nuez
				if(lista.getListaPlant()[p].getNombre().equals("nuez")) {
					if(lista.getListaPlant()[p].getPosX() == lista.getListaZombie()[z].getPosX()) {
						if(lista.getListaPlant()[p].getPosY() == lista.getListaZombie()[z].getPosY()-1) {
							hacerDañoAPlanta(p,z);
						}
					}
				}
				// peashooter				
				if(lista.getListaPlant()[p].getNombre().equals("peashooter")) {
					if(lista.getListaPlant()[p].getPosX() == lista.getListaZombie()[z].getPosX()) {
						if(lista.getListaPlant()[p].getPosY() == lista.getListaZombie()[z].getPosY()-1) {
							hacerDañoAZombie(p,z);
							hacerDañoAPlanta(p,z);
						}
						else {
							hacerDañoAZombie(p,z);
						}
					}
				}
			}
		}
		boolean perdido = false;
		if(!lista.listaZombieVacia()) {
			for (int x = 0; x < lista.getTamZ(); x++) {
				avanzarZombie(x);
				if (lista.getListaZombie()[x].getPosY() == 0) {
					perdido = true;
				}
			}
		}
		
		if (lista.listaZombieVacia() && !lista.listaPlantaVacia()) {
			for (int p = 0; p < lista.getTamP(); p++){
				if(lista.getListaPlant()[p].getNombre().equals("sunflower")) {
					updateS(p);
				}
				else if(lista.getListaPlant()[p].getNombre().equals("petacereza")) {
					if (updatePC(p)) { // cada dos ciclos explota
						eliminarPlanta(p);
					}
				}
			}
		}
		if (!lista.listaPlantaVacia() && !lista.listaZombieVacia()) {
			for (int p = 0; p < lista.getTamP(); p++){
				if(lista.getListaPlant()[p].getNombre().equals("sunflower")) {
					updateS(p);
				}
			}
		}

		if(zomMng.getRemZombies() == 0 && lista.listaZombieVacia()) {
			ganado();
			return true;
		}
		if(perdido) {
			perdido();
			return true;
		}
		return false;
	}

	public void eliminarZombie(int pos) {
		int posYzombie = lista.getListaZombie()[pos].getPosY();
		int posXzombie = lista.getListaZombie()[pos].getPosX();
		lista.eliminarZombie(pos);
		this.tablero[posXzombie][posYzombie] = "    ";
	}

	public void hacerDañoAPlanta(int p, int z) {
		int dañoZombie;
		int vidaPlanta;
		int vidaRestanteP;

		int x = lista.getListaPlant()[p].getPosX();
		int y = lista.getListaPlant()[p].getPosY();

//		if (lista.getListaZombie()[z].mov()) {
			vidaPlanta = lista.getListaPlant()[p].getResistencia();
			dañoZombie = lista.getListaZombie()[z].getDaño();
			vidaRestanteP = vidaPlanta - dañoZombie;
			lista.getListaPlant()[p].setResistencia(vidaRestanteP);	
			this.tablero[x][y] = lista.getListaPlant()[p].toString();

			if (vidaRestanteP == 0) {
				lista.getListaPlant()[p].setResistencia(vidaRestanteP);
				eliminarPlanta(p);
			}
//		}
	}

	public void hacerDañoAZombie(int p, int z) {
		int dañoPlanta;
		int vidaZombie;
		int vidaRestanteZ;
		boolean esPrimero = true;
		for(int i = 0;  i < lista.getTamZ(); i++) {
			if(i != z && lista.getListaZombie()[i].getPosX() == lista.getListaZombie()[z].getPosX() && lista.getListaZombie()[i].getPosY() < lista.getListaZombie()[z].getPosY()) {
				esPrimero = false;
			}
		}

		if(esPrimero) {
			int x = lista.getListaZombie()[z].getPosX();
			int y = lista.getListaZombie()[z].getPosY();

			vidaZombie = lista.getListaZombie()[z].getResistencia();
			dañoPlanta = lista.getListaPlant()[p].getDaño();
			vidaRestanteZ = vidaZombie - dañoPlanta;
			lista.getListaZombie()[z].setResistencia(vidaRestanteZ);		
		
			if(vidaRestanteZ <= 0) {
				eliminarZombie(z);
			}else {
				this.tablero[x][y] = lista.getListaZombie()[z].toString();
			}
		}
	}

	public void avanzarZombie(int z) {
		int x = lista.getListaZombie()[z].getPosX();
		int y = lista.getListaZombie()[z].getPosY();
		if(lista.getListaZombie()[z].mov()) {
			if (this.tablero[x][y-1].equals("    ")) {
				int ynueva = y-1;
				lista.getListaZombie()[z].setPosY(ynueva);
				this.tablero[x][ynueva] = lista.getListaZombie()[z].toString(); //tablero[x][y] 
				this.tablero[x][y] = "    ";
			}
		}

	}

	public void eliminarPlanta(int pos) {
		int posYplant = lista.getListaPlant()[pos].getPosY();
		int posXplant = lista.getListaPlant()[pos].getPosX();
		this.tablero[posXplant][posYplant] = "    ";
		lista.eliminarPlanta(pos);
	}

	public String[][] actualizarTablero(){
		//Recorres cada lista, coges su posición y en el tablero en esa posición dibujas el elemento

		for(int i = 0; i < lista.getTamP(); i++) {
			int x = lista.getListaPlant()[i].getPosX();
			int y = lista.getListaPlant()[i].getPosY();
			this.tablero[x][y] = lista.getListaPlant()[i].toString();
		}
		for(int j = 0; j < lista.getTamZ(); j++) {
			int x = lista.getListaZombie()[j].getPosX();
			int y = lista.getListaZombie()[j].getPosY();
			this.tablero[x][y] = lista.getListaZombie()[j].toString();
		}
		return this.tablero;
	}

	// pierdes cuando un zombie pasa a la pos 0
	public void perdido() {
		finished = true;
		StringBuilder sb = new StringBuilder();
		sb.append("Zombies win"+'\n');
		sb.toString();
		System.err.println(sb);
		
	}

	public void ganado() {
		finished = true;
		StringBuilder sb = new StringBuilder();
		sb.append("Player win"+'\n');
		sb.toString();
		System.err.println(sb);
		
	}

	public void setR() {
		int x = zomMng.reset();
		this.zomMng.setRemZombies(x);
	}
	
	public void reset() {
		GameObjectList listaNueva = new GameObjectList();
		lista = listaNueva;
		setCiclo(0);
		suncoinMng.setSunCoins(50);
		setR();
	}

	public boolean isFinished() {
		return finished;
	}

	public int getSeed() {
		return this.seed;
	}

	public int getSuncoinMng() {
		return this.suncoinMng.getSunCoins();
	}

	public int getZomMng() {
		return this.zomMng.getRemZombies();
	}

	public int getCiclo() {
		return this.ciclo;
	}

	public void setCiclo(int c) {
		this.ciclo=c;
	}

	public String[][] getTablero() {
		return tablero;
	}


	public void setTablero(String[][] tablero) {
		this.tablero = tablero;
	}

	public GameObjectList getLista(){
		return this.lista;
	}

	public void setLista(GameObjectList l) {
		this.lista = l;
	}

	public void store(String nombreArchivo) throws IOException{
		FileWriter fichero = null;
		PrintWriter pw = null;

		try {
			fichero = new FileWriter(nombreArchivo);
			pw = new PrintWriter(fichero);

			pw.println("Plants Vs Zombies v3.0");
			pw.println();
			pw.println("cycles: " + getCiclo());
			pw.println("sunCoins: " + getSuncoinMng());
			pw.println("level: " + getN().toLowerCase());
			pw.println("remZombies: " + getZomMng());
			pw.println("plantList: " + lista.imprimeElementosPlanta());
			pw.println("zombieList: " + lista.imprimeElementosZombie());

		}finally {
			fichero.close();
		}
	}	


	public void load(String nombreArchivo) throws IOException, FileContentsException{
		scn = new Scanner(new File(nombreArchivo));
		String linea = scn.nextLine();
		GameObjectList listaNueva = new GameObjectList();

		if(linea.equalsIgnoreCase("Plants Vs Zombies v3.0")) {
			linea = scn.nextLine();
			String lineaArchivo[] = linea.split("\\s+");

			///*********************************
			linea = scn.nextLine();
			lineaArchivo = linea.split("\\s+");

			int cicloNuevo = Integer.parseInt(lineaArchivo[1]);
			linea = scn.nextLine();
			lineaArchivo = linea.split("\\s+");
			int sunNuevo = Integer.parseInt(lineaArchivo[1]);
			linea = scn.nextLine();
			lineaArchivo = linea.split("\\s+");
			String nvlNuevo = lineaArchivo[1];
			linea = scn.nextLine();
			lineaArchivo = linea.split("\\s+");
			int remZomNUevo = Integer.parseInt(lineaArchivo[1]);
			linea = scn.nextLine();
			lineaArchivo = linea.split("\\s+");
			// lista plantas
			for(int i = 1; i < lineaArchivo.length; i++) {
				String s = lineaArchivo[i].substring(0,1);
				if(!s.equalsIgnoreCase(",")) {
					int v = Integer.parseInt(lineaArchivo[i].substring(2,3));
					int x = Integer.parseInt(lineaArchivo[i].substring(4,5));
					int y = Integer.parseInt(lineaArchivo[i].substring(6,7));
					int f = Integer.parseInt(lineaArchivo[i].substring(8,9));
					listaNueva.meteElementosPlanta(s,v,x,y,f);
				}
			}	
			linea = scn.nextLine();
			lineaArchivo = linea.split("\\s+");
			// lista zombies
			for(int j = 1; j < lineaArchivo.length; j++) {
				String s = lineaArchivo[j].substring(0,1);
				if (!s.equalsIgnoreCase(",")) {
					int v = Integer.parseInt(lineaArchivo[j].substring(2,3));
					int x = Integer.parseInt(lineaArchivo[j].substring(4,5));
					int y = Integer.parseInt(lineaArchivo[j].substring(6,7));
					int f = Integer.parseInt(lineaArchivo[j].substring(8,9));
					listaNueva.meteElementosZombie(s,v,x,y,f);
				}
			}
			setCiclo(cicloNuevo);
			zomMng.setRemZombies(remZomNUevo);
			suncoinMng.setSunCoins(sunNuevo);
			setNievel(nvlNuevo);
			setLista(listaNueva);

		}else {
			throw new FileNotFoundException();
		}		
	}

}
