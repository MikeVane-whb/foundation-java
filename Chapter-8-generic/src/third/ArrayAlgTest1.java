package third;

/**
 * @author: whb
 * @date: 2022-10-01-15-32
 * @version: 1.0
 */
public class ArrayAlgTest1 {
    public static void main(String[] args) {
        // 8.3泛型方法
        System.out.println("=============8.3泛型方法=============");
        String middle1 = ArrayAlg.<String>getMiddle("john", "Q", "Public");
        System.out.println(middle1);
        //不指定<String>类型参数
        String middle2 = ArrayAlg.getMiddle("john", "Q", "Public");
        System.out.println(middle2);


        // 通过错误来推断遍历器对一个泛型方法调用后的最终类型
        // JButton button = ArrayAlg.getMiddle("hello",0,null);
        // 这里通过编译器错误可以将结果赋给 Object,Serializable 或 Comparable
        // Serializable & Comparable<? extends Serializable & Comparable<?>>
    }
}

class ArrayAlg{

    /**
     * 获取 T 中的中间值
     * @param a
     * @return 返回 T 中的中间值
     * @param <T>
     */
    public static <T> T getMiddle(T... a){
        return a[a.length / 2];
    }

}
