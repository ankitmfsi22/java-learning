package day3;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SetDemo {
    public static void main(String[] args) {
        System.out.println("HashSet");

        Set<String> emails = new HashSet<>();

        emails.add("a@gmail.com");
        emails.add("b@gmail.com");
        emails.add("a@gmail.com");
        System.out.println("Set:" + emails);
        System.out.println("Size:" + emails.size());

        boolean added = emails.add("b@gmail.com");
        System.out.println("Added duplicate? " + added);

        System.out.println("Has a@gmail.com? " + emails.contains("a@gmail.com"));

        emails.remove("b@gmail.com");
        System.out.println("After remove: " + emails);

        List<Integer> nums = List.of(1, 2, 2, 3, 3, 3);
        Set<Integer> unique = new HashSet<>(nums);
        System.out.println("Unique numbers: " + unique);
    }
}
