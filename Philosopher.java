

class Philosopher extends Thread {
    private int id;
    private PhilosopherState state;
    private Fork leftFork;
    private Fork rightFork;
    private int ate;

    public Philosopher(int id, Fork leftFork, Fork rightFork) {
        this.id = id;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.state = PhilosopherState.thinking;
        this.ate = 0;
    }

    @Override
    public void start() {
        if (state == PhilosopherState.thinking) {
            setPhilosopherState(PhilosopherState.hungry);
        }
        if (state == PhilosopherState.hungry) {
            eat();
        }
        if (state == PhilosopherState.eating) {
            stopEating();
        }
    }

    private void setPhilosopherState(PhilosopherState state) {
        this.state = state;
    }

    private void eat() {
        if (leftFork.pickUp() && rightFork.pickUp()) {
            System.out.println("Philosopher " + id + " is eating.");
            setPhilosopherState(PhilosopherState.eating);

        }
    }

    private void stopEating() {
        System.out.println("Philosopher " + id + " is done eating.");
        this.ate++;
        leftFork.putDown();
        rightFork.putDown();
        think();
    }

    private void think() {
        System.out.println("Philosopher " + id + " is thinking.");
        setPhilosopherState(PhilosopherState.thinking);
    }

    public int getAte() {
        return this.ate;
    }
}