package day3;

import java.util.List;
import java.util.stream.Collectors;

public class StreamDemo {
    public static void main(String[] args) {
        List<Integer> nums = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println("Original: " + nums);

        System.out.println("\nfilter");
        List<Integer> evens = nums.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());
        System.out.println("Even numbers: " + evens);

        System.out.println("\nmap");
        List<Integer> squares = nums.stream()
                .map(n -> n * n)
                .collect(Collectors.toList());
        System.out.println("Squares: " + squares);

        System.out.println("\nfilter + map");
        List<Integer> result = nums.stream()
                .filter(n -> n > 5)
                .map(n -> n * 10)
                .collect(Collectors.toList());
        System.out.println("Greater than 5, x10: " + result);

        System.out.println("\n reduce ");
        int total = nums.stream().reduce(0, (a, b) -> a + b);
        System.out.println("Total: " + total);

        System.out.println("\ncount");
        long count = nums.stream().filter(n -> n > 5).count();
        System.out.println("Count > 5: " + count);

        System.out.println("\nsum, max, average");
        int sum = nums.stream().mapToInt(n -> n).sum();
        int max = nums.stream().mapToInt(n -> n).max().getAsInt();
        double avg = nums.stream().mapToInt(n -> n).average().getAsDouble();
        System.out.println("Sum: " + sum);
        System.out.println("Max: " + max);
        System.out.println("Average: " + avg);

        System.out.println("\nStrings");
        List<String> names = List.of("ankit", "rahul", "priya", "amit");
        List<String> upper = names.stream()
                .filter(name -> name.startsWith("a"))
                .map(name -> name.toUpperCase())
                .collect(Collectors.toList());
        System.out.println("Names starting with 'a': " + upper);

        System.out.println("\nOriginal list unchanged: " + nums);
    }
}