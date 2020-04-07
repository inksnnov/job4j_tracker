package ru.job4j.tracker;

/**
 * Генерация исключения на основе {@link RuntimeException} для работы с пунктами меню
 * при пользовательском вводе.
 *
 * @author Alexandr Khomichevskiy.
 * @version 1.0.
 */
public class MenuOutException extends RuntimeException {

    public MenuOutException() {
    }
    /**
     * Переопределяем конструктор класса {@link RuntimeException}.
     *
     * @param msg String сообщение об иключении.
     */
    public MenuOutException(String msg) {
        super(msg);
    }
}
