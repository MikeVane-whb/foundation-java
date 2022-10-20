package seven_reflection.decaringException;

import common.Demo;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;

/**
 * 声明异常入门
 * @author: whb
 * @date: 2022-10-07-00-11
 * @version: 1.0
 */
public class PrimerOnDeclaringExceptions {
    public static void main(String[] args) {
        System.out.println("=============反射5.7.2 声明异常入门=============");
        System.out.println("抛出异常比终止程序灵活：因为可以提供一个 handler 捕获这个异常并处理");
        System.out.println("异常有两种类型：非检查型(unchecked)异常和检查型(checked)异常");

        try {
            Class<?> forName = Class.forName("java.util.ArrayList");
            ArrayList<Integer> arrayList = new ArrayList<>();
            System.out.println(forName.getName());
            Type type = arrayList.getClass().getGenericSuperclass();
            if(type instanceof ParameterizedType){
                System.out.println(type);
                Type[] actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();
                System.out.println(actualTypeArguments[0]);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        // 测试使用反射获取泛型的参数类型
        new Demo<ArrayList<Integer>>(){};
    }
}
