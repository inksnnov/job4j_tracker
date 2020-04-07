package ru.job4j.tracker;

/**
 * Абстрактный класс частично реализует интерфейс {@link UserAction},
 *
 * @author Alexandr Khomichevskiy.
 * @version 1.0.
 */
public abstract class BaseAction implements UserAction {

    /**
     * Хранит номер пункта меню.
     */
    private final int key;

    /**
     * Хранит описание пункта меню.
     */
    private final String name;

    /**
     * Конструктор инициализации полей key и name.
     *
     * @param key  номер пункта меню.
     * @param name описание пункта меню.
     */
    public BaseAction(final int key, final String name) {
        this.key = key;
        this.name = name;
    }

    /**
     * Возвращает номер пункта меню.
     *
     * @return пункт меню.
     */
    @Override
    public int key() {
        return this.key;
    }

    /**
     * Возвращает описание пункта меню.
     *
     * @return описание пункта меню.
     */
    @Override
    public String info() {
        return String.format("%s - %s", this.key, this.name);
    }

    /**
     * Вывод в консоль имени и описания заявки в форматированном виде.
     *
     * @param name {@link Item#getName()}.
     * @param desc {@link Item#getDesc()}.
     */
    String format(String name, String desc) {
        String ln = System.lineSeparator();
        StringBuilder result = new StringBuilder()
                .append(String.format("%s %s %s", "Имя заявки : ", name, ln))
                .append(String.format("%s %s %s %s", "Описание заявки :", ln, desc, ln));
        return result.toString();
    }
}
