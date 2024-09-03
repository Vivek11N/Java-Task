import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GradeCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> marksList = new ArrayList<>();
        boolean continueEntering = true;

        while (continueEntering) {
            System.out.print("Enter marks obtained (out of 100): ");
            int marks = scanner.nextInt();
            marksList.add(marks);


            String grade = calculateGrade(marks);
            System.out.println("Grade: " + grade);

            System.out.print("Do you want to enter more marks? (yes/no): ");
            String response = scanner.next();
            continueEntering = response.equalsIgnoreCase("yes");
        }


        double averageMarks = calculateAverage(marksList);
        System.out.println("Average Marks: " + averageMarks);


        String finalGrade = calculateGrade(averageMarks);
        System.out.println("Final Grade based on average marks: " + finalGrade);

        scanner.close();
    }


    private static double calculateAverage(List<Integer> marksList) {
        if (marksList.isEmpty()) {
            return 0;
        }

        int sum = 0;
        for (int marks : marksList) {
            sum += marks;
        }

        return (double) sum / marksList.size();
    }


    private static String calculateGrade(double marks) {
        if (marks >= 90) {
            return "Grade A";
        } else if (marks >= 80) {
            return "Grade B";
        } else if (marks >= 70) {
            return "Grade C";
        } else if (marks >= 60) {
            return "Grade D";
        } else {
            return "Grade F (Fail)";
        }
    }
}
