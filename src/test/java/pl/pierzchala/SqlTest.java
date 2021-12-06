package pl.pierzchala;


import org.junit.Test;

import java.sql.*;

public class SqlTest {


    private final String QUERY = "SELECT * FROM questions";

    @Test
    public void sqlTest() {
        try (Connection conn = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/bassjude_bhp?useSSL=false&characterEncoding=utf8&serverTimezone=UTC",
            "root", "coderslab")) {

            PreparedStatement preparedStatement = conn.prepareStatement(QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
