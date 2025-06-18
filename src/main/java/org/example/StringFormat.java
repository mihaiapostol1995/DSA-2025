package org.example;

public class StringFormat {
    public static void main(String[] args) {
        var s1 = "hello";
        var s2 = s1.substring(0, s1.length())
                .substring(0, s1.length())
                .indent(2)
                .strip()
                .substring(0, s1.length());
        System.out.println(s1 == s2); //false
        System.out.println(s1.equals(s2)); //true
        System.out.println(s1 == s2.intern());
    }
}

class A {
    Object method1() {
        return new Object();
    }
}

class B extends A {
    @Override
    Number method1() {
        return 1;
    }
}
