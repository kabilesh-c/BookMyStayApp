import java.util.*;

/**
 * BookMyStayApp
 *
 * UC4 - Room Search System (Read-Only)
 *
 * Demonstrates:
 * - Read-only search operations
 * - Separation of concerns
 * - Inventory as state holder
 * - Filtering unavailable rooms
 *
 * @author Kabilesh C
 * @version 1.3.0
 */

/* ============================== DOMAIN MODEL ============================== */

abstract class Room {
    private String roomType;
    private int numberOfBeds;
    private double sizeInSqFt;
    private double pricePerNight;

    public Room(String roomType, int numberOfBeds, double sizeInSqFt, double pricePerNight) {
        this.roomType = roomType;
        this.numberOfBeds = numberOfBeds;
        this.sizeInSqFt = sizeInSqFt;
        this.pricePerNight = pricePerNight;
    }

    public String getRoomType() {
        return roomType;
    }

    public int getBeds() {
        return numberOfBeds;
    }

    public double getPrice() {
        return pricePerNight;
    }

    public void displayRoomDetails() {
        System.out.println("Room Type : " + roomType);
        System.out.println("Beds      : " + numberOfBeds);
        System.out.println("Size      : " + sizeInSqFt + " sq.ft");
        System.out.println("Price     : $" + pricePerNight);
    }
}

class SingleRoom extends Room {
    public SingleRoom() {
        super("Single Room", 1, 180, 120.00);
    }
}

class DoubleRoom extends Room {
    public DoubleRoom() {
        super("Double Room", 2, 300, 200.00);
    }
}

class SuiteRoom extends Room {
    public SuiteRoom() {
        super("Suite Room", 3, 550, 450.00);
    }
}

/* ============================== INVENTORY ============================== */

class RoomInventory {

    private Map<String, Integer> availabilityMap;

    public RoomInventory() {
        availabilityMap = new HashMap<>();
    }

    public void registerRoom(String roomType, int count) {
        availabilityMap.put(roomType, count);
    }

    public int getAvailability(String roomType) {
        return availabilityMap.getOrDefault(roomType, 0);
    }

    public void displayInventory() {
        System.out.println("====== Current Inventory ======");
        for (Map.Entry<String, Integer> entry : availabilityMap.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
        System.out.println("===============================\n");
    }
}

/* ============================== SEARCH SERVICE (UC4) ============================== */

class SearchService {

    private RoomInventory inventory;
    private List<Room> rooms;

    public SearchService(RoomInventory inventory, List<Room> rooms) {
        this.inventory = inventory;
        this.rooms = rooms;
    }

    /**
     * Read-only search (NO modification)
     */
    public void searchAvailableRooms() {

        System.out.println("\n===== AVAILABLE ROOMS =====");

        for (Room room : rooms) {

            int available = inventory.getAvailability(room.getRoomType());

            // Filter unavailable rooms
            if (available > 0) {
                System.out.println("----------------------------");
                room.displayRoomDetails();
                System.out.println("Available : " + available);
            }
        }

        System.out.println("===========================\n");
    }
}

/* ============================== MAIN APPLICATION ============================== */

public class BookMyStayApp {

    private static final String APP_NAME = "BookMyStayApp";
    private static final String VERSION = "1.3.0";

    public static void main(String[] args) {

        System.out.println("==========================================");
        System.out.println(" Welcome to " + APP_NAME);
        System.out.println(" Version: " + VERSION);
        System.out.println("==========================================\n");

        // Create Room Objects
        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        List<Room> rooms = new ArrayList<>();
        rooms.add(single);
        rooms.add(doubleRoom);
        rooms.add(suite);

        // Initialize Inventory
        RoomInventory inventory = new RoomInventory();
        inventory.registerRoom(single.getRoomType(), 5);
        inventory.registerRoom(doubleRoom.getRoomType(), 3);
        inventory.registerRoom(suite.getRoomType(), 0); // simulate unavailable

        // UC4: Search Service (READ ONLY)
        SearchService searchService = new SearchService(inventory, rooms);

        // Perform Search
        searchService.searchAvailableRooms();

        // Inventory remains unchanged
        inventory.displayInventory();

        System.out.println("Application Terminated Successfully.");
    }
}