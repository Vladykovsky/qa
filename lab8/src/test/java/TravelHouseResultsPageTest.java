import model.Tour;
import org.testng.annotations.Test;
import page.TravelHouseHomePage;
import page.TravelHouseResultsPage;
import service.TourCreator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TravelHouseResultsPageTest extends CommonConditions {

    Tour testTour = TourCreator.withEmptyPlaceOfDeparture();
    final String WRONG_DESTINATION = "Pharaoh Egypt";
    final String MAX_PRICE = "2500";
    final int HOTEL_STARS = 5;
    final int RATING_OF_HOTEL = 4;

    @Test
    void compareDestinationToTourNameTest() {

        TravelHouseHomePage travelHouseHomePage = new TravelHouseHomePage(driver);

        TravelHouseResultsPage travelHouseResultsPage = travelHouseHomePage
                .openPage()
                .enterLocationTo(testTour)
                .chooseLocationTo()
                .searchTours();

        final String destination = testTour.getDestination();
        final String tourName = travelHouseResultsPage.copyTourName();

        assertThat(destination, is(equalTo(tourName)));

    }

    @Test
    void tourPriceWithOnePassengerLessThanTourPriceWithTwoPassengersTest() {

        TravelHouseHomePage travelHouseHomePage = new TravelHouseHomePage(driver);

        TravelHouseResultsPage travelHouseResultsPage = travelHouseHomePage
                .openPage()
                .enterLocationTo(testTour)
                .chooseLocationTo()
                .searchTours();

        final int tourPriceWithTwoPassengers = Integer.parseInt(travelHouseResultsPage.copyTourPrice());
        final int tourPriceWithOnePassenger = Integer.parseInt(travelHouseResultsPage
                .removePassenger()
                .searchNewTours()
                .waitStalenessOfTourPrice()
                .copyTourPrice());

        assertThat(tourPriceWithOnePassenger, is(lessThan(tourPriceWithTwoPassengers)));
    }

    @Test
    void errorMessageIsDisplayedWithWrongDestinationTest() {

        TravelHouseHomePage travelHouseHomePage = new TravelHouseHomePage(driver);

        TravelHouseResultsPage travelHouseResultsPage = travelHouseHomePage.openPage()
                .enterWrongLocationTo(WRONG_DESTINATION)
                .chooseWrongLocationTo()
                .searchTours();

        assertThat(travelHouseResultsPage.isErrorMessageDisplayed(), is(true));
    }

    @Test
    void maxPriceInFilterGreaterThanOfferedTourPriceTest() {

        TravelHouseHomePage travelHouseHomePage = new TravelHouseHomePage(driver);

        final String tourPriceWithFilter = travelHouseHomePage.openPage()
                .searchTours()
                .enterMaxPrice(MAX_PRICE)
                .copyTourPriceWithFilter();

        final int maxPrice = Integer.parseInt(MAX_PRICE);
        final int tourPrice = Integer.parseInt(tourPriceWithFilter);

        assertThat(maxPrice, is(greaterThan(tourPrice)));
    }

    @Test
    void amountOfHotelStarsIsSameAsInHotelCategoryTest() {

        TravelHouseHomePage travelHouseHomePage = new TravelHouseHomePage(driver);

        final TravelHouseResultsPage travelHouseResultsPage = travelHouseHomePage.openPage()
                .searchTours()
                .chooseFiveStarsCategoryOfHotel();

        final int amountOfHotelStarsInOfferedTours = travelHouseResultsPage.amountOfHotelStars();

        assertThat(amountOfHotelStarsInOfferedTours, is(equalTo(HOTEL_STARS)));
    }

    @Test
    void amountOfHotelStarsInOfferedToursIsEqualToOrGreaterThanChosenRatingTest() {

        TravelHouseHomePage travelHouseHomePage = new TravelHouseHomePage(driver);

        final TravelHouseResultsPage travelHouseResultsPage = travelHouseHomePage.openPage()
                .searchTours()
                .chooseFourPlusRatingOfHotel();

        final int amountOfHotelStarsInOfferedTours = travelHouseResultsPage.amountOfHotelStars();

        assertThat(amountOfHotelStarsInOfferedTours, is(greaterThanOrEqualTo(RATING_OF_HOTEL)));
    }

    @Test
    void tourPriceWithBabyIsGreaterThanWithoutTest() {

        TravelHouseHomePage travelHouseHomePage = new TravelHouseHomePage(driver);

        TravelHouseResultsPage travelHouseResultsPage = travelHouseHomePage
                .openPage()
                .enterLocationTo(testTour)
                .chooseLocationTo()
                .searchTours();

        final int tourPriceWithoutBaby = Integer.parseInt(travelHouseResultsPage.copyTourPrice());

        final int tourPriceWithBaby = Integer.parseInt(travelHouseResultsPage
                .addBaby()
                .searchNewTours()
                .waitStalenessOfTourPrice()
                .copyTourPrice());

        assertThat(tourPriceWithoutBaby, is(lessThan(tourPriceWithBaby)));
    }

    @Test
    void tourPriceInRussianRublesGreaterThanInBelorussianTest() {

        TravelHouseHomePage travelHouseHomePage = new TravelHouseHomePage(driver);

        TravelHouseResultsPage travelHouseResultsPage = travelHouseHomePage
                .openPage()
                .searchTours();

        final int tourPriceInBelorussianRubles = Integer.parseInt(travelHouseResultsPage.copyTourPriceWithFilter());
        final int tourPriceInRussianRubles = Integer.parseInt(travelHouseResultsPage
                .convertTourPriceToRussianRubles()
                .waitStalenessOfTourPriceWithFilter()
                .copyTourPriceInRussianRubles());

        assertThat(tourPriceInBelorussianRubles, is(lessThan(tourPriceInRussianRubles)));
    }
}
