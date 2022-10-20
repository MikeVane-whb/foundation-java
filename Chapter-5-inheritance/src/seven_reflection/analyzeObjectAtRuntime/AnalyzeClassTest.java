package seven_reflection.analyzeObjectAtRuntime;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * 测试 使用反射在运行时分析对象
 * @author: whb
 * @date: 2022-10-20-01-06
 * @version: 1.0
 */
public class AnalyzeClassTest{

    List<Object> list = new ArrayList<>();

    public void test(Object obj) throws IllegalAccessException {
        // 防空指针
        if (obj == null){
            System.out.println("null");
            return;
        }
        // 防止循环打印
        if(list.contains(obj)){
            return;
        }
        list.add(obj);
        // 获取 obj 的class对象
        Class<?> objClass = obj.getClass();
        // 如果是字符串类型就直接打印
        if(objClass == String.class){
            System.out.println((String) obj);
            return;
        }
        // 如果是数组类型
        if (objClass.isArray()){
            // 获得一个数组的Class对象
            System.out.println(objClass.getComponentType());
            // 遍历数组的元素
            for (int i = 0; i < Array.getLength(obj); i++) {
                System.out.println(Array.get(obj, i));
                // 获取数组的元素
                Object val = Array.get(obj, i);
                // 如果obj的Class对象为原始类型，则打印
                if (objClass.getComponentType().isPrimitive()){
                    System.out.println(val);
                }
                // 否则，递归数组的元素 val
                else {
                    test(val);
                }
            }
            return;
        }
        // 获取 obj 对应的 class 名字
        String objClassName = objClass.getName();
        do {
            System.out.println(objClass.getName());
            // 获取 objClass 里所有的成员变量(不能访问到超类成员)
            Field[] fields = objClass.getDeclaredFields();
            // 设置可访问标志，用于添加访问权限(如访问 private)
            AccessibleObject.setAccessible(fields,true);
            // 遍历成员
            for (Field field : fields) {
                // 如果修饰符中没有 static
                if(!Modifier.isStatic(field.getModifiers())){
                    System.out.println(field.getName());
                    // 获取成员变量的类型
                    Class<?> type = field.getType();
                    // 获取成员变量的值
                    Object val = field.get(obj);
                    // 如果为原始变量(如，boolean，int...)
                    if (type.isPrimitive()){
                        System.out.println(val);
                    }
                    // 否则递归 val
                    else{
                        test(val);
                    }
                }
            }
            // 获取 objClass 的超类
            objClass = objClass.getSuperclass();
        }while (objClass != null);
        return;
    }

    public static void main(String[] args) throws IllegalAccessException {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            arrayList.add(i * 3);
        }
        new AnalyzeClassTest().test(arrayList);
    }
}
