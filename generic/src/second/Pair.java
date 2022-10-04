package second;

/**
 * java核心技术卷1 泛型 8.2
 * @author: whb
 * @date: 2022-10-01-14-54
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
