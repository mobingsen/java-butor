package org.plus;

public class _27_Mod {

    private static final int BASE = 0x7;

    public static void main(String[] args) {
        for (int i = 0; i < BASE; i++) {
            System.out.println((i & BASE) + "\t" + (i % BASE));
        }
    }
}
