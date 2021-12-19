package page;

import model.Tour;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import waits.Waits;

public class TravelHouseHomePage extends AbstractPage {

    private static String HOMEPAGE_URL = "https://th.by/";

    private static By locationFromLocator = By.xpath("(//div[@class = 'search-bar-element'])[1]");
    private static By locationFromChoiceLocator = By.xpath("((//div[contains(@class, 'search-bar-element-input-pane')]))[1]//li[6]");

    private static By locationToLocator = By.xpath("(//div[@class = 'search-bar-element'])[2]");
    private static By locationToInputLocator = By.xpath("//div[contains(@class, 'input-group input-group-sm')]/input");
    private static By locationToChoiceLocator = By.xpath("(//div[contains(@class,  'search-bar-select-scroll')])[2]//span[contains(text(), 'Moscow')]");
    private static By locationWrongToChoiceLocator = By.xpath("(//div[contains(@class,  'search-bar-select-scroll')])[2]//span[contains(text(), 'Pharaoh Egypt')]");

    private static By searchButtonLocator = By.xpath("//button[@class = 'search-bar-submit-btn']");

    public TravelHouseHomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public TravelHouseHomePage openPage() {
        driver.get(HOMEPAGE_URL);
        return this;
    }

    public TravelHouseHomePage chooseLocationFrom() {
        Waits.waitElementToBeClickable(driver, locationFromLocator).click();
        Waits.waitVisibilityOfElementLocated(driver, locationFromChoiceLocator).click();
        return this;
    }

    public TravelHouseHomePage enterLocationTo(Tour tour) {
        Waits.waitElementToBeClickable(driver, locationToLocator).click();
        Waits.waitVisibilityOfElementLocated(driver, locationToInputLocator).sendKeys(tour.getDestination());
        return this;
    }

    public TravelHouseHomePage enterWrongLocationTo(String to) {
        Waits.waitElementToBeClickable(driver, locationToLocator).click();
        Waits.waitVisibilityOfElementLocated(driver, locationToInputLocator).sendKeys(to);
        return this;
    }

    public TravelHouseHomePage chooseLocationTo() {
        Waits.waitVisibilityOfElementLocated(driver, locationToChoiceLocator).click();
        return this;
    }

    public TravelHouseHomePage chooseWrongLocationTo() {
        Waits.waitVisibilityOfElementLocated(driver, locationWrongToChoiceLocator).click();
        return this;
    }


    public TravelHouseResultsPage searchTours() {
        Waits.waitElementToBeClickable(driver, searchButtonLocator).click();
        return new TravelHouseResultsPage(driver);
    }

}
