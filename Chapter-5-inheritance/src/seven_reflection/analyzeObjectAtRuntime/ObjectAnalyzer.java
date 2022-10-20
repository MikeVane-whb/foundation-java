package seven_reflection.analyzeObjectAtRuntime;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

/**
 * 自定义类，实现一个通用 toString 方法
 * @author: whb
 * @date: 2022-10-20-00-31
 * @version: 1.0
 */
public class ObjectAnalyzer {
    private ArrayList<Object> visited = new ArrayList<>();

    /**
     * Converts an object to a string representation that lists all fields
     * 将对象转换为所有字段的字符串表示形式
     * @return
     */
    public String toString(Object obj) throws ReflectiveOperationException{
        if (obj == null){
            return "null";
        }
        if (visited.contains(obj)){
           return "...";
        }
        visited.add(obj);
        Class<?> cl = obj.getClass();
        if(cl == String.class){
            return (String)obj;
        }
        if(cl.isArray()){
            String r = cl.getComponentType() + "[]{";
            for (int i = 0; i < Array.getLength(obj); i++) {
                if (i > 0){
                    r += ",";
                }
                Object val = Array.get(obj, i);
                if (cl.getComponentType().isPrimitive()){
                    r += val;
                }
                else {
                    r += toString(val);
                }
            }
            return r + "}";
        }
        String r = cl.getName();
        // inspect the fields of this class and all superclasses
        do {
            r += "[";
            Field[] fields = cl.getDeclaredFields();
            AccessibleObject.setAccessible(fields,true);
            // get the names and values of all fields
            for (Field field : fields) {
                if(!Modifier.isStatic(field.getModifiers())){
                    if (!r.endsWith("[")){
                        r += ",";
                    }
                    r += field.getName() + "=";
                    Class<?> type = field.getType();
                    Object val = field.get(obj);
                    if (type.isPrimitive()){
                        r += val;
                    }
                    else {
                        r += toString(val);
                    }
                }
            }
            r += "]";
            cl = cl.getSuperclass();
        }while (cl != null);

        return r;
    }

    public static void main(String[] args) throws ReflectiveOperationException {
        System.out.println("=============反射5.7.5 使用反射在运行时分析对象=============");
        ArrayList<Integer> squares = new ArrayList<>();
        for (int i = 1; i <= 5 ; i++) {
            squares.add(i * i);
        }
        System.out.println(new ObjectAnalyzer().toString(squares));
    }
}
