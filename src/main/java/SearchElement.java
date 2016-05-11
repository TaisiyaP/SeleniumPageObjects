import org.openqa.selenium.*;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import java.util.List;

import static org.openqa.selenium.Keys.DOWN;

/**
 * Created by _admin on 17.04.2016.
 */

public class SearchElement extends HtmlElement {

    @FindBy(xpath=".//input")
    WebElement textInput;

    @FindBy(xpath=".//div[@class='autosuggest']/div")
    List<WebElement> items;

    public void enterStation(String string) {
        textInput.sendKeys(string);
        textInput.sendKeys(DOWN);
    }

    public void selectStationFromList(String station) {
        textInput.sendKeys(DOWN);
        for (WebElement item: items)
        {
            if (item.getText()== station);
            if (item != null) {
                item.click();
                break;
            }
        }
    }
    public Rectangle getRect() {
        return new Rectangle(getLocation(),getSize());
    }
}