package day3;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Student {
    private String name;
    private int marks;
    private String city;

    Student(String name, int marks, String city) {
        this.name = name;
        this.marks = marks;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public int getMarks() {
        return marks;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return name + "(" + marks + ")";
    }
}

public class StudentDemo {
    public static void main(String[] args) {
        List<Student> students = List.of(
                new Student("Ankit", 85, "Bhubaneswar"),
                new Student("Rahul", 62, "Cuttack"),
                new Student("Priya", 91, "Bhubaneswar"),
                new Student("Amit", 45, "Cuttack"),
                new Student("Sneha", 78, "Puri")
        );

        System.out.println("All students: " + students);

        System.out.println("\nToppers (marks >= 80)");
        List<String> toppers = students.stream()
                .filter(s -> s.getMarks() >= 80)
                .map(s -> s.getName())
                .collect(Collectors.toList());
        System.out.println(toppers);

        System.out.println("\nPassed students count (marks >= 50)");
        long passed = students.stream()
                .filter(s -> s.getMarks() >= 50)
                .count();
        System.out.println(passed);

        System.out.println("\nAverage marks");
        double avg = students.stream()
                .mapToInt(s -> s.getMarks())
                .average()
                .getAsDouble();
        System.out.println(avg);

        System.out.println("\nHighest scorer");
        Student top = students.stream()
                .max(Comparator.comparingInt(s -> s.getMarks()))
                .get();
        System.out.println(top.getName() + " with " + top.getMarks());

        System.out.println("\nSorted by marks (high to low)");
        List<Student> sorted = students.stream()
                .sorted((a, b) -> b.getMarks() - a.getMarks())
                .collect(Collectors.toList());
        System.out.println(sorted);

        System.out.println("\nName Marks map");
        Map<String, Integer> marksMap = students.stream()
                .collect(Collectors.toMap(s -> s.getName(), s -> s.getMarks()));
        System.out.println(marksMap);

        System.out.println("\nStudents grouped by city");
        Map<String, List<String>> byCity = students.stream()
                .collect(Collectors.groupingBy(
                        s -> s.getCity(),
                        Collectors.mapping(s -> s.getName(), Collectors.toList())
                ));
        System.out.println(byCity);
    }
}
