package Flow.Search;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Search {
    public static void goToSearchScreen(AppiumDriver appiumDriver) {
        appiumDriver.findElement(By.id("com.ziichat.android.media:id/action_search")).click();
    }

    public static void searchWithKeyword(AppiumDriver appiumDriver, String keyword) {
        appiumDriver.findElement(By.id("com.ziichat.android.media:id/txtSearch")).sendKeys(keyword);
    }

    public static void clickOnSearchResult(AppiumDriver appiumDriver, int index) {
        List<WebElement> userResultList = appiumDriver.findElements(By.xpath("//androidx.cardview.widget.CardView[@resource-id=\"com.ziichat.android.media:id/cvUser\"]"));
        userResultList.get(index).click();
    }
}
