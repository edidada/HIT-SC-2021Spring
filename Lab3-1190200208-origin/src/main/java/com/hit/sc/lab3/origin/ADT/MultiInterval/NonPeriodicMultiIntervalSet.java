package com.hit.sc.lab3.origin.ADT.MultiInterval;

public class NonPeriodicMultiIntervalSet<L> extends MultiIntervalSetDecorator<L> implements MultiIntervalSet<L> {

    // Constructor
    public NonPeriodicMultiIntervalSet(MultiIntervalSet<L> multiIntervalSet) {
        super(multiIntervalSet);
    }

    public boolean checkPeriodic() {
        return false;
    }
}
