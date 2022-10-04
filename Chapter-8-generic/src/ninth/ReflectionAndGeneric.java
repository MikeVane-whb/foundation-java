package ninth;

import seventh.Employee;
import seventh.Manager;

import java.lang.reflect.Method;

/**
 * 反射与泛型
 * @author: whb
 * @date: 2022-10-04-14-07
 * @version: 1.0
 */
public class ReflectionAndGeneric {
    public static void main(String[] args) {
        System.out.println("=============泛型8.9 反射和泛型=============");
        System.out.println("=============泛型8.9.1 泛型 Class 类=============");
        System.out.println("Class 类是泛型");
        Class<Employee> employeeClass = Employee.class;
        Manager manager = new Manager("mike",1000.0);
        System.out.println(manager);
        Employee cast = employeeClass.cast(manager);
        System.out.println(cast);

        System.out.println("=============泛型8.9.2 使用 Class<T>参数进行类型匹配=============");

    }
}