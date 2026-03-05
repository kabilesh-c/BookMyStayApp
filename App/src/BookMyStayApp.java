import java.util.HashMap;
import java.util.Map;

/**
 * BookMyStayApp
 *
 * UC3 - Centralized Inventory Management using HashMap
 *
 * Demonstrates:
 * - HashMap for centralized state management
 * - O(1) lookup complexity
 * - Separation of concerns
 * - Encapsulation of inventory logic
 *
 * @author Kabilesh C
 * @version 1.2.0
 */

/* ==============================
   DOMAIN MODEL (Room Hierarchy)
   ============================== */

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

    public void displayRoomDetails() {
        System.out.println("Room Type    : " + roomType);
        System.out.println("Beds         : " + numberOfBeds);
        System.out.println("Size (sq.ft) : " + sizeInSqFt);
        System.out.println("Price/Night  : $" + pricePerNight);
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

/* ==============================
   INVENTORY MANAGEMENT
   ============================== */

class RoomInventory {

    // Single Source of Truth
    private Map<String, Integer> availabilityMap;

    public RoomInventory() {
        availabilityMap = new HashMap<>();
    }

    // Register room type with initial count
    public void registerRoom(String roomType, int count) {
        availabilityMap.put(roomType, count);
    }

    // Retrieve availability (O(1) average time)
    public int getAvailability(String roomType) {
        return availabilityMap.getOrDefault(roomType, 0);
    }

    // Controlled update
    public void updateAvailability(String roomType, int newCount) {
        if (availabilityMap.containsKey(roomType)) {
            availabilityMap.put(roomType, newCount);
        } else {
            System.out.println("Room type not found in inventory.");
        }
    }

    // Display entire inventory
    public void displayInventory() {
        System.out.println("====== Current Room Inventory ======");
        for (Map.Entry<String, Integer> entry : availabilityMap.entrySet()) {
            System.out.println(entry.getKey() + " -> Available: " + entry.getValue());
        }
        System.out.println("=====================================\n");
    }
}

/* ==============================
   APPLICATION ENTRY POINT
   ============================== */

public class BookMyStayApp {

    private static final String APP_NAME = "BookMyStayApp";
    private static final String VERSION = "1.2.0";

    public static void main(String[] args) {

        System.out.println("==========================================");
        System.out.println(" Welcome to " + APP_NAME);
        System.out.println(" Version: " + VERSION);
        System.out.println("==========================================\n");

        // Create Room Objects
        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        // Initialize Inventory
        RoomInventory inventory = new RoomInventory();

        inventory.registerRoom(single.getRoomType(), 5);
        inventory.registerRoom(doubleRoom.getRoomType(), 3);
        inventory.registerRoom(suite.getRoomType(), 2);

        // Display Room Details
        single.displayRoomDetails();
        System.out.println("Available: " + inventory.getAvailability(single.getRoomType()));
        System.out.println();

        doubleRoom.displayRoomDetails();
        System.out.println("Available: " + inventory.getAvailability(doubleRoom.getRoomType()));
        System.out.println();

        suite.displayRoomDetails();
        System.out.println("Available: " + inventory.getAvailability(suite.getRoomType()));
        System.out.println();

        // Display centralized inventory state
        inventory.displayInventory();

        System.out.println("Application Terminated Successfully.");
    }
}