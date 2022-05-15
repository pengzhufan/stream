package com.yjiewei.exercises;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;

public class Exercise {

    public static void main(String[] args) {
        Trader a = new Trader("李四", "北京");
        Trader b = new Trader("张三", "上海");
        Trader c = new Trader("王五", "广州");
        Trader d = new Trader("赵六", "上海");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(a, 2011, 300),
                new Transaction(b, 2012, 1000),
                new Transaction(b, 2011, 400),
                new Transaction(c, 2012, 710),
                new Transaction(d, 2012, 700),
                new Transaction(d, 2012, 950)
        );
        //1.找出2011年发生的所有交易，并按交易额从小到大排序，并输出
        // 2.交易员在哪些不同的城市工作过，并输出
        // 3.查找所有来自上海的交易员，并按姓名排序
        // 4.返回所有交易员的姓名字符串，并按字母顺序排序 如 张三李四
        // 5.有没有交易员在北京工作的？　　　　
        // 6.打印生活在上海的交易员的所有交易额
        // 7.所有交易中，最高的交易额是多少


        System.out.println();
        System.out.println("第一题答案");
        transactions.stream().filter(e->e.getYear()==2011).sorted(Comparator.comparingInt(Transaction::getValue)).forEach(System.out::println);

        System.out.println();
        System.out.println("第二题答案");
        transactions.stream().map(e->e.getTrader().getCity()).distinct().forEach(System.out::println);

        System.out.println();
        System.out.println("第三题答案");
        transactions.stream().map(e->e.getTrader()).filter(e->e.getCity().equals("上海")).sorted(comparing(Trader::getName)).distinct().forEach(System.out::println);

        System.out.println();
        System.out.println("第四题答案");
        String reduce = transactions.stream().map(e -> e.getTrader().getName()).sorted().distinct().reduce("", (n1, n2) -> n1 + n2);
        System.out.println(reduce);

        System.out.println();
        System.out.println("第五题答案");
        boolean anyMatch = transactions.stream().anyMatch(transaction -> transaction.getTrader().getCity().equals("北京"));
        System.out.println("有没有在北京工作的："+anyMatch);

        System.out.println();
        System.out.println("第六题答案");
        Integer sum = transactions.stream().filter(e -> e.getTrader().getCity().equals("上海")).map(Transaction::getValue).reduce(0, (integer, integer2) -> integer + integer2);
        System.out.println(sum);

        System.out.println();
        System.out.println("第七题答案");
        Optional<Integer> first = transactions.stream().map(Transaction::getValue).sorted((o1, o2) -> o2-o1).findFirst();
        first.ifPresent(System.out::println);
    }
}