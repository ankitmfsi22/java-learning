package day2;

// Abstract class
abstract class Vehicle {
    String brand;

    Vehicle(String brand) {
        this.brand = brand;
    }

    abstract void start();

    void info() {
        System.out.println("Brand: " + brand);
    }
}

class Car extends Vehicle {
    Car(String brand) {
        super(brand);
    }

    @Override
    void start() {
        System.out.println(brand + " car starts with a key");
    }
}

class Bike extends Vehicle {
    Bike(String brand) {
        super(brand);
    }

    @Override
    void start() {
        System.out.println(brand + " bike starts with a kick");
    }
}


// Interface
interface Payment {
    void pay(double amount);
}

class UpiPayment implements Payment {
    @Override
    public void pay(double amount) {
        System.out.println("Paid Rs." + amount + " via UPI");
    }
}

class CardPayment implements Payment {
    @Override
    public void pay(double amount) {
        System.out.println("Paid Rs." + amount + " via Card");
    }
}
public class AbstractionDemo {
    public static void main(String[] args) {
        System.out.println("=== Abstract Class ===");

        Vehicle car = new Car("Honda");
        car.info();
        car.start();

        Vehicle bike = new Bike("Hero");
        bike.info();
        bike.start();

        System.out.println("\n=== Interface ===");
        Payment p1 = new UpiPayment();
        Payment p2 = new CardPayment();
        p1.pay(500);
        p2.pay(1200);
    }
}