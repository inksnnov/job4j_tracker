package ru.job4j.tracker;

import java.util.List;

/**
 * Интерфейс пользовательского ввода и иммитации ввода для тестов.
 *
 * @author Alexandr Khomichevskiy.
 * @version 1.0.
 */
public interface Input {

    /**
     * Ввод с консоли пользователем.
     *
     * @param question задаем вопрос пользователю.
     * @return возвращаем ввод.
     */
    String ask(String question);

    /**
     * Перегрузка метода ask для проверки корректности вводимых значений.
     *
     * @param question Задаем вопрос пользователю.
     * @param range    массив пунктов меню.
     * @return возвращаем ввод.
     */
    int ask(String question, List<Integer> range);
}
