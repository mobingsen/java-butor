package org.example.solutions;

/**
 * 自增id的实现解决方案：（MySQL innodb）
 *
 * 1.利用数据库的自增主键id来实现。优点：不用自己管理和维护这个id；缺点：无法自己灵活控制。
 * 2.自主控制维护new AtomicLong（'SELECT MAX(ID) FROM TABLE_NAME'）.优点：可以自己灵活控制和维护；缺点：服务启动时需要查询数据库。
 * 3.雪花算法。优点：不用自己维护；缺点：无法解决该服务器时间导致id唯一性，生成的id过大导致浪费，id不连续递增等。
 *
 * 参考地址：https://mp.weixin.qq.com/s/-yyMpwjIEL9jySMzYNs2ew
 *
 * Created by xiaomo on 2020/9/28 10:05
 */
public class _1_GeneratorId {
}
