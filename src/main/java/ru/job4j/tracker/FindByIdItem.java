package ru.job4j.tracker;

import java.util.function.Consumer;

/**
 * Класс поиск заявки по ID.
 *
 * @author Alexandr Khomichevskiy.
 * @version 1.0.
 */
public class FindByIdItem extends BaseAction {

    /**
     * Конструктор инициализирует поля key и name.
     *
     * @param key  номер пункта меню.
     * @param name Описание пункта меню для вывода в консоль.
     */
    public FindByIdItem(int key, String name) {
        super(key, name);
    }

    /**
     * Реализация поиск заявки по ID.
     *
     * @param input   {@link Input}.
     * @param tracker {@link Tracker}
     */
    @Override
    public void execute(Input input, ITracker tracker, Consumer<String> output) {
        output.accept("Поиск заявки по ID");
        String id = input.ask("Введите номер заявки");
        Item item = tracker.findById(id);
        if (item != null) {
            output.accept(super.format(item.getName(), item.getDesc()));
        } else {
            output.accept("Заявка не найдена, укажите верный ID.");
        }
    }
}
