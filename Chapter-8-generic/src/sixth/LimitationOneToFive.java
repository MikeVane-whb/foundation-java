package sixth;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 泛型的限制与局限性
 * @author: whb
 * @date: 2022-10-02-01-13
 * @version: 1.0
 */
public class LimitationOneToFive {
    public static void main(String[] args) {
        // 泛型8.6.1 不能用基本类型实例化类型参数
        System.out.println("=============泛型8.6.1 不能用基本类型实例化类型参数=============");
        // new Pair<int>(); ERROR

        // 泛型8.6.2 运行时类型查询只适用于原始类型
        System.out.println("=============泛型8.6.2 运行时类型查询只适用于原始类型=============");
        Pair a = new Pair<>();
        // if(a instanceof Pair<String>) ERROR
        Pair<String> p = (Pair<String>) a; // 注意，只能测试 a 是 Pair 类型

        // getClass总是返回原始类型
        Pair<String> stringPair = new Pair<>();
        Pair<Integer> intPair = new Pair<>();
        System.out.println("stringPair.getClass() == intPair.getClass()");
        System.out.println(stringPair.getClass() == intPair.getClass());

        System.out.println("=============泛型8.6.3 不能创建参数化类型的数组=============");
        // new Pair<String>[10]; ERROR
        // 因为类型擦除的原因，其实初始化泛型数组时，所有的对象都会被擦除变成object(没有限定类型的情况下)
        // 那么就会出现存储类型错误

        System.out.println("=============泛型8.6.4 Varargs警告=============");
        Collection<Pair<String>> collection = new ArrayList<>();
        Pair<String> pair1 = new Pair<>("hello","world");
        Pair<String> pair2 = new Pair<>("haha","none");
        addAll(collection,pair1,pair2);
        System.out.println(collection);

        System.out.println("=============泛型8.6.5 不能实例化类型变量=============");
        // public Pair(){ first = new T(); second = new T(); } ERROR
        Pair<String> pair3 = Pair.makePair(String::new);
        System.out.println(pair3);
        Pair<ArrayList> pair4 = Pair.makePair(ArrayList.class);
        System.out.println(pair4);
    }

    @SafeVarargs
    //@SuppressWarnings("unchecked")
    public static <T> void addAll(Collection<T> coll, T... ts){
        for(T t : ts){
            coll.add(t);
        }
    }
}
