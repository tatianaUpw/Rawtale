package model.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import tests.core.MessageData;
import tests.core.Visibility;

public class ProposalPage extends PageBase {
    public ProposalPage(tests.core.Driver driver) throws IllegalAccessException {
        super(driver, "RawTale | The Leading Platform for Business Partnership");
        this.header = new Header(driver);
    }
    public Header header;
    @FindBy(css = "[href*='create-mail']>span")
    @Visibility(VisibilityOnPage= Visibility.VisibilityOptions.DYNAMIC)
    public WebElement SendMessageButton;

    @FindBy(id = "pMesgtext")
    @Visibility(VisibilityOnPage= Visibility.VisibilityOptions.DYNAMIC)
    public WebElement MessageTextInput;

    @FindBy(id = "pSendMail")
    @Visibility(VisibilityOnPage= Visibility.VisibilityOptions.DYNAMIC)
    public WebElement ConfirmSendButton;

    @FindBy(className = "successmsg")
    @Visibility(VisibilityOnPage= Visibility.VisibilityOptions.DYNAMIC)
    public WebElement SuccessMsg;

    @FindBy(css = "#MessageModal .close")
    @Visibility(VisibilityOnPage= Visibility.VisibilityOptions.DYNAMIC)
    public WebElement CloseMessageDialogButton;


    public void sendMessage(MessageData message)
    {
        LOG.info("Click Send Message button");
        closeChat();
        SendMessageButton.click();
        waitForElementDisplayed(MessageTextInput);
        LOG.info("Set value to \"Message Text\" field");
        setValueToField(MessageTextInput,message.MessageText);
        ConfirmSendButton.click();

    }
    public String getResultMessage()
    {
        LOG.info("Getting result message");
        waitForElementDisplayed(SuccessMsg);
        return SuccessMsg.getText();
    }

    public void closeSendMessageDialog()
    {
        LOG.info("Close send message dialog");
        CloseMessageDialogButton.click();
        (new WebDriverWait(this.Driver.getInstance(), 20)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return !CloseMessageDialogButton.isDisplayed();
            }
        });
    }



}