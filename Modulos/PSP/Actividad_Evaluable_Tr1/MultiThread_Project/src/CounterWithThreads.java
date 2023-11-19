
public class CounterWithThreads extends Thread {

	private int counterStart = 1;
	private int counterEnd = 10;
	private boolean isEven;

	public CounterWithThreads(boolean isEven) {
		this.isEven = isEven;
	}
	
	public void run() {
		counterManagement(); // Entiendo que esta línea no tiene sentido y que podría incluirse todo directamente en el método run()
							 // pero creo que es mejor en términos de legibilidad si el código escalase a un programa mayor.
	}
	
	public void counterManagement() {
		int sum = 0;
		String sequence = "";
		if (isEven) {
			for (int i = counterStart; i <= counterEnd; i++) {
				if (i % 2 == 0) {
					sum += i;
					sequence += Integer.toString(i);
					if (counterEnd % 2 == 0 && i != counterEnd) {
						sequence+=(" + ");
					} else {
						sequence+=(" = " + sum);
					}
				}
			}
		} else {
			for (int i = counterStart; i <= counterEnd; i++) {
				if (i % 2 != 0) {
					sum += i;
					sequence+=Integer.toString(i);
					if (counterEnd % 2 == 0 && i != counterEnd - 1) {
						sequence+=(" + ");
					} else {
						sequence+=(" = " + sum);
					}
				}
			}
		}
		System.out.println(sequence);
	}
}
