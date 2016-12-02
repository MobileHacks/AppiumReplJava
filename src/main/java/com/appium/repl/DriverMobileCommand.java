package com.appium.repl;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.Set;


/**
 * Created by saikrisv on 2016/11/30.
 */
public class DriverMobileCommand {
    public AppiumDriver<MobileElement> driver;
    public AppiumDriverLocalService service;

    public AppiumDriver<MobileElement> start(String path)
        throws MalformedURLException, ParseException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        service = new AppiumServiceBuilder().
            withArgument(GeneralServerFlag.LOG_LEVEL, "info")
            .withLogFile(new File(System.getProperty("user.dir")
                + "/appium_server_logs" + dtf.format(now) + ".txt")).usingAnyFreePort()
            .build();
        service.start();
        if (setCapabilities(path).getCapability("deviceName").equals("android")) {
            return driver = new AndroidDriver<>(service.getUrl(), setCapabilities(path));
        } else {
            return driver = new IOSDriver<>(service.getUrl(), setCapabilities(path));

        }
    }

    public void stopServer() {
        service.stop();
    }

    private static DesiredCapabilities setCapabilities(String path) throws ParseException {
        JSONParser parser = new JSONParser();
        Object obj = null;
        try {
            obj = parser.parse(new FileReader(path));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        JSONObject jsonObject = (JSONObject) obj;
        System.out.println(jsonObject);
        Set keys = jsonObject.keySet();
        Iterator a = keys.iterator();
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        while (a.hasNext()) {
            String key = (String) a.next();
            // loop to get the dynamic key
            String value = (String) jsonObject.get(key);
            desiredCapabilities.setCapability(key, value);
        }
        System.out.println("Capabilities loaded" + desiredCapabilities);
        return desiredCapabilities;
    }
}
