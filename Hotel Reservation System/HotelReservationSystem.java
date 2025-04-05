import java.util.*;

class Room {
    int roomNumber;
    String category;
    boolean isAvailable;

    public Room(int roomNumber, String category) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.isAvailable = true;
    }

    public void book() {
        isAvailable = false;
    }

    public void cancel() {
        isAvailable = true;
    }

    @Override
    public String toString() {
        return "Room " + roomNumber + " (" + category + ") - " + (isAvailable ? "Available" : "Booked");
    }
}

class Booking {
    String guestName;
    Room room;
    boolean paymentStatus;

    public Booking(String guestName, Room room) {
        this.guestName = guestName;
        this.room = room;
        this.paymentStatus = false;
    }

    public void makePayment() {
        this.paymentStatus = true;
    }

    public void showDetails() {
        System.out.println("Guest Name: " + guestName);
        System.out.println("Room Number: " + room.roomNumber);
        System.out.println("Room Category: " + room.category);
        System.out.println("Payment Status: " + (paymentStatus ? "Paid" : "Pending"));
    }
}

public class HotelReservationSystem {
    private static final List<Room> rooms = new ArrayList<>();
    private static final List<Booking> bookings = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeRooms();

        while (true) {
            System.out.println("\n--- Hotel Reservation System ---");
            System.out.println("1. View Available Rooms");
            System.out.println("2. Make a Reservation");
            System.out.println("3. View Booking Details");
            System.out.println("4. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> viewAvailableRooms();
                case 2 -> makeReservation();
                case 3 -> viewBookingDetails();
                case 4 -> {
                    System.out.println("Thank you for using the system. visit again !");
                    System.out.println("---------------------------------------");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private static void initializeRooms() {
        // Add rooms to hotel (room number, category)
        rooms.add(new Room(101, "Single"));
        rooms.add(new Room(102, "Double"));
        rooms.add(new Room(103, "Suite"));
        rooms.add(new Room(201, "Single"));
        rooms.add(new Room(202, "Double"));
        rooms.add(new Room(203, "Suite"));
        rooms.add(new Room(301, "Single"));
        rooms.add(new Room(302, "Double"));
        rooms.add(new Room(302, "Suite"));
        
    }

    private static void viewAvailableRooms() {
        System.out.println("\n--- Available Rooms ---");
        boolean anyAvailable = false;
        for (Room room : rooms) {
            if (room.isAvailable) {
                System.out.println(room);
                anyAvailable = true;
            }
        }
        if (!anyAvailable) {
            System.out.println("No rooms available.");
        }
    }

    private static void makeReservation() {
        viewAvailableRooms();

        System.out.print("\nEnter your name: ");
        String guestName = scanner.nextLine();

        System.out.print("Enter preferred room category (Single, Double, Suite): ");
        String category = scanner.nextLine();

        Room selectedRoom = null;
        for (Room room : rooms) {
            if (room.isAvailable && room.category.equalsIgnoreCase(category)) {
                selectedRoom = room;
                break;
            }
        }

        if (selectedRoom == null) {
            System.out.println("No available rooms in selected category.");
            return;
        }

        selectedRoom.book();
        Booking booking = new Booking(guestName, selectedRoom);

        System.out.print("Proceed to payment? (yes/no): ");
        String payment = scanner.nextLine();
        if (payment.equalsIgnoreCase("yes")) {
            booking.makePayment();
            System.out.println("Payment successful.");
        } else {
            System.out.println("Reservation made without payment.");
        }

        bookings.add(booking);
        System.out.println("Reservation successful! Room " + selectedRoom.roomNumber + " is booked.");
    }

    private static void viewBookingDetails() {
        System.out.print("\nEnter your name to search bookings: ");
        String name = scanner.nextLine();
        boolean found = false;

        for (Booking booking : bookings) {
            if (booking.guestName.equalsIgnoreCase(name)) {
                System.out.println("\n--- Booking Details ---");
                booking.showDetails();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No bookings found for that name.");
        }
    }
}
