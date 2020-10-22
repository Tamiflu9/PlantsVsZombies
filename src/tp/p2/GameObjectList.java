package tp.p2;

public class GameObjectList {
	private int tamP;
	private int tamZ;
	private GameObject[] listaPlantas;
	private GameObject[] listaZombie;
	
	public GameObjectList() {
		this.tamP = 0;
		this.tamZ = 0;
		listaPlantas = new GameObject[32];
		listaZombie = new GameObject[32];
	}
	
	public boolean listaPlantaVacia() {
		if (tamP == 0) {
			return true;
		}
		return false;
	}
	
	public boolean listaZombieVacia() {
		if (tamZ == 0) {
			return true;
		}
		return false;
	}
	
	public int posPlanta(int f, int c) {
		int pos = -1;
		boolean z = false;
		int i = 0;
		while(i < this.tamP && !z) {
			if(this.listaPlantas[i].getPosX() == f && this.listaPlantas[i].getPosY() == c) {
				pos = i;
				z = true;
			}
			i++;
		}
		return pos;
	}
	
	public void eliminarPlanta(int i) {
		listaPlantas[i] = listaPlantas[tamP-1];
		tamP--;
	}
	/*
	public void eliminarZombie(int i) {
		listaZombie[i] = listaZombie[tamZ-1];
		tamZ--;
	}*/
	public void eliminarZombie(int i) {
		for(int j = i; j < tamZ-1; j++) {
			listaZombie[j] = listaZombie[j+1];
		}
		tamZ--;
	}
	public void añadirPlanta(GameObject p) {
		listaPlantas[tamP] = p;
		tamP++;
	}
	
	public void añadirZombie(GameObject z) {
		listaZombie[tamZ] = z;
		tamZ++;
	}
	
	public void resetListaPlant() {
		listaPlantas = new GameObject[32];
	}
	
	public void resetListaZombie() {
		listaZombie = new GameObject[32];
	}
	
	public GameObject[] getListaPlant() {
		return listaPlantas;
	}

	public GameObject[] getListaZombie() {
		return listaZombie;
	}
	
	public void setPlant(GameObject planta[]) {
		this.listaPlantas = planta;
	}
	
	public void setZombie(GameObject zombie[]) {
		this.listaZombie = zombie;
	}
	
	public String posPlantaDe(int pos) {
		return this.listaPlantas[pos].getObjectDebug();
	}
public String posZombieDe(int pos) {
		
		return this.listaZombie[pos].getObjectDebug();
	}
	public int getTamP() {
		return this.tamP;
	}
	
	public int getTamZ() {
		return this.tamZ;
	}
	
	public String imprimeElementosPlanta() {
		StringBuilder p = new StringBuilder();
		for(int i = 0; i < tamP; i++) {
			if (i == 0) {
				p.append(listaPlantas[i].externalise(i));
			}else if(i > 0) {
				p.append(" , " +listaPlantas[i].externalise(i));
			}	
		}
		
		return p.toString();
	}

	public String imprimeElementosZombie() {
		StringBuilder z = new StringBuilder();
		for(int i = 0; i < tamZ; i++) {
			if (i == 0) {
				z.append(listaZombie[i].externalise(i));
			}else if(i > 0) {
				z.append(", " +listaZombie[i].externalise(i));
			}
		}
		return z.toString();
	}

	//posX, posY, resistencia, daño, nombre, simbolo, frecuencia, coste
	
	public void meteElementosPlanta(String s, int v, int x, int y, int f) {
		if(s.equalsIgnoreCase("s")) {
			GameObject p = new GameObject(x,y,v,1,"sunflower",s,f,20);
			añadirPlanta(p);
		}else if(s.equalsIgnoreCase("p")) {
			GameObject p = new GameObject(x,y,v,1,"peashooter",s,f,50);
			añadirPlanta(p);
		}else if(s.equalsIgnoreCase("n")) {
			GameObject p = new GameObject(x,y,v,0,"nuez",s,f,50);
			añadirPlanta(p);
		}else if(s.equalsIgnoreCase("c")) {
			GameObject p = new GameObject(x,y,v,10,"petacereza",s,f,50);
			añadirPlanta(p);
		}
	}

	public void meteElementosZombie(String s, int v, int x, int y, int f) {
		if(s.equalsIgnoreCase("z")) {
			GameObject z = new GameObject(x,y,v,1,"zombieComun",s,f,0);
			añadirZombie(z);
		}else if(s.equalsIgnoreCase("w")) {
			GameObject z = new GameObject(x,y,v,1,"zombieCaracubo",s,f,0);
			añadirZombie(z);
		}else if(s.equalsIgnoreCase("x")) {
			GameObject z = new GameObject(x,y,v,1,"zombieDeportista",s,f,0);
			añadirZombie(z);
		}
	}

	public String pintaZ(int i) {
		return listaZombie[i].toString();
	}
	
	public String pintaP(int i) {
		return listaPlantas[i].toString();
	}

	public int posZombie(int c, int f) {
		int i = 0;
		int pos = -1;
		boolean z = false;
		while (i < this.tamZ && !z) {
			if(this.listaZombie[i].getPosX() == f && this.listaZombie[i].getPosY() == c) {
				pos = i;
				z = true;
			}
			i++;
		}
		return pos;
	}
	
}
