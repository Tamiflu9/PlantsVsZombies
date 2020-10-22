package tp.p2.Plants;

public class PlantFactory {

	public Plant creaPlant(TipoPlantas p, int x, int y) {
		
		if (p==TipoPlantas.Sunflower) {
			return new Sunflower(x,y);
		}
		else if (p==TipoPlantas.Peashooter) {
			return new Peashooter(x,y);
		}
		else if (p==TipoPlantas.Petacereza) {
			return new Petacereza(x,y);
		}
		else if (p==TipoPlantas.Nuez) {
			return new Nuez(x,y);
		}
		return null;
	}
	
	
	public static Plant getPlant(String plantName, int x, int y){
		if (plantName.equals("sunflower") || plantName.equals("s")) {
			return new Sunflower(x,y);
		}
		else if(plantName.equals("peashooter") || plantName.equals("p")) {
			return new Peashooter(x,y);
		}
		else if(plantName.equals("petacereza") || plantName.equals("c")) {
			return new Petacereza(x,y);
		}
		else if (plantName.equals("nuez") || plantName.equals("n")) {
			return new Nuez(x,y);
		}
		return null;  

	}
	
	public static String listOfAvilablePlants() {
		StringBuilder s = new StringBuilder();
		s.append("[S]unflower: Cost: 20 suncoins Harm: 0").append(System.lineSeparator());
		s.append("[P]eashooter: Cost: 50 suncoins Harm: 1").append(System.lineSeparator());
		s.append("Peta[c]ereza: Cost: 50 suncoins Harm: 10").append(System.lineSeparator());
		s.append("[N]uez: Cost: 50 suncoins Harm: 0").append(System.lineSeparator());
		return s.toString();
	} 
	
	
}
