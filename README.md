# Hostel Room Allocation System

## 1. Project Title

Hostel Room Allocation System

## 2. Project Description

The Hostel Room Allocation System is a Java-based application designed to manage hostel rooms and student accommodation efficiently.

It allows users to view rooms, allocate rooms to students, search available rooms, check out students, and sort rooms by price.

The system uses file handling to store room and booking information permanently.

## 3. Objectives

- To simplify hostel room allocation.
- To maintain accurate room availability records.
- To reduce manual work in hostel management.
- To calculate accommodation charges automatically.
- To provide an easy-to-use room management system.

## 4. Features

- View all hostel rooms.
- Allocate rooms to students.
- Search available rooms by type.
- Check out students.
- Calculate room charges.
- Sort rooms by price.
- Display available room count.
- Store room information using file handling.
- Prevent booking of already allocated rooms.

## 5. Technologies Used

- Programming Language: Java
- Collections: ArrayList and HashMap
- File Handling: FileReader, FileWriter, BufferedReader, PrintWriter
- Input Handling: Scanner
- Concepts: Object-Oriented Programming, Constructors, Methods, Loops, and Conditional Statements

## 6. System Requirements

- Java Development Kit (JDK) 8 or above.
- Windows, Linux, or macOS.
- Any Java IDE, such as IntelliJ IDEA, Eclipse, or VS Code.
- Command Prompt or Terminal.

## 7. Project Structure

```text
Hostel-Room-Allocation-System/
│
├── Hostel.java
├── rooms.txt
└── README.md
```

Note: The Room class is defined inside Hostel.java in the current implementation. The rooms.txt file is automatically created when the program runs for the first time.

## 8. Default Room Details

| Room Number | Room Type | Price per Night |
|-------------|-----------|-----------------|
| 101 | Single | 1500 |
| 102 | Double | 2500 |
| 103 | Suite | 5000 |
| 104 | Single | 1800 |
| 105 | Double | 3000 |

## 9. Installation and Execution

### Step 1: Clone the Repository

```bash
git clone https://github.com/YOUR-USERNAME/Hostel-Room-Allocation-System.git
```

### Step 2: Open the Project Folder

```bash
cd Hostel-Room-Allocation-System
```

### Step 3: Compile the Program

```bash
javac Hostel.java
```

### Step 4: Run the Program

```bash
java Hostel
```

## 10. Main Menu

```text
================================
   HOSTEL ROOM ALLOCATION SYSTEM
================================
1. View All Rooms
2. Book Room
3. Search Available Rooms
4. Check Out Guest
5. Sort Rooms By Price
6. Exit

Enter Your Choice:
```

## 11. Sample Output

### View All Rooms

```text
========== HOSTEL ROOMS ==========

Room No.    Type         Price        Status
101         Single       1500         Available
102         Double       2500         Available
103         Suite        5000         Available
104         Single       1800         Available
105         Double       3000         Available

Available rooms: 5
```

### Book a Room

```text
Enter Room Number: 101
Enter Guest Name: Rahul
Enter Number of Nights: 3

Room 101 booked for Rahul.
Total: 4500

Available rooms: 4
```

### Check Out Guest

```text
Enter Room Number: 101

Room 101 checked out.
Guest Name: Rahul
Bill: 4500
Status: Available

Available rooms: 5
```

## 12. Data Storage and File Handling

The system stores room details in a file named rooms.txt.

Each record contains:

- Room Number
- Room Type
- Price
- Booking Status
- Guest Name
- Number of Nights

Example:

```text
101,Single,1500,true,Rahul,3
102,Double,2500,false,,0
```

The application loads saved information when it starts and updates the file whenever a room is booked or checked out.

## 13. Advantages

- Easy to use and understand.
- Reduces manual record keeping.
- Provides quick room availability information.
- Automatically calculates accommodation charges.
- Maintains booking information between program runs.
- Demonstrates practical Java programming concepts.

## 14. Future Enhancements

- Student registration and login.
- Admin authentication.
- Database integration using MySQL.
- Graphical User Interface (GUI).
- Online payment facility.
- Hostel fee management.
- Room allocation based on student preferences.

## 15. Author and Conclusion

### Author

**Monu Kumar**

B.Tech CSE Core

GitHub: [Your GitHub Profile](https://github.com/your-username)

### Conclusion

The Hostel Room Allocation System provides a simple and efficient solution for managing hostel rooms and student accommodation.

This project demonstrates the practical application of Java, Object-Oriented Programming, collections, and file handling in developing a real-world management system.

It can be further improved by adding database connectivity, authentication, and a graphical user interface.
