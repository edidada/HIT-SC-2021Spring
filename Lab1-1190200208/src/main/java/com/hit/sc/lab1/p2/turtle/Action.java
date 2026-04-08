package com.hit.sc.lab1.p2.turtle;

public class Action {

    public enum ActionType {
        FORWARD, TURN, COLOR
    }

    private final ActionType type;
    private final String displayString;
    private final LineSegment lineSegment;

    public Action(ActionType type, String displayString, LineSegment lineSeg) {
        this.type = type;
        this.displayString = displayString;
        this.lineSegment = lineSeg;
    }

    public ActionType type() {
        return type;
    }

    @Override
    public String toString() {
        return displayString;
    }

    public LineSegment lineSegment() {
        return lineSegment;
    }
}
