package com.appium.repl;

import javarepl.Main;

/**
 * Created by saikrisv on 2016/11/30.
 */
public class Appium {
    public static final String SEPARATOR = System.getProperty("line.separator");

    public static DriverMobileCommand driver;

    static {
        driver = new DriverMobileCommand();
    }

    public static void main(String... args) throws Exception {
        welcome();
        Main.main(args);
    }

    private static void welcome() {
        console("----------------------------");
        console(" :::- Appium Java REPL -::: " + SEPARATOR);
        console("-----------------------------" + SEPARATOR);
    }
    public static void console(final String msg) {
        System.out.println(msg);
    }
}
