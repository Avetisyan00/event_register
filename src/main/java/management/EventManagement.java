package management;

import db.DBConnectionProvider;
import model.Event;
import model.EventType;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class EventManagement {
    private final Connection connection;
    final String sql = "insert into event(`name`, place, isOnline, price, event_type) values(?,?,?,?,?)";

    public EventManagement() {
        connection = DBConnectionProvider.getInstance().getConnection();
    }

    public void addEvent(Event event) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, event.getName());
        preparedStatement.setString(2, event.getPlace());
        preparedStatement.setBoolean(3, event.isOnline());
        preparedStatement.setDouble(4, event.getPrice());
        preparedStatement.setObject(5, event.getEventType().name());

        preparedStatement.executeUpdate();
        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        if (resultSet.next()) {
            int id = resultSet.getInt(1);
            event.setId(id);
        }
    }

    public List<Event> getAllEvents() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from event");
        List<Event> events = new LinkedList<>();
        while (resultSet.next()) {
            Event event = new Event();
            event.setId(resultSet.getInt(1));
            event.setName(resultSet.getString(2));
            event.setPlace(resultSet.getString(3));
            event.setPrice(resultSet.getDouble(5));
            event.setEventType(EventType.valueOf(resultSet.getString(6)));
            events.add(event);
        }
        return events;
    }
}
