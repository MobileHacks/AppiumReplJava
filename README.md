# AppiumReplJava

## Build project with mvn

mvn clean install

Navigate to target folder and execute

java -jar appiumrepljava-1.0-SNAPSHOT.jar

Follow the instructions on the repl console

Start appium server on different terminal 

Sample android caps:
```
{
  "deviceName": "android",
  "app": "/Users/XXXX/Documents/workspace/PagePatternAppium/build/wordpress.apk",
  "platformName": "android",
  "newCommandTimeout": "700000",
  "appPackage": "org.wordpress.android",
  "appActivity": "org.wordpress.android.ui.WPLaunchActivity"
}
```
Sample iOS caps:
```
{
"platformName":"ios"
"deviceName":"iPhone 6"
"platformVersion":"8.3"
"app":"/Users/XXXXX/git/VodQa_MobileAutomationWorkShop/VodQA_WorkShop/build/ios.app"
}
```





