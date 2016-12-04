package com.appium.repl;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Set;


/**
 * Created by saikrisv on 2016/11/30.
 */
public class DriverMobileCommand {
    public AppiumDriver<MobileElement> driver;

    public AppiumDriver<MobileElement> start(String path, String HostIP, String port)
            throws MalformedURLException, ParseException {
        if (setCapabilities(path).getCapability("deviceName").equals("android")) {
            return driver = new AndroidDriver<>(new URL("http://" + HostIP + ":" + port + "/wd/hub"),
                    setCapabilities(path));
        } else {
            return driver = new IOSDriver<>(new URL("http://" + HostIP + ":" + port + "/wd/hub"),
                    setCapabilities(path));

        }
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
            String value = (String) jsonObject.get(key);
            desiredCapabilities.setCapability(key, value);
        }
        System.out.println("Capabilities loaded" + desiredCapabilities);
        return desiredCapabilities;
    }
}
