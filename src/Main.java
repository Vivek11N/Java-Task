import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

public class Main {
    public static void main(String[] args) {
        // Array of table names
        String[] tableNames = {"employees", "departments", "projects"};

        // ConcurrentHashMap to store data from each table
        ConcurrentHashMap<String, List<List<String>>> dataMap = new ConcurrentHashMap<>();

        // CountDownLatch to wait for all threads to finish
        CountDownLatch latch = new CountDownLatch(tableNames.length);

        // Select queries
        for (String tableName : tableNames) {
            String selectQuery = "SELECT * FROM " + tableName;
            DatabaseFetchThread thread = new DatabaseFetchThread(tableName, "SELECT", selectQuery, dataMap, latch);
            thread.start();
        }

        // Wait for all threads to finish
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Print the data from each table in order
        for (String tableName : tableNames) {
            List<List<String>> tableData = dataMap.get(tableName);
            if (tableData != null) {
                System.out.println("Table: " + tableName);
                List<String> headers = tableData.get(0);
                System.out.println(String.join(" | ", headers));

                for (int i = 1; i < tableData.size(); i++) {
                    List<String> row = tableData.get(i);
                    System.out.println(String.join(" | ", row));
                }
                System.out.println(); // Add an empty line for better readability
            }
        }

        // Example insert queries with unique emails
        String[] insertQueries = {
                //"INSERT INTO employees (first_name, last_name, email, phone_number, hire_date, job_title, salary, department_id) VALUES ('John', 'Doe', 'john.doe1@exampe.com', '123-456-7890', '2024-07-27', 'Software Engineer', 60000.00, 1)",
                //"INSERT INTO employees (first_name, last_name, email, phone_number, hire_date, job_title, salary, department_id) VALUES ('Jane', 'Doe', 'jane.doe1@examle.com', '123-456-7891', '2024-07-27', 'Software Engineer', 65000.00, 1)",
                //"INSERT INTO employees (first_name, last_name, email, phone_number, hire_date, job_title, salary, department_id) VALUES ('Alice', 'Smith', 'alice.smith1@exmple.com', '123-456-7892', '2024-07-27', 'Data Analyst', 70000.00, 2)",
                //"INSERT INTO employees (first_name, last_name, email, phone_number, hire_date, job_title, salary, department_id) VALUES ('Bob', 'Brown', 'bob.brown1@exmple.com', '123-456-7893', '2024-07-27', 'Data Scientist', 75000.00, 2)",
                //"INSERT INTO employees (first_name, last_name, email, phone_number, hire_date, job_title, salary, department_id) VALUES ('Charlie', 'Davis', 'charlie.dais1@example.com', '123-456-7894', '2024-07-27', 'Project Manager', 80000.00, 3)"
        };

        for (String insertQuery : insertQueries) {
            latch = new CountDownLatch(1);
            DatabaseFetchThread insertThread = new DatabaseFetchThread("employees", "INSERT", insertQuery, dataMap, latch);
            insertThread.start();

            // Wait for the insert thread to finish
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Example update query
        String updateQuery = "UPDATE employees SET salary = 90000.00 WHERE first_name = 'John' AND last_name = 'Doe'";
        latch = new CountDownLatch(1);
        DatabaseFetchThread updateThread = new DatabaseFetchThread("employees", "UPDATE", updateQuery, dataMap, latch);
        updateThread.start();

        // Wait for the update thread to finish
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
