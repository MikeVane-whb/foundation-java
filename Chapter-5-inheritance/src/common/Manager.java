package common;

/**
 * @author: whb
 * @date: 2022-10-04-09-44
 * @version: 1.0
 */
public class Manager extends Employee {

    private String skill;

    public Manager() {
    }

    public Manager(String name) {
        super(name);
    }


    public Manager(String name, Double bonus) {
        super(name, bonus);
    }

    @Override
    public String toString() {
        return "Manager{" +
                "name='" + super.getName() + '\'' +
                ", bonus=" + super.getBonus() +
                ", skill='" + skill + '\'' +
                '}';
    }
}
