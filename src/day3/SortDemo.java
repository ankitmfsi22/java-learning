package day3;

import java.util.ArrayList;
import java.util.List;

public class SortDemo {
    public static void main(String[] args) {
        List<Integer> nums = new ArrayList<>(List.of(5, 2, 8, 1, 9));

        System.out.println("forEach");
        nums.forEach(n -> System.out.println(n));

        System.out.println("\nSort Numbers");
        nums.sort((a, b) -> a - b);
        System.out.println("Ascending: " + nums);

        nums.sort((a, b) -> b - a);
        System.out.println("Descending: " + nums);

        List<String> names = new ArrayList<>(List.of("Rahul", "Ankit", "Priya", "Amit"));

        System.out.println("\nSort Strings");
        names.sort((a, b) -> a.length() - b.length());
        System.out.println("By length: " + names);

        names.sort((a, b) -> a.compareTo(b));
        System.out.println("Alphabetical: " + names);

        System.out.println("\nremoveIf");
        nums.removeIf(n -> n < 5);
        System.out.println("After removing < 5: " + nums);

        System.out.println("\nMethod Reference");
        names.forEach(System.out::println);
    }
}