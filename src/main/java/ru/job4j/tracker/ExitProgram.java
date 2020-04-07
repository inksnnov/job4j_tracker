package ru.job4j.tracker;

import java.util.function.Consumer;

/**
 * Реализация класса выхода из программы.
 *
 * @author Alexandr Khomichevskiy.
 * @version 1.0.
 */
public class ExitProgram extends BaseAction {

    /**
     * Поле объекта {@link StartUI} для вызова метода {@link StartUI#exit()}.
     */
    private final StartUI ui;

    /**
     * Конструктор передает объект {@link StartUI} для реализации выхода из программы.
     * Инициализирует поля key и name.
     *
     * @param key  номер пункта меню.
     * @param name описание пункта меню.
     * @param ui   {@link StartUI}.
     */
    public ExitProgram(int key, String name, StartUI ui) {
        super(key, name);
        this.ui = ui;
    }

    /**
     * Выход из программы.
     *
     * @param input   {@link Input}.
     * @param tracker {@link Tracker}.
     */
    @Override
    public void execute(Input input, ITracker tracker, Consumer<String> output) {
        this.ui.exit();
    }
}
