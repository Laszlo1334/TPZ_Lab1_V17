import java.util.Scanner;

public class Main {
    // Границі числового проміжку згідно з табл. 2 (N=17)
    public static final double MIN_VAL = -117.0;
    public static final double MAX_VAL = 117.0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Лабораторна робота №1. Варіант 17. Золотаревич Владислав");

        try {
            // Введення першої прямої (Тип 2)
            System.out.println("\nЗадайте координати для ПЕРШОЇ прямої (через 2 точки):");
            double x1_1 = readValidInput(scanner, "x1");
            double y1_1 = readValidInput(scanner, "y1");
            double x1_2 = readValidInput(scanner, "x2");
            double y1_2 = readValidInput(scanner, "y2");
            Line line1 = Line.fromTwoPoints(x1_1, y1_1, x1_2, y1_2);

            // Введення другої прямої (Тип 2)
            System.out.println("\nЗадайте координати для ДРУГОЇ прямої (через 2 точки):");
            double x2_1 = readValidInput(scanner, "x1");
            double y2_1 = readValidInput(scanner, "y1");
            double x2_2 = readValidInput(scanner, "x2");
            double y2_2 = readValidInput(scanner, "y2");
            Line line2 = Line.fromTwoPoints(x2_1, y2_1, x2_2, y2_2);

            // Введення третьої прямої (Тип 4: у відрізках)
            System.out.println("\nЗадайте координати для ТРЕТЬОЇ прямої (у відрізках на осях a, b):");
            double a = readValidInput(scanner, "a (відрізок на Ox)");
            double b = readValidInput(scanner, "b (відрізок на Oy)");
            Line line3 = Line.fromIntercepts(a, b);

            // Обчислення та виведення результату
            System.out.println("\n--- РЕЗУЛЬТАТ ---");
            String result = GeometrySolver.solve(line1, line2, line3);
            System.out.println(result);

        } catch (IllegalArgumentException e) {
            // Виведення повідомлень про позаштатні ситуації
            System.err.println("\nВИКЛЮЧЕННЯ:");
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println("\nНеочікувана помилка вводу. Перезапустіть програму.");
        } finally {
            scanner.close();
        }
    }

    // Метод для зчитування з перевіркою меж (еквівалентне розбиття)
    private static double readValidInput(Scanner scanner, String varName) {
        System.out.print(varName + " = ");
        double val = scanner.nextDouble();
        if (val < MIN_VAL || val > MAX_VAL) {
            throw new IllegalArgumentException(
                    "Опис допущеної помилки: Введене значення " + val + " виходить за межі проміжку [" + MIN_VAL + "; " + MAX_VAL + "].\n" +
                            "Опис дій з виправлення: Введіть число, що належить вказаному діапазону."
            );
        }
        return val;
    }
}