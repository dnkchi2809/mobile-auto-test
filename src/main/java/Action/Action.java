package Action;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;

import java.io.IOException;

public class Action {
    public static void usingFingerprint() throws InterruptedException, IOException {
        Thread.sleep(2000);
        Runtime.getRuntime().exec("adb -e emu finger touch 1");
    }
    public static void swipeToLeft(AppiumDriver appiumDriver){
        Dimension windowSize = appiumDriver.manage().window().getSize();
        int screenWidth = windowSize.getWidth();
        int point = 90 * screenWidth / 100;

        ((JavascriptExecutor) appiumDriver).executeScript("mobile: swipeGesture", ImmutableMap.of(
                "left", 0, "top", point, "width", point, "height", 0,
                "direction", "left",
                "percent", 0.75
        ));
    }


}
