package ru.job4j.tracker;

import java.util.function.Consumer;

/**
 * Интерфейс для реализации пунктов меню как классов.
 *
 * @author Alexandr Khomichevskiy.
 * @version 1.0.
 */
public interface UserAction {

    /**
     * Уникальный ключ доступа через консоль.
     *
     * @return int ключ.
     */
    int key();

    /**
     * Метод реализация действия пункта меню.
     *
     * @param input   {@link Input}.
     * @param tracker {@link Tracker}.
     */
    void execute(Input input, ITracker tracker, Consumer<String> output);

    /**
     * Описание пункта меню.
     *
     * @return String описание.
     */
    String info();

}
