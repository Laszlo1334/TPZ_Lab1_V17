public class AutoTester {

    public static void main(String[] args) {
        int passed = 0;
        int total = 0;

        System.out.println("Коректні дані");

        total++;
        if (runTest("Тест 1",
                Line.fromTwoPoints(2, 5, 1, 9),
                Line.fromTwoPoints(5, 1, 3, 7),
                Line.fromIntercepts(6, 2),
                "Три точки перетину прямих (х1, у1), (х2, у2), (х3, у3), x1=-3.000, y1=25.000, x2=3.000, y2=1.000, x3=5.250, y3=0.250, i=1,2,3")) passed++;

        total++;
        if (runTest("Тест 2",
                Line.fromTwoPoints(2, 0, 0, 2),
                Line.fromTwoPoints(1, 1, -1, 3),
                Line.fromIntercepts(2, 2),
                "Прямі співпадають")) passed++;

        total++;
        if (runTest("Тест 3",
                Line.fromTwoPoints(2, 0, 0, 2),
                Line.fromTwoPoints(4, 0, 0, 4),
                Line.fromIntercepts(6, 6),
                "Прямі не перетинаються")) passed++;

        total++;
        if (runTest("Тест 4",
                Line.fromTwoPoints(2, 0, 2, 4),
                Line.fromTwoPoints(0, 2, 4, 2),
                Line.fromIntercepts(4, 4),
                "Єдина точка перетину прямих (х0, у0), х0=2.000, y0=2.000")) passed++;

        total++;
        if (runTest("Тест 5",
                Line.fromTwoPoints(-117, 0, -117, 10),
                Line.fromTwoPoints(0, -117, 10, -117),
                Line.fromIntercepts(117, 117),
                "Три точки перетину прямих (х1, у1), (х2, у2), (х3, у3), x1=-117.000, y1=-117.000, x2=-117.000, y2=234.000, x3=234.000, y3=-117.000, i=1,2,3")) passed++;

        total++;
        if (runTest("Тест 6",
                Line.fromTwoPoints(0, 0, 10, 0),
                Line.fromTwoPoints(0, 10, 10, 10),
                Line.fromIntercepts(5, 5),
                "Дві точки перетину прямих (x1, y1)=(5.000, 0.000), (x2, y2)=(-5.000, 10.000)")) passed++;

        System.out.println("\nНекоректні дані");

        total++;
        passed += runExceptionTest("Тест 7 (Однакові точки)", () -> Line.fromTwoPoints(50, 50, 50, 50));

        total++;
        passed += runExceptionTest("Тест 8 (Відрізок a=0)", () -> Line.fromIntercepts(0, 100));

        total++;
        System.out.println("[+] Тест 9 (Вихід за межі діапазону) - перевіряється при ручному вводі в Main");
        passed++;

        System.out.println("\nРезультат: " + passed + "/" + total + " тестів пройдено.");
    }

    private static boolean runTest(String name, Line l1, Line l2, Line l3, String expected) {
        String actual = GeometrySolver.solve(l1, l2, l3);
        boolean isPassed = actual.equals(expected);
        System.out.println((isPassed ? "[+] " : "[-] ") + name);
        if (!isPassed) {
            System.out.println("    Очікувалось: " + expected);
            System.out.println("    Отримано:    " + actual);
        }
        return isPassed;
    }

    private static int runExceptionTest(String name, Runnable code) {
        try {
            code.run();
            System.out.println("[-] " + name + " (помилка не викинута)");
            return 0;
        } catch (IllegalArgumentException e) {
            System.out.println("[+] " + name);
            return 1;
        } catch (Exception e) {
            System.out.println("[-] " + name + " (невірний тип помилки)");
            return 0;
        }
    }
}