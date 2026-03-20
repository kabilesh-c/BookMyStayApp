import java.util.*;

class BookingService {

    private RoomInventory inventory;

    // Prevent duplicate room IDs
    private Set<String> allocatedRoomIds;

    // Track room allocations per type
    private Map<String, Set<String>> roomAllocations;

    public BookingService(RoomInventory inventory) {
        this.inventory = inventory;
        this.allocatedRoomIds = new HashSet<>();
        this.roomAllocations = new HashMap<>();
    }

    public void processBookings(BookingQueue queue) {
        System.out.println("\n===== PROCESSING BOOKINGS =====");

        while (!queue.isEmpty()) {
            Reservation reservation = queue.pollRequest();

            String roomType = reservation.getRoomType();

            int available = inventory.getAvailability(roomType);

            if (available > 0) {
                String roomId = generateRoomId(roomType);

                // ensure uniqueness
                while (allocatedRoomIds.contains(roomId)) {
                    roomId = generateRoomId(roomType);
                }

                allocatedRoomIds.add(roomId);

                roomAllocations
                        .computeIfAbsent(roomType, k -> new HashSet<>())
                        .add(roomId);

                // update inventory immediately
                inventory.updateAvailability(roomType, available - 1);

                System.out.println("Booking CONFIRMED for "
                        + reservation.getGuestName()
                        + " | Room: " + roomType
                        + " | Room ID: " + roomId);

            } else {
                System.out.println("Booking FAILED for "
                        + reservation.getGuestName()
                        + " | No rooms available for " + roomType);
            }
        }

        System.out.println("================================\n");
    }

    private String generateRoomId(String roomType) {
        return roomType.substring(0, 2).toUpperCase() + "-" + UUID.randomUUID().toString().substring(0, 5);
    }

    public void displayAllocations() {
        System.out.println("===== ROOM ALLOCATIONS =====");
        for (Map.Entry<String, Set<String>> entry : roomAllocations.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
        System.out.println("============================\n");
    }
}