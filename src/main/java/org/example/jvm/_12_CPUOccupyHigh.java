package org.example.jvm;

/**
 * linux查找java程序cpu占用最高的线程
 * top -H -p <pid>查看线程占用情况
 * 将线程id转换为十六进制   #printf %x <pid>
 * 然后使用jstack查看线程堆栈信息    jstack <pid> | grep -a 线程id（十六进制）
 * Created by xiaomo on 2020/9/28 10:00
 */
public class _12_CPUOccupyHigh {
}
