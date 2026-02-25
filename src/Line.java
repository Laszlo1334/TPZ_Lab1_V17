public class Line {
    public final double a, b, c; // Коефіцієнти Ax + By + C = 0 [cite: 102, 103]
    public static final double EPSILON = 1e-8; //

    private Line(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    // Для прямих 1 та 2 (Тип 2: За двома точками) [cite: 112, 152]
    public static Line fromTwoPoints(double x1, double y1, double x2, double y2) {
        if (Math.abs(x1 - x2) <= EPSILON && Math.abs(y1 - y2) <= EPSILON) {
            // Формат помилки згідно з вимогами лаби [cite: 44, 45, 113]
            throw new IllegalArgumentException(
                    "Опис допущеної помилки: Точки для побудови прямої співпадають.\n" +
                            "Опис дій з виправлення: Введіть координати двох РІЗНИХ точок."
            );
        }
        double a = y1 - y2; // [cite: 134]
        double b = x2 - x1; // [cite: 134]
        double c = x1 * y2 - x2 * y1; // [cite: 135]
        return new Line(a, b, c);
    }

    // Для прямої 3 (Тип 4: У відрізках на осях a, b) [cite: 120, 152]
    public static Line fromIntercepts(double aIntercept, double bIntercept) {
        if (Math.abs(aIntercept) <= EPSILON || Math.abs(bIntercept) <= EPSILON) {
            throw new IllegalArgumentException(
                    "Опис допущеної помилки: Відрізки, що відтинаються на осях, дорівнюють нулю.\n" + // [cite: 44, 45]
                            "Опис дій з виправлення: Введіть ненульові значення для відрізків a та b." // [cite: 44, 45, 120, 121]
            );
        }
        double a = bIntercept;
        double b = aIntercept;
        double c = -(aIntercept * bIntercept);
        return new Line(a, b, c);
    }

    // Знаходження точки перетину двох прямих (формули Крамера) [cite: 139, 140]
    public Point getIntersection(Line other) {
        double determinant = this.a * other.b - other.a * this.b; // [cite: 141, 142]

        if (Math.abs(determinant) <= EPSILON) {
            return null; // Прямі паралельні або співпадають [cite: 142, 143]
        }

        double x = -(this.c * other.b - other.c * this.b) / determinant; // [cite: 141]
        double y = -(this.a * other.c - other.a * this.c) / determinant; // [cite: 141]
        return new Point(x, y);
    }

    // Перевірка, чи співпадають прямі [cite: 144]
    public boolean isCoincidentWith(Line other) {
        double detAB = this.a * other.b - other.a * this.b;
        double detAC = this.a * other.c - other.a * this.c;
        double detBC = this.b * other.c - other.b * this.c;

        return Math.abs(detAB) <= EPSILON &&
                Math.abs(detAC) <= EPSILON &&
                Math.abs(detBC) <= EPSILON;
    }
}