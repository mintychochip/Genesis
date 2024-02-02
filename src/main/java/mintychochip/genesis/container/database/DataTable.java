package mintychochip.genesis.container.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DataTable {

    private List<String> columnNames;

    private List<String> dataTypes;

    private String tableName;

    public DataTable(Connection connection, List<String> columnNames, List<String> dataTypes, String tableName) {
        this.columnNames = columnNames;
        this.dataTypes = dataTypes;
        this.tableName = tableName;

        //attempts to create a table
        StringBuilder sql = new StringBuilder("CREATE TABLE IF NOT EXISTS (");
        for (int i = 0; i < columnNames.size(); i++) {
            sql.append(columnNames.get(i)).append(" ").append(dataTypes.get(i));
            if(i != columnNames.size() - 1) {
                sql.append(",");
            }
        }
        sql.append(")");
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> getColumnNames() {
        return columnNames;
    }

    public List<String> getDataTypes() {
        return dataTypes;
    }

    public String getTableName() {
        return tableName;
    }
}
