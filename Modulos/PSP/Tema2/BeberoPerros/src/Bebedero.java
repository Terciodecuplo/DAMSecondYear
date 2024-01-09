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
        Bebedero dogA = new Bebedero("Sputnik");
        Bebedero dogB = new Bebedero("Layka");
        Bebedero dogC = new Bebedero("Apolo");
        Bebedero dogD = new Bebedero("Soyuz");
        Bebedero dogE = new Bebedero("Falcon");
        Bebedero dogF = new Bebedero("Artemis");
        Bebedero dogG = new Bebedero("Gagarin");
        Bebedero dogH = new Bebedero("Miura");
        Bebedero dogI = new Bebedero("Duque");
        dogA.start();
        dogB.start();
        dogC.start();
        dogD.start();
        dogE.start();
        dogF.start();
        dogG.start();
        dogH.start();
        dogI.start();
        dogA.join();
        dogB.join();
        dogC.join();
        dogD.join();
        dogE.join();
        dogF.join();
        dogG.join();
        dogH.join();
        dogI.join();
        System.out.printf("The total water consumption by the dogs has been %d liters.", Bebedero.totalWaterConsumption);
    }
}



