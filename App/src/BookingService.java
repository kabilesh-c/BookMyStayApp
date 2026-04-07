import java.util.*;

class BookingService {

    private RoomInventory inventory;

    // Prevent duplicate room IDs
    private Set<String> allocatedRoomIds;

    // Track room allocations per type
    private Map<String, Set<String>> roomAllocations;

    // UC8: Booking History
    private List<Reservation> bookingHistory;

    // UC9: Validator
    private BookingValidator validator;
    private List<String> validRoomTypes;

    public BookingService(RoomInventory inventory, List<String> validRoomTypes) {
        this.inventory = inventory;
        this.allocatedRoomIds = new HashSet<>();
        this.roomAllocations = new HashMap<>();
        this.bookingHistory = new ArrayList<>();
        this.validator = new BookingValidator();
        this.validRoomTypes = validRoomTypes;
    }

    public void processBookings(BookingQueue queue) {
        System.out.println("\n===== PROCESSING BOOKINGS =====");

        while (!queue.isEmpty()) {
            Reservation reservation = queue.pollRequest();

            try {
                // UC9: VALIDATE BEFORE PROCESSING
                validator.validate(reservation, inventory, validRoomTypes);

                String roomType = reservation.getRoomType();
                int available = inventory.getAvailability(roomType);

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

                // UC8: ADD TO BOOKING HISTORY
                reservation.setRoomId(roomId);
                bookingHistory.add(reservation);

                System.out.println("Booking CONFIRMED for "
                        + reservation.getGuestName()
                        + " | Room: " + roomType
                        + " | Room ID: " + roomId);

            } catch (BookingException e) {
                // UC9: GRACEFUL ERROR HANDLING
                System.out.println("Booking REJECTED: " + e.getMessage());
            }
        }

        System.out.println("================================\n");
    }

    public List<Reservation> getBookingHistory() {
        return bookingHistory;
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