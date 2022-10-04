package seventh;

import second.Pair;

import java.util.ArrayList;

/**
 * 继承规则
 * @author: whb
 * @date: 2022-10-03-16-59
 * @version: 1.0
 */
public class InheritRule {
    public static void main(String[] args) {

        System.out.println("=============泛型8.7 泛型类型的继承规则=============");

        /**
         * Manager[] topHonchos = ...
         * Pair<Employee> result = ArrayAlg.minmax(hopHonchos); // ERROR
         * Manager 与 Employee 没有关系
         */

        /**
         *
         * Manager ceo = null;
         * Manager cfo = null;
         * Pair<Manager> managerPair = new Pair<Manager>(ceo, cfo);
         * Pair<Employee> employeePair = managerPair;
         */

        System.out.println("泛型与 Java 数组的一个重要区别：" +
                "\nJava 数组可以将 Manager[] 数组赋值给一个类型为 Employee 的变量");
        Manager[] managers = {};
        Employee[] employees = managers;
        System.out.println("继承规则：");
        System.out.println("总是可以将参数化类型转换成一个原始类型");

        /**
         * var managerBuddies = new Pair<Manager><ceo,cfo>;
         * Pair rawBuddies = managerBuddies;
         * rawBuddies.setFirst(new File("...")); // only a compile-time warning
         */

        System.out.println("最后，泛型类可以拓展或实现其他的泛型类");
        ArrayList<Employee> arrayList = new ArrayList<>();
    }

    static class ArrayAlg {

        /**
         * 计算数组中最小的元素
         *
         * @param a
         * @param <T> 这里必须要实现 Comparable 接口，因为不知道 T 所属的类是否有 compareTo 方法
         * @return
         */
        public static <T extends Comparable> T min(T[] a) {
            if (a == null || a.length == 0) return null;
            T smallest = a[0];
            for (int i = 1; i < a.length; i++) {
                if (smallest.compareTo(a[i]) > 0) {
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

}
