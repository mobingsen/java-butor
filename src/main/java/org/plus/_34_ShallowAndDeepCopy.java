package org.plus;

/**
 * 深浅拷贝
 * 对象浅拷贝只是对基本类型的属性值进行拷贝，对引用不进行拷贝。
 *      拷贝方式：实现Cloneable接口，调用clone方法进行拷贝。
 * 深拷贝对基本类型进行拷贝，引用对象的属性值也进行拷贝生成新的对象引用。
 *      拷贝方式：1.通过json序列化来实现；2.通过输入输出流来实现。
 * Created by mbs on 2021/1/20 11:15
 */
public class _34_ShallowAndDeepCopy {

    public static void main(String[] args) throws CloneNotSupportedException {
        Copy copy = Copy.newInstance(1, "zhang san", new Student(1, "lisi"));
        Copy clone = copy.clone();
        System.out.println(copy.hashCode() + ", " + clone.getCopy().hashCode());
    }

    static class Copy implements Cloneable {
        private int id;
        private String name;
        private Student student;
        private Copy copy;

        @Override
        protected Copy clone() throws CloneNotSupportedException {
            return (Copy) super.clone();
        }

        public static Copy newInstance(int id, String name, Student student) {
            Copy copy = new Copy(id, name, student);
            copy.setCopy(copy);
            return copy;
        }

        public Copy(int id, String name, Student student) {
            this.id = id;
            this.name = name;
            this.student = student;
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

        public Student getStudent() {
            return student;
        }

        public void setStudent(Student student) {
            this.student = student;
        }

        public Copy getCopy() {
            return copy;
        }

        public void setCopy(Copy copy) {
            this.copy = copy;
        }
    }

    static class Student {

        private int id;
        private String name;

        public Student(int id, String name) {
            this.id = id;
            this.name = name;
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
    }
}
