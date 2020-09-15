package org.example.jvm;

/**
 * javap：查阅 Java 字节码
 * javap 是一个能够将 class 文件反汇编成人类可读格式的工具。
 * 默认情况下 javap 会打印所有非私有的字段和方法，当加了 -p 选项后，它还将打印私有的字段和方法。第二个选项是 -v。它尽可能地打印所有信息。
 * 如果你只需要查阅方法对应的字节码，那么可以用 -c 选项来替换 -v。
 * javap 的 -v 选项的输出分为几大块。
 * 1. 基本信息，涵盖了原 class 文件的相关信息。
 *      class 文件的版本号（minor version，major version），该类的访问权限（flags: ACC_PUBLIC, ACC_SUPER），该
 *      类（this_class）以及父类（super_class）的名字，所实现接口（interfaces）、字段（fields）、方法（methods）
 *      以及属性（attributes: 1）的数目。
 * 2. 常量池，用来存放各种常量以及符号引用。
 *      其从1开始计数的，不是从0开始的。
 * 3. 字段区域，用来列举该类中的各个字段。
 *      最主要的信息便是该字段的类型（descriptor: I）以及访问权限（flags: (0x0002) ACC_PRIVATE）。对于声明为 final 的静态字段而言，
 *      如果它是基本类型或者字符串类型，那么字段区域还将包括它的常量值。Java 虚拟机同样使用了“描述符”（descriptor）来描述字段的类型。
 * 4. 方法区域，用来列举该类中的各个方法。
 *      方法描述符、访问权限、代码区域（Code:)【代码区域一开始会声明该方法中的操作数栈（stack=2）和局部变量数目（locals=3）的最大值，以
 *      及该方法接收参数的个数（args_size=1）。注意这里局部变量指的是字节码中的局部变量，而非 Java 程序中的局部变量。】、方法的字节码、
 *      异常表（Exception table:）、行数表（LineNumberTable:）【是 Java 源程序到字节码偏移量的映射。如果你在编译时使用了 -g 参数
 *      （javac -g Foo.java），那么这里还将出现局部变量表（LocalVariableTable:），展示 Java 程序中每个局部变量的名字、类型以及作用
 *      域。】、字节码操作数栈的映射表（StackMapTable: number_of_entries = ？）【该表描述的是字节码跳转后操作数栈的分布情况，一般被
 *      Java 虚拟机用于验证所加载的类，以及即时编译相关的一些操作，正常情况下，你无须深入了解。】
 *
 * OpenJDK 项目 Code Tools：实用小工具集：http://openjdk.java.net/projects/code-tools/
 *      字节码汇编器反汇编器 ASMTools
 *
 * ASM：Java 字节码框架：https://wiki.openjdk.java.net/display/CodeTools/asmtools
 * https://adopt-openjdk.ci.cloudbees.com/view/OpenJDK/job/asmtools/lastSuccessfulBuild/artifact/asmtools-6.0.tar.gz
 * Created by xiaomo on 2020/7/21 14:16
 */
public class _5_Tools {
}
