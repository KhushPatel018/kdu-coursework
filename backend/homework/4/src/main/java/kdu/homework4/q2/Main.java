package kdu.homework4.q2;

import kdu.homework4.logging.LoggingSystem;

public class Main {
    private static final LoggingSystem ls = new LoggingSystem();
    public static void main(String[] args) {
        TicketReservation ticketReservation = new TicketReservation();

        String female = "Female";
        String economy = "Economy";
        // Booking passengers
        ls.logInfo("Booking passengers...");
        ls.logInfo("Booking Result 1: " + ticketReservation.bookFlight("John", "Doe", 25, "Male", economy, "CN1"));
        ls.logInfo("Booking Result 2: " + ticketReservation.bookFlight("Jane", "Smith", 30, female, "Business", "CN2"));
        ls.logInfo("Booking Result 3: " + ticketReservation.bookFlight("Bob", "Johnson", 22, "Male", "First Class", "CN3"));
        ls.logInfo("Booking Result 4: " + ticketReservation.bookFlight("Alice", "Williams", 28, female, economy, "CN4"));

        // Display confirmed and waiting lists
        displayLists(ticketReservation);

        // Canceling a passenger
        ls.logInfo("Cancelling a passenger...");
        ls.logInfo("Cancellation Result: " + ticketReservation.cancel("CN2"));

        // Display confirmed and waiting lists after cancellation
        displayLists(ticketReservation);

        // Attempting to book more passengers
        ls.logInfo("Booking more passengers...");
        ls.logInfo("Booking Result 5: " + ticketReservation.bookFlight("Charlie", "Brown", 40, "Male", economy, "CN5"));
        ls.logInfo("Booking Result 6: " + ticketReservation.bookFlight("Eva", "Green", 35, female, "Business", "CN6"));
        ls.logInfo("Booking Result 7: " + ticketReservation.bookFlight("David", "Lee", 28, "Male", "First Class", "CN7"));

        // Display confirmed and waiting lists after additional bookings
        displayLists(ticketReservation);
    }

    private static void displayLists(TicketReservation ticketReservation) {
        ls.logInfo("Confirmed List:");
        ticketReservation.getConfirmedList().forEach(passenger -> ls.logInfo(passenger.getConfirmationNumber() + ": " + passenger.getFirstName() + " " + passenger.getLastName()));

        ls.logInfo("Waiting List:");
        ticketReservation.getWaitingList().forEach(passenger -> ls.logInfo(passenger.getConfirmationNumber() + ": " + passenger.getFirstName() + " " + passenger.getLastName()));
    }
}
