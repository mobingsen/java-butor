package org.example.jvm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * java内存泄露怎么定位
 * 一般采用下面的步骤分析：
 * 1. 用工具生成java应用程序的heap dump（如jmap）
 * 2. 使用Java heap分析工具（如MAT），找出内存占用超出预期的嫌疑对象
 * 3. 根据情况，分析嫌疑对象和其他对象的引用关系。
 * 4. 分析程序的源代码，找出嫌疑对象数量过多的原因。
 *
 * ps -ef|grep java 能查看进程的pid等具体信息
 * jmap -histo:live pid | head -7 或者生成离线文件jmap -dump:live,format=b,file=/opt/jvm/dump.hprof pid
 * Memory Analyzer插件下载：http://www.eclipse.org/mat/downloads.php
 *
 * -XX:+HeapDumpOnOutOfMemoryError
 * -XX:HeapDumpPath=/opt/jvmdump
 * Created by mobingsen on 2020/9/28 9:40
 */
public class _11_OutOfMemory {

    public static void main(String[] args) {
        final Map<Integer, Date> map = new HashMap<>();
        for (int i = 0; i < 600000000; i++) {
            map.put(i, new Date());
        }
    }
}
