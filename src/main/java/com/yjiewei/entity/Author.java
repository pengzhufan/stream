/*
 * @author yjiewei
 * @date 2022/2/13 15:34
 */
package com.yjiewei.entity;

import lombok.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Author implements Comparable<Author>{
    private Long id;
    private String name;
    private String introduction;
    private Integer age;
    private List<Book> bookList;

    public int compareTo(Author o) {
        return o.getAge() - this.getAge();
    }

    /**
     * 使用sorted时比较整个元素时，要实现比较接口，并重写方法
     */
//    @Override
//    public int compareTo(Author o) {
//        // return 0; // 这里是如何比较
//        // 0表示年纪一样大，负数表示传入的大
//        // 这里sorted如果输出的是降序，你就把这俩顺序对换就可以了，不用记忆
//        return o.getAge() - this.getAge();
//    }
}
