package seven_reflection.invokingMethodAndConstructors;

import common.Employee;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * This program shows how to invoke methods through reflection.
 * 此程序演示如何通过反射调用方法
 * @author: whb
 * @date: 2022-10-20-23-42
 * @version: 1.0
 */
public class MethodTableTest {
    public static void main(String[] args) throws ReflectiveOperationException{
        System.out.println("=============反射5.7.7 调用任意方法和构造器=============");
        // get method pointers to the square and sqrt methods
        // 获取指向 square 和 sqrt 方法的方法指针
        Method square = MethodTableTest.class.getMethod("square", double.class);
        Method sqrt = Math.class.getMethod("sqrt", double.class);

        // print tables of x- and y-values
        // 打印 x 值和 y 值表
        printTable(1,10,10,square);
        printTable(1,10,10,sqrt);

        System.out.println("=================test=====================");
        Employee employee = new Employee("mikevane");
        // 反射改变 salary 值为 123.0
        ReflectionToSetField("salary",123.0,employee);
        // 反射改变 name 为 dick
        ReflectionToSetField("name","Dick",employee);
    }

    public static double square(double x){
        return x * x;
    }

    /**
     * Prints a table with x- and y-values for a method
     * 打印具有方法的 x 值和 y 值的表
     * @param from the lower bound for the x-values
     * @param to the upper bound for the x-values
     * @param n the number of rows in the table
     * @param f a method with a double parameter and double return value
     */
    private static void printTable(double from, double to, int n, Method f) throws ReflectiveOperationException {
        // print out the method as table header
        // 将方法打印出来作为表头
        System.out.println(f);

        double dx = (to - from) / (n - 1);

        for (double x = from; x <= to; x += dx){
            /**
             * invoke()
             *      调用这个对象描述的方法，传入给定参数，并返回方法的返回值。
             *      对于静态方法，传入 null 作为隐式参数。
             *      使用包装器传递基本类型值。基本类型的返回值必须是未包装的。
             */
            double y = (double) f.invoke(null, x);
            System.out.printf("%10.4f | %10.4f%n", x, y);
        }
    }

    /**
     * 通过反射去调用 set 方法改变成员变量的值
     * @param fieldName 成员变量名称
     * @param value 值
     * @param obj 对象
     */
    public static void ReflectionToSetField(String fieldName, Object value, Object obj) throws ReflectiveOperationException {
        // 打印 obj 对象
        System.out.println(obj);
        if(fieldName == null || fieldName.length() < 1){
            System.out.println("fieldName 不可为空");
        }
        if(value == null){
            System.out.println("value 不可为空");
        }
        // 将成员变量的第一个字符变为大写
        String replace = fieldName.replace(fieldName.charAt(0), Character.toUpperCase(fieldName.charAt(0)));
        // 拼接字符串为：set + 成员变量名字
        replace = "set" + replace;
        System.out.println(replace);
        // 获取 value 的 class对象
        Class<?> valueClass = value.getClass();
        // 获取 obj 对象对应的 method， valueClass 为参数类型
        Method method = obj.getClass().getMethod(replace, valueClass);
        // 调用 method，value 为参数
        method.invoke(obj,value);
        System.out.println(obj);
    }
}
