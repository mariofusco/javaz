package org.javaz;

import org.junit.*;

import static org.junit.Assert.assertEquals;

public class SalaryCalculatorTest {

    @Test
    public void testImperative() {
        SalaryCalculator salaryCalculator = new SalaryCalculator();
        assertEquals(1000, salaryCalculator.calculateImperative(1000, false, false, false, false), 0.01);
        assertEquals(1320, salaryCalculator.calculateImperative(1000, true, true, false, false), 0.01);
        assertEquals(630, salaryCalculator.calculateImperative(1000, false, false, true, true), 0.01);
        assertEquals(831.6, salaryCalculator.calculateImperative(1000, true, true, true, true), 0.01);
    }

    @Test
    public void testFunctional() {
        SalaryCalculator salaryCalculator = new SalaryCalculator();
        assertEquals(1000, salaryCalculator.calculate(1000, false, false, false, false), 0.01);
        assertEquals(1320, salaryCalculator.calculate(1000, true, true, false, false), 0.01);
        assertEquals(630, salaryCalculator.calculate(1000, false, false, true, true), 0.01);
        assertEquals(831.6, salaryCalculator.calculate(1000, true, true, true, true), 0.01);
    }
}
