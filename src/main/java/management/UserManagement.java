package management;


import db.DBConnectionProvider;
import model.User;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserManagement {

    private final Connection connection;
    final String createSql = "Insert into user(`name`, surname, email, event_id, password) Values(?,?,?,?,?)";

    public UserManagement() {
        connection = DBConnectionProvider.getInstance().getConnection();

    }

    public void addUser(User user) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(createSql, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getSurname());
        preparedStatement.setString(3, user.getEmail());
        preparedStatement.setInt(4, user.getEventId());
        preparedStatement.setString(5, user.getPassword());

        preparedStatement.executeUpdate();
        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        if (resultSet.next()) {
            int id = resultSet.getInt(1);
            user.setId(id);
        }
    }

    public List<User> getAllUsers() throws SQLException {

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from user");
        List<User> users = new LinkedList<>();
        while (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getInt(1));
            user.setName(resultSet.getString(2));
            user.setSurname(resultSet.getString(3));
            user.setEmail(resultSet.getString(4));
            user.setEventId(resultSet.getInt(5));
            user.setPassword(resultSet.getString(6));
            users.add(user);
        }
        return users;

    }
}

