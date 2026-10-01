package com.demo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CalculatorTest {

    Calculator c = new Calculator();

    @Test
    void addWorks() {
        assertEquals(6, c.add(2, 3));
    }

    @Test
    void subtractWorks() {
        assertEquals(1, c.subtract(3, 2));
    }

    @Test
    void multiplyWorks() {
        assertEquals(6, c.multiply(2, 3));
    }

    @Test
    void divideWorks() {
        assertEquals(2, c.divide(6, 3));
    }

    @Test
    void divideByZeroThrows() {
        assertThrows(IllegalArgumentException.class, () -> c.divide(1, 0));
    }
}
