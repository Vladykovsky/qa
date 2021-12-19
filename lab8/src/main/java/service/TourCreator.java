package service;

import model.Tour;

public class TourCreator {

    private final static String TESTDATA_DESTINATION = "testdata.tour.destination";
    private final static String TESTDATA_PLACE_OF_DEPARTURE = "testdata.tour.placeOfDeparture";

    public static Tour withFullWay() {
        return new Tour(TestDataReader.getTestData(TESTDATA_PLACE_OF_DEPARTURE),
                TestDataReader.getTestData(TESTDATA_DESTINATION));
    }

    public static Tour withEmptyPlaceOfDeparture() {
        return new Tour("", TestDataReader.getTestData(TESTDATA_DESTINATION));
    }

    public static Tour withEmptyDestination() {
        return new Tour(TestDataReader.getTestData(TESTDATA_PLACE_OF_DEPARTURE), "");
    }
}
