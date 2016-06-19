package com.company;

/**
 * Created on 03/04/2016.
 */
public class MultiThreadedArraySearch<T> {
    private T[] arr;
    private SearchListener searchListener;
    private T element;
    private boolean found = false;
    private int doneCounter = 0;

    private static final int NUMBER_OF_THREADS = 2;

    public MultiThreadedArraySearch(T[] arr, SearchListener searchListener, T element) {
        if (arr == null || searchListener == null || element == null)
            return;
        this.arr = arr;
        this.searchListener = searchListener;
        this.element = element;
        SearchThread searchThread1 = new SearchThread(0, arr.length / 2);
        searchThread1.start();
        SearchThread searchThread2 = new SearchThread(arr.length / 2, arr.length);
        searchThread2.start();
    }

    public interface SearchListener {
        void found(int index);
    }

    private class SearchThread extends Thread {
        private int fromIndex, toIndex;

        public SearchThread(int fromIndex, int toIndex) {
            this.fromIndex = fromIndex;
            this.toIndex = toIndex;
        }

        @Override
        public void run() {
            for (int i = fromIndex; !found && i < toIndex; i++) {
                if (MultiThreadedArraySearch.this.arr[i].equals(MultiThreadedArraySearch.this.element)) {
                    boolean f = false;
                    synchronized (this) {
                        found = true;
                        f = true;
                    }
                    if (f)
                        searchListener.found(i);
                    doneCounter = -100;
                    return;
                }
            }
            doneCounter++;
            if (doneCounter == NUMBER_OF_THREADS)
                searchListener.found(-1);
        }
    }
}

