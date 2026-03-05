/**
 * BookMyStayApp
 *
 * UC2 - Object Modeling with Inheritance & Abstraction
 *
 * Demonstrates:
 * - Abstract Class
 * - Inheritance
 * - Polymorphism
 * - Encapsulation
 * - Separation of Domain and State
 *
 * @author Kabilesh C
 * @version 1.1.0
 */

abstract class Room {

    // Encapsulated attributes
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

    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public double getSizeInSqFt() {
        return sizeInSqFt;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    // Common behavior
    public void displayRoomDetails() {
        System.out.println("Room Type      : " + roomType);
        System.out.println("Beds           : " + numberOfBeds);
        System.out.println("Size (sq.ft)   : " + sizeInSqFt);
        System.out.println("Price/Night    : $" + pricePerNight);
    }
}

// Concrete Room Types

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

public class BookMyStayApp {

    private static final String APP_NAME = "BookMyStayApp";
    private static final String VERSION = "1.1.0";

    public static void main(String[] args) {

        System.out.println("==========================================");
        System.out.println(" Welcome to " + APP_NAME);
        System.out.println(" Version: " + VERSION);
        System.out.println("==========================================\n");

        // Create Room Objects (Polymorphism)
        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        // Static Availability Representation
        int singleAvailability = 5;
        int doubleAvailability = 3;
        int suiteAvailability = 2;

        // Display Room Information
        displayRoomWithAvailability(single, singleAvailability);
        displayRoomWithAvailability(doubleRoom, doubleAvailability);
        displayRoomWithAvailability(suite, suiteAvailability);

        System.out.println("Application Terminated Successfully.");
    }

    private static void displayRoomWithAvailability(Room room, int availability) {
        System.out.println("------------------------------------------");
        room.displayRoomDetails();
        System.out.println("Available Rooms: " + availability);
        System.out.println("------------------------------------------\n");
    }
}