import java.util.*;
import java.io.*;

// Room Class
class Room {

    int roomNumber;
    String roomType;
    int price;
    boolean isBooked;
    String guestName;
    int nights;

    Room(int roomNumber, String roomType, int price) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.price = price;
        this.isBooked = false;
        this.guestName = "";
        this.nights = 0;
    }
}

public class Main {

    static Scanner sc = new Scanner(System.in);

    static ArrayList<Room> rooms = new ArrayList<>();
    static HashMap<Integer, Room> roomMap = new HashMap<>();

    static final String FILE_NAME = "rooms.txt";

    // Default Rooms
    static void addDefaultRooms() {

        rooms.add(new Room(101, "Single", 1500));
        rooms.add(new Room(102, "Double", 2500));
        rooms.add(new Room(103, "Suite", 5000));
        rooms.add(new Room(104, "Single", 1800));
        rooms.add(new Room(105, "Double", 3000));
    }

    // Load Rooms From File
    static void loadRooms() {

        rooms.clear();
        roomMap.clear();

        File file = new File(FILE_NAME);

        if (!file.exists()) {
            addDefaultRooms();
            updateRoomMap();
            saveRooms();
            return;
        }

        try (BufferedReader br = new BufferedReader(
                new FileReader(file))) {

            String line;

            while ((line = br.readLine()) != null) {

                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] data = line.split(",", -1);

                if (data.length < 3) {
                    continue;
                }

                try {

                    int number = Integer.parseInt(data[0].trim());
                    String type = data[1].trim();
                    int price = Integer.parseInt(data[2].trim());

                    Room room = new Room(number, type, price);

                    if (data.length >= 6) {

                        room.isBooked =
                                Boolean.parseBoolean(data[3].trim());

                        room.guestName = data[4].trim();

                        room.nights =
                                Integer.parseInt(data[5].trim());
                    }

                    rooms.add(room);

                } catch (NumberFormatException e) {
                    System.out.println("Invalid room data skipped.");
                }
            }

        } catch (IOException e) {
            System.out.println("Error loading room data.");
            return;
        }

        // Add Missing Default Rooms
        int[] numbers = {101, 102, 103, 104, 105};

        String[] types = {
            "Single", "Double", "Suite", "Single", "Double"
        };

        int[] prices = {1500, 2500, 5000, 1800, 3000};

        for (int i = 0; i < numbers.length; i++) {

            boolean found = false;

            for (Room room : rooms) {

                if (room.roomNumber == numbers[i]) {
                    found = true;
                    break;
                }
            }

            if (!found) {
                rooms.add(new Room(
                        numbers[i], types[i], prices[i]));
            }
        }

        // Sort Rooms By Room Number
        rooms.sort(Comparator.comparingInt(
                r -> r.roomNumber));

