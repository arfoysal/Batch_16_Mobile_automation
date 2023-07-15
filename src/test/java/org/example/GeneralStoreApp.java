package org.example;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class GeneralStoreApp {
    public AndroidDriver<AndroidElement> driver;

    @BeforeSuite
    public void setDriver() throws MalformedURLException {
        File f = new File("src");
        File fs = new File(f,"General-Store.apk");
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("platformName", "android");
        cap.setCapability("udid", "emulator-5554");
        cap.setCapability("appPackage","com.androidsample.generalstore");
        cap.setCapability("appActivity", "com.androidsample.generalstore.SplashActivity");
        cap.setCapability("app", fs.getAbsolutePath());
        driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterSuite
    public void quitDriver(){
        driver.quit();
    }

    @Test
    public void testStartPage() throws InterruptedException {
        driver.findElement(By.id("android:id/text1")).click();
        driver.findElement(By.xpath("//android.widget.TextView[@text='Andorra']")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Test test");
        driver.findElement(By.id("com.androidsample.generalstore:id/radioFemale")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
        Thread.sleep(5000);
        Assert.assertEquals(driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")).getText(), "Products");
    }
}
