package fifth;

import java.io.Serializable;

/**
 * @author: whb
 * @date: 2022-10-01-16-19
 * @version: 1.0
 */
public class Interval<T extends Comparable & Serializable> implements Serializable{
    private T lower;
    private T upper;

    public Interval(T lower, T upper) {
        if(lower.compareTo(upper) <= 0){
            this.lower = lower;
            this.upper = upper;
        } else{
            this.lower = upper;
            this.upper = lower;
        }
    }

    // 原始类型 Interval 如下所示
    /**
     * public class Interval implements Serializable{
     *     private Comparable lower;
     *     private Comparable upper;
     *     ...
     *     public Interval(Comparable lower, Comparable upper) {...}
     */
}
