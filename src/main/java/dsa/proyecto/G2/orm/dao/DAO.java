package dsa.proyecto.G2.orm.dao;

import dsa.proyecto.G2.util.ConnectionManager;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAO<T> {
    private final Class<T> clazz;

    public DAO(Class<T> clazz) {
        this.clazz = clazz;
    }

    public void insert(T object) {
        StringBuilder query = new StringBuilder("INSERT INTO ");
        query.append(clazz.getSimpleName().toLowerCase()).append(" (");

        Field[] fields = clazz.getDeclaredFields();
        List<String> fieldNames = new ArrayList<>();
        List<Object> fieldValues = new ArrayList<>();

        for (Field field : fields) {
            if (!field.getName().equals("id")) { // Assuming 'id' is auto-incremented
                field.setAccessible(true);
                fieldNames.add(field.getName());
                try {
                    fieldValues.add(field.get(object));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        query.append(String.join(", ", fieldNames));
        query.append(") VALUES (");
        for (int i = 0; i < fieldNames.size(); i++) {
            query.append("?");
            if (i < fieldNames.size() - 1) {
                query.append(", ");
            }
        }

        query.append(")");

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query.toString())) {

            for (int i = 0; i < fieldValues.size(); i++) {
                statement.setObject(i + 1, fieldValues.get(i));
            }

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public T selectById(int id) {
        String query = "SELECT * FROM " + clazz.getSimpleName().toLowerCase() + " WHERE id = ?";
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToObject(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<T> selectAll() {
        List<T> results = new ArrayList<>();
        String query = "SELECT * FROM " + clazz.getSimpleName().toLowerCase();

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                results.add(mapResultSetToObject(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return results;
    }

    public void update(T object) {
        StringBuilder query = new StringBuilder("UPDATE ");
        query.append(clazz.getSimpleName().toLowerCase()).append(" SET ");

        Field[] fields = clazz.getDeclaredFields();
        List<String> updateParts = new ArrayList<>();
        List<Object> fieldValues = new ArrayList<>();
        int id = -1;

        for (Field field : fields) {
            field.setAccessible(true);
            try {
                if (!field.getName().equals("id")) {
                    updateParts.add(field.getName() + " = ?");
                    fieldValues.add(field.get(object));
                } else {
                    id = (int) field.get(object);
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

        query.append(String.join(", ", updateParts));
        query.append(" WHERE id = ?");

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query.toString())) {

            for (int i = 0; i < fieldValues.size(); i++) {
                statement.setObject(i + 1, fieldValues.get(i));
            }
            statement.setInt(fieldValues.size() + 1, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private T mapResultSetToObject(ResultSet resultSet) {
        try {
            T object = clazz.getDeclaredConstructor().newInstance();

            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);
                Object value = resultSet.getObject(field.getName());
                field.set(object, value);
            }

            return object;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
