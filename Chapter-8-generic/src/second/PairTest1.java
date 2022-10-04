package second;

import javax.swing.*;

/**
 * @author: whb
 * @date: 2022-10-01-14-57
 * @version: 1.0
 */
public class PairTest1 {
    public static void main(String[] args) {
        // 8.2定义简单泛型类
        System.out.println("=============8.2定义简单泛型类=============");
        String[] words = {"mary","had","a","little","lamb","zed"};
        Pair<String> mm = ArrayAlg.minmax(words);
        System.out.println(mm);
    }
}

class ArrayAlg{
    /**
     * Gets the minimum and maximum of an array of strings.
     * 获取字符串数组中最大与最小的值
     * @param a 字符串数组
     * @return 包含最大值与最小值的Pair，或者在字符串数组为空的情况下返回null
     */
    public static Pair<String> minmax(String[] a){
        if(a == null || a.length == 0) return null;
        String min = a[0];
        String max = a[0];
        for (int i = 0; i < a.length; i++) {
            if(min.compareTo(a[i]) > 0) min = a[i];
            if(max.compareTo(a[i]) < 0) max = a[i];
        }
        return new Pair<>(min,max);
    }

}
