import java.util.Scanner;

public class StringOperations {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);


        String originalString = " Tarento,string task ";
        System.out.println("Given String: " + originalString);

        int length = originalString.length();
        System.out.println("Length of the string: " + length);


        String upperCaseString = originalString.toUpperCase();
        System.out.println("Uppercase string: " + upperCaseString);


        String substring = originalString.substring(7);
        System.out.println("Substring: " + substring);


        System.out.print("Enter the character to be replaced: ");
        char oldChar = scanner.next().charAt(0);
        System.out.print("Enter the new character: ");
        char newChar = scanner.next().charAt(0);


        String replacedString = originalString.replace(oldChar, newChar);
        System.out.println("String after replacement: " + replacedString);


        scanner.close();
    }
}
