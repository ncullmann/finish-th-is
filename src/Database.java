import java.sql.*;
public class Database {
    String url;
    public Database() {
        url = "jdbc:sqlite:User";
    }
    public static void updateTable(Connection connection, String tableName, Object blob)
            throws SQLException {
        String sql = "INSERT INTO " + tableName + "";
    }
}
