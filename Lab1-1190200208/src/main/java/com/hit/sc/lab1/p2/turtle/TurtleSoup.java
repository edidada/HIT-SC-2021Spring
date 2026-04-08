package com.hit.sc.lab1.p2.turtle;

import java.util.*;

public class TurtleSoup {

    public static void drawSquare(Turtle turtle, int sideLength) {
        for (double i = 0; i < 360; i = i + 90) {
            turtle.forward(sideLength);
            turtle.turn(90);
        }
    }

    public static double calculateRegularPolygonAngle(int sides) {
        double doubleSides = sides;
        return (180 * (doubleSides - 2)) / doubleSides;
    }

    public static int calculatePolygonSidesFromAngle(double angle) {
        int i = 3;
        for (; ((i * angle) / (i - 2)) - 180 > Math.pow(10, -2); i++) ;
        return i;
    }

    public static void drawRegularPolygon(Turtle turtle, int sides, int sideLength) {
        double ang = calculateRegularPolygonAngle(sides);
        for (int i = 0; i < sides; i++) {
            turtle.forward(sideLength);
            turtle.turn(180 - ang);
        }
    }

    public static double calculateBearingToPoint(double currentBearing, int currentX, int currentY,
                                                 int targetX, int targetY) {
        double ang = 0;
        double delta_x = targetX - currentX;
        int delta_y = targetY - currentY;
        if (delta_y == 0) {
            if (delta_x > 0)
                ang = 90 - currentBearing;
            else
                ang = 270 - currentBearing;
            if (ang <= 0)
                ang += 360;
            return ang;
        }
        if (delta_x == 0) {
            if (delta_y > 0)
                ang = 360 - currentBearing;
            else
                ang = 180 - currentBearing;
            if (ang <= 0)
                ang += 360;
            if (ang == 360)
                ang = 0;
            return ang;
        }
        double tan = delta_y / delta_x;
        double relative_ang = Math.abs(Math.toDegrees(Math.atan(tan)));
        if (delta_x > 0 && delta_y > 0)
            ang = 90 - relative_ang - currentBearing;
        if (delta_x > 0 && delta_y < 0)
            ang = 90 + relative_ang - currentBearing;
        if (delta_x < 0 && delta_y < 0)
            ang = 270 - relative_ang - currentBearing;
        if (delta_x < 0 && delta_y > 0)
            ang = 270 + relative_ang - currentBearing;
        if (ang <= 0)
            ang += 360;
        if (ang == 360)
            ang = 0;
        return ang;
    }

    public static List<Double> calculateBearings(List<Integer> xCoords, List<Integer> yCoords) {
        List<Double> angles = new ArrayList<Double>();
        int currentX = xCoords.get(0);
        int currentY = yCoords.get(0);
        int targetX, targetY;
        double angle = 0;
        for (int i = 1; i < xCoords.size(); i++) {
            targetX = xCoords.get(i);
            targetY = yCoords.get(i);
            angle = calculateBearingToPoint(angle, currentX, currentY, targetX, targetY);
            angles.add(angle);
            currentX = targetX;
            currentY = targetY;
        }
        return angles;
    }

    public static Set<Point> convexHull(Set<Point> points) {
        if (points.size() <= 3)
            return points;
        Set<Point> minpoints = new HashSet<>();
        List<Point> pointsList = new ArrayList<>(points);
        Point initial = new Point(Double.MAX_VALUE, Double.MAX_VALUE);
        for (Point point : pointsList)
            if (point.y() < initial.y())
                initial = point;
        minpoints.add(initial);
        Point current = initial;
        Point target;
        Point temp;
        double bearing = 0;
        double angle, temp_angle;
        double distance, temp_distance;
        int i;

        if (pointsList.get(0) != current)
            target = pointsList.get(0);
        else
            target = pointsList.get(1);

        while (target != initial) {
            angle = 360;
            temp_angle = 0;
            distance = Double.MAX_VALUE;
            temp_distance = 0;
            i = 0;
            for (; i < pointsList.size(); i++) {
                temp = pointsList.get(i);
                if (temp != current) {
                    temp_angle = calculateBearingToPoint(bearing, (int) current.x(), (int) current.y(), (int) temp.x(), (int) temp.y());
                    temp_distance = Math.pow(current.x() - temp.x(), 2) + Math.pow(current.y() - temp.y(), 2);
                    if (temp_angle < angle) {
                        angle = temp_angle;
                        distance = temp_distance;
                        target = temp;
                    } else if (temp_angle == angle) {
                        if (temp_distance > distance)
                            target = temp;
                    }
                }
            }
            current = target;
            bearing += angle;
            if (target != initial)
                minpoints.add(target);
        }
        return minpoints;
    }

    public static void drawPersonalArt(Turtle turtle) {
        int number;
        for (int i = 1; i <= 800; i++) {
            turtle.forward(2 * i);
            turtle.turn(235);
            number = i % 3;
            switch (number) {
                case 1:
                    turtle.color(PenColor.BLUE);
                    break;
                case 2:
                    turtle.color(PenColor.RED);
                    break;
                default:
                    turtle.color(PenColor.GREEN);
                    break;
            }
        }
    }

    public static void main(String args[]) {
        DrawableTurtle turtle = new DrawableTurtle();

//        turtle.drawPersonalArt(turtle);
        turtle.draw();
    }

}