        updateRoomMap();
        saveRooms();
    }

    // Update HashMap
    static void updateRoomMap() {

        roomMap.clear();

        for (Room room : rooms) {
            roomMap.put(room.roomNumber, room);
        }
    }

    // Save Rooms In File
    static void saveRooms() {

        try (PrintWriter pw = new PrintWriter(
                new FileWriter(FILE_NAME))) {

            for (Room room : rooms) {

                pw.println(
                        room.roomNumber + "," +
                        room.roomType + "," +
                        room.price + "," +
                        room.isBooked + "," +
                        room.guestName + "," +
                        room.nights
                );
            }

        } catch (IOException e) {
            System.out.println("Error saving data.");
        }
    }

    // View All Rooms
    static void viewRooms() {

        System.out.println("\n========== HOTEL ROOMS ==========");

        System.out.printf("%-12s %-12s %-12s %-12s%n",
                "Room No.", "Type", "Price", "Status");

        for (Room room : rooms) {

            String status = room.isBooked
                    ? "Booked"
                    : "Available";

            System.out.printf("%-12d %-12s %-12d %-12s%n",
                    room.roomNumber,
                    room.roomType,
                    room.price,
                    status);
        }

        showAvailableRooms();
    }

    // Count Available Rooms
    static void showAvailableRooms() {

        int count = 0;

        for (Room room : rooms) {

            if (!room.isBooked) {
                count++;
            }
        }

        System.out.println("Available rooms: " + count);
    }

    // Book Room
    static void bookRoom() {

        System.out.print("Enter Room Number: ");
        int number = readInt();

        Room room = roomMap.get(number);

        if (room == null) {
            System.out.println("Room " + number + " not found.");
            return;
        }

        if (room.isBooked) {
            System.out.println("Room is already booked.");
            return;
        }

        System.out.print("Enter Guest Name: ");
        String name = sc.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("Invalid guest name.");
            return;
        }

        System.out.print("Enter Number of Nights: ");
        int nights = readInt();

        if (nights <= 0) {
            System.out.println("Invalid number of nights.");
            return;
        }

        int total = room.price * nights;

        room.isBooked = true;
        room.guestName = name;
        room.nights = nights;

        saveRooms();

        System.out.println("\nRoom " + number +
                " booked for " + name + ".");

        System.out.println("Total: " + total);

        showAvailableRooms();
    }

    // Search Available Rooms By Type
    static void searchAvailableRooms() {

        System.out.print("Enter Room Type: ");
        String type = sc.nextLine().trim();

        if (type.isEmpty()) {
            System.out.println("Please enter a room type.");
            return;
        }

        boolean found = false;

        for (Room room : rooms) {

            if (room.roomType.equalsIgnoreCase(type)
                    && !room.isBooked) {

                System.out.println(
                        room.roomNumber + " | " +
                        room.roomType + " | " +
                        room.price + " | Available");

                found = true;
            }
        }

        if (!found) {
            System.out.println(
                    "No available rooms found for type: " + type);
        }
    }

    // Check Out Guest
    static void checkOut() {

        System.out.print("Enter Room Number: ");
        int number = readInt();

        Room room = roomMap.get(number);

        if (room == null) {
            System.out.println("Room " + number + " not found.");
            return;
        }

        if (!room.isBooked) {
            System.out.println("Room is not booked.");
            return;
        }

        int bill = room.price * room.nights;

        System.out.println("\nRoom " + number + " checked out.");
        System.out.println("Guest Name: " + room.guestName);
        System.out.println("Bill: " + bill);

        room.isBooked = false;
        room.guestName = "";
        room.nights = 0;

        saveRooms();

        System.out.println("Status: Available");

        showAvailableRooms();
    }

    // Sort Rooms By Price (High To Low)
    static void sortRoomsByPrice() {

        ArrayList<Room> sortedRooms =
                new ArrayList<>(rooms);

        sortedRooms.sort((r1, r2) ->
                Integer.compare(r2.price, r1.price));

        System.out.println(
                "\nRooms Sorted By Price (High to Low)");

        for (Room room : sortedRooms) {

            System.out.println(
                    room.roomNumber + " | " +
                    room.roomType + " | " +
                    room.price);
        }
    }

    // Read Integer Input
    static int readInt() {

        while (true) {

            try {
                return Integer.parseInt(
                        sc.nextLine().trim());

            } catch (NumberFormatException e) {
                System.out.print("Enter a valid number: ");
            }
        }
    }

    // Main Method
    public static void main(String[] args) {

        loadRooms();

        while (true) {

            System.out.println("\n================================");
            System.out.println("      HOTEL MANAGEMENT SYSTEM");
            System.out.println("================================");

            System.out.println("1. View All Rooms");
            System.out.println("2. Book Room");
            System.out.println("3. Search Available Rooms");
            System.out.println("4. Check Out Guest");
            System.out.println("5. Sort Rooms By Price");
            System.out.println("6. Exit");

            System.out.print("\nEnter Your Choice: ");

            int choice = readInt();

            switch (choice) {

                case 1:
                    viewRooms();
                    break;

                case 2:
                    bookRoom();
                    break;

                case 3:
                    searchAvailableRooms();
                    break;

                case 4:
                    checkOut();
                    break;

                case 5:
                    sortRoomsByPrice();
                    break;

                case 6:
                    saveRooms();

                    System.out.println("Data saved successfully.");

                    System.out.println(
                            "Thank you for using Hotel Management System!");

                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
