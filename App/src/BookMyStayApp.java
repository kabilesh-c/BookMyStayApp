import java.util.*;

/**
 * BookMyStayApp
 *
 * UC6 - Booking Allocation System
 *
 * Demonstrates:
 * - FIFO Queue Processing
 * - Unique Room Allocation using Set
 * - Inventory Synchronization
 * - Prevention of Double Booking
 *
 * @author Kabilesh C
 * @version 1.5.0
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

    public void addRoom(String type, int count) {
        map.put(type, count);
    }

    public int getAvailability(String type) {
        return map.getOrDefault(type, 0);
    }

    public void decrement(String type) {
        map.put(type, map.get(type) - 1);
    }

    public void display() {
        System.out.println("\nInventory:");
        for (String key : map.keySet()) {
            System.out.println(key + " -> " + map.get(key));
        }
    }
}

/* ============================== UC4 SEARCH ============================== */

class SearchService {
    private RoomInventory inventory;
    private List<Room> rooms;

    public SearchService(RoomInventory inventory, List<Room> rooms) {
        this.inventory = inventory;
        this.rooms = rooms;
    }

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

/* ============================== UC5 QUEUE ============================== */

class Reservation {
    String name;
    String roomType;

    public Reservation(String name, String roomType) {
        this.name = name;
        this.roomType = roomType;
    }
}

class BookingQueue {
    Queue<Reservation> queue = new LinkedList<>();

    public void add(Reservation r) {
        queue.offer(r);
    }

    public Reservation next() {
        return queue.poll();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}

/* ============================== UC6 BOOKING SERVICE ============================== */

class BookingService {

    private RoomInventory inventory;

    // roomType → assigned roomIds
    private Map<String, Set<String>> allocatedRooms = new HashMap<>();

    public BookingService(RoomInventory inventory) {
        this.inventory = inventory;
    }

    public void process(BookingQueue queue) {

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

            // Ensure set exists
            allocatedRooms.putIfAbsent(r.roomType, new HashSet<>());

            // Prevent duplicate (Set ensures uniqueness)
            allocatedRooms.get(r.roomType).add(roomId);

            // Update inventory
            inventory.decrement(r.roomType);

            System.out.println("SUCCESS: " + r.name +
                    " booked " + r.roomType +
                    " | Room ID: " + roomId);
        }
    }

    private String generateRoomId(String roomType) {
        return roomType.substring(0, 2).toUpperCase() + "-" + UUID.randomUUID().toString().substring(0, 5);
    }
}

/* ============================== MAIN ============================== */

public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("BookMyStayApp v1.5");

        // Rooms
        List<Room> rooms = Arrays.asList(
                new SingleRoom(),
                new DoubleRoom(),
                new SuiteRoom()
        );

        // Inventory
        RoomInventory inventory = new RoomInventory();
        inventory.addRoom("Single Room", 2);
        inventory.addRoom("Double Room", 1);
        inventory.addRoom("Suite Room", 0);

        // Search (UC4)
        new SearchService(inventory, rooms).search();

        // Queue (UC5)
        BookingQueue queue = new BookingQueue();
        queue.add(new Reservation("Alice", "Single Room"));
        queue.add(new Reservation("Bob", "Single Room"));
        queue.add(new Reservation("Charlie", "Single Room"));
        queue.add(new Reservation("David", "Suite Room"));

        // UC6 Processing
        BookingService service = new BookingService(inventory);
        service.process(queue);

        inventory.display();

        System.out.println("\nDone.");
    }
}