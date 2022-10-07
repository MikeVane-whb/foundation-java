package seven_reflection;

import common.Employee;
import common.Manager;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

/**
 * Class 类
 * @author: whb
 * @date: 2022-10-06-23-28
 * @version: 1.0
 */
public class TheClassClass {
    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println("=============反射5.7.1 Class 类=============");
        System.out.println("获取 Class 类对象的三种方式");
        System.out.println("1. getClass()");

        Employee e = new Employee("mike");
        Class employeeClass = e.getClass();
        System.out.println(employeeClass.getName() + " " + e.getName());

        e = new Manager("mike");
        employeeClass = e.getClass();
        System.out.println(employeeClass.getName() + " " + e.getName());

        System.out.println("2. 使用静态方法 forName 获得类名对应的 Class 对象");
        String className = "java.lang.Integer";
        Class<?> cl = Class.forName(className);
        System.out.println(cl.getName() + " " + className);

        System.out.println("3. 如果 T 是任意的 Java 类型(或 void 关键字)，T.class 将代表匹配的类对象");
        Class<Double> doubleClass = Double.class;
        System.out.println(doubleClass.getName());
        System.out.println("注意：一个 Class 对象实际上表示的是一个类型，可能是类，也有可能不是类");
        Class<Integer> intClass = int.class;
        System.out.println(intClass.getName());

        System.out.println("虚拟机为每个类型管理一个唯一的 Class 对象");
        if(e.getClass() == Employee.class){
            System.out.println("Employee.class");
        }
        if(e.getClass() == Manager.class){
            System.out.println("Manager.class");
        }

        System.out.println("利用 Class 对象构造实例");
        String className1 = "java.util.Random";
        try {
            // forName() 返回一个 Class 对象，表示名为 className 的类
            Class<?> forName = Class.forName(className1);
            // getConstructor() 生成一个对象，描述有指定参数类型的构造器
            Constructor<?> forNameConstructor = forName.getConstructor();
            Object obj = forNameConstructor.newInstance();
            Random random = (Random) obj;
            System.out.println(random.nextInt());
        } catch (NoSuchMethodException ex) {
            ex.printStackTrace();
        } catch (InvocationTargetException ex) {
            throw new RuntimeException(ex);
        } catch (InstantiationException ex) {
            throw new RuntimeException(ex);
        } catch (IllegalAccessException ex) {
            throw new RuntimeException(ex);
        }
    }
}
