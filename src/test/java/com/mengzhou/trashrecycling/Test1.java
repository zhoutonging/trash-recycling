package com.mengzhou.trashrecycling;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author ZHOUTONG
 * @date 2019年09月15日 10:32
 */
public class Test1 {

    public static void main(String[] args) {
        List list = Arrays.asList("141", 212, 3432, 4, 5, 6, 7);

        list.forEach(v -> {
            if (v.equals("141")) {
                System.out.println(v);
            }
        });
    }
}
