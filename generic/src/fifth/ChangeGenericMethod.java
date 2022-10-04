package fifth;

import second.Pair;

import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.Arrays;

/**
 * 转换泛型方法
 * 泛型的类型转换实质
 *  1. 虚拟机中没有泛型，只有普通的类和方法
 *  2. 所有的类型参数(<>中的类型)都会替换为他们的限定类型(extends后面的类型)
 *  3. 会合成桥方法来保持多态
 *  4. 为保持类型安全性，必要时会插入强制类型转换
 * @author: whb
 * @date: 2022-10-02-00-39
 * @version: 1.0
 */
public class ChangeGenericMethod {
    /**
     * 验证是否会产生桥接方法
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("=============泛型8.5.3 转换泛型方法=============");
        Method[] methods = DateInterval.class.getDeclaredMethods();
        for (Method method : methods) {
            String name = method.getName();
            Class<?>[] parameterTypes = method.getParameterTypes();
            System.out.println(name + "(" + Arrays.toString(parameterTypes) + ")");
            /**
             * 结果为
             * setSecond([class java.time.LocalDate])
             * setSecond([class java.lang.Object])
             * 说明存在桥接方法
             */
        }
    }
}

class DateInterval extends Pair<LocalDate>{


    /**
     * 下面的方法在类型擦除后，父类对应的方法就为(public void setSecond(Object second))
     * 那么子类这里就不符合重写方法，因为父类的参数列表类型为Object，而这里类型为 LocalDate
     * 所以编译器在这里会生成一个桥方法
     * public void setSecond(Object second){ setSecond((LocalDate) second); }
     */

    @Override
    public void setSecond(LocalDate second){
        super.setSecond(second);
    }

}
