package org.example;

import java.awt.desktop.SystemEventListener;

public class Main {
    public static void main(String[] args) {
        String name ="guru";
        stepInto();
        System.out.println("Hello world! "+name);

    }
    public static void stepInto(){
        System.out.println("step into 1");
        stepOver();
        System.out.println("step into 2");
        System.out.println("step into 3");
    }

    public static void stepOver(){
        System.out.println("step into 1");
        System.out.println("step into 2");
        System.out.println("step into 3");
    }
}