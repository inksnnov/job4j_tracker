package ru.job4j.tracker;

import java.util.List;

/**
 * @author Alexandr Khomichevskiy.
 * @version 1.0.
 */
public interface ITracker {
    Item add(Item item);

    boolean replace(String id, Item item);

    boolean delete(String id);

    List<Item> findAll();

    List<Item> findByName(String key);

    Item findById(String id);
}
