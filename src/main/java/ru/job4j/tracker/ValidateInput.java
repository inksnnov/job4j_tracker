package ru.job4j.tracker;

import java.util.List;

/**
 * Декоратор пользовательского ввода с обработкой исключений.
 *
 * @author Alexandr Khomichevskiy.
 * @version 1.0.
 */
public class ValidateInput implements Input {

    /**
     * Объект типа Input.
     */
    private Input input;

    /**
     * Конструктор принимает объект Input и записывает в поле input;
     *
     * @param input Input.
     */
    public ValidateInput(Input input) {
        this.input = input;
    }

    /**
     * Переопределенный и перегруженный метод ask.Для консольного ввода.
     *
     * @param question задаем вопрос пользователю.
     * @return пользовательский ввод.
     */
    @Override
    public String ask(String question) {
        return this.input.ask(question);
    }

    /**
     * Переопределенный и перегруженный метод пользовательского ввода с обработкой исключений
     * при неверном вводе.Служит для корректного выбора пункта меню.
     *
     * @param question String Вопрос пользователю.
     * @param range    List список элементов меню.
     * @return int выбранный пользователем ключ меню.
     */
    @Override
    public int ask(String question, List<Integer> range) {
        boolean invalid = true;
        int value = -1;
        do {
            try {
                value = this.input.ask(question, range);
                invalid = false;
            } catch (MenuOutException moe) {
                System.out.println("Введите пункт меню из диапазона значений.");
            } catch (NumberFormatException nfe) {
                System.out.println("Введите корректное числовое значение.");
            }
        } while (invalid);
        return value;
    }
}
