package ru.job4j.tracker;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.io.InputStream;
import java.util.Properties;

/**
 * Трекер с сохранением заявок в базе данных.
 *
 * @author Alexandr Khomichevskiy.
 * @version 1.0.
 */
public class TrackerSQL implements ITracker, AutoCloseable {

    /**
     * Соединение с базой данных.
     */
    private Connection connection;

    /**
     * Устанавливаем соединение с базой данных.
     *
     * @return true - соединение установлено, иначе false.
     */
    public boolean init() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        return connection != null;
    }

    /**
     * Добавление заявки в базу данных.
     *
     * @param item новая заявка.
     * @return добавленная заявка, если не добавлена возвращаем null.
     */
    @Override
    public Item add(Item item) {
        try (PreparedStatement pr = connection.prepareStatement(
                "insert into item(name, description, created) values (?,?,?)",
                PreparedStatement.RETURN_GENERATED_KEYS)) {
            pr.setString(1, item.getName());
            pr.setString(2, item.getDesc());
            pr.setTimestamp(3, new Timestamp(item.getTime()));
            pr.executeUpdate();
            try (ResultSet generatedKeys = pr.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    item.setId(String.valueOf(generatedKeys.getInt(1)));
                }
            }
        } catch (SQLException e) {
            item = null;
        }
        return item;
    }

    /**
     * Изменение существующей заяки, поиск по id.
     *
     * @param id   id существующей заявки.
     * @param item новая заявка.
     * @return true если заявка изменена, иначе false.
     */
    @Override
    public boolean replace(String id, Item item) {
        boolean result = true;
        try (PreparedStatement pr = connection.prepareStatement(
                "update item set name = ?, description = ?, created = ? where id = ?")) {
            pr.setString(1, item.getName());
            pr.setString(2, item.getDesc());
            pr.setTimestamp(3, new Timestamp(item.getTime()));
            pr.setInt(4, Integer.parseInt(id));
            pr.executeUpdate();
        } catch (SQLException e) {
            result = false;
        }
        if (result) {
            item.setId(id);
        }
        return result;
    }

    /**
     * Удаляем существующую заявку из базы.
     *
     * @param id id существующей заявки.
     * @return true заявка удалена, иначе false.
     */
    @Override
    public boolean delete(String id) {
        boolean result = true;
        try (PreparedStatement pr = connection.prepareStatement("delete from item where id = ?")) {
            pr.setInt(1, Integer.parseInt(id));
            pr.executeUpdate();
        } catch (SQLException e) {
            result = false;
        }
        return result;
    }

    /**
     * Возвращаем все заявки из базы.
     *
     * @return список всех заявок.
     */
    @Override
    public List<Item> findAll() {
        List<Item> result;
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery("select id, name, description, created from item")) {
            result = resultSetToItems(rs);
        } catch (SQLException e) {
            result = null;
        }
        return result;
    }

    /**
     * Возвращает список заявок по имени.
     *
     * @param key имя заявки.
     * @return Список заявок.
     */
    @Override
    public List<Item> findByName(String key) {
        List<Item> result;
        try (PreparedStatement pr = connection.prepareStatement(
                "select id, name, description, created from item where name = ?")) {
            pr.setString(1, key);
            pr.executeQuery();
            try (ResultSet rs = pr.executeQuery()) {
                result = resultSetToItems(rs);
            }
        } catch (SQLException e) {
            result = null;
        }
        return result;
    }

    /**
     * Возвращаем заявку айденную по Id.
     *
     * @param id id заявки.
     * @return найденная заявка, иначе null.
     */
    @Override
    public Item findById(String id) {
        List<Item> result;
        try (PreparedStatement pr = connection.prepareStatement(
                "select id, name, description, created from item where id = ?")) {
            pr.setInt(1, Integer.parseInt(id));
            pr.executeQuery();
            try (ResultSet rs = pr.executeQuery()) {
                result = resultSetToItems(rs);
            }
        } catch (SQLException e) {
            result = null;
        }
        return result != null && !result.isEmpty() ? result.get(0) : null;
    }

    /**
     * Закрываем соединение с базой данных.
     *
     * @throws Exception кидаем если закрытие не удалось.
     */
    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    /**
     * Метод добавляет найденные заявки в список.
     *
     * @param rs ResultSet - запрос из базы.
     * @return Список заявок.
     * @throws SQLException кидаем при невозможности достать информацию
     *                      из резалтсета.
     */
    private List<Item> resultSetToItems(ResultSet rs) throws SQLException {
        List<Item> result = new ArrayList<>();
        while (rs.next()) {
            Item item = new Item(
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getTimestamp("created").getTime());
            item.setId(String.valueOf(rs.getInt("id")));
            result.add(item);
        }
        return result;
    }
}
