package Polymorphism;

public class Render extends Car {
    public static void main(String[] args) {
        Vehicle v1 = new Vehicle();
        v1.move();
        Vehicle v2 = new Car();
        v2.move();
        Car c1 = new Render();
        c1.move();
    }
}
