package org.aston;

/**
 * Здесь выполняется проект по созданию своей реализации Array List
 * @author Roman Bai
 */
public class SimpleArrayList<T> {

    private static final int DEF_CAPACITY = 3;
    private T[] initialArray;
    private int position;

    /**
     * Дефолтный конструктор, для создания массива
     */
    public SimpleArrayList() {
            this.initialArray = (T[]) new Object[DEF_CAPACITY];
    }

    /**
     * Конструктор, для создания листа с определённой ёмкостью
     */
    public SimpleArrayList(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Сapacity cannot be <= 0");
        }
            this.initialArray = (T[]) new Object[capacity];

    }

    /**
     * В этом методе мы добавляем новые объекты в наш Массив
     * Сначала используется проверка на то, что массив не переполнен
     * Далее, если массив переполнен, мы создаем новый массив, с увеличенной вместимостью
     * и с помощью цикла for перекладываем туда объекты
     * Если же вместимость нашего массива позволяет, мы добавляем объект в него.
     *
     * @param item - объект который нужно добавить
     */
    public void add(T item) {
            if (position >= initialArray.length) {
                T[] newArray = (T[]) new Object[initialArray.length + 5];
                for (int i = 0; i < initialArray.length; i++) {
                    newArray[i] = initialArray[i];
                }
                newArray[position] = (T) item;
                position++;
                this.initialArray = newArray;
            } else {
                this.initialArray[position++] = (T) item;
            }
    }
    /**
     * В этом методе мы добавляем новые объекты в наш Массив по определенному индексу
     * Сначала используется проверка на то, что массив не переполнен
     * Далее, если массив переполнен, мы создаем новый массив, с увеличенной вместимостью
     * и с помощью цикла for перекладываем туда объекты до нашего индекса.
     * Далее мы присваиваем на месте индекса новый объект
     * И с помощью еще одного цикла, мы перекладываем в новый массив объекты ,
     * находившиеся после индекса.
     * Если же , массив не переполнен, и места в нём хватает, мы создаём новый массив с той же ёмкостью,
     * и повторяем первые пункты.
     *
     * @param index - индекс
     * @param item - элемент, который нужно добавить
     */
    public void add(int index, T item) {
            if (position >= initialArray.length) {
                T[] newArray = (T[]) new Object[initialArray.length + 1];

                for (int i = 0; i < index; i++) {
                    newArray[i] = initialArray[i];
                }
                newArray[index] = (T) item;
                for (int i = index + 1; i <= initialArray.length; i++) {
                    newArray[i] = initialArray[i - 1];
                }
                position++;
                this.initialArray = newArray;

            } else {

                T[] newArray = (T[]) new Object[initialArray.length];
                for (int i = 0; i < index; i++) {
                    newArray[i] = initialArray[i];
                }
                newArray[index] = (T) item;
                for (int i = index + 1; i < initialArray.length; i++) {
                    newArray[i] = initialArray[i - 1];
                }
                position++;
                this.initialArray = newArray;
            }
    }
    /**
     * В этом методе , мы добавляем элементы из одного массива в другой.
     * С помощью создания нового массива, с емкостью сложенной из входящих массивов,
     * мы перекладываем все элементы из первого массива, и во втором цикле перекладываем
     * элементы из второго массива
     * @param items - массив , который нужно добавить
     */
    public void addAll(SimpleArrayList items) {
            T[] newArray = (T[]) new Object[initialArray.length + items.size()];
            for (int i = 0; i < initialArray.length; i++) {
                newArray[i] = initialArray[i];
            }
            for (int i = initialArray.length; i < newArray.length; i++) {
                newArray[position] = (T) items.get(i - initialArray.length);
                position++;
            }
            this.initialArray = newArray;
    }
    /**
     * Этот метод создан для получения объекта из массива по индексу
     * @param index - индекс
     */
    public Object get(int index) {
        return initialArray[index];
    }

    /**
     * Этот метод создан для удаления объекта из массива по индексу
     * Сначала мы создаём новый массив, далее перекладываем в него элементы до индекса,
     * сам элемент в месте индекса не включая. Далее с помощью второго цикло, перекладывааем
     * элементы после индекса
     */
    public void remove(int index) {
        try {
            T[] newArray = (T[]) new Object[initialArray.length - 1];
            for (int i = 0; i < index; i++) {
                newArray[i] = initialArray[i];
            }
            for (int i = index + 1; i < newArray.length; i++) {
                newArray[i - 1] = initialArray[i];
            }
            position--;
            this.initialArray = newArray;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException(e.getMessage());
        }
    }

    /**
     * Этот метод создан для удаления объекта из массива по содержанию
     * Мы также создаём новый массив, и сначала с помощью цикла находим индекс объекта
     * одновременно перекладывая элементы. Как только индекс найден, мы останавливаем цикл
     * Далее мы с помощью второго цикла, кладём все оставшиеся после индекса элементы, не включая
     * элемент, находящийся по найденному индексу
     * @param removedItem - объект , который нужно удалить
     */
    public void remove(T removedItem) {
        try {
            T[] newArray = (T[]) new Object[initialArray.length - 1];
            int index = 0;
            for (int i = 0; i < initialArray.length; i++) {
                if (initialArray[i].equals(removedItem)) {
                    index = i;
                    break;
                }
                newArray[i] = initialArray[i];
            }
            for (int i = index + 1; i < newArray.length; i++) {
                newArray[i - 1] = initialArray[i];
            }
            position--;
            this.initialArray = newArray;
        } catch (NullPointerException e) {
            throw new NullPointerException(e.getMessage());
        }
    }
    /**
     *Этот метод очищает массив , создаётся новый массив с дефолтным объёмом
     */
    public void removeAll() {

            this.initialArray = (T[]) new Object[DEF_CAPACITY];

    }

    /**
     *В этом методе мы заменяем объект по индексу на переданный объект
     * @param index - индекс элемента
     * @param item - объект
     */
    public void replace(int index, T item) {
        try {
        this.initialArray[index] = item;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException(e.getMessage());
        }
    }
    /**
     *В этом методе мы проверяем с помощью цикла, находится ли переданный объект в массиве
     * @param item - объект
     * @return boolean, есть ли в массиве указанный элемент
     */
    public boolean contains(T item) {
        for (T t : initialArray) {
            if (t != null && t.equals(item)) {
                return true;
            }
        }
        return false;
    }
    /**
     *В этом методе мы возвращаем размер нашего массива, включая null
     *  @return size
     */
    public int size() {
        return initialArray.length;
    }

    /**
     *Метод для сортировки. В нём мы вызываем другой, приватный метод, в который передаём переменные
     */
    public void sort() {
        quicksort(0, position - 1);
    }
    /**
     *Далее в этом методе мы проверяем выполнения условия. Если условие не выполняется, идём дальше
     * Создаём переменную, в которой вызываем ещё один метод.
     * Также далее рекурсивно вызываем функцию quick sort
     * Она будет выполняться, пока условие в начале не выполнится
     */
    private void quicksort(int start, int end) {
        if (start >= end) {
            return;
        }
        int part = partition(initialArray, start, end);
        quicksort(start, part - 1);
        quicksort(part + 1, end);
    }
    /**
     *В этом методе мы сначала создаём интерфейс Comparable
     * Далее с помощью цикла мы перекладываем объекты из нашего входящего массива в
     * массив Comparable
     * Далее мы создаём две новых переменных, для выполнения дальнейшего цикла сравнения
     * в цикле while в зависимости от значений сравнения наших объектов , выполняются определённые дейстсвия
     * Далее мы запускаем ещё один метод
     */
    private int partition(T[] array, int start, int end) {
        try {
            Comparable[] arrayComp = new Comparable[initialArray.length];
            for (int i = 0; i < initialArray.length; i++) {
                arrayComp[i] = (Comparable) initialArray[i];
            }
            int i = start + 1;
            int j = end;
            while (i <= j) {
                if (arrayComp[i].compareTo(arrayComp[start]) <= 0) {
                    i++;
                } else if (arrayComp[j].compareTo(arrayComp[start]) > 0) {
                    j--;
                }
            }
            array = (T[]) arrayComp;
            exchange(array, start, j);
            return j;
        } catch (ClassCastException e) {
            throw new ClassCastException(e.getMessage());
        }
    }
    /**
     *В этом методе мы получив необходимые сравнения объектов, перекладываем их по нужным индексам
     */
    private void exchange(T[] array, int i, int j) {
        T tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
        initialArray = array;
    }

    /**
     *В этом методе мы реализуем воспринимаемый для человека вывод данных объектов нашего массива
     */
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (T t : initialArray) {
            if (t != null) {
                stringBuilder.append(t + ", ");
            }
        }
        return stringBuilder.toString();
    }

}
