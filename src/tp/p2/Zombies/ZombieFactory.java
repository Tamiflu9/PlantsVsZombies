package tp.p2.Zombies;

public class ZombieFactory {

	public Zombie creaZombie(TipoZombies z, int x, int y) {
		if (z==TipoZombies.ZombieComun) {
			return new ZombieComun(x,y);
		}
		else if (z==TipoZombies.ZombieCaracubo) {
			return new ZombieCaracubo(x,y);
		}
		else if (z==TipoZombies.ZombieDeportista) {
			return new ZombieDeportista(x,y);
		}
		return null;
	}
	
	
	public static Zombie getZombie(String ZombieName, int x, int y){
		if (ZombieName.equals("zombieComun") || ZombieName.equals("z")) {
			return new ZombieComun(x,y);
		}
		else if(ZombieName.equals("zombieCaracubo") || ZombieName.equals("w")) {
			return new ZombieCaracubo(x,y);
		}
		else if(ZombieName.equals("zombieDeportista") || ZombieName.equals("x")) {
			return new ZombieDeportista(x,y);
		}
		return null;  
	}
	
	public static String listOfAvilableZombies() {
		StringBuilder s = new StringBuilder();
		s.append("[Z]ombie comun: speed: 2 Harm: 1 Life: 5").append(System.lineSeparator());
		s.append("[Z]ombie deportista: speed: 1 Harm: 1 Life: 2").append(System.lineSeparator());
		s.append("[Z]ombie caracubo: speed: 4 Harm: 1 Life: 8").append(System.lineSeparator());
		return s.toString();
	} 
}
