import java.util.Scanner;

public class Utility {
    private static final Scanner scanner = new Scanner(System.in);

    public static int getIntInput(String inputPrompt, String errorMessage, int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("min cannot be greater than max");
        }

        try {
            System.out.printf("%s", inputPrompt);
            final int input = Integer.parseInt(scanner.nextLine().trim());

            if (input >= min && input <= max) {
                return input;
            } else {
                System.out.printf("%s%n", errorMessage);
            }
        } catch (NumberFormatException e) {
            System.out.printf("%s%n", errorMessage);
        }

        return getIntInput(inputPrompt, errorMessage, min, max);
    }

    public static int getIntInput(String inputPrompt, int min, int max) {
        final String errorMessage = String.format("INPUT ERROR. Only accepts integers %d to %d.", min, max);
        return getIntInput(inputPrompt, errorMessage, min, max);
    }

    public static boolean getBooleanInput(String inputPrompt, String errorMessage, String trueString, String falseString) {
        System.out.printf("%s", inputPrompt);
        final String input = scanner.nextLine().trim();

        if (input.equalsIgnoreCase(trueString)) {
            return true;
        } else if (input.equalsIgnoreCase(falseString)) {
            return false;
        } else {
            System.out.printf("%s%n", errorMessage);
            return getBooleanInput(inputPrompt, errorMessage, trueString, falseString);
        }
    }

    public static boolean getBooleanInput(String inputPrompt, String errorMessage) {
        final String trueString = "y";
        final  String falseString = "n";
        return getBooleanInput(inputPrompt, errorMessage, trueString, falseString);
    }

    public static boolean getBooleanInput(String inputPrompt) {
        final String errorMessage = "INPUT ERROR. Only accepts y or n.";
        return getBooleanInput(inputPrompt, errorMessage);
    }

    public static String getStringInput(String inputPrompt) {
        System.out.printf("%s", inputPrompt);
        final String input = scanner.nextLine().trim();

        return input;
    }
}
