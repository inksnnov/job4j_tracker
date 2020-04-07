package ru.job4j.tracker;

import java.util.List;
import java.util.Scanner;

/**
 * Класс пользовательского ввода.
 *
 * @author Alexandr Khomichevskiy.
 * @version 1.0.
 */
public class ConsoleInput implements Input {

    /**
     * Создаем объект ввода с консоли.
     */
    private Scanner scanner = new Scanner(System.in);

    /**
     * Главный метод - принимает значения из консоли, задает пользователю вопросы.
     *
     * @param question String задать вопрос перед вводом.
     * @return String ввод пользователя.
     */
    @Override
    public String ask(String question) {
        System.out.print(question);
        return scanner.nextLine();
    }

    /**
     * Перегруженный метод ask.
     *
     * @param question String вопрос пользователю.
     * @param range    List передаем массив элементов меню.
     * @return int полученное значение пункта меню от пользователя.
     */
    @Override
    public int ask(String question, List<Integer> range) {
        int key = Integer.parseInt(this.ask(question));
        boolean exist = false;
        for (int value : range) {
            if (value == key) {
                exist = true;
                break;
            }
        }
        if (!exist) {
            throw new MenuOutException();
        }
        return key;
    }
}
