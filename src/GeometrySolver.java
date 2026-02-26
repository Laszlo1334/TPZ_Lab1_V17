import java.util.ArrayList;
import java.util.List;

public class GeometrySolver {

    public static String solve(Line l1, Line l2, Line l3) {
        if (l1.isCoincidentWith(l2) && l1.isCoincidentWith(l3) && l2.isCoincidentWith(l3)) {
            return "Lines coincide";
        }

        List<Point> points = new ArrayList<>();

        addUniquePoint(points, l1.getIntersection(l2));
        addUniquePoint(points, l1.getIntersection(l3));
        addUniquePoint(points, l2.getIntersection(l3));

        int size = points.size();

        if (size == 0) {
            return "Lines do not intersect";
        } else if (size == 1) {
            Point p = points.get(0);
            return String.format(java.util.Locale.US, "Single intersection point of lines (x0, y0), x0=%.3f, y0=%.3f", p.x, p.y);
        } else if (size == 2) {
            Point p1 = points.get(0);
            Point p2 = points.get(1);
            return String.format(java.util.Locale.US, "Two intersection points of lines (x1, y1)=(%.3f, %.3f), (x2, y2)=(%.3f, %.3f)", p1.x, p1.y, p2.x, p2.y);
        } else {
            Point p1 = points.get(0);
            Point p2 = points.get(1);
            Point p3 = points.get(2);
            return String.format(java.util.Locale.US, "Three intersection points of lines (x1, y1), (x2, y2), (x3, y3), x1=%.3f, y1=%.3f, x2=%.3f, y2=%.3f, x3=%.3f, y3=%.3f, i=1,2,3", p1.x, p1.y, p2.x, p2.y, p3.x, p3.y);
        }
    }

    private static void addUniquePoint(List<Point> points, Point newPoint) {
        if (newPoint == null) return;
        for (Point p : points) {
            if (p.isSame(newPoint)) return;
        }
        points.add(newPoint);
    }
}