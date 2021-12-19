import model.Tour;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import page.TravelHouseBookingPage;
import page.TravelHouseHomePage;
import page.TravelHouseResultsPage;
import service.TourCreator;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TravelHouseBookingPageTest extends CommonConditions {

    Tour testTour = TourCreator.withEmptyPlaceOfDeparture();

    @Test
    void compareTourPriceInResultsPageToTotalPrice() {

        TravelHouseHomePage travelHouseHomePage = new TravelHouseHomePage(driver);

        TravelHouseResultsPage travelHouseResultsPage = travelHouseHomePage
                .openPage()
                .enterLocationTo(testTour)
                .chooseLocationTo()
                .searchTours();

        final String tourPrice = travelHouseResultsPage.copyTourPrice();

        Set<String> oldWindowsSet = driver.getWindowHandles();

        TravelHouseBookingPage travelHouseBookingPage = travelHouseResultsPage.openPage();

        String newWindow = (new WebDriverWait(driver, 10))
                .until((ExpectedCondition<String>) driver -> {
                    Set<String> newWindowsSet = driver.getWindowHandles();
                    newWindowsSet.removeAll(oldWindowsSet);
                    return newWindowsSet.size() > 0 ?
                            newWindowsSet.iterator().next() : null;
                }
                );

        driver.switchTo().window(newWindow);

        final String totalPrice = travelHouseBookingPage.copyTotalPrice();

        assertThat(tourPrice, is(equalTo(totalPrice)));
    }
}
