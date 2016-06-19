package com.company;

public class Main {

    public static void main(String[] args) {
        Giraffe g1 = new Giraffe();
        Giraffe g2 = new Giraffe();
        Elephant e1 = new Elephant();
        //Kayak k = new Kayak(g1, g2);
        //Elephant e = (Elephant)k.getFront();

        Kayak<Giraffe> kayak = new Kayak<>(g1, g2);

    }
}
/*

class Kayak {
    Object front;
    Object rear;

    public Kayak(Object front, Object rear) {
        this.front = front;
        this.rear = rear;
    }

    public Object getFront() {
        return front;
    }

    public void setFront(Object front) {
        this.front = front;
    }

    public Object getRear() {
        return rear;
    }

    public void setRear(Object rear) {
        this.rear = rear;
    }
}
*/

class Kayak<T> {
    private T front;
    private T rear;

    public Kayak(T front, T rear) {
        this.front = front;
        this.rear = rear;
    }

    public T getFront() {
        return front;
    }

    public void setFront(T front) {
        this.front = front;
    }

    public T getRear() {
        return rear;
    }

    public void setRear(T rear) {
        this.rear = rear;
    }
}

class Giraffe {

}

class Elephant {

}