package day2;

class Calculator {
    int add(int a, int b) {
        return a + b;
    }

    int add(int a, int b, int c) {
        return a + b + c;
    }

    double add(double a, double b) {
        return a + b;
    }
}



// Method Overriding (Runtime Polymorphism)
class Shape {
    double area() {
        return 0;
    }
}

class Circle extends Shape {
    double radius;

    Circle(double radius) {
        this.radius = radius;
    }

    @Override
    double area() {
        return Math.PI * radius * radius;
    }
}

class Rectangle extends Shape {
    double width, height;

    Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    double area() {
        return width * height;
    }
}

public class PolymorphismDemo {
    public static void main(String[] args) {
        System.out.println("Method Overloading");
        Calculator calc = new Calculator();
        System.out.println(calc.add(2, 3));
        System.out.println(calc.add(2, 3, 4));
        System.out.println(calc.add(2.5, 3.5));
        System.out.println("Method Overriding");
        Shape[] shapes = { new Circle(2), new Rectangle(4, 5) };
        for (Shape s : shapes) {
            System.out.println(s.getClass().getSimpleName() + " area: " + s.area());
        }
    }
}
