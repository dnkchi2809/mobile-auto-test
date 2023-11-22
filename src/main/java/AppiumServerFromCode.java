import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.*;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;


public class AppiumServerFromCode {

    static AppiumDriverLocalService server;
    static AndroidDriver driver;

    static void setInstance(){
        AppiumServiceBuilder builder = new AppiumServiceBuilder();
        builder
                .usingPort(4723)
                .withIPAddress("127.0.0.1");
        server = AppiumDriverLocalService.buildService(builder);
    }

    static AppiumDriverLocalService getInstance(){
        if(server == null){
            setInstance();
        }
        return server;
    }

    public static void startAppiumServer(){
        getInstance().start();
        System.out.println("startAppiumServer");
        System.out.println("URL: " + server.getUrl());
        System.out.println("is Server running: " +  server.isRunning());
    }

    public static void stopAppiumServer(){
        if(server == null){
            getInstance().stop();
        }
        System.out.println("stopAppiumServer");
    }

    public static void usingFingerprint() throws InterruptedException, IOException {
        Thread.sleep(2000);
        Runtime.getRuntime().exec("adb -e emu finger touch 1");
    }

    public static void unlockLockScreenByFingerprint() throws InterruptedException, IOException {
        if(driver.findElement(By.id("com.android.systemui:id/keyguard_message_area_container")).isDisplayed()){
            usingFingerprint();
        }
    }

    protected static void swipeToLeft(AppiumDriver appiumDriver){
        Dimension windowSize = appiumDriver.manage().window().getSize();
        int screenWidth = windowSize.getWidth();
        int point = 90 * screenWidth / 100;

        ((JavascriptExecutor) appiumDriver).executeScript("mobile: swipeGesture", ImmutableMap.of(
                "left", 0, "top", point, "width", point, "height", 0,
                "direction", "left",
                "percent", 0.75
        ));
    }

    public static void loginZiiChat() throws IOException, InterruptedException {
        Thread.sleep(3000);
        swipeToLeft(driver);
        Thread.sleep(2000);
        swipeToLeft(driver);


        Thread.sleep(4000);
        driver.findElement(By.id("com.ziichat.android.media:id/buttonContinue")).click();

        Thread.sleep(2000);
        driver.findElement(By.id("com.ziichat.android.media:id/btn_get_started")).click();

        Thread.sleep(3000);
        driver.findElement(By.id("com.ziichat.android.media:id/txtUsername")).sendKeys("automobile.halome.0001");

        Thread.sleep(2000);
        driver.findElement(By.id("com.ziichat.android.media:id/btnContinue")).click();

        Thread.sleep(2000);
        if (driver.findElement(By.id("com.ziichat.android.media:id/alertTitle")).isDisplayed()) {
            driver.findElement(By.id("android:id/button1")).click();

            Thread.sleep(2000);
            driver.findElement(By.id("om.google.android.gms:id/continue_button")).click();

            usingFingerprint();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        try {
            AppiumServerFromCode.startAppiumServer();

            UiAutomator2Options options = new UiAutomator2Options();
            options.setAutomationName("uiautomator2")
                    .setAppPackage("com.ziichat.android.media")
                    .setAppActivity("com.halome.media.app.MainActivity");
            driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"),options );

            unlockLockScreenByFingerprint();

            Thread.sleep(4000);
            loginZiiChat();
        } finally {
            AppiumServerFromCode.stopAppiumServer();
            System.out.println("finish");
        }

    }
}
