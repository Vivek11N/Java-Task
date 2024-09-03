import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

class DatabaseFetchThread extends Thread {
    private String tableName;
    private String queryType;
    private String sqlQuery;
    private String url = "jdbc:postgresql://localhost:5432/company_db"; // Change as per your setup
    private String user = "postgres"; // Change to your PostgreSQL username
    private String password = "1234"; // Change to your PostgreSQL password
    private ConcurrentHashMap<String, List<List<String>>> dataMap;
    private CountDownLatch latch;

    public DatabaseFetchThread(String tableName, String queryType, String sqlQuery, ConcurrentHashMap<String, List<List<String>>> dataMap, CountDownLatch latch) {
        this.tableName = tableName;
        this.queryType = queryType;
        this.sqlQuery = sqlQuery;
        this.dataMap = dataMap;
        this.latch = latch;
    }

    @Override
    public void run() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // Connect to PostgreSQL database
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();

            if (queryType.equalsIgnoreCase("SELECT")) {
                rs = stmt.executeQuery(sqlQuery);
                List<List<String>> tableData = new ArrayList<>();

                // Add column headers
                List<String> headers = new ArrayList<>();
                int columnCount = rs.getMetaData().getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    headers.add(rs.getMetaData().getColumnName(i));
                }
                tableData.add(headers);

                // Add data rows
                while (rs.next()) {
                    List<String> row = new ArrayList<>();
                    for (int i = 1; i <= columnCount; i++) {
                        row.add(rs.getString(i));
                    }
                    tableData.add(row);
                }
                dataMap.put(tableName, tableData);
            } else {
                int result = stmt.executeUpdate(sqlQuery);
                System.out.println(result + " rows affected.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                latch.countDown(); // Decrement the latch count
            }
        }
    }
}
