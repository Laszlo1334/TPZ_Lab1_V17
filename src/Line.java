public class Line {
    public final double a, b, c;
    public static final double EPSILON = 1e-8;

    private Line(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public static Line fromTwoPoints(double x1, double y1, double x2, double y2) {
        if (Math.abs(x1 - x2) <= EPSILON && Math.abs(y1 - y2) <= EPSILON) {
            throw new IllegalArgumentException(
                    "Error description: Points used to build the line coincide.\n" +
                            "Fix action: Enter coordinates of two DIFFERENT points."
            );
        }
        double a = y1 - y2;
        double b = x2 - x1;
        double c = x1 * y2 - x2 * y1;
        return new Line(a, b, c);
    }

    public static Line fromIntercepts(double aIntercept, double bIntercept) {
        if (Math.abs(aIntercept) <= EPSILON || Math.abs(bIntercept) <= EPSILON) {
            throw new IllegalArgumentException(
                    "Error description: Intercepts on the axes are zero.\n" +
                            "Fix action: Enter non-zero values for intercepts a and b."
            );
        }
        // Convert intercept form x/aIntercept + y/bIntercept = 1 to ax + by + c = 0
        double coefA = bIntercept;
        double coefB = aIntercept;
        double c = -(aIntercept * bIntercept);
        return new Line(coefA, coefB, c);
    }

    public Point getIntersection(Line other) {
        double determinant = this.a * other.b - other.a * this.b;

        if (Math.abs(determinant) <= EPSILON) {
            return null;
        }

        double x = -(this.c * other.b - other.c * this.b) / determinant;
        double y = -(this.a * other.c - other.a * this.c) / determinant;
        return new Point(x, y);
    }

    public boolean isCoincidentWith(Line other) {
        double detAB = this.a * other.b - other.a * this.b;
        double detAC = this.a * other.c - other.a * this.c;
        double detBC = this.b * other.c - other.b * this.c;

        return Math.abs(detAB) <= EPSILON && Math.abs(detAC) <= EPSILON && Math.abs(detBC) <= EPSILON;
    }
}