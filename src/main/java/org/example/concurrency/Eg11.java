package org.example.concurrency;

/**
 * 传统上，我们可以通过synchronized关键字+wait+notify/notifyAll来实现多个线程之间的协调与通信，整个过程都是由jvm来帮助我们实现的；开
 * 发者无需（也是无法）了解底层的实现细节。
 *
 * 从jdk1.5开始，并发包提供了lock，condition（wait与signal/signalAll）来实现多个线程之间的协调与通信，整个过程都是由开发者来控制的，
 * 而且相比于传统方式，更加灵活，功能也更加强大。
 * Created by mbs on 2020/7/7 16:00
 */
public class Eg11 {

}
