package day1;

// Parent class
class Animal {
    String name;

    Animal(String name) {          // constructor
        this.name = name;
    }

    void eat() {
        System.out.println(name + " is eating");
    }

    void sound() {
        System.out.println(name + " makes a sound");
    }
}

// Child class
class Dog extends Animal {
    Dog(String name) {
        super(name);
    }

    @Override
    void sound() {
        System.out.println(name + " says Woof");
    }
}

public class InheritanceDemo {
    public static void main(String[] args) {
        Dog d = new Dog("Tommy");
        d.eat();
        d.sound();
    }
}
