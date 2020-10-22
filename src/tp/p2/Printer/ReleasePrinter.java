package tp.p2.Printer;

import java.io.PrintWriter;

import tp.p1.Game;

public class ReleasePrinter extends BoardPrinter {

	public ReleasePrinter(Game g) {
		this.posX = 4;
		this.posY = 8;
		this.game=g;
		this.setTableroP(inicializa());
		this.setCell(7);
	}

	@Override
	public void encodeGame(Game game) {
		game.setTablero(tableroP);
		for(int i = 0; i < this.posX; i++) {
			for(int j = 0; j < this.posY; j++) {
				if(game.getPosPlanta(i,j) != -1) {
					this.tableroP[i][j] = game.getPintaP(game.getPosPlanta(i,j));
				}else if(game.getPosZombie(i,j) != -1) {
					this.tableroP[i][j] = game.getPintaZ(game.getPosZombie(i,j));
				}
			}
		}
	}

	// TABLERO VACIO
	public String[][] inicializa() {
		tableroP = new String[posX][posY];
		for(int i = 0; i < posX; i++) {
			for(int j = 0; j < posY; j++) {
				tableroP[i][j] =  space;
			}
		}
		return tableroP;
	}
	
	public void load(String nombrArchivo, PrintWriter pw) {
		
		
		
		tableroP = new String[posX][posY];
		for(int i = 0; i < posX; i++) {
			for(int j = 0; j < posY; j++) {
				pw.print(tableroP[j][i]+ space);
			}
		}
		pw.println();
	}

	@Override
	public void printGame() {
		StringBuilder str = new StringBuilder();
		String c = ""+game.getCiclo();
		str.append("Number of cycles: ").append(c).append(System.lineSeparator());
		str.append("Sun coins: ").append(game.getSuncoinMng()).append(System.lineSeparator());
		str.append("Remaining zombies: ").append(game.getZomMng()).append(System.lineSeparator());
		str.append(this.toString()).append(System.lineSeparator());
		System.out.println(str.toString());
	}


}
