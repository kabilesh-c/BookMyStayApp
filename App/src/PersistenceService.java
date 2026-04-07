import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * UC12: Data Persistence & System Recovery
 * This service handles saving and loading the application state using Java Serialization.
 */
public class PersistenceService {
    private static final String DATA_FILE = "system_state.ser";

    /**
     * Data Transfer Object (DTO) for system-wide state snapshot.
     */
    public static class SystemState implements Serializable {
        private static final long serialVersionUID = 1L;
        private Map<String, Integer> inventoryState;
        private List<Reservation> bookingHistory;

        public SystemState(Map<String, Integer> inventoryState, List<Reservation> bookingHistory) {
            this.inventoryState = inventoryState;
            this.bookingHistory = bookingHistory;
        }

        public Map<String, Integer> getInventoryState() { return inventoryState; }
        public List<Reservation> getBookingHistory() { return bookingHistory; }
    }

    /**
     * Saves the entire system snapshot to a binary file.
     */
    public static void saveSystemState(Map<String, Integer> currentInventory, List<Reservation> allBookings) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            SystemState snapshot = new SystemState(currentInventory, allBookings);
            oos.writeObject(snapshot);
            System.out.println("\n[UC12] SUCCESS: System snapshot saved to " + DATA_FILE);
        } catch (IOException e) {
            System.err.println("\n[UC12] ERROR: Persistence failed: " + e.getMessage());
        }
    }

    /**
     * Loads the last saved system state.
     */
    public static SystemState loadSystemState() {
        File file = new File(DATA_FILE);
        if (!file.exists()) {
            System.out.println("\n[UC12] System recovery skipped: No existing backup file found.");
            return null;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
            SystemState state = (SystemState) ois.readObject();
            System.out.println("\n[UC12] RECOVERY: Successfully restored " + state.getBookingHistory().size() + " records.");
            return state;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("\n[UC12] ERROR: System recovery failed: " + e.getMessage());
            return null;
        }
    }
}
