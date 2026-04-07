/**
 * Reservation - represents a booking request
 */
public class Reservation {

    private String guestName;
    private String roomType;
    private String roomId;
    private boolean isCancelled = false;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public boolean isCancelled() {
        return isCancelled;
    }

    public void setCancelled(boolean cancelled) {
        isCancelled = cancelled;
    }

    public void display() {
        String status = isCancelled ? " [CANCELLED]" : "";
        System.out.println("Guest: " + guestName + " | Requested: " + roomType + (roomId != null ? " | Room ID: " + roomId : "") + status);
    }
}