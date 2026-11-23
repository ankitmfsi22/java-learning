package inheritance;

public class Employee extends Manager {
    public static void main(String[] args) {
        Employee emp1 = new Employee();
        System.out.println(emp1.Salary);
        Manager emp2 = new Manager();
        System.out.println("Manager " + emp2.Salary);
    }
}
