package ru.job4j.tracker;

import java.util.List;
import java.util.function.Consumer;

/**
 * Класс вывод всех заявок.
 *
 * @author Alexandr Khomichevskiy.
 * @version 1.0.
 */
public class ShowAllItem extends BaseAction {

    /**
     * Конструктор инициализирует поля key и name.
     *
     * @param key  номер пункта меню.
     * @param name Описание пункта меню для вывода в консоль.
     */
    public ShowAllItem(int key, String name) {
        super(key, name);
    }

    /**
     * Реализация вывод всех заявок.
     *
     * @param input   {@link Input}.
     * @param tracker {@link Tracker}
     */
    @Override
    public void execute(Input input, ITracker tracker, Consumer<String> output) {
        System.out.println("------------Все заявки------------");
        List<Item> items = tracker.findAll();
        if (items == null) {
            output.accept("Не удалось считать информацию из базы данных.");
        } else if (items.isEmpty()) {
            output.accept("Записей в базе данных нет.");
        } else {
            items.forEach(i -> output.accept(super.format(i.getName(), i.getDesc())));
        }
    }
}
