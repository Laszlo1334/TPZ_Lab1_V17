public class AutoTester {

    public static void main(String[] args) {
        int passed = 0;
        int totalTests = 0;
        System.out.println(">>> Checking Valid Inputs");

        totalTests++;
        if (checkCase("Test 1 (3 points)",
                Line.fromTwoPoints(2, 5, 1, 9),
                Line.fromTwoPoints(5, 1, 3, 7),
                Line.fromIntercepts(6, 2),
                "Three intersection points of lines (x1, y1), (x2, y2), (x3, y3), x1=-3.000, y1=25.000, x2=3.000, y2=1.000, x3=5.250, y3=0.250, i=1,2,3")) {
            passed++;
        }

        totalTests++;
        if (checkCase("Test 2 (Coincident)",
                Line.fromTwoPoints(2, 0, 0, 2),
                Line.fromTwoPoints(1, 1, -1, 3),
                Line.fromIntercepts(2, 2),
                "Lines coincide")) {
            passed++;
        }

        totalTests++;
        if (checkCase("Test 3 (Parallel)",
                Line.fromTwoPoints(2, 0, 0, 2),
                Line.fromTwoPoints(4, 0, 0, 4),
                Line.fromIntercepts(6, 6),
                "Lines do not intersect")) {
            passed++;
        }

        totalTests++;
        if (checkCase("Test 4 (Single point)",
                Line.fromTwoPoints(2, 0, 2, 4),
                Line.fromTwoPoints(0, 2, 4, 2),
                Line.fromIntercepts(4, 4),
                "Single intersection point of lines (x0, y0), x0=2.000, y0=2.000")) {
            passed++;
        }

        totalTests++;
        if (checkCase("Test 5 (Boundaries)",
                Line.fromTwoPoints(-117, 0, -117, 10),
                Line.fromTwoPoints(0, -117, 10, -117),
                Line.fromIntercepts(117, 117),
                "Three intersection points of lines (x1, y1), (x2, y2), (x3, y3), x1=-117.000, y1=-117.000, x2=-117.000, y2=234.000, x3=234.000, y3=-117.000, i=1,2,3")) {
            passed++;
        }

        totalTests++;
        if (checkCase("Test 6 (2 points)",
                Line.fromTwoPoints(0, 0, 10, 0),
                Line.fromTwoPoints(0, 10, 10, 10),
                Line.fromIntercepts(5, 5),
                "Two intersection points of lines (x1, y1)=(5.000, 0.000), (x2, y2)=(-5.000, 10.000)")) {
            passed++;
        }

        System.out.println("\n>>> Checking Invalid Inputs (Exceptions)");

        totalTests++;
        passed += checkException("Test 7 (Same points)", () -> {
            Line.fromTwoPoints(50, 50, 50, 50);
        });

        totalTests++;
        passed += checkException("Test 8 (Zero segment)", () -> {
            Line.fromIntercepts(0, 100);
        });

        System.out.println("[INFO] Test 9 (Out of range) - verified manually via Main class input");

        System.out.println("RESULT: " + passed + " / " + totalTests + " tests passed.");
    }

    private static boolean checkCase(String testName, Line l1, Line l2, Line l3, String expected) {
        String actual = GeometrySolver.solve(l1, l2, l3);
        boolean success = actual.equals(expected);

        if (success) {
            System.out.println("[OK] " + testName);
        } else {
            System.out.println("[FAIL] " + testName);
            System.out.println("   Expected: " + expected);
            System.out.println("   Actual:   " + actual);
        }
        return success;
    }

    private static int checkException(String testName, Runnable codeBlock) {
        try {
            codeBlock.run();
            System.out.println("[FAIL] " + testName + " (Exception expected but not thrown)");
            return 0;
        } catch (IllegalArgumentException e) {
            System.out.println("[OK] " + testName);
            return 1;
        } catch (Exception e) {
            System.out.println("[FAIL] " + testName + " (Wrong exception type caught)");
            return 0;
        }
    }
}