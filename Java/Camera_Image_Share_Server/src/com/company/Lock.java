package com.company;

/**
 * Created by resin on 26/06/2016.
 */
public class Lock {
    public int imageVersion;
    public boolean lock;

    public Lock() {
        imageVersion = 1;
        lock = false;
    }
}
