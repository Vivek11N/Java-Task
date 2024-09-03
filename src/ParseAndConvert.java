public class ParseAndConvert {

    public static void main(String[] args) {

        String intString = "123";
        String doubleString = "123.45";
        String booleanString = "true";
        String longString = "123456789";
        String floatString = "123.45f";
        String charString = "A";


        int intValue = Integer.parseInt(intString);
        double doubleValue = Double.parseDouble(doubleString);
        boolean booleanValue = Boolean.parseBoolean(booleanString);
        long longValue = Long.parseLong(longString);
        float floatValue = Float.parseFloat(floatString);
        char charValue = charString.charAt(0);


        System.out.println("Parsed int value: " + intValue);
        System.out.println("Parsed double value: " + doubleValue);
        System.out.println("Parsed boolean value: " + booleanValue);
        System.out.println("Parsed long value: " + longValue);
        System.out.println("Parsed float value: " + floatValue);
        System.out.println("Parsed char value: " + charValue);


        String intToString = String.valueOf(intValue);
        String doubleToString = String.valueOf(doubleValue);
        String booleanToString = String.valueOf(booleanValue);
        String longToString = String.valueOf(longValue);
        String floatToString = String.valueOf(floatValue);
        String charToString = String.valueOf(charValue);


        System.out.println("Converted int to string: " + intToString);
        System.out.println("Converted double to string: " + doubleToString);
        System.out.println("Converted boolean to string: " + booleanToString);
        System.out.println("Converted long to string: " + longToString);
        System.out.println("Converted float to string: " + floatToString);
        System.out.println("Converted char to string: " + charToString);
    }
}
