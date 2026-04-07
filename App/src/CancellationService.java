import java.util.*;

/**
 * CancellationService - Logic for canceling confirmed bookings and reversing state.
 * UC10: Booking Cancellation & Inventory Rollback
 */
public class CancellationService {
    
    // UC10: Stack for rollback (LIFO - tracking released room IDs)
    private Stack<String> releasedRoomIds;
    private RoomInventory inventory;
    private List<Reservation> history;

    public CancellationService(RoomInventory inventory, List<Reservation> history) {
        this.inventory = inventory;
        this.history = history;
        this.releasedRoomIds = new Stack<>();
    }

    /**
     * Cancels a reservation by name and room type.
     * Reverts inventory and booking state.
     */
    public void cancelBooking(String guestName, String roomType) throws BookingException {
        System.out.println("\nInitiating cancellation for: " + guestName + " (" + roomType + ")");
        
        Reservation foundReservation = null;
        for (Reservation res : history) {
            if (res.getGuestName().equalsIgnoreCase(guestName) && 
                res.getRoomType().equalsIgnoreCase(roomType) && 
                !res.isCancelled()) {
                foundReservation = res;
                break;
            }
        }

        if (foundReservation == null) {
            throw new BookingException("Cancellation Failed: No active booking found for " + guestName);
        }

        // 1. Mark as cancelled
        foundReservation.setCancelled(true);
        
        // 2. Track released room ID for LIFO rollback/audit
        String roomId = foundReservation.getRoomId();
        releasedRoomIds.push(roomId);
        
        // 3. Increment inventory count
        int currentAvailable = inventory.getAvailability(roomType);
        inventory.updateAvailability(roomType, currentAvailable + 1);

        System.out.println("CANCELLALATION SUCCESSFUL: State reversed for " + guestName);
        System.out.println("Inventory Rollback: " + roomType + " count increased.");
        System.out.println("Rollback Trail: Released ID " + roomId + " added to stack.");
    }

    public void displayRollbackHistory() {
        System.out.println("\n=== LIFO ROLLBACK HISTORY (Released IDs) ===");
        if (releasedRoomIds.isEmpty()) {
            System.out.println("No rollbacks recorded.");
        } else {
            // Displaying top of stack (most recent cancellation)
            System.out.println("Recent releases (Stack): " + releasedRoomIds);
        }
        System.out.println("============================================");
    }
}
