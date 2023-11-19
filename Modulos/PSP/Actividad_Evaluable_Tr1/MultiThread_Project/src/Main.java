
public class Main {

	public static void main(String[] args) {
		CounterWithThreads evenCounter = new CounterWithThreads(true);
		CounterWithThreads oddCounter = new CounterWithThreads(false);
		evenCounter.start();
		oddCounter.start();

	}

}
