import java.util.*;

/**
 * BookMyStayApp
 *
 * UC4 + UC5 + UC6 + UC7 + UC8 + UC9 + UC10 Combined
 *
 * Features:
 * - UC4: Read-only Room Search
 * - UC5: Booking Request Queue (FIFO)
 * - UC6: Safe Booking Allocation (No Double Booking)
 * - UC7: Booking Confirmation & Safe Allocation
 * - UC8: Booking History & Reporting (Historical Tracking)
 * - UC9: Error Handling & Validation
 * - UC10: Booking Cancellation & Inventory Rollback (LIFO Rollback)
 *
 * @author Kabilesh C
 * @version 1.10.0
 */

/* ============================== DOMAIN MODEL ============================== */
abstract class Room {
    private String roomType;
    private int beds;
    private double price;

    public Room(String roomType, int beds, double price) {
        this.roomType = roomType;
        this.beds = beds;
        this.price = price;
    }

    public String getRoomType() {
        return roomType;
    }

    public void display() {
        System.out.println(roomType + " | Beds: " + beds + " | Price: $" + price);
    }
}

class SingleRoom extends Room {
    public SingleRoom() { super("Single Room", 1, 120); }
}

class DoubleRoom extends Room {
    public DoubleRoom() { super("Double Room", 2, 200); }
}

class SuiteRoom extends Room {
    public SuiteRoom() { super("Suite Room", 3, 450); }
}

/* ============================== INVENTORY ============================== */
class RoomInventory {
    private Map<String, Integer> map = new HashMap<>();

    public synchronized void addRoom(String type, int count) {
        map.put(type, count);
    }

    public synchronized int getAvailability(String type) {
        return map.getOrDefault(type, 0);
    }

    public synchronized void decrement(String type) {
        map.put(type, map.get(type) - 1);
    }

    // UC10 Addition for rollbacks
    public synchronized void updateAvailability(String type, int count) {
        map.put(type, count);
    }

    public synchronized void display() {
        System.out.println("\nInventory:");
        for (String key : map.keySet()) {
            System.out.println(key + " -> " + map.get(key));
        }
    }
}

/* ============================== UC4: SEARCH ============================== */
class SearchService {
    private RoomInventory inventory;
    private List<Room> rooms;

    public SearchService(RoomInventory inventory, List<Room> rooms) {
        this.inventory = inventory;
        this.rooms = rooms;
    }

    // READ ONLY (no modification)
    public void search() {
        System.out.println("\n=== Available Rooms ===");
        for (Room room : rooms) {
            int available = inventory.getAvailability(room.getRoomType());

            if (available > 0) {
                room.display();
                System.out.println("Available: " + available);
                System.out.println("----------------------");
            }
        }
    }
}

/* ============================== UC5: QUEUE ============================== */
class Reservation {
    String name;
    String roomType;
    String roomId; // Added in UC7
    boolean isCancelled = false; // Added in UC10

    public Reservation(String name, String roomType) {
        this.name = name;
        this.roomType = roomType;
    }
}

class BookingQueue {
    private Queue<Reservation> queue = new LinkedList<>();

    public synchronized void addRequest(Reservation r) {
        queue.offer(r);
    }

    public synchronized Reservation next() {
        return queue.poll();
    }

    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }

    public synchronized void displayQueue() {
        System.out.println("\n=== Booking Queue ===");
        for (Reservation r : queue) {
            System.out.println(r.name + " -> " + r.roomType);
        }
    }
}

/* ============================== UC6: BOOKING SERVICE ============================== */
class BookingService {

    private RoomInventory inventory;

    // roomType → allocated roomIds
    private Map<String, Set<String>> allocatedRooms = new HashMap<>();

    public BookingService(RoomInventory inventory) {
        this.inventory = inventory;
    }

