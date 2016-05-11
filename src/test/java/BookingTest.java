import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import static org.testng.AssertJUnit.assertEquals;


/**
 * Created by _admin on 15.04.2016.
 */
public class BookingTest extends DriverSetup {

    WebDriver wd;
    BookingPage bookingPage;

    String URL = "http://booking.uz.gov.ua/en";


    @BeforeMethod
    public void setUp() throws Exception {

        wd = getDriver(Browser.FF);
        bookingPage = new BookingPage(wd);
        bookingPage.openPage(URL);
    }


    @Test
    public void orderTicketKievFrankivskTest() {
        bookingPage.enterStationFrom("Kyiv");
        bookingPage.selectStationFrom("Kyiv");
        bookingPage.enterStationTo("Ivano-Frankivsk");
        bookingPage.selectStationTo("Ivano-Frankivsk");
        bookingPage.selectDateFromCalendar();
        bookingPage.searchTrains();
        assertEquals("043 К", bookingPage.getTrainNumberFromTable("043 К"));
        assertEquals("143 К", bookingPage.getTrainNumberFromTable("143 К"));
    }

    @AfterMethod
    public void closeDriver() {
        driver.quit();
    }
}

