    import java.util.Scanner;

    public class SimpleCalculator{


        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

            while (true) {
                // Displaying options to the user
               // System.out.println("Welcome to the Simple Calculator");
                System.out.println("Please select an operation:");
                System.out.println("1. Addition (+)");
                System.out.println("2. Subtraction (-)");
                System.out.println("3. Multiplication (*)");
                System.out.println("4. Division (/)");
                System.out.println("5. Exit");


                System.out.print("Enter your choice (1/2/3/4/5): ");
                int choice = scanner.nextInt();


                if (choice == 5) {
                    System.out.println("Thank you for using the Simple Calculator. Goodbye!");
                    break;
                }


                System.out.print("Enter the first number: ");
                double num1 = scanner.nextDouble();
                System.out.print("Enter the second number: ");
                double num2 = scanner.nextDouble();

                double result = 0;
                boolean validOperation = true;


                switch (choice) {
                    case 1:
                        result = num1 + num2;
                        break;
                    case 2:
                        result = num1 - num2;
                        break;
                    case 3:
                        result = num1 * num2;
                        break;
                    case 4:
                        if (num2 != 0) {
                            result = num1 / num2;
                        } else {
                            System.out.println("Error: Division by zero is not allowed.");
                            validOperation = false;
                        }
                        break;
                    default:
                        System.out.println("Invalid choice. Please select a valid operation.");
                        validOperation = false;
                        break;
                }


                if (validOperation) {
                    System.out.println("The result is: " + result);
                }
            }

            scanner.close();
        }
    }

