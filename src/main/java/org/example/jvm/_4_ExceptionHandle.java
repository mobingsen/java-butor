package org.example.jvm;

/**
 * 异常处理的两大组成要素是抛出异常和捕获异常。这两大要素共同实现程序控制流的非正常转移。
 * 抛出异常可分为显式和隐式两种。显式抛异常的主体是应用程序，它指的是在程序中使用“throw”关键字，手动将异常实例抛出。
 * 隐式抛异常的主体则是 Java 虚拟机，它指的是 Java 虚拟机在执行过程中，碰到无法继续执行的异常状态，自动抛出异常。
 *
 * 所有异常都是 Throwable 类或者其子类的实例。Throwable 有两大直接子类。第一个是 Error，涵盖程序不应捕获的异常。当程序触发 Error 时，
 * 它的执行状态已经无法恢复，需要中止线程甚至是中止虚拟机。第二子类则是 Exception，涵盖程序可能需要捕获并且处理的异常。
 *
 * 在编译生成的字节码中，每个方法都附带一个异常表。异常表中的每一个条目代表一个异常处理器，并且由 from 指针、to 指针、target 指针以及所
 * 捕获的异常类型构成。这些指针的值是字节码索引（bytecode index，bci），用以定位字节码。
 *
 * try-catch-finally 代码块
 * Java 编译器会生成一个或多个异常表条目，监控整个 try-catch 代码块，并且捕获所有种类的异常（在 javap 中以 any 指代）。这些异常表条目的
 * target 指针将指向另一份复制的 finally 代码块。并且，在这个 finally 代码块的最后，Java 编译器会重新抛出所捕获的异常。
 * 当前版本 Java 编译器的做法，是复制 finally 代码块的内容，分别放在 try-catch 代码块所有正常执行路径以及异常执行路径的出口中。
 * 编译结果包含三份 finally 代码块。其中，前两份分别位于 try 代码块和 catch 代码块的正常执行路径出口。最后一份则作为异常处理器，监控try
 * 代码块以及 catch 代码块。它将捕获 try 代码块触发的、未被 catch 代码块捕获的异常，以及 catch 代码块触发的异常。
 *
 * Java 7 专门构造了一个名为 try-with-resources 的语法糖，在字节码层面自动使用 Supressed 异常。当然，该语法糖的主要目的并不是使用
 * Supressed 异常，而是精简资源打开关闭的用法。
 * Java 7 的 try-with-resources 语法糖，极大地简化了上述代码。程序可以在 try 关键字后声明并实例化实现了 AutoCloseable 接口的类，编译
 * 器将自动添加对应的 close() 操作。在声明多个 AutoCloseable 实例的情况下，编译生成的字节码类似于上面手工编写代码的编译结果。与手工代码
 * 相比，try-with-resources 还会使用 Supressed 异常的功能，来避免原异常“被消失”。
 *
 * Created by xiaomo on 2020/7/21 10:54
 */
public class _4_ExceptionHandle {
}
