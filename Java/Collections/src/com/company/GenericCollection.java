package com.company;

/**
 * Created on 20/03/2016.
 */
public interface GenericCollection <T> {
    void add(T x);
    void add(T x, int index);
    void add(GenericCollection<T> genericCollection);
    void set(T x, int index);
    void clear();
    boolean contains(T x);
    T get(int index);

    /**
     * finds an element in the collection
     * @param x
     * @return the position of the element in the collection
     * if the element doesn't exist in the collection, -1 is returned.
     * if the element exists multiple times, the first one's position is returned
     */
    int indexOf(T x);
    int lastIndexOf(T x);
    boolean isEmpty();

    /**
     * remove the first instance of x from the collection
     * @param x
     */
    void remove(T x);
    void removeAll(T x);
    int size();
    GenericCollection<T> subList(int fromIndex, int toIndex);

    /**
     * remember that you may NOT instantiate an object of type T. Instead,create
     * an object of type Object and downcast it to T.
     * T[] tempArr = (T[]) new T[_arrSize];
     * @return
     */
    T[] toArray();
}
