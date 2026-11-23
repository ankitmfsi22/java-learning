package interfaces;

public class Credit implements Money {
    @Override
    public void Operation() {
        System.out.println("Credited");
    }
}
