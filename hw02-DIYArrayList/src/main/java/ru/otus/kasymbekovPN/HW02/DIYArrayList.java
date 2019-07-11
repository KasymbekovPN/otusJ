package ru.otus.kasymbekovPN.HW02;

//<
//import jdk.jshell.spi.ExecutionControl;

import java.util.*;

public class DIYArrayList<T> extends AbstractList<T> implements List<T>, RandomAccess
{

    //<
//    static public void main(String... args)
//    {
//        System.out.println("hello1");
//
//        List<Integer> l = new DIYArrayList<>();
//        l.add(12);
//
//        System.out.println(l);
//
////        Collections.addAll()
//    }

    @Override
    public boolean add(T t)
    {
        throw new UnsupportedOperationException("add");
    }

    @Override
    public void add(int index, T element)
    {
        throw new UnsupportedOperationException("add");
    }

    @Override
    public int size()
    {
        throw new UnsupportedOperationException("size");
    }

    @Override
    public boolean isEmpty()
    {
        throw new UnsupportedOperationException("isEmpty");
    }

    @Override
    public boolean contains(Object o)
    {
        throw new UnsupportedOperationException("contains");
    }

    @Override
    public Iterator<T> iterator()
    {
        throw new UnsupportedOperationException("iterator");
    }

    @Override
    public Object[] toArray()
    {
        throw new UnsupportedOperationException("toArray");
    }

    @Override
    public <T1> T1[] toArray(T1[] a)
    {
        throw new UnsupportedOperationException("toArray");
    }

    @Override
    public boolean remove(Object o)
    {
        throw new UnsupportedOperationException("remove");
    }

    @Override
    public boolean containsAll(Collection<?> c)
    {
        throw new UnsupportedOperationException("containsAll");
    }

    @Override
    public boolean addAll(Collection<? extends T> c)
    {
        throw new UnsupportedOperationException("addAll");
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c)
    {
        throw new UnsupportedOperationException("addAll");
    }

    @Override
    public boolean removeAll(Collection<?> c)
    {
        throw new UnsupportedOperationException("removeAll");
    }

    @Override
    public boolean retainAll(Collection<?> c)
    {
        throw new UnsupportedOperationException("retainAll");
    }

    @Override
    public void clear()
    {
        throw new UnsupportedOperationException("clear");
    }

    @Override
    public T get(int index)
    {
        throw new UnsupportedOperationException("get");
    }

    @Override
    public T set(int index, T element)
    {
        throw new UnsupportedOperationException("set");
    }

    @Override
    public T remove(int index)
    {
        throw new UnsupportedOperationException("remove");
    }

    @Override
    public int indexOf(Object o)
    {
        throw new UnsupportedOperationException("indexOf");
    }

    @Override
    public int lastIndexOf(Object o)
    {
        throw new UnsupportedOperationException("lastIndexOf");
    }

    @Override
    public ListIterator<T> listIterator()
    {
        throw new UnsupportedOperationException("listIterator");
    }

    @Override
    public ListIterator<T> listIterator(int index)
    {
        throw new UnsupportedOperationException("listIterator");
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex)
    {
        throw new UnsupportedOperationException("subList");
    }
}
