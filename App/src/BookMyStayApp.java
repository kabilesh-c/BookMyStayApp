/**
 * BookMyStayApp
 *
 * Entry point of the Hotel Booking Application.
 * Demonstrates how a Java application starts execution,
 * prints console output, and terminates gracefully.
 *
 * This implementation establishes the foundational
 * execution flow of the system.
 *
 * @author Kabilesh C
 * @version 1.0.0
 */

public class BookMyStayApp {

    // Application Constants
    private static final String APP_NAME = "BookMyStayApp";
    private static final String VERSION = "1.0.0";
    private static final String COMPANY_TAGLINE = "Explore. Book. Relax.";

    public static void main(String[] args) {

        initializeApplication();
        displayWelcomeMessage();
        displayVersionInformation();
        closeApplication();

    }

    /**
     * Initializes the application.
     */
    private static void initializeApplication() {
        System.out.println("==========================================");
        System.out.println("       Initializing Application...        ");
        System.out.println("==========================================\n");
    }

    /**
     * Displays the welcome screen.
     */
    private static void displayWelcomeMessage() {
        System.out.println("==========================================");
        System.out.println("        Welcome to " + APP_NAME);
        System.out.println("==========================================");
        System.out.println("Your trusted platform for booking stays.");
        System.out.println(COMPANY_TAGLINE);
        System.out.println();
    }

    /**
     * Displays version information.
     */
    private static void displayVersionInformation() {
        System.out.println("------------------------------------------");
        System.out.println("Application Version : " + VERSION);
        System.out.println("Status              : Running Successfully");
        System.out.println("------------------------------------------\n");
    }

    /**
     * Closes the application gracefully.
     */
    private static void closeApplication() {
        System.out.println("Thank you for using " + APP_NAME + "!");
        System.out.println("Application Closed Successfully.");
        System.out.println("==========================================");
    }
}