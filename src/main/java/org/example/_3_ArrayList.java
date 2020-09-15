package org.example;

import java.util.ArrayList;
import java.util.List;

/**
 * ArrayList其底层就是一个数组，由于数组的创建必须是要制定数组长度来申请连续的内存分配的存储空间的，而数组列表扩容则是依靠数组元素的拷贝
 * 来实现动态扩容。以此如果数据量大时还是指定创建的长度比较好，避免不必要的拷贝操作。
 * ArrayList扩容：
 * 在没有指定其容量进行new时，其elementData大小就是0即是空数组，
 * 在添加第一个元素时会触发扩容，其容量将变为10，即是数组内定的最小容量。
 * 往后再次扩容时，int newCapacity = oldCapacity + (oldCapacity >> 1);从这行代码看出容量变为了原来的1.5倍。
 * Created by xiaomo on 2020/9/9 20:06
 */
public class _3_ArrayList {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(i);
        }
        for (Integer e : list) {
            System.out.println(e);
        }
    }
}
