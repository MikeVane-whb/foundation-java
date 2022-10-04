package sixth;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.IntFunction;

/**
 * @author: whb
 * @date: 2022-10-02-11-32
 * @version: 1.0
 */
public class LimitationSixToTen {
    public static void main(String[] args) {
        System.out.println("=============泛型8.6.6 不能构造泛型数组=============");
        String[] minmax1 = minmax((IntFunction<String[]>) String[]::new, "Tom", "Dick", "Harry");
        String[] minmax2 = minmax("Mike", "Peter", "Zed");
        System.out.println(Arrays.toString(minmax1));
        System.out.println(Arrays.toString(minmax2));

        System.out.println("=============泛型8.6.7 泛型类的静态上下文中类型变量无效=============");
        System.out.println("不能在静态字段或方法中引用类型变量");

        System.out.println("=============泛型8.6.8 不能抛出或捕获泛型类的实例=============");
        System.out.println("1. 既不能抛出也不能捕获泛型类的对象 ");
        System.out.println("2. catch 子句中不能使用类型变量");
        System.out.println("3. 在异常规范中使用类型变量是允许的");

        System.out.println("=============泛型8.6.9 可以取消对检查性异常(checked exception)的检查=============");
        Thread thread = new Thread(Task.asRunnable(() -> {
            Thread.sleep(1000);
            System.out.println("hello world");
            throw new Exception("这里不需要捕获异常");
        }));
        thread.start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // 这里需要抛出异常，而上面取消了对检查类异常的检查
                    Thread.sleep(1000);
                    System.out.println("hello world");
                    throw new RuntimeException("这里需要捕获异常");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        System.out.println("=============泛型8.6.10 注意擦除后的冲突=============");
        System.out.println("当泛型类型被擦除后，不允许创建引发冲突的条件");
        System.out.println("在类型擦除后不能覆盖另一个方法");
    }


    /**
     * 如何获取泛型数组
     * @param constr
     * @param a
     * @return
     * @param <T>
     */
    public static <T extends Comparable> T[] minmax(IntFunction<T[]> constr,T... a){
        // 这里其实就获得了泛型数组
        // 这里使用了构造器表达式指示的函数，给定的长度为2，所以 T 数组的类型为给定的类型，长度为 2
        T[] result = constr.apply(2);
        if(a == null || a.length == 0) return null;
        result[0] = a[0];
        result[1] = a[0];
        for (int i = 0; i < a.length; i++) {
            if(result[0].compareTo(a[i]) > 0) result[0] = a[i];
            if(result[1].compareTo(a[i]) < 0) result[1] = a[i];
        }
        return result;
    }

    /**
     * 使用反射生成正确类型的数组
     * @param a
     * @return
     * @param <T>
     */
    public static <T extends Comparable> T[] minmax(T... a){
        T[] result = (T[]) Array.newInstance(a.getClass().getComponentType(), 2);
        if(a == null || a.length == 0) return null;
        result[0] = a[0];
        result[1] = a[0];
        for (int i = 0; i < a.length; i++) {
            if(result[0].compareTo(a[i]) > 0) result[0] = a[i];
            if(result[1].compareTo(a[i]) < 0) result[1] = a[i];
        }
        return result;
    }

    /**
     * 实现一个方法中不允许抛出检查型异常(checked exception)
     */
    interface Task{
        void run() throws Exception;

        @SuppressWarnings("unchecked")
        static <T extends Throwable> void throwAs(Throwable t) throws T{
            throw (T)t;
        }

        static Runnable asRunnable(Task task){
            return () ->{
              try{
                  task.run();
              } catch (Exception e) {
                  Task.<RuntimeException>throwAs(e);
              }
            };
        }
    }
}
