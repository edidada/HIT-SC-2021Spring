package com.hit.sc.lab1.p2.turtle;

public class LineSegment {

    private final Point start;
    private final Point end;
    private final PenColor color;

    public LineSegment(double startx, double starty, double endx, double endy, PenColor color) {
        this.start = new Point(startx, starty);
        this.end = new Point(endx, endy);
        this.color = color;
    }

    public LineSegment(Point start, Point end, PenColor color) {
        this.start = start;
        this.end = end;
        this.color = color;
    }

    public Point start() {
        return start;
    }

    public Point end() {
        return end;
    }

    public PenColor color() {
        return color;
    }

    public double length() {
        return Math.sqrt(Math.pow(this.start.x() - this.end.x(), 2.0)
                         + Math.pow(this.start.y() - this.end.y(), 2.0));
    }
}
