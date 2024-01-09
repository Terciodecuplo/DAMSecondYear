import java.util.Random;
import java.util.concurrent.Semaphore;

public class Bebedero extends Thread {

    private String dogName;
    private int waterDrunk;
    private static int totalWaterConsumption;
    private static Semaphore maxDogsAtTheSameTime = new Semaphore(3);

    public Bebedero(String dogName) {
        this.dogName = dogName;
    }

    public void run() {
        try {
            maxDogsAtTheSameTime.acquire();
            System.out.printf("%s has began to drink.%n", dogName);
            Random random = new Random();
            waterDrunk = random.nextInt(10);
            Thread.sleep(waterDrunk * 1000);
            System.out.printf("%s has drunk %d liters.%n", dogName, waterDrunk);
            maxDogsAtTheSameTime.release();
            totalWaterConsumption += waterDrunk;

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Bebedero[] bebederos = new Bebedero[]{
                new Bebedero("Sputnik"),
                new Bebedero("Layka"),
                new Bebedero("Apolo"),
                new Bebedero("Soyuz"),
                new Bebedero("Falcon"),
                new Bebedero("Artemis"),
                new Bebedero("Gagarin"),
                new Bebedero("Miura"),
                new Bebedero("Duque"),
        };
        for (Bebedero dog : bebederos) {
            dog.start();
        }
        for (Bebedero dog : bebederos) {
            dog.join();
        }
        System.out.printf("The total water consumption by the dogs has been %d liters.", Bebedero.totalWaterConsumption);
    }
}



