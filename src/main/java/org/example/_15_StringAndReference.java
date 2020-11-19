package org.example;

/**
 * Created by 小墨 on 2020/9/29 17:15
 */
public class _15_StringAndReference {

    public static void main(String[] args) {
        String str = "a";
        Data d = new Data();
        d.setName("c");
        d.setAge(1);
        change(str, d);
        System.out.println(str + ", " + d.getName());
    }

    private static void change(String str, Data d) {
        str += "b";
        d.setName(d.getName() + "d");
    }

    static class Data {
        private String name;
        private int age;

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
}
