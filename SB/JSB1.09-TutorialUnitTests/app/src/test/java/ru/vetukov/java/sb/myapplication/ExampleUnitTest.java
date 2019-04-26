package ru.vetukov.java.sb.myapplication;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ExampleUnitTest {
    public SimpleCalculator mCalculator;

    @Before
    public void init() {
        mCalculator = new SimpleCalculator();
    }

    @Test
    public void testAddition() {
        Assert.assertEquals("Addition failed", 2, mCalculator.add(1, 1));
    }

    @Test
    public void testSubtraction() {
        Assert.assertEquals("Subtraction failed", 0, mCalculator.sub(1, 1));
    }
}