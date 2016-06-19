package com.example.myfirstandroidapp;

import java.io.Serializable;

public class Dog implements Serializable{
    private String _name;
    private int _age;

    public Dog(String _name, int _age) {
        this._name = _name;
        this._age = _age;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public int get_age() {
        return _age;
    }

    public void set_age(int _age) {
        this._age = _age;
    }
}
