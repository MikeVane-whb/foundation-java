package fifth;

import second.Pair;

/**
 * @author: whb
 * @date: 2022-10-01-16-42
 * @version: 1.0
 */
public class Fifth {
    public static void main(String[] args) {

        System.out.println("=============泛型8.5.2 转换泛型表达式=============");

        Pair<Employee> buddies = new Pair<>();
        Employee first = buddies.getFirst();
        /**
         * 自动强转
         * 这里其实被转化成两条指令
         * 1. 对原始方法 pair.getFirst 的调用
         * 2、 将返回的 object 类型转换为 Employee 类型
         */

        // 当访问一个泛型字段时也要插入强制类型转换
        // Employee buddy = buddies.first;
    }
}

class Employee{}
