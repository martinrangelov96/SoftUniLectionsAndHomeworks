package exercise02_get_villans_names;

import java.sql.*;
import java.util.Properties;

public class Application {

    public static void main(String[] args) throws SQLException {

        String user = "root";
        String password = "12345";

        Properties properties = new Properties();
        properties.setProperty("user", user);
        properties.setProperty("password", password);

        Connection connection =
                DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", properties);

        Engine engine = new Engine(connection);

        engine.run();

    }

}
