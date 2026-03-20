import java.util.LinkedList;
import java.util.Queue;

/**
 * BookingQueue - Handles incoming booking requests (FIFO)
 */
public class BookingQueue {

    private Queue<Reservation> queue;

    public BookingQueue() {
        queue = new LinkedList<>();
    }

    // Add request
    public void addRequest(Reservation reservation) {
        queue.offer(reservation);
        System.out.println("Request Added:");
        reservation.display();
    }

    // View queue
    public void displayQueue() {
        System.out.println("\n===== BOOKING REQUEST QUEUE =====");

        if (queue.isEmpty()) {
            System.out.println("No pending requests.");
            return;
        }

        for (Reservation r : queue) {
            r.display();
        }

        System.out.println("=================================\n");
    }
}