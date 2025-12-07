import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProducerConsumer {

    public static void main(String[] args) throws InterruptedException {
        // Source container (simulated input)
        List<Integer> source = Arrays.asList(1, 2, 3, 4, 5);

        // Destination container (simulated output)
        List<Integer> destination = new ArrayList<>();

        int capacity = 2; // bounded queue size

        BlockingQueue<Integer> sharedQueue = new BlockingQueue<>(capacity);

        int itemsToTransfer = source.size();

        Thread producer = new Thread(new Producer(sharedQueue, source), "Producer");
        Thread consumer = new Thread(new Consumer(sharedQueue, destination, itemsToTransfer), "Consumer");

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();

        System.out.println("Destination container: " + destination);
    }
}