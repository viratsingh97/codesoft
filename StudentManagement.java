import java.util.*;
import java.io.*;
class Student {
    String name;
    int rollNumber;
    String grade;

    public Student(String name, int rollNumber, String grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }

    public void display() {
        System.out.println("Roll Number: " + rollNumber + ", Name: " + name + ", Grade: " + grade);
    }

    public String toFileString() {
        return rollNumber + "," + name + "," + grade;
    }

    public static Student fromFileString(String line) {
        String[] parts = line.split(",");
        int roll = Integer.parseInt(parts[0]);
        String name = parts[1];
        String grade = parts[2];
        return new Student(name, roll, grade);
    }
}
class StudentManagementSystem {
    ArrayList<Student> students = new ArrayList<>();
    final String fileName = "students.txt";

    public StudentManagementSystem() {
        loadFromFile();
    }

    public void addStudent(Student student) {
        students.add(student);
        System.out.println("Student added successfully!");
        saveToFile();
    }

    public void removeStudent(int rollNumber) {
        boolean found = false;
        for (Student s : students) {
            if (s.rollNumber == rollNumber) {
                students.remove(s);
                System.out.println("Student removed.");
                found = true;
                saveToFile();
                break;
            }
        }
        if (!found) {
            System.out.println("Student not found.");
        }
    }

    public void searchStudent(int rollNumber) {
        for (Student s : students) {
            if (s.rollNumber == rollNumber) {
                s.display();
                return;
            }
        }
        System.out.println("Student not found.");
    }

    public void displayAll() {
        if (students.isEmpty()) {
            System.out.println("No students available.");
        } else {
            for (Student s : students) {
                s.display();
            }
        }
    }

    public void saveToFile() {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(fileName));
            for (Student s : students) {
                writer.println(s.toFileString());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving data.");
        }
    }

    public void loadFromFile() {
        try {
            File file = new File(fileName);
            if (!file.exists()) return;

            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                Student s = Student.fromFileString(line);
                students.add(s);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error reading data.");
        }
    }
}
public class StudentManagement{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentManagementSystem sms = new StudentManagementSystem();
        int choice;

        do {
            System.out.println("\n========= Student Management System =========");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    if (name.isEmpty()) {
                        System.out.println("Name cannot be empty.");
                        break;
                    }

                    System.out.print("Enter roll number: ");
                    int roll = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter grade: ");
                    String grade = scanner.nextLine();
                    if (grade.isEmpty()) {
                        System.out.println("Grade cannot be empty.");
                        break;
                    }

                    Student newStudent = new Student(name, roll, grade);
                    sms.addStudent(newStudent);
                    break;

                case 2:
                    System.out.print("Enter roll number of student to remove: ");
                    int removeRoll = scanner.nextInt();
                    sms.removeStudent(removeRoll);
                    break;

                case 3:
                    System.out.print("Enter roll number to search: ");
                    int searchRoll = scanner.nextInt();
                    sms.searchStudent(searchRoll);
                    break;

                case 4:
                    sms.displayAll();
                    break;

                case 5:
                    System.out.println("Exiting program. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 5);

        scanner.close();
    }
}
