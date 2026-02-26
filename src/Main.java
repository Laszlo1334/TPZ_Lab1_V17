import java.util.Scanner;

public class Main {
    public static final double MIN_VAL = -117.0;
    public static final double MAX_VAL = 117.0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Line line1 = null;
        Line line2 = null;
        Line line3 = null;

        while (line1 == null) {
            try {
                System.out.println("\nEnter coordinates for the first line (via 2 points):");
                double x1_1 = readValidInput(scanner, "x1");
                double y1_1 = readValidInput(scanner, "y1");
                double x1_2 = readValidInput(scanner, "x2");
                double y1_2 = readValidInput(scanner, "y2");
                line1 = Line.fromTwoPoints(x1_1, y1_1, x1_2, y1_2);
            } catch (IllegalArgumentException e) {
                System.out.println("\nLogic Error:");
                System.out.println(e.getMessage());
                System.out.println("Please try entering coordinates for the first line again.");
            }
        }

        while (line2 == null) {
            try {
                System.out.println("\nEnter coordinates for the second line (via 2 points):");
                double x2_1 = readValidInput(scanner, "x1");
                double y2_1 = readValidInput(scanner, "y1");
                double x2_2 = readValidInput(scanner, "x2");
                double y2_2 = readValidInput(scanner, "y2");
                line2 = Line.fromTwoPoints(x2_1, y2_1, x2_2, y2_2);
            } catch (IllegalArgumentException e) {
                System.out.println("\nLogic Error:");
                System.out.println(e.getMessage());
                System.out.println("Please try entering coordinates for the second line again.");
            }
        }

        while (line3 == null) {
            try {
                System.out.println("\nEnter coordinates for the third line (intercepts a, b):");
                double coefa = readValidInput(scanner, "coefa");
                double coefb = readValidInput(scanner, "coefb");
                line3 = Line.fromIntercepts(coefa, coefb);
            } catch (IllegalArgumentException e) {
                System.out.println("\nLogic Error:");
                System.out.println(e.getMessage());
                System.out.println("Please try entering coordinates for the third line again.");
            }
        }

        try {
            System.out.println("\nResult:");
            String result = GeometrySolver.solve(line1, line2, line3);
            System.out.println(result);
        } catch (Exception e) {
            System.out.println("\nUnexpected error calculating the result.");
        } finally {
            scanner.close();
        }
    }

    private static double readValidInput(Scanner scanner, String varName) {
        while (true) {
            System.out.print(varName + " = ");
            String input = scanner.next();
            try {
                double val = Double.parseDouble(input);
                if (val < MIN_VAL || val > MAX_VAL) {
                    System.out.println("Error: The entered value " + val + " is out of range [" + MIN_VAL + "; " + MAX_VAL + "].");
                    System.out.println("Fix action: Enter a number within the specified range.");
                } else {
                    return val;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid input format. Expected a number.");
                System.out.println("Fix action: Enter a valid numeric value.");
            }
        }
    }
}