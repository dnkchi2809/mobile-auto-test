import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.net.URL;

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
        Thread.sleep(1000);
        Runtime.getRuntime().exec("adb -e emu finger touch 1");
    }

    public static void unlockLockScreenByFingerprint() throws InterruptedException, IOException {
        if(driver.findElement(By.id("com.android.systemui:id/keyguard_message_area_container")).isDisplayed()){
            usingFingerprint();
        }
    }

    public static void loginZiiChat() throws IOException, InterruptedException {
        driver.findElement(By.id("com.ziichat.android.media:id/buttonContinue")).click();

        driver.findElement(By.id("com.ziichat.android.media:id/btn_get_started")).click();

        driver.findElement(By.id("com.ziichat.android.media:id/txtUsername")).sendKeys("automobile.halome.0001");

        driver.findElement(By.id("com.ziichat.android.media:id/btnContinue")).click();

        if(driver.findElement(By.id("com.ziichat.android.media:id/alertTitle")).isDisplayed()){
            WebElement welcomeBackTitle = (WebElement) driver.findElements(By.id("com.ziichat.android.media:id/alertTitle"));
            String titleText = welcomeBackTitle.getText();

            if(titleText.contains("Welcome back")){
                driver.findElement(By.id("android:id/button1")).click();

                usingFingerprint();
            }
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        AppiumServerFromCode.startAppiumServer();

        UiAutomator2Options options = new UiAutomator2Options();
        options.setAutomationName("uiautomator2")
                .setAppPackage("com.ziichat.android.media")
                .setAppActivity("com.halome.media.app.MainActivity");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"),options );

        unlockLockScreenByFingerprint();

        AppiumServerFromCode.stopAppiumServer();
        System.out.println("finish");
    }
}
