package com.yjiewei;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class TestParallelStrram {
    public static void main(String[] args) {
        //获取并行流
        Stream<String> parallel = Stream.of("one", "two", "three", "four").parallel();

        Arrays.asList("one", "two", "three", "four").stream().parallel();

    }

    public static long times = 500000000;

    private long start;

    @Before
    public void before(){
        start = System.currentTimeMillis();
    }

    @After
    public void end(){
        long end = System.currentTimeMillis();
        System.out.println(end-start);
    }

    @Test
    public void test01(){
        long res = 0;
        for (long i = 0; i < times; i++) {
            res+=i;
        }
    }

    @Test
    public void test02(){
        OptionalLong reduce = LongStream.rangeClosed(0, times).reduce(Long::sum);
    }

    @Test
    public void test03(){
        OptionalLong reduce = LongStream.rangeClosed(0, times).parallel().reduce(Long::sum);
    }

    @Test
    public void testSyn(){

        //线程问题
        /*ArrayList<Integer> list1 = new ArrayList<>();
        IntStream.rangeClosed(1,1000).parallel().forEach(list1::add);
        System.out.println(list1.size());*/

        //同步锁
        ArrayList<Integer> list2 = new ArrayList<>();
        Object obj = new Object();
        IntStream.rangeClosed(1,1000).parallel().forEach(e->{
            synchronized (obj){
                list2.add(e);
            }
        });
        System.out.println(list2.size());

        //线程安全集合
        Vector<Integer> list3 = new Vector<>();
        IntStream.rangeClosed(1,1000).parallel().forEach(list3::add);
        System.out.println(list3.size());

        //线程不安全容器转线程安全容器
        ArrayList<Integer> list4 = new ArrayList<>();
        List<Integer> integers = Collections.synchronizedList(list4);
        IntStream.rangeClosed(1,1000).parallel().forEach(integers::add);
        System.out.println(integers.size());

        //线程安全
        ArrayList<Integer> list5 = new ArrayList<>();
        List<Integer> collect = IntStream.rangeClosed(1, 1000).parallel().boxed().collect(Collectors.toList());
        System.out.println(collect.size());
    }
}
