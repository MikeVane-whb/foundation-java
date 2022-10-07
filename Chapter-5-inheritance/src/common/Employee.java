package common;
/**
 * @author: whb
 * @date: 2022-10-04-09-44
 * @version: 1.0
 */
public class Employee implements Comparable{

    private String name;
    private Double bonus;

    public Employee(){}

    public Employee(String name) {
        this.name = name;
    }

    public Employee(String name, Double bonus) {
        this.name = name;
        this.bonus = bonus;
    }

    public String getName() {
        return name;
    }

    public Double getBonus() {
        return bonus;
    }

    public void setBonus(Double bonus) {
        this.bonus = bonus;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", bonus=" + bonus +
                '}';
    }
}

