package org.plus.jvm;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class _10_LoadSeq {

    private static final Map<Character, Character> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        new Data();
        new Data();
    }
}

class Data extends Parent {

    public Data() {
        System.out.println("data constructor");
    }

    static {
        System.out.println("data static block");
    }

    {
        System.out.println("data block");
    }

    public void doJob() {
        System.out.println("data do job");
    }
}

class Parent {

    public Parent() {
        System.out.println("Parent constructor");
    }

    static {
        System.out.println("Parent static block");
    }

    {
        System.out.println("Parent block");
    }

    public void doJob() {
        System.out.println("Parent do job");
    }
}
