package org.example.jvm;

/**
 * 基本数据类型
 * 在 Java 虚拟机规范中，boolean 类型则被映射成 int 类型。具体来说，“true”被映射为整数 1，而“false”被映射为整数 0。
 * boolean、byte、char、short 这四种类型，在栈上占用的空间和 int 是一样的，和引用类型也是一样的。因此，在 32 位的 HotSpot 中，这些类
 * 型在栈上将占用 4 个字节；而在 64 位的 HotSpot 中，他们将占 8 个字节。
 * --------------------------------------------------------------
 * 类型           值域          默认值         虚拟机内部符号
 * boolean    {false,true}     false            Z
 * byte       [-128, 127]        0              B
 * short    [-32768, 32767]      0              S
 * char         [0, 65535]    '\u0000'          C
 * int      [-2^31, 2^31-1]      0              I
 * long     [-2^63, 2^63-1]      0              J
 * float   ~[-3.4E38, 3.4E38]   +0.0F           F
 * double ~[-1.8E308, 1.8E308]  +0.0D           D
 * --------------------------------------------------------------
 * 在这些基本类型中，boolean 和 char 是唯一的无符号类型。
 * 当然，这种情况仅存在于局部变量，而并不会出现在存储于堆中的字段或者数组元素上。对于 byte、char 以及 short 这三种类型的字段或者数组单元，
 * 它们在堆上占用的空间分别为一字节、两字节，以及两字节，也就是说，跟这些类型的值域相吻合。
 * 因此，当我们将一个 int 类型的值，存储到这些类型的字段或数组时，相当于做了一次隐式的掩码操作。举例来说，当我们把 0xFFFFFFFF（-1）存储
 * 到一个声明为 char 类型的字段里时，由于该字段仅占两字节，所以高两位的字节便会被截取掉，最终存入“\uFFFF”。
 * 除 long 和 double 外，其他基本类型与引用类型在解释执行的方法栈帧中占用的大小是一致的，但它们在堆中占用的大小确不同。在将 boolean、
 * byte、char 以及 short 的值存入字段或者数组单元时，Java 虚拟机会进行掩码操作。在读取时，Java 虚拟机则会将其扩展为 int 类型。
 * https://adopt-openjdk.ci.cloudbees.com/view/OpenJDK/job/asmtools/lastSuccessfulBuild/artifact/asmtools-6.0.tar.gz
 * Created by 小墨 on 2020/7/20 16:49
 */
public class _1_BasicType {
}
