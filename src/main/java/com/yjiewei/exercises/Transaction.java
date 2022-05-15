package com.yjiewei.exercises;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Transaction {

    private Trader trader;//交易员姓名

    private int year;//交易年份

    private int value;//交易金额
}
