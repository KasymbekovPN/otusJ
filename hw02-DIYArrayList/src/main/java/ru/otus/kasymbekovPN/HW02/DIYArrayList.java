package ru.otus.kasymbekovPN.HW02;

import java.util.*;
import java.util.function.Consumer;

public class DIYArrayList<T> implements List<T> {
    //region private members
    //region private final members
    private static final int DEFAULT_CAPACITY = 21;
    private static final Object[] EMPTY_DATA = {};
    private static final Object[] DEFAULT_CAPACITY_EMPTY_DATA = {};
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
    //endregion
    //region private transient members
    private transient Object[] data;
    private transient int modCount = 0;
    //endregion
    //region other
    private int size;
    //endregion
    //endregion

    //region constructors
    public DIYArrayList(int initCapacity) {
        if (0 == initCapacity) {
            this.data = EMPTY_DATA;
        } else if (0 < initCapacity) {
            this.data = new Object[initCapacity];
        } else {
            throw new IllegalArgumentException("Illegal Capacity (less than zero) : " + initCapacity);
        }
    }

    public DIYArrayList() {
        this.data = DEFAULT_CAPACITY_EMPTY_DATA;
    }

    public DIYArrayList(Collection<? extends T> initData) {
        this.data = initData.toArray();
        if(0 != (size = this.data.length)) {
            // https://bugs.openjdk.java.net/browse/JDK-6260652
            if (data.getClass() != Object[].class) {
                data = Arrays.copyOf(data, size, Object[].class);
            }
        } else {
            this.data = EMPTY_DATA;
        }
    }
    //endregion

    //region implemented methods

    //region public
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("DIYArray[");
        if (data != null) {
            for(int i = 0; i < data.length; i++) {
                sb.append(
                        i + " : " +
                        (data[i] == null ? "null" : data[i].toString()) +
                        (i == data.length - 1 ? ' ' : ", ")
                );
            }
            sb.append("]\nsize = " + size);
        }
        return sb.toString();
    }

    @Override
    public boolean add(T t) {
        add(size(), t);
        return true;
    }

    @Override
    public void add(int index, T element) {
        rangeCheckForAdd(index);
        final int s;
        Object[] data;
        if ((s = size) == (data = this.data).length) {
            data = grow();
        }
        System.arraycopy(data, index, data, index + 1, s - index);
        data[index] = element;
        size = s + 1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(data, size);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T1> T1[] toArray(T1[] array) {
        if (array.length < size) {
            return (T1[]) Arrays.copyOf(data, size, array.getClass());
        }
        System.arraycopy(data, 0, array, 0, size);
        if (array.length > size) {
            array[size] = null;
        }

        return array;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return data(index);
    }

    @Override
    public T set(int index, T element) {
        Objects.checkIndex(index, size);
        T oldVal = data(index);
        data[index] = element;
        return oldVal;
    }

    @Override
    public ListIterator<T> listIterator() {
        return new ListItr(0);
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return new ListItr(index);
    }
    //endregion

    //region private

    //region not static
    private Object[] grow() {
        return grow(size + 1);
    }

    private Object[] grow(int minCap) {
        return data = Arrays.copyOf(data, newCapacity(minCap));
    }

    private int newCapacity(int minCap) {
        int oldCap = data.length;
        int newCap = oldCap + (oldCap >> 1);
        if (newCap <= minCap) {
            if (data == DEFAULT_CAPACITY_EMPTY_DATA) {
                return Math.max(DEFAULT_CAPACITY, minCap);
            }
            if (0 > minCap) {
                throw new OutOfMemoryError();
            }

            return minCap;
        }

        return newCap <= MAX_ARRAY_SIZE
                ? newCap
                : hugeCapacity(minCap);
    }

    @SuppressWarnings("unchecked")
    private T data(int index) {
        return (T) data[index];
    }

    private void rangeCheckForAdd(int index) {
        if (0 > index || index > size()) {
            throw new IndexOutOfBoundsException("Index : " + index + ", size : " + size);
        }
    }
    //endregion

    //region static
    @SuppressWarnings("unchecked")
    static private <T> T elementAt(Object[] d, int index) {
        return (T) d[index];
    }

    static private int hugeCapacity(int minCap) {

        if (0 > minCap) {
            throw new OutOfMemoryError();
        }

        return minCap > MAX_ARRAY_SIZE
                ? Integer.MAX_VALUE
                : MAX_ARRAY_SIZE;
    }
    //endregion

    //endregion

    //endregion

    //region not implemented methods
    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("isEmpty");
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException("contains");
    }

    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException("iterator");
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("remove");
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException("containsAll");
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        throw new UnsupportedOperationException("addAll");
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        throw new UnsupportedOperationException("addAll");
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("removeAll");
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("retainAll");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("clear");
    }

    @Override
    public T remove(int index) {
        throw new UnsupportedOperationException("remove");
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException("indexOf");
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException("lastIndexOf");
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("subList");
    }

    //endregion

    //region inner classes
    private class Itr implements Iterator<T> {
        int cursor;
        int lastRet = -1;
        int expectedModCount = modCount;

        Itr() {}

        public boolean hasNext() {
            return cursor != size;
        }

        @SuppressWarnings("unchecked")
        public T next() {
            checkForComodification();
            int i = cursor;
            if (i >= size) {
                throw new NoSuchElementException();
            }

            Object[] data = DIYArrayList.this.data;
            if (i >= data.length) {
                throw new ConcurrentModificationException();
            }

            cursor = i + 1;
            return (T) data[lastRet = i];
        }

        public void remove() {
            if (0 > lastRet) {
                throw new IllegalStateException();
            }
            checkForComodification();

            try {
                DIYArrayList.this.remove(lastRet);
                cursor = lastRet;
                lastRet = -1;
                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

        @Override
        public void forEachRemaining(Consumer<? super T> action) {
            Objects.requireNonNull(action);
            final int size = DIYArrayList.this.size;
            int i = cursor;
            if (i < size) {
                final Object[] d = data;
                if (i >= d.length) {
                    throw new ConcurrentModificationException();
                }
                for( ; i < size && modCount == expectedModCount; i++) {
                    action.accept(elementAt(d, i));
                }

                cursor = i;
                lastRet = i - 1;
                checkForComodification();
            }
        }

        final void checkForComodification() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }
    }

    private class ListItr extends Itr implements ListIterator<T> {
        ListItr(int index) {
            super();
            cursor = index;
        }

        public boolean hasPrevious() {
            return cursor != 0;
        }

        public int nextIndex() {
            return cursor;
        }

        public int previousIndex() {
            return cursor - 1;
        }

        @SuppressWarnings("unchecked")
        public T previous() {
            checkForComodification();

            int i = cursor - 1;
            if (i < 0) {
                throw new NoSuchElementException();
            }

            Object[] data = DIYArrayList.this.data;
            if (i >= data.length) {
                throw new ConcurrentModificationException();
            }

            cursor = i;
            return (T) data[lastRet = i];
        }

        public void set(T e) {
            if (lastRet < 0) {
                throw new IllegalStateException();
            }

            checkForComodification();

            try {
                DIYArrayList.this.set(lastRet, e);
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

        public void add(T e) {
            checkForComodification();

            try {
                int i = cursor;
                DIYArrayList.this.add(i, e);
                cursor = i + 1;
                lastRet = -1;
                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }
    }
    //endregion
}
