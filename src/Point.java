public class Point {
    public final double x;
    public final double y;
    public static final double EPSILON = 1e-8;

    public Point(double x, double y) {
        this.x = (Math.abs(x) < EPSILON) ? 0.0 : x;
        this.y = (Math.abs(y) < EPSILON) ? 0.0 : y;
    }

    public boolean isSame(Point other) {
        if (other == null) return false;
        return Math.abs(this.x - other.x) <= EPSILON && Math.abs(this.y - other.y) <= EPSILON;
    }

    @Override
    public String toString() {
        return String.format(java.util.Locale.US, "(%.3f, %.3f)", x, y);
    }
}