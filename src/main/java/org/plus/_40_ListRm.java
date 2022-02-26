package org.plus;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author mbs on 2021/5/25 17:07
 */
public class _40_ListRm {

    public static void main(String[] args) {
        List<Integer> ints = IntStream.range(0, 50).boxed().collect(Collectors.toList());
//        for (Integer itr : ints) {
//            if (itr % 2 == 0) {
//                ints.remove(itr);
//            }
//        }
//        System.out.println(ints);
        final Iterator<Integer> iterator = ints.iterator();
        if (iterator.hasNext()) {
            final Integer itr = iterator.next();
            if (itr % 2 == 0) {
                iterator.remove();
            }
        }
        System.out.println(ints);
    }
}
