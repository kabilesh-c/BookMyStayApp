import java.util.ArrayList;
import java.util.List;

/**
 * BookingReportService - Generates summaries and reports from stored booking data.
 * UC8: Booking History & Reporting
 */
public class BookingReportService {

    /**
     * Prints a summary report of all confirmed bookings.
     * @param history The list of confirmed reservations.
     */
    public void generateSummaryReport(List<Reservation> history) {
        System.out.println("\n========== BOOKING SUMMARY REPORT ==========");
        System.out.println("Total Bookings Confirmed: " + history.size());
        
        if (history.isEmpty()) {
            System.out.println("No bookings found in history.");
        } else {
            System.out.println("--------------------------------------------");
            for (int i = 0; i < history.size(); i++) {
                Reservation res = history.get(i);
                System.out.println((i + 1) + ". Guest: " + res.getGuestName() + " | Room: " + res.getRoomType());
            }
        }
        System.out.println("============================================\n");
    }

    /**
     * Prints a report grouped by room type.
     * @param history The list of confirmed reservations.
     */
    public void generateTypeBasedReport(List<Reservation> history) {
        System.out.println("\n========== ROOM TYPE DISTRIBUTION ==========");
        java.util.Map<String, Integer> counts = new java.util.HashMap<>();
        
        for (Reservation res : history) {
            counts.put(res.getRoomType(), counts.getOrDefault(res.getRoomType(), 0) + 1);
        }
        
        counts.forEach((type, count) -> {
            System.out.println(type + ": " + count + " bookings");
        });
        System.out.println("============================================\n");
    }
}
