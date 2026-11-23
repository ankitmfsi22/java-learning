package interfaces;

public class InterfaceDemo {
    public static void main(String[] args) {
        Credit crd = new Credit();
        crd.Operation();
        Debit dbt = new Debit();
        dbt.Operation();
        InterfaceCreditedDemo id = new InterfaceCreditedDemo();
        id.Calculate();
        id.Operation();
    }
}
