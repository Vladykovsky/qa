package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import waits.Waits;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TravelHouseResultsPage extends AbstractPage {

    private static By bookingButtonLocator = By.xpath("(//div[@class = 'tour-list-offer-item-actions']/a)[1]");

    private static By tourNameLocator = By.xpath("(//div[@class = 'p-base']//span)[1]");
    private static By tourPricelocator = By.xpath("(//div[@class = 'tour-list-offer-item-price'])[1]");
    private static By tourPriceWithFilter = By.xpath("(//div[contains(@class, 'tour-offer-price')])[1]");

    private static By passengersFieldLocator = By.xpath("(//div[contains(@class, 'search-bar-element-choice')])[3]");
    private static By removePassengerButtonLocator = By.xpath("(//button[contains(@class, 'search-bar-number-input-btn')])[5]");
    private static By addBabyButtonLocator = By.xpath("(//button[contains(@class, 'search-bar-input-btn')])[7]");

    private static By searchNewToursButtonLocator = By.xpath("//button[@class = 'search-bar-submit-btn']");

    private static By errorMessageLocator = By.xpath("//div[@class = 'mt-75']//span");

    private static By maxPriceInputLocator = By.xpath("(//div[contains(@class, 'input-group-sm')])[2]/input[2]");

    private static By fiveStarsCategoryOfHotelLocator = By.xpath("(//div[@class = 'filter-panel-element'])[2]//div[contains(@class, 'custom-checkbox')][1]");
    private static By hotelStarsFieldLocator = By.xpath("(//span[@class = 'hotel-category-stars text-base'])[3]");
    private static By hotelStarsLocator = By.xpath("(//span[@class = 'hotel-category-stars text-base'])[3]/*[name()='svg']");

    private static By fourPlusRatingOfHotelLocator = By.xpath("(//div[@class = 'filter-panel-element'])[4]//div[contains(@class, 'custom-checkbox')][2]");

    private static By converterToRussianRublesLocator = By.xpath("//span[contains(text(), 'RUB')]");


    public TravelHouseResultsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public TravelHouseBookingPage openPage() {
        Waits.waitElementToBeClickable(driver, bookingButtonLocator).click();
        return new TravelHouseBookingPage(driver);
    }

    public String copyTourName() {
        return Waits.waitVisibilityOfElementLocated(driver, tourNameLocator).getText();
    }

    public String copyTourPrice() {
        return Waits.waitVisibilityOfElementLocated(driver, tourPricelocator).getText()
                .replace("BYN", "").replace(" ", "").trim();
    }

    public TravelHouseResultsPage removePassenger() {
        Waits.waitElementToBeClickable(driver, passengersFieldLocator).click();
        Waits.waitElementToBeClickable(driver, removePassengerButtonLocator).click();
        return this;
    }

    public TravelHouseResultsPage searchNewTours() {
        Waits.waitElementToBeClickable(driver, searchNewToursButtonLocator).click();
        return this;
    }

    public TravelHouseResultsPage waitStalenessOfTourPrice() {
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT_IN_SECONDS);
        wait.until(ExpectedConditions.stalenessOf(Waits.waitVisibilityOfElementLocated(driver, tourPricelocator)));
        return this;
    }

    public TravelHouseResultsPage waitStalenessOfTourPriceWithFilter() {
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT_IN_SECONDS);
        wait.until(ExpectedConditions.stalenessOf(Waits.waitVisibilityOfElementLocated(driver, tourPriceWithFilter)));
        return this;
    }

    public boolean isErrorMessageDisplayed() {
        return Waits.waitVisibilityOfElementLocated(driver, errorMessageLocator).isDisplayed();
    }

    public TravelHouseResultsPage enterMaxPrice(String price) {
        Waits.waitElementToBeClickable(driver, maxPriceInputLocator).click();
        Waits.waitVisibilityOfElementLocated(driver, maxPriceInputLocator).sendKeys(price);
        return this;
    }

    public String copyTourPriceWithFilter() {
        return Waits.waitVisibilityOfElementLocated(driver, tourPriceWithFilter).getText().replace("от", "")
                .replace("BYN", "").replace(" ", "");
    }

    public String copyTourPriceInRussianRubles() {
        return Waits.waitVisibilityOfElementLocated(driver, tourPriceWithFilter).getText().replace("от", "")
                .replace("RUB", "").replace(" ", "");
    }

    public TravelHouseResultsPage chooseFiveStarsCategoryOfHotel() {
        Waits.waitElementToBeClickable(driver, fiveStarsCategoryOfHotelLocator).click();
        return this;
    }

    public int amountOfHotelStars() {
        List<WebElement> list = Waits.waitVisibilityOfElementLocated(driver, hotelStarsFieldLocator)
                .findElements(hotelStarsLocator);
        return list.size();
    }

    public TravelHouseResultsPage chooseFourPlusRatingOfHotel() {
        Waits.waitElementToBeClickable(driver, fourPlusRatingOfHotelLocator).click();
        return this;
    }

    public TravelHouseResultsPage addBaby() {
        Waits.waitElementToBeClickable(driver, passengersFieldLocator).click();
        Waits.waitElementToBeClickable(driver, addBabyButtonLocator).click();
        return this;
    }

    public TravelHouseResultsPage convertTourPriceToRussianRubles() {
        Waits.waitElementToBeClickable(driver, converterToRussianRublesLocator).click();
        return this;
    }
}