    public void processBookings(BookingQueue queue) {
        System.out.println("\n=== PROCESSING BOOKINGS ===");

        while (!queue.isEmpty()) {
            Reservation r = queue.next();

            int available = inventory.getAvailability(r.roomType);

            if (available <= 0) {
                System.out.println("FAILED: No rooms available for " + r.name);
                continue;
            }

            // Generate unique room ID
            String roomId = generateRoomId(r.roomType);

            // Ensure Set exists
            allocatedRooms.putIfAbsent(r.roomType, new HashSet<>());

            // Add (Set prevents duplicates)
            allocatedRooms.get(r.roomType).add(roomId);

            // Update inventory
            inventory.decrement(r.roomType);

            System.out.println("SUCCESS: " + r.name +
                    " booked " + r.roomType +
                    " | Room ID: " + roomId);
        }
    }

    private String generateRoomId(String roomType) {
        return roomType.substring(0, 2).toUpperCase() +
                "-" + UUID.randomUUID().toString().substring(0, 5);
    }
}

/* ============================== MAIN ============================== */
public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("===== BookMyStayApp v1.5 =====");

        // Create Rooms
        List<Room> rooms = Arrays.asList(
                new SingleRoom(),
                new DoubleRoom(),
                new SuiteRoom()
        );

        // Initialize Inventory
        RoomInventory inventory = new RoomInventory();
        inventory.addRoom("Single Room", 2);
        inventory.addRoom("Double Room", 1);
        inventory.addRoom("Suite Room", 0);

        // UC4: SEARCH
        SearchService searchService = new SearchService(inventory, rooms);
        searchService.search();

        // UC5: QUEUE
        BookingQueue queue = new BookingQueue();
        queue.addRequest(new Reservation("Alice", "Single Room"));
        queue.addRequest(new Reservation("Bob", "Single Room"));
        queue.addRequest(new Reservation("Charlie", "Single Room"));
        queue.addRequest(new Reservation("David", "Suite Room"));
        
        // UC9: INVALID INPUTS for validation test
        queue.addRequest(new Reservation("", "Single Room")); // Empty name
        queue.addRequest(new Reservation("Eve", "Penthouse")); // Unsupported room type

        queue.displayQueue();

        // UC6: PROCESS BOOKINGS (Now with UC9 Validation)
        List<String> validTypes = Arrays.asList("Single Room", "Double Room", "Suite Room");
        BookingService bookingService = new BookingService(inventory, validTypes);
        bookingService.processBookings(queue);

        // UC8: BOOKING HISTORY & REPORTING
        System.out.println("\n===== UC8: BOOKING HISTORY & REPORTING =====");
        BookingReportService reportService = new BookingReportService();
        List<Reservation> history = bookingService.getBookingHistory();
        
        reportService.generateSummaryReport(history);
        reportService.generateTypeBasedReport(history);

        // UC10: BOOKING CANCELLATION & ROLLBACK
        System.out.println("\n===== UC10: BOOKING CANCELLATION & ROLLBACK =====");
        CancellationService cancellationService = new CancellationService(inventory, history);
        
        try {
            // Cancel Alice's booking
            cancellationService.cancelBooking("Alice", "Single Room");
            
            // Try to cancel a non-existent booking
            cancellationService.cancelBooking("Xavier", "Double Room");
        } catch (BookingException e) {
            System.out.println("Wait! " + e.getMessage());
        }

        // Display Rollback History
        cancellationService.displayRollbackHistory();

        // UC11: CONCURRENT BOOKING SIMULATION
        System.out.println("\n===== UC11: CONCURRENT BOOKING SIMULATION =====");
        inventory.addRoom("Single Room", 2); // Reset for simulation
        
        BookingQueue concurrentQueue = new BookingQueue();
        Thread t1 = new Thread(() -> {
            concurrentQueue.addRequest(new Reservation("Concurrent_Alice", "Single Room"));
            concurrentQueue.addRequest(new Reservation("Concurrent_Bob", "Single Room"));
            bookingService.processBookings(concurrentQueue);
        });

        Thread t2 = new Thread(() -> {
            concurrentQueue.addRequest(new Reservation("Concurrent_Charlie", "Single Room"));
            concurrentQueue.addRequest(new Reservation("Concurrent_David", "Single Room"));
            bookingService.processBookings(concurrentQueue);
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.err.println("Simulation interrupted.");
        }

        // Final Inventory
        inventory.display();

        System.out.println("\nDone.");
    }
}