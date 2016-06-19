package com.company;

/**
 * Created on 19/03/2016.
 */
public class MyArrayGeneric<T> implements GenericCollection<T> {
    private T[] _numArray;
    private int _arrSize;

    public MyArrayGeneric() {
        _arrSize = 0;
        _numArray =(T[]) new Object [5];
    }

    @Override
    public void add(T x) {
        add(x, _arrSize);
    }

    @Override
    public void add(T x, int index) {
        if (index < 0)
            throw new IndexOutOfBoundsException("index " + index + " is out of bounds");
        if (index > _arrSize)
            index = _arrSize;
        if (_arrSize == _numArray.length) {
            T[] tempArr = (T[]) new Object[_arrSize * 2];
            for (int i = 0; i < index; i++) {
                tempArr[i] = _numArray[i];
            }
            for (int i = index; i < _arrSize; i++) {
                tempArr[i + 1] = _numArray[i];
            }
            _numArray = tempArr;
        } else {
            for (int i = _arrSize; i > index; i--) {
                _numArray[i] = _numArray[i - 1];
            }
        }
        _arrSize++;
        _numArray[index] = x;
    }

    @Override
    public void add(GenericCollection<T> genericCollection) {
        if (genericCollection == null)
            throw new NullPointerException();
        for (int i = 0; i < genericCollection.size(); i++) {
            this.add(genericCollection.get(i));
        }
    }

    @Override
    public void set(T x, int index) {
        if (index < 0 || index >=_arrSize)
            throw new IndexOutOfBoundsException("index " + index + "is out of bounds");
        _numArray[index]= x;
    }

    @Override
    public void clear() {
        _arrSize = 0;
    }

    @Override
    public boolean contains(T x) {
        return indexOf(x) != -1;
    }

    @Override
    public T get(int index) {
        if (index >= _arrSize || index < 0) {
            throw new IndexOutOfBoundsException("index " + index + " is out of bounds");
        }
        return _numArray[index];
    }

    @Override
    public int indexOf(T x) {
        for (int i = 0; i < _arrSize; i++) {
            if (_numArray[i] == x)
                return i;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(T x) {
        for (int i = _arrSize - 1; i >= 0; i--) {
            if (_numArray[i] == x)
                return i;
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        return _arrSize == 0;
    }

    @Override
    public void remove(T x) {
        int index = indexOf(x);
        if (index == -1)
            return;
        for (int i = index; i < _arrSize - 1; i++) {
            _numArray[i] = _numArray[i + 1];
        }
        _arrSize--;
    }

    @Override
    public void removeAll(T x) {
        //while (contains(x))
        //    remove(x);
        int count = 0, place = 0;
        for (int i = 0; i < _arrSize; i++) {
            if (_numArray[i] != x)
                _numArray[place++] = _numArray[i];
            else
                count++;
        }
        _arrSize -= count;
    }

    @Override
    public int size() {
        return _arrSize;
    }

    @Override
    public GenericCollection<T> subList(int fromIndex, int toIndex) {
        GenericCollection<T> tempCollection = new MyArrayGeneric();
        if (fromIndex > 0 && fromIndex < toIndex && toIndex < _arrSize) {
            for (int i = fromIndex; i < toIndex; i++) {
                tempCollection.add(this.get(i));
            }
        }
        return tempCollection;
    }

    @Override
    public T[] toArray() {
        T[] tempArr =(T[]) new Object[_arrSize];
        for (int i = 0; i < _arrSize; i++) {
            tempArr[i] = _numArray[i];
        }
        return tempArr;
    }

    @Override
    public String toString() {
        String str = "";
        str += "Array(";
        for (int i = 0; i < _arrSize; i++) {
            if (i < _arrSize - 1)
                str += _numArray[i] + ",";
            else
                str += _numArray[i] + ")";
        }
        return str;
    }
}
