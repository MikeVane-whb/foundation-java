package fourth;

import second.Pair;

import java.time.LocalDate;

/**
 * @author: whb
 * @date: 2022-10-01-15-33
 * @version: 1.0
 */
public class ArrayAlgTest2 {
    public static void main(String[] args) {
        // 泛型8.4 类型变量的限定
        System.out.println("=============泛型8.4 类型变量的限定=============");
        String[] s = {"add","better","open","sit"};
        String min = ArrayAlg.min(s);
        System.out.println(min);

        System.out.println("===========重写泛型方法================");
        LocalDate[] birthdays = {
                LocalDate.of(1906,12,9),
                LocalDate.of(1815,12,10),
                LocalDate.of(1903,12,3),
                LocalDate.of(1910,6,22),
        };
        Pair<LocalDate> mm = ArrayAlg.minmax(birthdays);
        System.out.println(mm);
    }
}

class ArrayAlg{

    /**
     * 计算数组中最小的元素
     * @param a
     * @return
     * @param <T> 这里必须要实现 Comparable 接口，因为不知道 T 所属的类是否有 compareTo 方法
     */
    public static <T extends Comparable> T min(T[] a){
        if (a == null || a.length == 0) return null;
        T smallest = a[0];
        for (int i = 1; i < a.length; i++) {
            if(smallest.compareTo(a[i]) > 0){
                smallest = a[i];
            }
        }
        return smallest;
    }

    /**
     * 使用泛型方法重写 PairTest 中的 minmax 方法
     * Gets the minimum and maximum of an array of objects.
     * 获取数组中最大与最小的值
     * @param a 数组
     * @return 包含最大值与最小值的Pair，或者在数组为空的情况下返回null
     */
    public static <T extends Comparable> Pair<T> minmax(T[] a){
        if(a == null || a.length == 0) return null;
        T min = a[0];
        T max = a[0];
        for (int i = 0; i < a.length; i++) {
            if(min.compareTo(a[i]) > 0) min = a[i];
            if(max.compareTo(a[i]) < 0) max = a[i];
        }
        return new Pair<>(min,max);
    }
}
