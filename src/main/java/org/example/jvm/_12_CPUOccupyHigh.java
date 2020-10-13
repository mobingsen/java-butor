package org.example.jvm;

/**
 * linux查找java程序cpu占用最高的线程
 * top -H -p pid查看线程占用情况
 * 将线程id转换为十六进制   printf '%x\n' pid   得到nid
 * 然后使用jstack查看线程堆栈信息    jstack <pid> | grep -a 线程id（十六进制，比如 '0xnid'）-C5 –color
 * 我们对整个jstack文件进行分析，通常我们会比较关注WAITING和TIMED_WAITING的部分，BLOCKED就不用说了。我们可以使用命令
 * cat jstack.log | grep "java.lang.Thread.State" | sort -nr | uniq -c来对jstack的状态有一个整体的把握，如果WAITING之类的特别
 * 多，那么多半是有问题啦。
 *
 * 当然我们还是会使用jstack来分析问题，但有时候我们可以先确定下gc是不是太频繁，使用jstat -gc pid 1000命令来对gc分代变化情况进行观察，
 * 1000表示采样间隔(ms)，S0C/S1C、S0U/S1U、EC/EU、OC/OU、MC/MU分别代表两个Survivor区、Eden区、老年代、元数据区的容量和使用量。
 * YGC/YGT、FGC/FGCT、GCT则代表YoungGc、FullGc的耗时和次数以及总耗时。如果看到gc比较频繁，再针对gc方面做进一步分析。
 *
 * 针对频繁上下文问题，我们可以使用vmstat命令来进行查看
 * cs(context switch)一列则代表了上下文切换的次数。
 * 如果我们希望对特定的pid进行监控那么可以使用 pidstat -w pid命令，cswch和nvcswch表示自愿及非自愿切换。
 *
 * 参考地址：https://mp.weixin.qq.com/s/L5MqY-eC5OyUdg2PsU6BVw
 * Created by 小墨 on 2020/9/28 10:00
 */
public class _12_CPUOccupyHigh {
}
