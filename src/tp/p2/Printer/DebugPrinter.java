package tp.p2.Printer;

import tp.p1.Game;

public class DebugPrinter extends BoardPrinter {

	public DebugPrinter(Game g) {
		this.game=g;
		this.posX = 0;
		if(game.getNumZ() + game.getNumP() > 0){
			this.posY = game.getNumZ() + game.getNumP();
		}
		else{
			this.posY = 1;
		}
		this.setCell(25);
	}

	@Override
	public void encodeGame(Game game) {
		tableroP = new String[posX+1][posY];
		if(game.getLista().listaPlantaVacia() && game.getLista().listaZombieVacia()){
			for (int i = 0; i < this.posY ; i++) {
				tableroP[posX][i] = space;
			}
		}
		else{
			for (int i = 0; i < game.getNumP() ; i++) {
				tableroP[posX][i] = game.getLista().posPlantaDe(i);
			}
			for (int j = 0; j < game.getNumZ() ; j++) {
				tableroP[posX][j+game.getNumP()] = game.getLista().posZombieDe(j);
			}
		}
		game.setTablero(tableroP);

	}

	public void setTablero(String[][] tablero) {
		this.tableroP= tablero;
	}

	public String[][] getTablero(){
		return this.tableroP;
	}

	

	public void printGame() {
		StringBuilder str = new StringBuilder();

		String c = ""+ game.getCiclo();
		str.append("Number of cycles: ").append(c).append(System.lineSeparator());
		str.append("Sun coins: ").append(game.getSuncoinMng()).append(System.lineSeparator());
		str.append("Remaining zombies: ").append(game.getZomMng()).append(System.lineSeparator());
		str.append("Level: ").append("nivel").append(System.lineSeparator());
		str.append("Seed: ").append(game.getSeed()).append(System.lineSeparator());
		str.append(this.toString()).append(System.lineSeparator());

		System.out.println(str.toString());
	}




}
