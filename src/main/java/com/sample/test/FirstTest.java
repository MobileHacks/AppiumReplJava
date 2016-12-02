package com.sample.test;

import io.appium.java_client.AppiumDriver;
import org.junit.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * Created by saikrisv on 2016/11/30.
 */
public class FirstTest {

    @Test
    public void testApp() {
        Class c = AppiumDriver.class;
        for (Method m : c.getDeclaredMethods()) {
            System.err.println(m.getName());
        }
    }
}
