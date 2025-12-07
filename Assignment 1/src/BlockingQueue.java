import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueue<T> {
    private final Queue<T> queue = new LinkedList<>();
    private final int capacity;

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void put(T item) throws InterruptedException {
        while (queue.size() == capacity) {
            wait(); // wait until space is available
        }
        queue.add(item);
        notifyAll(); // notify waiting threads
    }

    public synchronized T take() throws InterruptedException {
        while (queue.isEmpty()) {
            wait(); // wait until item is available
        }
        T item = queue.remove();
        notifyAll(); // notify waiting threads
        return item;
    }
}