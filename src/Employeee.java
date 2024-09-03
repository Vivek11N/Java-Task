import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Employee class to hold employee data
class Employee {
    private String id;
    private String name;

    public Employee(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return id + "," + name;
    }

    // Static method to create Employee object from a CSV line
    public static Employee fromString(String line) {
        String[] parts = line.split(",");
        if (parts.length == 2) {
            return new Employee(parts[0].trim(), parts[1].trim());
        }
        return null;
    }
}

public class Employeee {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\ASUS\\Desktop\\Untitled.txt";
        List<Employee> employees = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        // Read existing employee records from the file
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Employee employee = Employee.fromString(line);
                if (employee != null) {
                    employees.add(employee);
                }
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file: " + e.getMessage());
        }

        // Display existing records
        System.out.println("Existing employee records:");
        for (Employee emp : employees) {
            System.out.println(emp);
        }

        // Input new employee records
        System.out.println("Enter new employee records. Type 'done' when finished.");
        while (true) {
            System.out.print("Enter employee ID (or 'done' to finish): ");
            String id = scanner.nextLine();
            if (id.equalsIgnoreCase("done")) {
                break;
            }

            System.out.print("Enter employee name: ");
            String name = scanner.nextLine();

            // Add new employee to the list
            employees.add(new Employee(id, name));
        }

        // Write all employee records back to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Employee employee : employees) {
                writer.write(employee.toString());
                writer.newLine(); // Add a new line after each record
            }
            System.out.println("Employee records have been successfully updated in " + filePath);
        } catch (IOException e) {
            System.err.println("An error occurred while writing the file: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
