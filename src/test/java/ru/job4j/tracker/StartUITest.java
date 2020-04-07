package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.function.Consumer;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author Alexandr Khomichevskiy.
 * @version 1.0.
 * @since 19.03.19.
 */
public class StartUITest {

    private final PrintStream stdout = System.out;

    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    private final Consumer<String> output = new Consumer<String>() {
        private final PrintStream stdout = new PrintStream(out);

        @Override
        public void accept(String s) {
            stdout.println(s);
        }

        @Override
        public String toString() {
            return out.toString();
        }
    };

    @Before
    public void loadOutput() {
        System.setOut(new PrintStream(this.out));
    }

    @After
    public void backOutput() {
        System.setOut(this.stdout);
    }

    @Test
    public void createItemTestOne() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{"0", "testName", "desc", "6"});
        new StartUI(input, tracker, this.output).init();
        assertThat(tracker.findAll().get(0).getName(), is("testName"));
    }

    private String showMenu() {
        String ln = System.lineSeparator();
        return new StringBuilder()
                .append("0 - Добавление заявки.").append(ln)
                .append("1 - Замена заявки.").append(ln)
                .append("2 - Удаление заявки.").append(ln)
                .append("3 - Показать все заявки.").append(ln)
                .append("4 - Поиск заявок по имени.").append(ln)
                .append("5 - Поиск заявки по ID.").append(ln)
                .append("6 - Выход из программы.").append(ln)
                .toString();
    }

    @Test
    public void replaceItemTestOne() {
        Tracker tracker = new Tracker();
        long create = System.currentTimeMillis();
        Item itemOne = new Item("NameOne", "DescOne", create);
        Item itemTwo = new Item("NameTwo", "DescTwo", create);
        tracker.add(itemOne);
        tracker.add(itemTwo);
        Input input = new StubInput(new String[]{"1", itemTwo.getId(), "NameThree", "DescThree", "6"});
        new StartUI(input, tracker, this.output).init();
        assertThat(tracker.findByName("NameThree").get(0).getName(), is("NameThree"));
    }

    @Test
    public void deleteItemOne() {
        Tracker tracker = new Tracker();
        long create = System.currentTimeMillis();
        Item itemOne = new Item("NameOne", "DescOne", create);
        Item itemTwo = new Item("NameTwo", "DescTwo", create);
        tracker.add(itemOne);
        tracker.add(itemTwo);
        Input input = new StubInput(new String[]{"2", itemTwo.getId(), "6"});
        new StartUI(input, tracker, this.output).init();
        assertNull(tracker.findById(itemTwo.getId()));
    }

    @Test
    public void showAllItemTestOne() {
        Tracker tracker = new Tracker();
        long create = System.currentTimeMillis();
        Item itemOne = new Item("NameOne", "DescOne", create);
        Item itemTwo = new Item("NameTwo", "DescTwo", create);
        tracker.add(itemOne);
        tracker.add(itemTwo);
        String ln = System.lineSeparator();
        Input input = new StubInput(new String[]{"3", "6"});
        new StartUI(input, tracker, this.output).init();
        assertThat(this.output.toString(), is(new StringBuilder()
                .append(this.showMenu())
                .append("------------Все заявки------------").append(ln)
                .append("Имя заявки :  NameOne ").append(ln)
                .append("Описание заявки : ").append(ln)
                .append(" DescOne ")
                .append(ln)
                .append(ln)
                .append("Имя заявки :  NameTwo ").append(ln)
                .append("Описание заявки : ").append(ln)
                .append(" DescTwo ")
                .append(ln)
                .append(ln)
                .append(this.showMenu())
                .toString()
        ));
    }

    @Test
    public void findByNameItemsTestOne() {
        Tracker tracker = new Tracker();
        long create = System.currentTimeMillis();
        Item itemOne = new Item("NameOne", "DescOne", create);
        Item itemTwo = new Item("NameTwo", "DescTwo", create);
        tracker.add(itemOne);
        tracker.add(itemTwo);
        String ln = System.lineSeparator();
        Input input = new StubInput(new String[]{"4", "NameOne", "6"});
        new StartUI(input, tracker, this.output).init();
        assertThat(this.output.toString(), is(new StringBuilder()
                .append(this.showMenu())
                .append("Поиск заявок по имени.").append(ln)
                .append("Имя заявки :  NameOne ").append(ln)
                .append("Описание заявки : ").append(ln)
                .append(" DescOne ")
                .append(ln)
                .append(ln)
                .append(this.showMenu())
                .toString()
        ));
    }

    @Test
    public void findByIdItemsTestOne() {
        Tracker tracker = new Tracker();
        long create = System.currentTimeMillis();
        Item itemOne = new Item("NameOne", "DescOne", create);
        Item itemTwo = new Item("NameTwo", "DescTwo", create);
        tracker.add(itemOne);
        tracker.add(itemTwo);
        String ln = System.lineSeparator();
        Input input = new StubInput(new String[]{"5", itemTwo.getId(), "6"});
        new StartUI(input, tracker, this.output).init();
        assertThat(this.output.toString(), is(new StringBuilder()
                .append(this.showMenu())
                .append("Поиск заявки по ID").append(ln)
                .append("Имя заявки :  NameTwo ").append(ln)
                .append("Описание заявки : ").append(ln)
                .append(" DescTwo ")
                .append(ln)
                .append(ln)
                .append(this.showMenu())
                .toString()
        ));
    }
}
