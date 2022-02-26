package org.plus.jvm;

/**
 *
 * 在 Java 中，方法调用会被编译为 invokestatic，invokespecial，invokevirtual 以及 invokeinterface 四种指令。
 * 在这四种调用指令中，Java 虚拟机明确要求方法调用需要提供目标方法的类名。在这种体系下，我们有两个解决方案:直接调用和反射机制。这两种方法
 * 都相当复杂，执行效率也可想而知。为了解决这个问题，Java 7 引入了一条新的指令 invokedynamic。该指令的调用机制抽象出调用点这一个概念，
 * 并允许应用程序将调用点链接至任意符合条件的方法上。
 * 作为 invokedynamic 的准备工作，Java 7 引入了更加底层、更加灵活的方法抽象 ：方法句柄（MethodHandle）。
 *
 *
 * Created by mobingsen on 2020/7/21 17:21
 */
public class _7_InvokeDynamic {
}
