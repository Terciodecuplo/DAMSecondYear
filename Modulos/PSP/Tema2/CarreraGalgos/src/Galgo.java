public class Galgo extends Thread {

	private String name;
	private int speed;

	public Galgo(String name, int speed) {
		this.name = name;
		this.speed = speed;
	}

	public void run() {
		
		try {
			System.out.printf("¡¡%s inicia la carrera!!%n", name);
			Thread.sleep(speed * 1000);
			System.out.printf("¡¡%s ha completado la carrera!!%n", name);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		Galgo galgoA = new Galgo("Rupert", 5);
		Galgo galgoB = new Galgo("Little", 3);
		Galgo galgoC = new Galgo("Johnny Bravo", 7);
		Galgo galgoD = new Galgo("Mad Max", 4);
		Galgo galgoE = new Galgo("Jack'O Lantern", 6);
		galgoA.start();
		galgoB.start();
		galgoC.start();
		galgoD.start();
		galgoE.start();
	}
}
