package test;

/**
 * @author: whb
 * @date: 2022-10-18-16-41
 * @version: 1.0
 */
public class Test {
    public static void main(String[] args) {
        String a = new String("abc");
        String b = "abc";
        Object o = new Object();
        o.toString();
        System.out.println(Integer.toHexString(a.hashCode()));
        System.out.println(Integer.toHexString(b.hashCode()));
        System.out.println(a == b);
    }
}
