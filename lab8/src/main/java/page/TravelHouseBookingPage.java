package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import waits.Waits;

public class TravelHouseBookingPage extends AbstractPage {

    private static By totalPriceLocator = By.xpath("//div[contains(@class, 'text-25')]");

    public TravelHouseBookingPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public TravelHouseBookingPage openPage() {
        return this;
    }

    public String copyTotalPrice() {
        return Waits.waitVisibilityOfElementLocated(driver, totalPriceLocator).getText().replace("от", "")
                .replace("BYN", "").replace(" ", "");
    }


}
