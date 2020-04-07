package ru.job4j.tracker;

import java.util.function.Consumer;

/**
 * Класс удаления заявки.
 *
 * @author Alexandr Khomichevskiy.
 * @version 1.0.
 */
public class DeleteItem extends BaseAction {

    /**
     * Конструктор инициализирует поля key и name.
     *
     * @param key  номер пункта меню.
     * @param name Описание пункта меню для вывода в консоль.
     */
    public DeleteItem(int key, String name) {
        super(key, name);
    }

    /**
     * Реализация удаления заявки.
     *
     * @param input   {@link Input}.
     * @param tracker {@link Tracker}
     */
    @Override
    public void execute(Input input, ITracker tracker, Consumer<String> output) {
        output.accept("------------Удаление заявки------------");
        String id = input.ask("Введите ID удаляемой заявки.");
        if (tracker.delete(id)) {
            output.accept("Заявка удалена.");
        } else {
            output.accept("Заявка не удалена, попробуйте снова.");
        }
    }
}
