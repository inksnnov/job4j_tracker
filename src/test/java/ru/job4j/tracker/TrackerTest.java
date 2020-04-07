package ru.job4j.tracker;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author Alexandr Khomichevskiy.
 * @version 1.0.
 * @since 13.03.19.
 */
public class TrackerTest {

    @Test
    public void addTestOne() {
        Tracker tracker = new Tracker();
        long create = System.currentTimeMillis();
        Item item = new Item("NameTest", "TestDesc", create);
        tracker.add(item);
        Item result = tracker.findById(item.getId());
        assertThat(result.getName(), is(item.getName()));
    }

    @Test
    public void replaceTestOne() {
        Tracker tracker = new Tracker();
        long create = System.currentTimeMillis();
        Item itemOne = new Item("ItemOneName", "TestDescOne", create);
        Item itemTwo = new Item("ItemTwoName", "TestDescTwo", create);
        Item itemThree = new Item("ItemThreeName", "TestDescThree", create);
        tracker.add(itemOne);
        tracker.add(itemTwo);
        tracker.replace(itemTwo.getId(), itemThree);
        assertThat(tracker.findById(itemThree.getId()), is(itemThree));
    }

    @Test
    public void deleteTestOne() {
        Tracker tracker = new Tracker();
        long create = System.currentTimeMillis();
        Item itemOne = new Item("TestName", "TestDesc", create);
        Item itemTwo = new Item("NameTwo", "DescTwo", create);
        tracker.add(itemOne);
        tracker.add(itemTwo);
        tracker.delete(itemTwo.getId());
        assertNull(tracker.findById(itemTwo.getId()));
    }

    @Test
    public void findAllTestOne() {
        Tracker tracker = new Tracker();
        long create = System.currentTimeMillis();
        Item itemOne = new Item("NameOne", "DescOne", create);
        Item itemTwo = new Item("NameTwo", "DescTwo", create);
        List<Item> testArr = new ArrayList<>(Arrays.asList(itemOne, itemTwo));
        tracker.add(itemOne);
        tracker.add(itemTwo);
        List<Item> result = tracker.findAll();
        assertTrue(result.containsAll(testArr));

    }

    @Test
    public void findByNameTestOne() {
        Tracker tracker = new Tracker();
        long create = System.currentTimeMillis();
        Item itemOne = new Item("NameOne", "DescOne", create);
        Item itemTwo = new Item("NameTwo", "DescTwo", create);
        Item itemThree = new Item("NameThree", "Three", create);
        Item itemFour = new Item("NameThree", "Three", create);
        Item itemFive = new Item("NameThree", "Three", create);
        tracker.add(itemOne);
        tracker.add(itemTwo);
        tracker.add(itemThree);
        tracker.add(itemFour);
        tracker.add(itemFive);
        List<Item> result = tracker.findByName(itemThree.getName());
        List<Item> expect = new ArrayList<>(Arrays.asList(itemThree, itemFour, itemFive));
        assertTrue(result.containsAll(expect));
    }

    @Test
    public void findByIdTestOne() {
        Tracker tracker = new Tracker();
        long create = System.currentTimeMillis();
        Item itemOne = new Item("NameOne", "DescOne", create);
        Item itemTwo = new Item("NameTwo", "DescTwo", create);
        tracker.add(itemOne);
        tracker.add(itemTwo);
        Item result = tracker.findById(itemTwo.getId());
        assertThat(itemTwo, is(result));
    }

}
