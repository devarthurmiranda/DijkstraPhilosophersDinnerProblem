public class Main {
    public static void main(String[] args) {
        int laps = 0;
        Fork[] forks = new Fork[5];
        Philosopher[] philosophers = new Philosopher[5];

        for (int i = 0; i < 5; i++) {
            forks[i] = new Fork(i);
        }

        for (int i = 0; i < 5; i++) {
            philosophers[i] = new Philosopher(i, forks[i], forks[(i + 1) % 5]);
        }

        while (laps < 100) {
            for (int i = 0; i < 5; i++) {
                philosophers[i].start(); // Start each philosopher in a separate thread
            }

            try {
                Thread.sleep(100); // Sleep for a short time to allow philosophers to execute
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            laps++;
        }

        System.out.println("\n");
        for (int i = 0; i < 5; i++) {
            System.out.println("Philosopher " + i + " ate " + philosophers[i].getAte() + " times.");
        }
    }
}