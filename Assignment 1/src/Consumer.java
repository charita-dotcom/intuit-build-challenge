import java.util.List;

public class Consumer implements Runnable {
    private final BlockingQueue<Integer> queue;
    private final List<Integer> destination;
    private final int itemsToConsume;

    public Consumer(BlockingQueue<Integer> queue, List<Integer> destination, int itemsToConsume) {
        this.queue = queue;
        this.destination = destination;
        this.itemsToConsume = itemsToConsume;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < itemsToConsume; i++) {
                Integer item = queue.take();
                destination.add(item);
                System.out.println("Consumed: " + item);
            }
            System.out.println("Consumer finished after " + itemsToConsume + " items.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
