package org.plus;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * map is empty
 * orelse
 * ----------------------
 * orelseget
 *
 * map.put(0, new DataObject("0"))
 * 0
 * orelse
 * ----------------------
 * Created by mbs on 2020/12/28 18:34
 */
public class _31_LambdaOrElseAndOrElseGet {

    public static void main(String[] args) {
        Map<Integer, DataObject> map = new HashMap<>();
//        map.put(0, new DataObject("0"));
        final Object o = Optional.ofNullable(map.get(0))
                .orElse(new DataObject("orelse"));
        System.out.println("----------------------");
        final Object o2 = Optional.ofNullable(map.get(0))
                .orElseGet(() -> new DataObject("orelseget"));
    }
}

class DataObject {

    public DataObject(String log) {
        System.out.println(log);
    }
}
