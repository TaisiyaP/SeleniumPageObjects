import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.net.URL;
import java.text.ParseException;

import static org.testng.AssertJUnit.assertEquals;


/**
 * Created by _admin on 15.04.2016.
 */
public class BookingTest extends DriverSetup {

    WebDriver wd;
    BookingPage bookingPage;

    @Parameters({"url", "browser"})
    @BeforeMethod
    public void setUp(@Optional(value = "http://booking.uz.gov.ua/en") String url,
                      @Optional(value = "FF") Browser browser) throws Exception {
        wd = getDriver(Browser.FF);
        bookingPage = new BookingPage(wd, url);
        bookingPage.openPage(url);
    }


    @DataProvider(name = "searchData")
    public Object[][] createData1() {
        return new Object[][]{
                {"Kyiv", "Ivano-Frankivsk", "043 К"},
                {"Kyiv", "Ivano-Frankivsk", "143 К"},
        };
    }

    @Test(dataProvider = "searchData")
    public void orderTicketKievFrankivskTest(String fromStation, String toStation, String trainNumber) throws ParseException {
        bookingPage.enterStationFrom(fromStation);
        bookingPage.selectStationFrom(fromStation);
        bookingPage.enterStationTo(toStation);
        bookingPage.selectStationTo(toStation);
        bookingPage.selectDateFromCalendar();
        bookingPage.searchTrains();
        assertEquals(trainNumber, bookingPage.getTrainNumberFromTable(trainNumber));
//        assertEquals("143 К", bookingPage.getTrainNumberFromTable("143 К"));
    }

    @AfterMethod
    public void closeDriver() {
        driver.quit();
    }
}

