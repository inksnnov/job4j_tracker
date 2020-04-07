package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Класс манипулирует объектами типа Item.
 *
 * @author Alexandr Khomichevskiy.
 * @version 1.0.
 * @since 12.03.19.
 */
public class Tracker implements ITracker {

    /**
     * ArrayList- хранилище объектов типа Item, хранит заявки.
     */
    private final List<Item> items = new ArrayList<>();

    /**
     * Метод добавления заявки(Item) в массив Items.
     *
     * @param item Созданая заявки.
     * @return Item возвращает созданную заявку.
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items.add(item);
        return item;
    }

    /**
     * Метод заменяет созданную ранее заявку в массиве items.
     * Id берем из заменяемой заявки.
     *
     * @param id   заявки которую  нужно заменить.
     * @param item Объект новой заявки.
     * @return возвращает true если замена прошла успешно, иначе false.
     */
    public boolean replace(String id, Item item) {
        ListIterator<Item> iterator = this.items.listIterator();
        boolean result = false;
        while (iterator.hasNext()) {
            if (iterator.next().getId().equals(id)) {
                item.setId(id);
                iterator.set(item);
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Метод удаляет созданную заявку из массива items, сдвигая
     * элементы после удаленной ячейки на 1 позицию влево и
     * перемещает курсор - position так же влево на единицу.
     *
     * @param id удаляемой заявки?
     * @return если заявка удалена возвращает true, иначе false.
     */
    public boolean delete(String id) {
        ListIterator<Item> iterator = this.items.listIterator();
        boolean result = false;
        while (iterator.hasNext()) {
            if (iterator.next().getId().equals(id)) {
                iterator.remove();
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Возвращает все заявки из массива items.
     *
     * @return Возващает список заявок.
     */
    public List<Item> findAll() {
        return this.items;
    }

    /**
     * Поиск заявки по имени.
     *
     * @param name имя искомой заявки.
     * @return список заявок с искомыми именами.
     */
    public List<Item> findByName(String name) {
        List<Item> result = new ArrayList<>();
        for (Item item : this.items) {
            if (item != null && item.getName().equals(name)) {
                result.add(item);
            }
        }
        return result;
    }

    /**
     * Поиск заявки по Id.
     *
     * @param id искомое Id.
     * @return Возвращает найденную заявку, иначе null.
     */
    public Item findById(String id) {
        Item result = null;
        for (Item item : this.items) {
            if (item != null && item.getId().equals(id)) {
                result = item;
                break;
            }
        }
        return result;
    }

    /**
     * Генерация уникального Id для каждой созданной заявки.
     *
     * @return Id заявки.
     */
    private String generateId() {
        return String.valueOf((long) (Math.random() * System.currentTimeMillis()));
    }
}
