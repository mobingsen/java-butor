package org.plus.agent.collector.init;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定義註解
 * 可以查看下  ElementType 定義
 * https://blog.csdn.net/xcy1193068639/article/details/81464165
 *
 * @author mobingsen
 */
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.FIELD})
public @interface NotProguard {
}

/**
 * java中元注解(用来标识注解的注解)有四个： @Retention @Target @Document @Inherited；
 * <p>
 * 　　 @Retention：注解的保留位置
 * <p>
 * 　　　　@Retention(RetentionPolicy.SOURCE)   //注解仅存在于源码中，在class字节码文件中不包含
 * <p>
 * 　　　　@Retention(RetentionPolicy.CLASS)     // 默认的保留策略，注解会在class字节码文件中存在，但运行时无法获得，
 * <p>
 * 　　　　@Retention(RetentionPolicy.RUNTIME)  // 注解会在class字节码文件中存在，在运行时可以通过反射获取到
 * <p>
 *
 * <p>
 * 　　@Target:注解的作用目标
 * <p>
 * 　　　　@Target(ElementType.TYPE)   //接口、类、枚举、注解
 * <p>
 * 　　　　@Target(ElementType.FIELD) //字段、枚举的常量
 * <p>
 * 　　　　@Target(ElementType.METHOD) //方法
 * <p>
 * 　　　　@Target(ElementType.PARAMETER) //方法参数
 * <p>
 * 　　　　@Target(ElementType.CONSTRUCTOR)  //构造函数
 * <p>
 * 　　　　@Target(ElementType.LOCAL_VARIABLE)//局部变量
 * <p>
 * 　　　　@Target(ElementType.ANNOTATION_TYPE)//注解
 * <p>
 * 　　　　@Target(ElementType.PACKAGE) ///包
 *
 * @Document：说明该注解将被包含在javadoc中 　  @Inherited：说明子类可以继承父类中的该注解
 */
