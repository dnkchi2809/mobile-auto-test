import Action.Action;
import Flow.Login.Login;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;


public class Main {

    static AppiumDriverLocalService server;
    public static AndroidDriver driver;

    static void setInstance() {
        AppiumServiceBuilder builder = new AppiumServiceBuilder();
        builder
                .usingPort(4723)
                .withIPAddress("127.0.0.1");
        server = AppiumDriverLocalService.buildService(builder);
    }

    static AppiumDriverLocalService getInstance() {
        if (server == null) {
            setInstance();
        }
        return server;
    }

    public static void startAppiumServer() {
        getInstance().start();
        System.out.println("startAppiumServer");
        System.out.println("URL: " + server.getUrl());
        System.out.println("is Server running: " + server.isRunning());
    }

    public static void stopAppiumServer() {
        if (server == null) {
            getInstance().stop();
        }
        System.out.println("stopAppiumServer");
    }

    public static void unlockLockScreenByFingerprint() throws InterruptedException, IOException {
        Action.usingFingerprint(driver);
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        try {
            Main.startAppiumServer();

            UiAutomator2Options options = new UiAutomator2Options();
            options.setAutomationName("uiautomator2")
                    .setAppPackage("com.ziichat.android.media")
                    .setAppActivity("com.halome.media.app.MainActivity");
            driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), options);

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

            unlockLockScreenByFingerprint();

            Login.loginZiiChat(driver, "automobile.chi.0010");

        } finally {
            Main.stopAppiumServer();
            System.out.println("finish");
        }
    }
}
