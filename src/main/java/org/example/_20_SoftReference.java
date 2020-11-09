package org.example;

import java.lang.ref.SoftReference;

/**
 * 软引用(SoftReference)
 * 如果一个对象只具有软引用，则内存空间充足时，垃圾回收器就不会回收它；如果内存空间不足了，就会回收这些对象的内存。只要垃圾回收器没有回收它，该对象就可以被程序使用。
 */
public class _20_SoftReference {

    private static final SoftReference<Integer> reference = new SoftReference<>(100);

    public static void main(String[] args) {
        System.out.println(reference.get());
        System.out.println(reference.get());
    }
}
