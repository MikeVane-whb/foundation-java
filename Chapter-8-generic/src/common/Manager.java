package common;

/**
 * @author: whb
 * @date: 2022-10-04-09-44
 * @version: 1.0
 */
public class Manager extends Employee
{
    private double bonus;

    /**
     * @param name the employee's name
     * @param salary the salary
     * @param year the hire year
     * @param month the hire month
     * @param day the hire day
     */
    public Manager(String name, double salary, int year, int month, int day)
    {
        super(name, salary, year, month, day);
        bonus = 0;
    }

    public Manager(String name) {
        super(name);
    }

    public Manager(String name, double salary) {
        super(name, salary);
    }

    public double getSalary()
    {
        double baseSalary = super.getSalary();
        return baseSalary + bonus;
    }

    public double getBonus(){
        return bonus;
    }

    public void setBonus(double b)
    {
        bonus = b;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "name='" + super.getName() + '\'' +
                ", salary=" + super.getSalary() +
                ", hireDay=" + super.getHireDay() +
                ", bonus=" + bonus +
                '}';
    }
}
