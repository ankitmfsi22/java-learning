package interfaces;

public class Debit implements Money {
    @Override
    public void Operation() {
        System.out.println("Debited");
    }
}
