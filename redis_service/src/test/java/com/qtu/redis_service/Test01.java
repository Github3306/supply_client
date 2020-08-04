package com.qtu.redis_service;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author Hu Shengkai
 * @create 2020-01-14 22:08
 */
public class Test01 {

    @Test
    public void test9() {
        List<Date> list = new ArrayList<>();
        list.add(new Date());
        Object o = list;
        List<Object> ll = Collections.singletonList(list);
        Object o1 = ll.get(0);
        System.out.println(o1.getClass());
    }
}
