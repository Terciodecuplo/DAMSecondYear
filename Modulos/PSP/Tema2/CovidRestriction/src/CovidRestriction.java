import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class CovidRestriction extends Thread {

    private static Semaphore waitingRoom = new Semaphore(6);
    private int patient;
    private static Scanner scanner = new Scanner(System.in);

    public CovidRestriction(int patients) {
        this.patient = patients;
    }

    public void run() {
        try {
            waitingRoom.acquire();
            System.out.printf("The patient number %d has entered the room.\n", patient);
            Thread.sleep((long) (Math.random() * 2000) + 100);
            waitingRoom.release();
            System.out.printf("The patient number %d has exited the room.\n", patient);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) {
        int numberOfPatients;
        System.out.println("How many patients do we expect today?");
        numberOfPatients = scanner.nextInt();
        for (int i = 1; i <= numberOfPatients; i++) {
            new CovidRestriction(i).start();
        }
    }

}

