package scripts;

import com.seera.managers.DataManager;
import com.seera.mappers.SearchRequestMapper;
import com.seera.models.Dates;
import com.seera.models.Guest;
import com.seera.models.Room;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.util.*;
import java.util.stream.Stream;

public class ChallengeTest extends BaseTest{

    @ParameterizedTest
    @ValueSource(strings = {"paris", "newyork"})
    public void getGeoLoc(String place){
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type","application/json");
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("query",place);
        baseAPI.setHeaders(headers);
        baseAPI.setQueryParams(queryParams);
        baseAPI.invokeGET("/api/hotel/ahs/v2/geo-suggest");
        Assertions.assertEquals(200, baseAPI.getResponseStatusCode());
        Assertions.assertTrue(baseAPI.getResponseBody().toUpperCase().contains(place.toUpperCase()));
    }

    @ParameterizedTest
    @MethodSource("generateData")
    public void postSearchRequest(Dates dates, String destination, List<Room> room, String placeId){
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type","application/json");
        baseAPI.setHeaders(headers);
        baseAPI.setBody(new SearchRequestMapper(dates, destination, room, placeId));
        baseAPI.invokePOST("/api/hotel/ahs/search/request");
        Assertions.assertEquals(200, baseAPI.getResponseStatusCode());
    }

    @ParameterizedTest
    @MethodSource("generateDataFromExcel")
    public void postSearchRequestFromExcel(String body){
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type","application/json");
        baseAPI.setHeaders(headers);
        baseAPI.setBody(body);
        baseAPI.invokePOST("/api/hotel/ahs/search/request");
        Assertions.assertEquals(200, baseAPI.getResponseStatusCode());
    }

    static Stream<Arguments> generateData() {
        Dates date1 = new Dates("11-09-2020", "13-09-2020");
        Dates date2 = new Dates("14-09-2020", "16-09-2020");
        List<Guest> guestList1 = new ArrayList<>();
        guestList1.add(new Guest("ADT"));
        guestList1.add(new Guest("ADT"));
        guestList1.add(new Guest("CHD",3));

        List<Guest> guestList2 = new ArrayList<>();
        guestList2.add(new Guest("ADT"));
        guestList2.add(new Guest("ADT"));

        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room(guestList1));
        rooms.add(new Room(guestList2));

        return Stream.of(
                Arguments.of(date1, "paris", rooms, "ChIJD7fiBh9u5kcRYJSMaMOCCwQ"),
                Arguments.of(date2, "newyork", rooms, "ChIJD7fiBh9u5kcRYJSMaMOCCwQ")
        );
    }

    static Stream<String> generateDataFromExcel() throws IOException {
        List<String> bodyStrings = DataManager.generateSearchRequestDataFromExcel();
        return bodyStrings.stream();
    }

}
