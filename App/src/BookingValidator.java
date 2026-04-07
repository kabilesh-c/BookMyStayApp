import java.util.List;

/**
 * BookingValidator - Validates booking inputs and system state.
 * UC9: Error Handling & Validation
 */
public class BookingValidator {

    /**
     * Validates a reservation request against inventory and business rules.
     * @param reservation The reservation to validate.
     * @param inventory The current room inventory.
     * @param validRoomTypes The list of supported room types.
     * @throws BookingException If validation fails.
     */
    public void validate(Reservation reservation, RoomInventory inventory, List<String> validRoomTypes) throws BookingException {
        
        // 1. Validate Input (Null checks)
        if (reservation == null) {
            throw new BookingException("Invalid Booking: Reservation data is missing.");
        }
        
        if (reservation.getGuestName() == null || reservation.getGuestName().trim().isEmpty()) {
            throw new BookingException("Invalid Booking: Guest name cannot be empty.");
        }

        // 2. Validate Room Type Existence
        String requestedType = reservation.getRoomType();
        if (!validRoomTypes.contains(requestedType)) {
            throw new BookingException("Invalid Booking: Room type '" + requestedType + "' is not supported.");
        }

        // 3. Validate Availability (Early Detection)
        int available = inventory.getAvailability(requestedType);
        if (available <= 0) {
            throw new BookingException("Inventory Error: No " + requestedType + " rooms available.");
        }
    }
}
