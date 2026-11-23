package day3;

import java.util.HashMap;
import java.util.Map;

public class MapDemo {
    public static void main(String[] args) {
        System.out.println("=== HashMap ===");

        Map<String, Integer> marks = new HashMap<>();

        marks.put("Ankit", 85);
        marks.put("Rahul", 72);
        marks.put("Priya", 91);
        System.out.println("Map: " + marks);

        System.out.println("Ankit's marks: " + marks.get("Ankit"));

        marks.put("Rahul", 78);
        System.out.println("After update: " + marks);

        System.out.println("Has Priya? " + marks.containsKey("Priya"));

        System.out.println("Amit's marks: " + marks.getOrDefault("Amit", 0));

        marks.remove("Priya");
        System.out.println("After remove: " + marks);

        System.out.println("Size: " + marks.size());

        for (Map.Entry<String, Integer> entry : marks.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }
}