import org.aston.SimpleArrayList;
import org.aston.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SimpleArrayListTest {
    private SimpleArrayList<String> stringList;
    private SimpleArrayList<Integer> integerList;

    @BeforeEach
    public void setup() {
        stringList = new SimpleArrayList<>();
        integerList = new SimpleArrayList<>();
    }

    @Test
    public void SimpleArrayListWithCapacity(){
        SimpleArrayList simpleArrayList = new SimpleArrayList<>(6);
        Assertions.assertEquals(6,simpleArrayList.size());
    }

    @Test
    public void SimpleArrayList(){
        SimpleArrayList simpleArrayList = new SimpleArrayList<>();
        Assertions.assertEquals(3,simpleArrayList.size());
    }

    @Test
    public void add() {
        stringList.add("первый");
        stringList.add("второй");
        stringList.add("третий");
        stringList.add("четвертый");
        stringList.add("пятый");
        stringList.add(0, "нулевой");

        Assertions.assertEquals("нулевой, первый, второй, третий, четвертый, пятый, ", stringList.toString());
        Assertions.assertEquals(8, stringList.size());
        Assertions.assertEquals(3, integerList.size());
    }
    @Test
    public void addToIndex() {
        stringList.add(0, "нулевой");
        stringList.add(1, "нулевой");
        stringList.add(1, "нулевой");
        stringList.add(2, "нулевой");
        stringList.add(1, "нулевой");
        Assertions.assertEquals(5, stringList.size());
    }



    @Test
    public void addAll() {
        SimpleArrayList<Integer> integerList2 = new SimpleArrayList<>();
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);
        integerList.add(4);
        integerList.add(5);
        integerList.add(5, 6);

        integerList2.add(0, 7);
        integerList2.add(1, 8);

        integerList.addAll(integerList2);

        Assertions.assertEquals("1, 2, 3, 4, 5, 6, 7, 8, ", integerList.toString());
    }

    @Test
    public void get() {
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);
        integerList.add(4);
        integerList.add(5);
        integerList.add(5, 6);
        String result = integerList.get(4).toString();

        Assertions.assertEquals("5", result);
    }

    @Test
    public void remove() {
        stringList.add("первый");
        stringList.add("второй");
        stringList.add("четвертый");
        stringList.add("пятый");
        stringList.remove(3);
        Assertions.assertEquals("первый, второй, четвертый, ", stringList.toString());

        stringList.remove("второй");
        Assertions.assertEquals("первый, четвертый, ", stringList.toString());
    }

    @Test
    public void removeAll() {
        stringList.add("первый");
        stringList.add("второй");
        stringList.add("четвертый");
        stringList.add("пятый");
        stringList.removeAll();
        Assertions.assertEquals("", stringList.toString());
    }

    @Test
    public void replace() {
        stringList.add("первый");
        stringList.add("второй");
        stringList.add("третий");
        stringList.replace(2, "четвертый");
        Assertions.assertEquals("первый, второй, четвертый, ", stringList.toString());
    }

    @Test
    public void contains() {
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);
        integerList.add(4);
        integerList.add(5);
        Assertions.assertEquals(false, integerList.contains(6));
        Assertions.assertEquals(true, integerList.contains(3));
    }

    @Test
    public void size() {
       for (int i = 0; i< 10000; i++){
           integerList.add(i);
       }
        for (int i = 0; i< 10000; i++){
            stringList.add(i + "string");
        }
        Assertions.assertEquals(10003, integerList.size());
        Assertions.assertEquals(10003, stringList.size());
    }

    @Test
    public void sort() {
        integerList.add(1);
        integerList.add(4);
        integerList.add(4);
        integerList.add(3);
        integerList.add(8);
        integerList.sort();
        Assertions.assertEquals("1, 3, 4, 4, 8, ", integerList.toString());

        stringList.add("первый");
        stringList.add("второй");
        stringList.add("третий");
        stringList.add("пятый");
        stringList.sort();
        Assertions.assertEquals("второй, первый, пятый, третий, ", stringList.toString());
    }



    @Test
    public void testMethodSimpleArrayListIllegalArgumentException() {
        boolean exceptionThrown = false;
        try {
            SimpleArrayList arrayList = new SimpleArrayList<>(-4);
        } catch (IllegalArgumentException e) {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
    }


    @Test
    public void testMethodThrowsException() {
        boolean exceptionThrown = false;
        User user = new User("Петя", 20);
        User user2 = new User("Петя", 15);
        SimpleArrayList<User> userSimpleArrayList = new SimpleArrayList<>();
        try {
            userSimpleArrayList.add(user);
            userSimpleArrayList.add(user2);
            userSimpleArrayList.sort();
        } catch (ClassCastException e) {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
    }

    @Test
    public void testMethodArrayIndexOutOfBoundsException() {
        boolean exceptionThrown = false;
        stringList.add("Петя");
        stringList.add("Вася");
        try {
           stringList.remove(4);
        } catch (ArrayIndexOutOfBoundsException e) {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
    }

    @Test
    public void testMethodNullPointerException() {
        boolean exceptionThrown = false;
        stringList.add("Петя");
        stringList.add("Вася");
        try {
            stringList.remove("4");
        } catch (NullPointerException e) {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
    }

    @Test
    public void testMethodReplaceArrayIndexOutOfBoundsException() {
        boolean exceptionThrown = false;
        stringList.add("Петя");
        stringList.add("Вася");
        try {
            stringList.replace(4, "Виктор");
        } catch (ArrayIndexOutOfBoundsException e) {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
    }


}
