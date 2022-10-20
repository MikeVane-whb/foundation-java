package seven_reflection.reflectionToGeneric;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * This program demonstrates the use of reflection for manipulating arrays.
 * 此程序演示如何使用反射来操作数组
 * @author: whb
 * @date: 2022-10-20-18-42
 * @version: 1.0
 */
public class CopyOfTest {
    public static void main(String[] args) {
        System.out.println("=============反射5.7.6 使用反射编写泛型数组代码=============");
        int[] a = { 1, 2, 3 };
        a = (int[]) goodCopyOf(a, 10);
        System.out.println(Arrays.toString(a));

        String[] b = { "Tom", "Dick", "Harry" };
        b = (String[]) goodCopyOf(b, 10);
        System.out.println(Arrays.toString(b));

        System.out.println("The following call will generate an exception.");
        System.out.println("以下调用将生成异常");
        b = (String[]) badCopyOf(b,10);
    }

    /**
     * This method attempts to grow an array by allocating a new array and copying all elements.
     * 此方法尝试通过分配新数组并复制所有元素来增大数组
     * @param a the array to grow
     * @param newLength the new length
     * @return a larger array that contains all elements of a. However, the returned
     * array has type Object[], not the same type as a
     */
    public static Object[] badCopyOf(Object[] a, int newLength){ // 无效
        Object[] newArray = new Object[newLength];
        System.arraycopy(a,0,newArray,0,Math.min(a.length,newLength));
        return newArray;
    }

    /**
     * This method grows an array by allocating a new array of the same type and
     * copying all elements.
     * 此方法通过分配相同类型的新数组来增大数组，并且复制所有元素。
     * @param a the array to grow. This can be an object array or a primitive
     * type array
     * @return a larger array that contains all elements of a .
     */
    public static Object goodCopyOf(Object a, int newLength){
        Class<?> cl = a.getClass();
        if (!cl.isArray()){
            return null;
        }
        Class<?> componentType = cl.getComponentType();
        int length = Array.getLength(a);
        Object newArray = Array.newInstance(componentType, newLength);
        System.arraycopy(a,0,newArray,0,Math.min(length,newLength));
        return newArray;
    }
}
