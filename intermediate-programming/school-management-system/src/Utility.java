import java.util.*;

public class Utility {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String errorMsg = "[ Error ] Input is not valid. Try again.";

    public static int getIntInput(String prompt) {
        return getIntInput(prompt, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public static int getIntInput(String prompt, int min, int max) {
        while (true) {
            try {
                System.out.print(prompt);
                final int input = Integer.parseInt(scanner.nextLine().trim());

                if (input >= min && input <= max)
                    return input;

                System.out.printf("\n%s\n", errorMsg);
                System.out.printf("\n[ Info ] Valid range is %d-%d.\n\n", min, max);
            } catch (NoSuchElementException e) {
                System.out.println("\n\n[ Info ] Input interrupted. Exiting...");
                System.exit(1);
            } catch (Exception e) {
                System.out.printf("\n%s\n\n", errorMsg);
            }
        }
    }

    public static String getStringInput(String prompt) {
        return getStringInput(prompt, Integer.MAX_VALUE, null, false);
    }

    public static String getStringInput(String prompt, int max) {
        return getStringInput(prompt, max, null, false);
    }

    public static String getStringInput(String prompt, String[] domain) {
        return getStringInput(prompt, Integer.MAX_VALUE, domain, false);
    }

    public static String getStringInput(String prompt, boolean required) {
        return getStringInput(prompt, Integer.MAX_VALUE, null, required);
    }

    public static String getStringInput(String prompt, int max, boolean required) {
        return getStringInput(prompt, max, null, required);
    }

    public static String getStringInput(String prompt, int max, String[] domain, boolean required) {
        while (true) {
            try {
                System.out.print(prompt);
                final String input = scanner.nextLine().trim();

                if (required && input.isEmpty()) {
                    System.out.printf("\n%s\n", errorMsg);
                    System.out.println("\n[ Info ] Input is required.\n");
                    continue;
                }

                if (input.length() > max) {
                    System.out.printf("\n%s\n", errorMsg);
                    System.out.printf("\n[ Info ] Maximum length of input is %d.\n\n", max);
                    continue;
                }

                if (domain == null)
                    return input;

                for (final String value : domain)
                    if (value.equalsIgnoreCase(input))
                        return value;

                System.out.printf("\n%s\n", errorMsg);
                System.out.printf("\n[ Info ] Possible values are: %s.\n\n", String.join(", ", domain));
            } catch (NoSuchElementException e) {
                System.out.println("\n\n[ Info ] Input interrupted. Exiting...");
                System.exit(1);
            } catch (Exception e) {
                System.out.printf("\n%s\n\n", errorMsg);
            }
        }
    }
}
