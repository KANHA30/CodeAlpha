import java.util.ArrayList;
import java.util.Scanner;

public class StudentGradeTracker {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Double> grades = new ArrayList<>();

        System.out.println("Student Grade Tracker");
        System.out.println("Enter student grades (type 0 to finish):");

        while (true) {
            System.out.print("Enter grade: ");
            double grade = scanner.nextDouble();
            if (grade == 0) {
                break;
            }
            if (grade < 0 || grade > 100) {
                System.out.println("Please enter a valid grade between 0 and 100.");
            } else {
                grades.add(grade);
            }
        }

        if (grades.isEmpty()) {
            System.out.println("No grades entered.");
        } else {
            double sum = 0;
            double highest = grades.get(0);
            double lowest = grades.get(0);

            for (double g : grades) {
                sum += g;
                if (g > highest) {
                    highest = g;
                }
                if (g < lowest) {
                    lowest = g;
                }
            }

            double average = sum / grades.size();

            System.out.println("\n--- Grade Summary ---");
            System.out.println("Total Students: " + grades.size());
            System.out.printf("Average Grade: %.2f\n", average);
            System.out.println("Highest Grade: " + highest);
            System.out.println("Lowest Grade: " + lowest);
        }

        scanner.close();
    }
}
