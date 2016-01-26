package model.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import tests.core.MessageData;
import tests.core.Visibility;

import java.util.List;

public class CorporateMessagesPage extends PageBase{
    protected CorporateMessagesPage(tests.core.Driver driver) throws IllegalAccessException {
        super(driver, "RawTale | The Leading Platform for Business Partnership");
    }

    @FindBy(className = "message-content-name")
    @Visibility(VisibilityOnPage= Visibility.VisibilityOptions.DYNAMIC)
    WebElement MessageFrom;

    @FindBy(className = "message-content-text")
    @Visibility(VisibilityOnPage= Visibility.VisibilityOptions.DYNAMIC)
    WebElement MessageText;

    public void clickMessageByText(String text)
    {

        String locator =String .format("//p[@class='message-contentbrief'][contains(.,'%1$s')]",text);
        List<WebElement> foundMessages = this.Driver.getInstance().findElements(By.xpath(locator));
        if (foundMessages.size()==0)
        {
            LOG.error(String.format("Message with text '%1$s' was not found",text));
        }
        else
        {
            LOG.info(String.format("Click on message with text '%1$s'",text));
            foundMessages.get(0).click();
        }
    }

    public MessageData getMessage()
    {
        LOG.info("Getting message Data from page");
        MessageData message = new MessageData();
        message.MessageFrom = MessageFrom.getText();
        message.MessageText = MessageText.getText();
        return message;
    }

}
