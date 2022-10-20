package eighth;

import common.Employee;
import common.Manager;
import sixth.Pair;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 通配符类型
 * @author: whb
 * @date: 2022-10-04-09-36
 * @version: 1.0
 */
public class WildCard {
    public static void main(String[] args) {
        System.out.println("=============泛型8.8.1 通配符概念=============");
        Manager mike = new Manager("mike");
        Manager peter = new Manager("peter");
        printBuddies(new Pair<Manager>(mike, peter));

        Pair<Manager> managerPair = new Pair<Manager>(mike, peter);
        Pair<? extends Employee> wirdcardBuddies = managerPair;
        System.out.println(wirdcardBuddies.getFirst());
        System.out.println("extends 只能get，不能set" +
                "\n因为编译器只知道需要 Employee 的某个子类型，但不知道具体是什么类型");
        // wirdcardBuddies.setFirst(new Employee("hello")); //ERROR capture of ? extends Employee

        System.out.println("\n=============泛型8.8.2 通配符的超类型限定=============");
        Pair<Employee> employeePair = new Pair<Employee>(mike, peter);
        System.out.println("Set before: " + employeePair);
        Pair<? super Manager> inheritEmp = employeePair;
        inheritEmp.setFirst(new Manager("dick"));
        Object first = inheritEmp.getFirst();
        System.out.println();
        System.out.println("Set after: " + inheritEmp);

        System.out.println("super 只能set，不能get(get 到的对象为object,所以使用这个对象有很多限制)" +
                "\n因为编译器无法知道 setFirst 方法的具体类型，因此不能接受参数类型为 employee 或 object的方法调用" +
                "\n只能传递 Manger 类型的对象，或者某个子类型对象");

        mike.setBonus(1000.0);
        peter.setBonus(2000.0);
        Manager ly = new Manager("ly", 2500.0);
        Pair<Manager> minmaxPair = new Pair<Manager>();
        System.out.println("Minmax before: " + minmaxPair);
        minmaxBonus(new Manager[]{mike,peter,ly},minmaxPair);
        System.out.println("Minmax after: " + minmaxPair);

        System.out.println("\n=============泛型8.8.3 无限定通配符=============");
        Pair<?> nullPair = new Pair<>(mike,peter);
        Pair testPair = new Pair(mike,peter);
        /**
         * nullPair.setFirst(ly); //ERROR
         * testPair.setFirst(ly); //OK
         * Pair<?> 和 Pair 本质的不同在于：Pair可以用任何 Object 对象调用 Pair 的 setFirst方法
         * 而 Pair<?> 只能给 setFirst 的参数为 null
         */
        testPair.setFirst(ly); //OK
        System.out.println(PairAlg.hasNulls(nullPair));
        nullPair.setFirst(null);
        System.out.println(PairAlg.hasNulls(nullPair));

        System.out.println("\n=============泛型8.8.4 通配符捕获=============");
        System.out.println("通配符不是类型变量，因此，不能在编写代码中使用 \"?\" 作为一种类型");
        /**
         * ? t = p.getFirst() //ERROR
         */
        Pair<Manager> swapPair = new Pair<>(mike,peter);
        System.out.println("Before swap: " + swapPair);
        PairAlg.swap(swapPair);
        System.out.println("After swap: " + swapPair);
        Manager[] swapArray = new Manager[]{mike,peter,ly};
        Pair<Manager> swapArrayPair = new Pair<>();
        minmaxBonus(swapArray,swapArrayPair);
        System.out.println("MinmaxBonus: " + swapArrayPair);
        maxminBonus(swapArray,swapArrayPair);
        System.out.println("MaxminBonus: " + swapArrayPair);
    }

    public static void printBuddies(Pair<? extends Employee> p){
        Employee first = p.getFirst();
        Employee second = p.getSecond();
        System.out.println(first.getName() + " and " + second.getName() + " are buddies");
    }

    /**
     * 获取奖金最高的经理和最低的经理
     * @param a 经理对象
     * @param result 结果
     */
    public static void minmaxBonus(Manager[] a, Pair<? super Manager> result){
        if(a.length == 0) return;
        Manager min = a[0];
        Manager max = a[0];
        for (int i = 1; i < a.length; i++) {
            if(min.getBonus() > a[i].getBonus()){
                min = a[i];
            }
            if(max.getBonus() < a[i].getBonus()){
                max = a[i];
            }
        }
        result.setFirst(min);
        result.setSecond(max);
    }

    /**
     * 获取奖金最低的经理和最高的经理 (使用通配符捕获机制)
     * @param a 经理对象
     * @param result 结果
     */
    public static void maxminBonus(Manager[] a, Pair<? super Manager> result){
        minmaxBonus(a,result);
        PairAlg.swapHelper(result); //通配符捕获机制
    }
}

class PairAlg{
    /**
     * 测试 pair 中是否有 null 的引用
     * @param p
     * @return
     */
    public static boolean hasNulls(Pair<?> p){
        return p.getFirst() == null || p.getSecond() == null;
    }

    /**
     * 交换 pair 中的数据
     * @param p
     */
    public static void swap(Pair<?> p){
        swapHelper(p);
    }

    /**
     * 辅助方法，实现 swap
     * @param p
     * @param <T>
     */
    public static <T> void swapHelper(Pair<T> p){
        T temp = p.getFirst();
        p.setFirst(p.getSecond());
        p.setSecond(temp);
    }
}
