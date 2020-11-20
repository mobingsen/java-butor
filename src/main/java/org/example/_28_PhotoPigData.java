package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

public class _28_PhotoPigData {

    public static void main(String[] args) throws InterruptedException {
        // 一.removeUser方法会报错
        List<UserDsVo> userDsVos = new ArrayList<>();
        Map<Integer, UserDsVo> finalUserDsVoMap = userDsVos.stream()
                .filter(vo -> !vo.getProjectCode().equals("IDT"))
                .collect(Collectors.toMap(UserDsVo::getAccount, Function.identity()));
        // 二.
        //    1.<=50
        //    2.会，volatile这个关键字只是保证了可见性，并不保证操作的原子性。
        //    3.否，提交给线程池的任务是否执行是由计算来调度的。
        //    4.否，就是2和3的共同结果。
        //    5.否，就是2和3的共同结果。
        //    6.对tryFunction方法加synchronized。
        ExecutorService executorService = Executors.newFixedThreadPool(6);
        BlockThread blockThread = new BlockThread();
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            executorService.submit(() -> blockThread.tryFunction(finalI));
        }
        // 三.syncUsers、syncUserDsDto要初始化；dataDsVo.getUserDsVoList().addAll(syncUsers);nullpointexception；SUNC_SER_NUM怎么存的？
        // 四.1.5;2.否，原子性；3.否，提交给线程池的任务是否执行是由计算来调度的；4.否，是，AtomicInteger是原子性操作，底层是通过cas来保证的。
        AtomicInteger atomicInteger = new AtomicInteger(0);
        for (int i = 0; i < 5; i++) {
            executorService.submit(new CustomedThread(i, atomicInteger));
        }
        System.out.println(4 >> 4);
        TimeUnit.SECONDS.sleep(5000);
    }

    static class CustomedThread implements Runnable {
        private final Integer blockObj;
        private final int tag;
        private final AtomicInteger count;

        public CustomedThread(int tag, AtomicInteger count) {
            this.blockObj = tag >> 4;
            this.tag = tag;
            this.count = count;
        }

        @Override
        public void run() {
            synchronized (blockObj) { // blockObj还是同一个对象，Integer还有缓存池这种情况
                int oldCount = count.get();
                int curCount = count.incrementAndGet();
                System.out.println(Thread.currentThread().getId() + String.format("customedthread tag is %d, oldCount is %d, curCount is %d", tag, oldCount, curCount));
            }
        }
    }

    static class UserDsVo {
        private int account;
        private String projectCode;

        public int getAccount() {
            return account;
        }

        public void setAccount(int account) {
            this.account = account;
        }

        public String getProjectCode() {
            return projectCode;
        }

        public void setProjectCode(String projectCode) {
            this.projectCode = projectCode;
        }
    }

    static class BlockThread {
        private volatile int count = 0;

        public void tryFunction(int tag) {
            int cur = 0;
            for (; cur < 10; cur++) {
                count++;
//                if (0 == count % 2) {
                    System.out.println(Thread.currentThread().getId() + String.format("thread %d get slice count: %d, cur: %d", tag, count, cur));
//                }
            }
        }
    }
}
