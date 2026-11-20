package day2;

import java.util.ArrayList;
import java.util.List;

public class CollectionsDemo {
    public static void main(String[] args) {
        System.out.println(" ArrayList ===");
        List<String> fruits = new ArrayList<>();
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Mango");
        System.out.println("List: " + fruits);
        System.out.println("First: " + fruits.get(0));
        System.out.println("Size: " + fruits.size());
        fruits.set(1, "Orange");
        System.out.println("After set: " + fruits);
        fruits.remove("Apple");
        System.out.println("After remove: " + fruits);
        System.out.println("Has Mango? " + fruits.contains("Mango"));

        for (String f : fruits) {
            System.out.println("- " + f);
        }
    }
}
