package egger.software.examples.mocking;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SqlDatabase implements Database {

    private DataSource dataSource;

    public SqlDatabase(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void insert(String table, int id, String value) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO " + table + " VALUES (?, ?)")) {
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, value);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }


    public String select(String table, int id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT value FROM " + table + " WHERE id=?")) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next())
                return null;

            return resultSet.getString("value");

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public int nextId() {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement selectStatement = connection.prepareStatement(
                     "SELECT nextId FROM ids WHERE counterName='main'");
             PreparedStatement updateStatement = connection.prepareStatement(
                     "UPDATE ids SET nextId = ?  WHERE counterName='main'")
        ) {
            ResultSet resultSet = selectStatement.executeQuery();
            resultSet.next();
            int id = resultSet.getInt("nextId");

            updateStatement.setInt(1, id + 1);
            updateStatement.executeUpdate();
            return id;

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }


}
