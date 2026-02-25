import java.util.Scanner;

public class Main {
    public static final double MIN_VAL = -117.0;
    public static final double MAX_VAL = 117.0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Laboratory work 1. Variant 17. Vladyslav Zolotarevych");

        try {
            System.out.println("Enter coordinates for the first line (via 2 points):");
            double x1_1 = readValidInput(scanner, "x1");
            double y1_1 = readValidInput(scanner, "y1");
            double x1_2 = readValidInput(scanner, "x2");
            double y1_2 = readValidInput(scanner, "y2");
            Line line1 = Line.fromTwoPoints(x1_1, y1_1, x1_2, y1_2);

            System.out.println("Enter coordinates for the second line (via 2 points):");
            double x2_1 = readValidInput(scanner, "x1");
            double y2_1 = readValidInput(scanner, "y1");
            double x2_2 = readValidInput(scanner, "x2");
            double y2_2 = readValidInput(scanner, "y2");
            Line line2 = Line.fromTwoPoints(x2_1, y2_1, x2_2, y2_2);

            System.out.println("Enter coordinates for the third line (intercepts a, b):");
            double a = readValidInput(scanner, "a");
            double b = readValidInput(scanner, "b");
            Line line3 = Line.fromIntercepts(a, b);

            System.out.println("\nResult:");
            String result = GeometrySolver.solve(line1, line2, line3);
            System.out.println(result);

        } catch (IllegalArgumentException e) {
            System.out.println("\nError:");
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("\nInput error. Restart the program.");
        } finally {
            scanner.close();
        }
    }

    private static double readValidInput(Scanner scanner, String varName) {
        System.out.print(varName + " = ");
        double val = scanner.nextDouble();
        if (val < MIN_VAL || val > MAX_VAL) {
            throw new IllegalArgumentException(
                    "Error description: The entered value " + val + " is out of range [" + MIN_VAL + "; " + MAX_VAL + "].\n" +
                            "Fix action: Enter a number within the specified range."
            );
        }
        return val;
    }
}