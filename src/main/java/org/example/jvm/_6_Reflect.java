package org.example.jvm;

/**
 * Java 里的反射机制
 * JAVA反射机制是在运行状态中，对于任意一个类，都能够知道这个类的所有属性和方法；对于任意一个对象，都能够调用它的任意一个方法和属性；这种动
 * 态获取的信息以及动态调用对象的方法的功能称为java语言的反射机制。
 *
 * 方法的反射调用，也就是 Method.invoke,它实际上委派给 MethodAccessor 来处理。MethodAccessor 是一个接口，它有两个已有的具体实现：
 * 一个通过本地方法来实现反射调用，另一个则使用了委派模式。
 * 每个 Method 实例的第一次反射调用都会生成一个委派实现，它所委派的具体实现便是一个本地实现。当进入了 Java 虚拟机内部之后，我们便拥有了
 * Method 实例所指向方法的具体地址。这时候，反射调用无非就是将传入的参数准备好，然后调用进入目标方法。
 *
 * 反射调用先是调用了 Method.invoke，然后进入委派实现（DelegatingMethodAccessorImpl），再然后进入本地实现
 * （NativeMethodAccessorImpl），最后到达目标方法。其实，Java 的反射调用机制还设立了另一种动态生成字节码的实现（下称动态实现），直接使
 * 用 invoke 指令来调用目标方法。之所以采用委派实现，便是为了能够在本地实现以及动态实现中切换。
 *
 * 许多反射调用仅会执行一次，Java 虚拟机设置了一个阈值 15（可以通过 -Dsun.reflect.inflationThreshold= 来调整），当某个反射调用的调用
 * 次数在 15 之下时，采用本地实现；当达到 15 时，便开始动态生成字节码，并将委派实现的委派对象切换至动态实现，这个过程我们称之为 Inflation。
 * 反射调用的 Inflation 机制是可以通过参数（-Dsun.reflect.noInflation=true）来关闭的。这样一来，在反射调用一开始便会直接生成动态实现，
 * 而不会使用委派实现或者本地实现。
 * 以 getMethod 为代表的查找方法操作，会返回查找得到结果的一份拷贝。因此，我们应当避免在热点代码中使用返回 Method 数组的 getMethods 或
 * 者 getDeclaredMethods 方法，以减少不必要的堆空间消耗。
 * 在生产环境中，我们往往拥有多个不同的反射调用，对应多个 GeneratedMethodAccessor，也就是动态实现。
 *
 * 在默认情况下，方法的反射调用为委派实现，委派给本地实现来进行方法调用。在调用超过 15 次之后，委派实现便会将委派对象切换至动态实现。这个动
 * 态实现的字节码是自动生成的，它将直接使用 invoke 指令来调用目标方法。
 * 方法的反射调用会带来不少性能开销，原因主要有三个：变长参数方法导致的 Object 数组，基本类型的自动装箱、拆箱，还有最重要的方法内联。
 *
 * 使用反射 API 的第一步便是获取 Class 对象。在 Java 中常见的有这么三种。
 * 1.使用静态方法 Class.forName 来获取。
 * 2.调用对象的 getClass() 方法。
 * 3.直接用类名 +“.class”访问。对于基本类型来说，它们的包装类型（wrapper classes）拥有一个名为“TYPE”的 final 静态字段，指向该基本类型
 * 对应的 Class 对象。
 *
 * 一旦得到了 Class 对象，我们便可以正式地使用反射功能了。下面我列举了较为常用的几项。
 *
 * 使用 newInstance() 来生成一个该类的实例。它要求该类中拥有一个无参数的构造器。
 *
 * 使用 isInstance(Object) 来判断一个对象是否该类的实例，语法上等同于 instanceof 关键字。
 *
 * 使用 Array.newInstance(Class,int) 来构造该类型的数组。
 *
 * 使用 getFields()/getConstructors()/getMethods() 来访问该类的成员。需要注意的是，方法名中带 Declared 的不会返回父类的成员，但是会
 * 返回私有成员；而不带 Declared 的则相反。
 *
 * 当获得了类成员之后，我们可以进一步做如下操作。
 *
 * 使用 Constructor/Field/Method.setAccessible(true) 来绕开 Java 语言的访问限制。
 * 使用 Constructor.newInstance(Object[]) 来生成该类的实例。
 * 使用 Field.get/set(Object) 来访问字段的值。
 * 使用 Method.invoke(Object, Object[]) 来调用方法。
 *
 * Created by mobingsen on 2020/7/21 16:14
 */
public class _6_Reflect {
}
