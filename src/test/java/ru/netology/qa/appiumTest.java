package ru.netology.qa;

import io.appium.java_client.android.AndroidDriver;

import java.net.MalformedURLException;
import java.net.URL;

import io.appium.java_client.appmanagement.BaseOptions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import ru.netology.qa.screens.MainScreen;

public class appiumTest {

    private AndroidDriver driver;

    private URL getUrl() {
        try {
            return new URL("http://127.0.0.1:4723");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Before
    public void setUp() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("appium:deviceName", "Android Emulator");
        desiredCapabilities.setCapability("appium:appPackage", "ru.netology.testing.uiautomator");
        desiredCapabilities.setCapability("appium:appActivity", "ru.netology.testing.uiautomator.MainActivity");
        desiredCapabilities.setCapability("appium:automationName", "UiAutomator2");
        desiredCapabilities.setCapability("appium:ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("appium:nativeWebScreenshot", true);
        desiredCapabilities.setCapability("appium:newCommandTimeout", 3600);
        desiredCapabilities.setCapability("appium:connectHardwareKeyboard", true);


        driver = new AndroidDriver(getUrl(), desiredCapabilities);
    }

    @Test
    public void textBeforeAndAfterTest() {
        MainScreen button = new MainScreen(driver);

        String textBefore = button.textToBeChanged.getText();
        button.userInput.sendKeys(" ");
        button.buttonChange.click();
        String textAfter = button.textToBeChanged.getText();
        Assertions.assertEquals(textBefore, textAfter);
    }

    @Test
    public void ActivityTest() {
        MainScreen button = new MainScreen(driver);
        String newText = "Hi, Netology";
        button.userInput.sendKeys(newText);
        button.buttonActivity.click();
        button.activityText.isDisplayed();
        Assertions.assertEquals(newText, button.activityText.getText()) ;
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}