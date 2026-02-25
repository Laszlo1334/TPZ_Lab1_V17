import java.util.ArrayList;
import java.util.List;

public class GeometrySolver {

    // Головний метод, який повертає потрібний рядок результату
    public static String solve(Line l1, Line l2, Line l3) {
        // Перевіряємо, чи всі три прямі співпадають
        if (l1.isCoincidentWith(l2) && l2.isCoincidentWith(l3)) {
            return "Прямі співпадають"; // [cite: 23]
        }

        List<Point> points = new ArrayList<>();

        // Знаходимо перетини кожної пари. Метод getIntersection поверне null, якщо вони паралельні
        addUniquePoint(points, l1.getIntersection(l2));
        addUniquePoint(points, l1.getIntersection(l3));
        addUniquePoint(points, l2.getIntersection(l3));

        int size = points.size();

        // Формуємо вивід строго за методичкою
        if (size == 0) {
            return "Прямі не перетинаються"; // [cite: 24]
        } else if (size == 1) {
            Point p = points.get(0);
            return String.format(java.util.Locale.US, "Єдина точка перетину прямих (х0, у0), х0=%.3f, y0=%.3f", p.x, p.y); // [cite: 25]
        } else if (size == 2) {
            Point p1 = points.get(0);
            Point p2 = points.get(1);
            return String.format(java.util.Locale.US, "Дві точки перетину прямих (x1, y1)=(%.3f, %.3f), (x2, y2)=(%.3f, %.3f)", p1.x, p1.y, p2.x, p2.y); // [cite: 26]
        } else {
            Point p1 = points.get(0);
            Point p2 = points.get(1);
            Point p3 = points.get(2);
            return String.format(java.util.Locale.US, "Три точки перетину прямих (х1, у1), (х2, у2), (х3, у3), x1=%.3f, y1=%.3f, x2=%.3f, y2=%.3f, x3=%.3f, y3=%.3f, i=1,2,3", p1.x, p1.y, p2.x, p2.y, p3.x, p3.y); // [cite: 27]
        }
    }

    // Допоміжний метод: додає точку в список тільки якщо такої ще немає
    private static void addUniquePoint(List<Point> points, Point newPoint) {
        if (newPoint == null) return; // Ігноруємо паралельні прямі
        for (Point p : points) {
            if (p.isSame(newPoint)) return; // Ігноруємо дублікати
        }
        points.add(newPoint);
    }
}