package org.example;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Created by mbs on 2020/12/18 13:51
 */
public class _30_TreeSet {

    public static void main(String[] args) {
        Data d1 = new Data(1, "yiyi", 10);
        Data d2 = new Data(2, "erer", 20);
        Data d3 = new Data(3, "sansan", 30);
        Data d4 = new Data(4, "erer", 40);
        // 根据name来实现去重
        Set<Data> dataSet = new TreeSet<>(Comparator.comparing(Data::getName));
        dataSet.add(d1);
        dataSet.add(d2);
        dataSet.add(d3);
        dataSet.add(d4);
        System.out.println(dataSet);
        Map<Data, Integer> map = new TreeMap<>(Comparator.comparing(Data::getAge));
        map.put(d1, 1);
        map.put(d2, 1);
        map.put(d3, 1);
        map.put(d4, 1);
        System.out.println(map);
        Map<Data, Integer> hashMap = new HashMap<>();
        hashMap.put(d1, 1);
        hashMap.put(d2, 1);
        hashMap.put(d3, 1);
        hashMap.put(d4, 1);
        System.out.println(hashMap);
    }
}

class Data {
    private int id;
    private String name;
    private int age;

    public Data(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return this.id + " " + this.name + " " + this.age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
