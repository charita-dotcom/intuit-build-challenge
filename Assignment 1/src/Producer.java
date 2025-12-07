import java.util.List;

public class Producer implements Runnable {
    private final BlockingQueue<Integer> queue;
    private final List<Integer> source;

    public Producer(BlockingQueue<Integer> queue, List<Integer> source) {
        this.queue = queue;
        this.source = source;
    }

    @Override
    public void run() {
        try {
            for (Integer item : source) {
                queue.put(item);
                System.out.println("Produced: " + item);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
