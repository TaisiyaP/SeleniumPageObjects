import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

import java.util.List;

public class BookingPage {


    @FindBy(id = "station_from")
    SearchElement from;

    @FindBy(id = "station_till")
    SearchElement to;

    @FindBy(name = "search")
    WebElement search;

    @FindBy(id = "date_dep")
    WebElement date;

    @FindBy(xpath = ".//table[@id='ts_res_tbl']//td[@class='num']//a")
    private List<WebElement> trains;

    WebDriver driver;

    public BookingPage(WebDriver wd, String url) {
        driver = wd;

        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(driver)), this);

    }

    public void enterStationFrom(String string) {
        from.enterStation(string);
    }

    public void enterStationTo(String string) {
        to.enterStation(string);
    }

    public void selectStationFrom(String station) {
        from.selectStationFromList(station);
    }

    public void selectStationTo(String station) {
        to.selectStationFromList(station);
    }


    public void searchTrains() {
        search.click();
    }

    public String getTrainNumberFromTable(String trainNumber) {
        for (WebElement train: trains) {
            if (train.getText() == trainNumber);
        }
        return trainNumber;
    }

    public void openPage(String uRL) {
        driver.get(uRL);
    }

    public void selectDateFromCalendar() {
        date.click();
        DateTime setDate = new DateTime().plusMonths(1);
        DateTimeFormatter fmt = DateTimeFormat.forPattern("MM.dd.yyyy");
        date.clear();
        date.sendKeys(setDate.toString(fmt));

    }

}
