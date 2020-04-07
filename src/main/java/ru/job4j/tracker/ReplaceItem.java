package ru.job4j.tracker;

import java.util.function.Consumer;

/**
 * Класс - редактирование заявки.
 *
 * @author Alexandr Khomichevskiy.
 * @version 1.0.
 */
public class ReplaceItem extends BaseAction {

    /**
     * Конструктор инициализирует поля key и name.
     *
     * @param key  номер пункта меню.
     * @param name Описание пункта меню для вывода в консоль.
     */
    public ReplaceItem(int key, String name) {
        super(key, name);
    }

    /**
     * Реализация редактирование существующей заявки.
     *
     * @param input   {@link Input}.
     * @param tracker {@link Tracker}
     */
    @Override
    public void execute(Input input, ITracker tracker, Consumer<String> output) {
        output.accept("-------------Редактирование заявки------------");
        String id = input.ask("Введите ID редактируемой заявки :");
        String name = input.ask("Введите новое имя заявки.");
        String desc = input.ask("Введите новое описание к заявке.");
        Item item = new Item(name, desc, System.currentTimeMillis());
        if (tracker.replace(id, item)) {
            output.accept("Заявка успешно отредактирована.");
        } else {
            output.accept("Заявка не отредактирована, попробуйте снова.");
        }
    }
}
