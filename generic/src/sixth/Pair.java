package sixth;

import java.lang.reflect.InvocationTargetException;
import java.util.function.Supplier;

/**
 * @author: whb
 * @date: 2022-10-02-11-02
 * @version: 1.0
 */
public class Pair<T> {
    private T first;
    private T second;

    public Pair(){
        this.first = null;
        this.second = null;
    }

    public Pair(T first, T second){
        this.first = first;
        this.second = second;
    }

    /**
     * 调用构造器表达式来实例化 Pair
     * @param constr
     * @return
     * @param <T>
     */
    public static <T> Pair<T> makePair(Supplier<T> constr){
        return new Pair<>(constr.get(),constr.get());
    }

    /**
     *     在类型擦除后不能覆盖另一个方法
     *     public boolean equals(T value){
     *         return first.equals(value) && second.equals(value);
     *     }
     *
     */


    /**
     * 使用反射来构造泛型对象
     * @param cl
     * @return
     * @param <T>
     */
    public static <T> Pair<T> makePair(Class<T> cl){
        try {
            return new Pair<>(cl.getConstructor().newInstance(),cl.getConstructor().newInstance());
        } catch (Exception e) {
            return null;
        }
    }

    public T getFirst() {
        return first;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public T getSecond() {
        return second;
    }

    public void setSecond(T second) {
        this.second = second;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "first=" + first +
                ", second=" + second +
                '}';
    }
}
