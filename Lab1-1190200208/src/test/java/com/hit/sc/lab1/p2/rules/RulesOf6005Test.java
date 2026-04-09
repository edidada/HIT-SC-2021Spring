package com.hit.sc.lab1.p2.rules;

import static org.junit.Assert.*;

import com.hit.sc.lab1.p2.RulesOf6005;
import org.junit.Test;

public class RulesOf6005Test {

    @Test
    public void testMayUseCodeInAssignment() {
        assertFalse("Expected false: un-cited publicly-available code",
                RulesOf6005.mayUseCodeInAssignment(false, true, false, false, false));
        assertTrue("Expected true: self-written required code",
                RulesOf6005.mayUseCodeInAssignment(true, false, true, true, true));
    }
}
