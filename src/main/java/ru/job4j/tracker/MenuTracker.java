package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Класс для работы с объектами меню.
 *
 * @author Alexandr Khomichevskiy.
 * @version 1.0.
 */
public class MenuTracker {

    /**
     * Получение данных от пользователя.
     */
    private Input input;

    /**
     * Хранение и обработка заявок.
     */
    private ITracker tracker;

    /**
     * Хранение объектов меню.
     */
    private List<UserAction> actions = new ArrayList<>();

    private final Consumer<String> output;

    /**
     * Инициализация полей input, tracker.
     *
     * @param input   {@link MenuTracker#input}.
     * @param tracker {@link MenuTracker#tracker}.
     */
    public MenuTracker(Input input, ITracker tracker, Consumer<String> output) {
        this.input = input;
        this.tracker = tracker;
        this.output = output;
    }

    /**
     * Метод возвращает количество пунктов в меню.
     *
     * @return List список пунктов меню.
     */
    public List<Integer> getMenuRange() {
        List<Integer> ranges = new ArrayList<>();
        for (int i = 0; i < this.actions.size(); i++) {
            ranges.add(i);
        }
        return ranges;
    }

    /**
     * Формирование списка объектов меню.
     *
     * @param ui {@link StartUI}.
     */
    public void fillActions(StartUI ui) {
        this.actions.add(new AddItem(0, "Добавление заявки."));
        this.actions.add(new ReplaceItem(1, "Замена заявки."));
        this.actions.add(new DeleteItem(2, "Удаление заявки."));
        this.actions.add(new ShowAllItem(3, "Показать все заявки."));
        this.actions.add(new FindByNameItems(4, "Поиск заявок по имени."));
        this.actions.add(new FindByIdItem(5, "Поиск заявки по ID."));
        this.actions.add(new ExitProgram(6, "Выход из программы.", ui));
    }

    /**
     * Выполнение действия выбранным объектом меню.
     *
     * @param key int выбранный пункт меню пользоваетелем.
     */
    public void select(int key) {
        this.actions.get(key).execute(this.input, this.tracker, this.output);
    }

    /**
     * Метод показывает пользователю меню из списка {@link MenuTracker#actions}.
     */
    public void show() {
        for (UserAction action : this.actions) {
            if (action != null) {
                System.out.println(action.info());
            }
        }
    }
}
