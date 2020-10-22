package tp.p2.Printer;

import tp.p1.Game;
import tp.p3.MyStringUtils;

public abstract class BoardPrinter implements GamePrinter{
	
	protected String[][]tableroP;
	protected Game game;
	private int cellSize; 
	protected int posX;
	protected int posY;
	protected String space = "    " ; 
	private String vDelimiter = "|"; 
	private String hDelimiter = "-";
	
	public BoardPrinter(){}
	
	public String toString() {
		int marginSize = 1;
		String rowDelimiter = MyStringUtils.repeat(hDelimiter, (posY * (cellSize+1))+1);
		String margin = MyStringUtils.repeat(space, marginSize);
		String lineDelimiter = String.format("%n%s%s%n", margin + space, rowDelimiter);

		StringBuilder str = new StringBuilder();
		str.append(lineDelimiter);

		for(int i=0; i<posX; i++) {
			str.append(margin).append(margin).append(vDelimiter);
			for (int j=0; j<posY; j++) {
				str.append( MyStringUtils.centre(tableroP[i][j], cellSize)).append(vDelimiter);
			}
			str.append(lineDelimiter);
		}
		
		if(posX == 0){
			str.append(margin).append(margin).append(vDelimiter);
			for (int j=0; j<posY; j++) {
				str.append( MyStringUtils.centre(tableroP[0][j], cellSize)).append(vDelimiter);
			}
			str.append(lineDelimiter);
		}
		return str.toString();
	}	
		
	public abstract void encodeGame(Game game);
	public abstract void printGame();

	public String[][] getTableroP() {
		return tableroP;
	}

	public void setTableroP(String[][] tableroP) {
		this.tableroP = tableroP;
	}
	public void setCell(int size) {
		this.cellSize=size;
	}
}
