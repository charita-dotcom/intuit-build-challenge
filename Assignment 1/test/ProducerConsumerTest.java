import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class ProducerConsumerTest {

    // BlockingQueue behaves like a FIFO queue in a single thread
    @Test
    public void testBlockingQueueFIFO() throws InterruptedException {
        BlockingQueue<Integer> queue = new BlockingQueue<>(10);

        queue.put(1);
        queue.put(2);
        queue.put(3);

        assertEquals(Integer.valueOf(1), queue.take());
        assertEquals(Integer.valueOf(2), queue.take());
        assertEquals(Integer.valueOf(3), queue.take());
    }

    // take() blocks until an item is available, then returns it
    @Test
    public void testTakeBlocksUntilItemAvailable() throws Exception {
        BlockingQueue<Integer> queue = new BlockingQueue<>(1);
        AtomicReference<Integer> result = new AtomicReference<>();

        Thread consumerThread = new Thread(() -> {
            try {
                result.set(queue.take());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        consumerThread.start();

        // Small pause: consumer should be blocked and have no value yet
        Thread.sleep(100);
        assertNull(result.get());

        // Now put an item; consumer should unblock and receive it
        queue.put(42);

        consumerThread.join(1000); // wait up to 1s for the thread to finish
        assertEquals(Integer.valueOf(42), result.get());
    }

    // Full producer-consumer flow: data moves from source to destination
    @Test
    public void testProducerConsumerTransfersAllItems() throws InterruptedException {
        List<Integer> source = Arrays.asList(10, 20, 30, 40, 50);
        List<Integer> destination = new ArrayList<>();

        int capacity = 2;
        BlockingQueue<Integer> sharedQueue = new BlockingQueue<>(capacity);

        int itemsToTransfer = source.size();

        Thread producer = new Thread(new Producer(sharedQueue, source), "Producer");
        Thread consumer = new Thread(new Consumer(sharedQueue, destination, itemsToTransfer), "Consumer");

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();

        // All items should have been transferred in order
        assertEquals(source, destination);
    }
}