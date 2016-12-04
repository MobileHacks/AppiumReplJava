package com.appium.repl;

import io.appium.java_client.AppiumDriver;
import javarepl.Main;

import java.lang.reflect.Method;

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
        console("-----------------------------------------------------------");
        console(" :::- Appium Java REPL -::: " + SEPARATOR);
        console("Enter import static com.appium.repl.Appium.*; " + SEPARATOR);
        console("Enter import io.appium.java_client.*; " + SEPARATOR);
        console("Enter AppiumDriver<MobileElement> driver = driver.start(path_to_json_file in quotes,appiumserverhost,appiumserverport)" + SEPARATOR);
        console("Type help() to get all appium commands");
        console("-----------------------------------------------------------" + SEPARATOR);
    }
    public static void console(final String msg) {
        System.out.println(msg);
    }

    public static void help(){
        Class c = AppiumDriver.class;
        for (Method m : c.getDeclaredMethods()) {
            System.err.println(m.getName());
        }
    }
}
