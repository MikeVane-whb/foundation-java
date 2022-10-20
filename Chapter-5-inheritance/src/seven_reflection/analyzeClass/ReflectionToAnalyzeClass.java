package seven_reflection.analyzeClass;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Scanner;

/**
 * @author: whb
 * @date: 2022-10-19-11-35
 * @version: 1.0
 */
public class ReflectionToAnalyzeClass {
    public static void main(String[] args) throws ReflectiveOperationException {
        System.out.println("=============反射5.7.4 利用反射分析类的能力=============");
        String name = null;
        if(args.length > 0){
            name = args[0];
        }
        else {
            Scanner in = new Scanner(System.in);
            System.out.println("Enter class name (e.g. java.util.Date): ");
            name = in.next();
        }

        // print class name and superclass name (if != Object)
        // 打印类与超类的名字(该类不为 Object)
        Class<?> baseClass = Class.forName(name);
        Class<?> superclass = baseClass.getSuperclass();
        // 使用 Modifier 解码类和成员访问修饰符
        /**
         * getModifiers()
         *      返回一个整数，描述这个构造器、方法或字段的修饰符。
         *      使用 Modifier 类中的方法来分析这个返回值
         */
        String modifiers = Modifier.toString(baseClass.getModifiers());
        if (modifiers.length() > 0){
            System.out.print(modifiers + " ");
        }
        System.out.print("class " + name);
        if (superclass != null && superclass != Object.class){
            /**
             * getName()
             *      返回一个表示构造器名、方法名或字段名的字符串
             */
            System.out.print(" extends " + superclass.getName());
        }
        System.out.print("\n{\n");
        printFields(baseClass);
        System.out.println();
        printConstructor(baseClass);
        System.out.println();
        printMethods(baseClass);
        System.out.println("}");
    }

    /**
     * prints all fields of a class
     * 打印该类所有的成员
     * @param baseClass
     */
    public static void printFields(Class<?> baseClass){
        /**
         * getFields()
         *      将返回一个包含Field对象的数组，这些对象对应这个类或其超类的公共字段
         * getDeclaredField()
         *      方法也返回包含Field对象的数组，这些对象对应这个类的全部字段
         *      如果类中没有字段，或者Class对象描述的是基本类型或数组类型，这些方法将返回一个长度为0的数组。
         */
        Field[] declaredFields = baseClass.getDeclaredFields();

        for (Field declaredField : declaredFields) {
            String declaredFieldName = declaredField.getName();
            Class<?> declaredFieldType = declaredField.getType();
            System.out.print("  ");
            String modifier = Modifier.toString(declaredField.getModifiers());
            if (modifier.length() > 0){
                System.out.print(modifier + " ");
            }
            System.out.print(declaredFieldType.getName() + " " + declaredFieldName);
            System.out.println(";");
        }

    }

    /**
     * prints all constructors of a class
     * 打印所有构造器
     * @param baseClass
     */
    private static void printConstructor(Class<?> baseClass) {
        /**
         * 返回包含Constructor 对象的数组，其中包含Class对象所表示的类的所有公共构造器(getConstructors)
         * 或全部构造器(getDeclaredConstructors)。
         */
        Constructor<?>[] constructors = baseClass.getDeclaredConstructors();

        for (Constructor<?> constructor : constructors) {
            String constructorName = constructor.getName();
            System.out.print("  ");
            String modifier = Modifier.toString(constructor.getModifiers());
            if (modifier.length() > 0){
                System.out.print(modifier + " ");
            }
            System.out.print(constructorName + "(");

            // print parameter types 打印参数类型
            /**
             * getParameterTypes() (在 Constructor 和 Method classes类中)
             *      返回一个 Class 对象数组，其中各个对象表示参数的类型
             */
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            for (int i = 0; i < parameterTypes.length; i++) {
                if(i > 0){
                    System.out.print(", ");
                }
                System.out.print(parameterTypes[i].getName());
            }
            System.out.println(");");
        }
    }

    /**
     * print all methods of a class
     * 打印该类的所有方法
     * @param baseClass
     */
    private static void printMethods(Class<?> baseClass) {
        /**
         * getMethods()
         *      返回包含Method对象的数组: getMethods 将返回所有的公共方法，包括从超类继承来的公共方法;
         * getDeclaredMethods
         *      返回这个类或接口的全部方法，但不包括由超类继承的方法。
         */
        Method[] declaredMethods = baseClass.getDeclaredMethods();

        for (Method declaredMethod : declaredMethods) {
            /**
             * getReturnType() (在 Method 类中)
             *      返回一个表示返回类型的 Class 对象
             */
            Class<?> returnType = declaredMethod.getReturnType();
            String declaredMethodName = declaredMethod.getName();

            System.out.print("  ");
            // print modifiers, return type and method name
            // 打印修饰符，返回类型与方法名称
            String modifier = Modifier.toString(declaredMethod.getModifiers());
            if (modifier.length() > 0){
                System.out.print(modifier + " ");
            }
            System.out.print(returnType.getName() + " " + declaredMethodName + "(");

            // print parameter types
            // 打印参数类型
            Class<?>[] parameterTypes = declaredMethod.getParameterTypes();
            for (int i = 0; i < parameterTypes.length; i++) {
                if (i > 0){
                    System.out.print(", ");
                }
                System.out.print(parameterTypes[i].getName());
            }
            System.out.println(");");
        }
    }
}
