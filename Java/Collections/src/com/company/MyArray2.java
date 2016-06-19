package com.company;

/**
 * Created on 20/03/2016.
 */
public class MyArray2 implements Collection {

    private Link _anchor;
    private int _size;

    public MyArray2() {
        _anchor = new Link(123);
        _size = 0;
    }

    @Override
    public void add(int x) {
        Link last = _anchor;
        while (last.next != null)
            last = last.next;
        last.next = new Link(x);
        _size++;
    }

    @Override
    public void add(int x, int index) {
        if (index < 0)
            throw new IndexOutOfBoundsException("index " + index + " is out of bounds");
        Link theOneBefore = _anchor;
        int counter = 0;
        while (theOneBefore.next != null && counter++ < index)
            theOneBefore = theOneBefore.next;
        Link added = new Link(x);
        added.next = theOneBefore.next;
        theOneBefore.next = added;
        _size++;
    }

    @Override
    public void add(Collection collections) {
        if (collections == null)
            throw new NullPointerException();
        for (int i = 0; i < collections.size(); i++) {
            this.add(collections.get(i));
        }
    }

    @Override
    public void set(int x, int index) {
        if (index < 0 || index >=_size)
            throw new IndexOutOfBoundsException("index " + index + " is out of bounds");
        Link theOneBefore = _anchor;
        int counter = 0;
        while (theOneBefore.next != null && counter++ < index)
            theOneBefore = theOneBefore.next;
        Link added = new Link(x);
        added.next = theOneBefore.next.next;
        theOneBefore.next = added;
    }

    @Override
    public void clear() {
        _anchor.next = null;
        _size = 0;
    }

    @Override
    public boolean contains(int x) {
        return indexOf(x) != -1;
    }

    @Override
    public int get(int index) {
        if (index < 0 || index >=_size)
            throw new IndexOutOfBoundsException("index " + index + "is out of bounds");
        Link link = _anchor;
        int counter = 0;
        while (link.next != null && counter++ <= index) {
            link = link.next;
        }
        return link.value;
    }

    @Override
    public int indexOf(int x) {
        Link link = _anchor;
        int counter = 0;
        while (link.next != null) {
            link = link.next;
            if (link.value == x)
                return counter;
            counter++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(int x) {
        Link link = _anchor;
        int counter = 0, index = -1;
        while (link.next != null) {
            link = link.next;
            if (link.value == x)
                index = counter;
            counter++;
        }
        return index;
    }

    @Override
    public boolean isEmpty() {
        return _anchor.next == null;
    }

    @Override
    public void remove(int x) {
        Link theOneBefore = _anchor;
        int counter = 0;
        while (theOneBefore.next != null && counter++ < _size-1) {
            theOneBefore = theOneBefore.next;
            if (theOneBefore.next.value ==x){
                theOneBefore.next = theOneBefore.next.next;
                _size--;
                return;
            }
        }
    }

    @Override
    public void removeAll(int x) {
        Link theOneBefore = _anchor;
        int counter = 0;
        while (theOneBefore.next != null && counter++ < _size-1) {
            theOneBefore = theOneBefore.next;
            if (theOneBefore.next.value == x){
                theOneBefore.next = theOneBefore.next.next;
                _size--;
            }
        }
    }

    @Override
    public int size() {
        return _size;
    }

    @Override
    public Collection subList(int fromIndex, int toIndex) {
        MyArray2 tempCollection = new MyArray2();
        if (fromIndex > 0 && fromIndex < toIndex && toIndex < _size) {
            for (int i = fromIndex; i < toIndex; i++) {
                tempCollection.add(this.get(i));
            }
        }
        return tempCollection;
    }

    @Override
    public int[] toArray() {
        int[] tempArr = new int[_size];
        Link l = _anchor;
        int counter = 0;
        while (l.next != null) {
            l = l.next;
            tempArr[counter++] = l.value;
        }
        return tempArr;
    }

    @Override
    public String toString() {
        String str = "";
        str += "Array(";
        Link last = _anchor;
        while (last.next != null) {
            last = last.next;
            str += last.value+",";
        }
        str+=")";
        return str;
    }

    public static class Link {

        public Link(int value) {
            this.value = value;
        }

        private int value;
        private Link next;
    }
}
