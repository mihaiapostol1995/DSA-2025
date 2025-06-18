package org.example;

public class Extension {
    public static void main(String[] args) {
        var child = new Child();
        child.doA();
    }
}

class Parent {
    void doA() {
        System.out.println("Parent doA");
    }
}

class Child extends Parent {

//    @Override
//    void doA() {
//        super.doA();
//    }
}