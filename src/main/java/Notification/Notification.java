package Notification;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class Notification {
    public static void Allow(AndroidDriver driver) {
        driver.findElement(By.id("com.android.permissioncontroller:id/permission_allow_button")).click();
    }

    public static void NotAllow(AndroidDriver driver) {
        driver.findElement(By.id("com.android.permissioncontroller:id/permission_deny_button")).click();
    }
}
