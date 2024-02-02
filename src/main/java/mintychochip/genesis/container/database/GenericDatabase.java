package mintychochip.genesis.container.database;

import mintychochip.genesis.config.DatabaseConfig;

import java.sql.*;

public class GenericDatabase {

    private final Connection connection;

    private final DatabaseConfig config;
    public GenericDatabase(DatabaseConfig config) {
        this.config = config;

        try {
            connection = DriverManager.getConnection(config.getUrl(),config.getUsername(),config.getPassword());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public ResultSet executeQuery(String query) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            return preparedStatement.getResultSet();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
