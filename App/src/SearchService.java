/**
 * SearchService
 *
 * Handles read-only search operations for available rooms.
 *
 * Author: Kabilesh C
 */

import java.util.List;

public class SearchService {

    private RoomInventory inventory;
    private List<Room> rooms;

    public SearchService(RoomInventory inventory, List<Room> rooms) {
        this.inventory = inventory;
        this.rooms = rooms;
    }

    /**
     * Displays available rooms (Read-Only)
     */
    public void searchAvailableRooms() {

        System.out.println("\n===== Available Rooms =====");

        for (Room room : rooms) {

            String type = room.getRoomType();
            int available = inventory.getAvailability(type);

            // Filter unavailable rooms
            if (available > 0) {

                System.out.println("----------------------------");
                System.out.println("Room Type : " + type);
                System.out.println("Price     : ₹" + room.getPrice());
                System.out.println("Beds      : " + room.getBeds());
                System.out.println("Available : " + available);
            }
        }

        System.out.println("============================\n");
    }
}