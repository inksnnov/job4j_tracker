package ru.job4j.tracker;

import java.util.List;

/**
 * Реализация интерфейса {@link Input}, для иммитации ввода с консоли.
 *
 * @author Alexandr Khomichevskiy.
 * @version 1.0.
 */
public class StubInput implements Input {

    private final String[] value;

    private int position;

    public StubInput(final String[] value) {
        this.value = value;
    }

    @Override
    public String ask(String question) {
        return this.value[this.position++];
    }

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
