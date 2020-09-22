package org.example.designpatterns;

/**
 * Created by mbs on 2020/9/21 14:34
 */
public class _责任链_ {

    public static void main(String[] args) {
        Worker1 worker1 = new Worker1();
        Worker2 worker2 = new Worker2(worker1);
        Worker3 worker3 = new Worker3(worker2);
        worker3.work();
    }
}

interface Job {
    void work();
}

class Worker1 implements Job {

    @Override
    public void work() {
        System.out.println("worker1 work...");
    }
}

class Worker2 implements Job {

    private final Job job;

    public Worker2(Job job) {
        this.job = job;
    }

    @Override
    public void work() {
        job.work();
        System.out.println("worker2 work...");
    }
}

class Worker3 implements Job {

    private final Job job;

    public Worker3(Job job) {
        this.job = job;
    }

    @Override
    public void work() {
        job.work();
        System.out.println("worker3 work...");
    }
}
