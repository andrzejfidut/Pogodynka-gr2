package group02.weatherforecast;

import java.util.Scanner;

public class UserDataInput {
    private final Scanner keyboardScanner = new Scanner(System.in);

    public String getNonNullStringInput() {
        String output = null;
        while (output == null) {
            String input = keyboardScanner.nextLine();
            if (input.isBlank()) {
                System.out.println("Error, input String cannot be empty.");
            } else {
                output = input;
            }
        }
        return output;
    }

    public String getNullableStringInput() {
        String input = keyboardScanner.nextLine();
        if (input.isBlank()) {
            return null;
        } else return input;
    }
    public int getNonZeroIntInput() {
        int output = 0;
        while (output == 0) {
            int input = 0;
            input = keyboardScanner.nextInt();
            if (input < 1) {
                System.out.println("Error, input cannot be lower than 0.");
            } else {
                output = input;
            }
        }
        return output;
    }

    public String getData() {
        StringBuilder outputStringBuilder = new StringBuilder();
        System.out.println("Please enter desired country: ");
        outputStringBuilder.append(getNonNullStringInput()).append(", ");

        System.out.println("Please enter desired city: ");
        outputStringBuilder.append(getNonNullStringInput()).append(", ");

        System.out.println("Please enter the city's postal code: ");
        outputStringBuilder.append(getNonNullStringInput());

        System.out.println("Please enter desired street (optional): ");
        String streetName = getNullableStringInput();
        if (streetName == null) {
            return outputStringBuilder.toString();
        } else {
            outputStringBuilder.append(", ").append(streetName);
            System.out.println("Please enter desired locale number: ");
            outputStringBuilder.append(", ").append(getNonZeroIntInput());
            return outputStringBuilder.toString();
        }
    }
}
