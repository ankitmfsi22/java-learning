package day3;

interface Greet {
    void say(String name);
}

interface MathOperation {
    int operate(int a, int b);
}

public class LambdaDemo {
    public static void main(String[] args) {
        System.out.println("Old Way");
        Greet oldGreet = new Greet() {
            @Override
            public void say(String name) {
                System.out.println("Hello " + name);
            }
        };
        oldGreet.say("Ankit");

        System.out.println("Lambda Way");
        Greet greet = name -> System.out.println("Hi " + name);
        greet.say("Ankit");

        System.out.println("Lambda with Return");
        MathOperation add = (a, b) -> a + b;
        MathOperation multiply = (a, b) -> a * b;
        MathOperation max = (a, b) -> {
            if (a > b) {
                return a;
            }
            return b;
        };

        System.out.println("Add: " + add.operate(5, 3));
        System.out.println("Multiply: " + multiply.operate(5, 3));
        System.out.println("Max: " + max.operate(5, 3));
    }
}
