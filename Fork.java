import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Fork {
    private final Lock lock = new ReentrantLock();
    private int id;
    private boolean inUse;

    public Fork(int id) {
        this.id = id;
        this.inUse = false;
    }

    public boolean pickUp() {
        if (lock.tryLock()) {
            if (!inUse) {
                inUse = true;
                return true;
            }
            lock.unlock();
        }
        return false;
    }

    public void putDown() {
        lock.unlock();
        inUse = false;
    }

    public boolean isInUse() {
        return inUse;
    }
}
