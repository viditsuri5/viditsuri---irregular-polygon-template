import java.awt.geom.*;
import java.util.ArrayList;
import gpdraw.*;

public class IrregularPolygon {

    private ArrayList<Point2D.Double> vertices;

    public IrregularPolygon() {
        vertices = new ArrayList<>();
    }

    public void add(Point2D.Double point) {
        vertices.add(point);
    }

    public double perimeter() {
        if (vertices.size() < 2) return 0.0;

        double totalDistance = 0.0;
        for (int i = 0; i < vertices.size(); i++) {
            Point2D.Double current = vertices.get(i);
            Point2D.Double next = vertices.get((i + 1) % vertices.size());
            totalDistance += current.distance(next);
        }
        return totalDistance;
    }

    public double area() {
        if (vertices.size() < 3) return 0.0;

        double sumA = 0.0, sumB = 0.0;
        for (int i = 0; i < vertices.size(); i++) {
            Point2D.Double curr = vertices.get(i);
            Point2D.Double next = vertices.get((i + 1) % vertices.size());
            sumA += curr.x * next.y;
            sumB += curr.y * next.x;
        }

        return Math.abs(sumA - sumB) * 0.5;
    }

    public void draw() {
        try {
            if (vertices.size() < 2) return;

            DrawingTool pen = new DrawingTool(new SketchPad(500, 500));
            pen.up();
            
            Point2D.Double start = vertices.get(0);
            pen.move(start.x, start.y);
            pen.down();
            
            for (Point2D.Double p : vertices) {
                pen.move(p.x, p.y);
            }

            pen.move(start.x, start.y);
        } catch (java.awt.HeadlessException e) {
            System.out.println("Graphics not supported.");
        }
    }
}