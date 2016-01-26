package model.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import tests.core.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import tests.core.Visibility;

import java.util.List;

public class Header extends WebElementContainer {

    @FindBy(css = ".dropdown-toggle")
    @Visibility(VisibilityOnPage = Visibility.VisibilityOptions.DYNAMIC)
    public WebElement ProfileImg;

    @FindBy(linkText = "Settings")
    @Visibility(VisibilityOnPage= Visibility.VisibilityOptions.DYNAMIC)
    public WebElement SettingsLink;

    @FindBy(linkText = "Log Out")
    @Visibility(VisibilityOnPage= Visibility.VisibilityOptions.DYNAMIC)
    public WebElement LogOutLink;

    public Header(Driver driver) throws IllegalAccessException {
        super(driver);
    }
    @FindBy(linkText = "Sign in")
    @Visibility(VisibilityOnPage= Visibility.VisibilityOptions.REQUIRED)
    public WebElement SignInLink;

    @FindBy(how= How.ID,using = "email")
    @Visibility(VisibilityOnPage= Visibility.VisibilityOptions.DYNAMIC)
    public WebElement EmailInput;

    @FindBy(id = "password")
    @Visibility(VisibilityOnPage= Visibility.VisibilityOptions.DYNAMIC)
    public WebElement PasswordInput;

    @FindBy(id = "signInButton")
    @Visibility(VisibilityOnPage = Visibility.VisibilityOptions.DYNAMIC)
    public WebElement SignInBtn;


    @FindBy(xpath = "//li[@class='dropdown-title'][contains(.,'Corporate Page')]/following-sibling::li/a")
    @Visibility(VisibilityOnPage = Visibility.VisibilityOptions.DYNAMIC)
    public WebElement CorporatePageLink;



    public void signIn(String email, String password)
    {
        String linktext= SignInLink.getText();
        (new WebDriverWait(this.Driver.getInstance(), 20)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.findElements(By.linkText("Sign in")).size()>0;
            }
        });
        SignInLink.click();
        LOG.info(String.format("Clicking on link %1$s",linktext));
        waitForElementDisplayed(EmailInput);
        LOG.info(String.format("Setting value into \"Email\" field "));
        setValueToField(EmailInput,email);
        LOG.info(String.format("Setting value into \"Password\" field "));
        setValueToField(PasswordInput,password);
        LOG.info(String.format("Click on \"Sign in\" button "));
        SignInBtn.click();
        waitForElementDisplayed(ProfileImg);
        closeChat();
    }
    protected void closeChat() {
        List<WebElement> closeChatLink =this.Driver.getInstance().findElements(By.className("intercom-launcher-hovercard-close-active"));
        if (closeChatLink.size()>0)
        {
            closeChatLink.get(0).click();
            LOG.info("Chat is closed");
        }
    }

    public RTHomePage logOut()
    {
        LOG.info("Log Out");
        waitForHeader();
        ProfileImg.click();
        waitForElementDisplayed(LogOutLink);
        LogOutLink.click();
        try {
            return new RTHomePage(this.Driver);
        } catch (IllegalAccessException e) {
            LOG.error(e.getMessage());
            e.printStackTrace();

        }
        return null;
    }

    public PersonalSettingsPage goToSettings()
    {
        LOG.info("Go to Settings page");
        waitForHeader();
        waitForElementDisplayed(ProfileImg);
        ProfileImg.click();
        waitForElementDisplayed(SettingsLink);
        SettingsLink.click();
        try {
            return new PersonalSettingsPage(this.Driver);
        } catch (IllegalAccessException e) {
            LOG.error(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    public CorporateMessagesPage goToCorporatePage()
    {
        LOG.info("Go to Corporate page");
        waitForHeader();
        ProfileImg.click();
        waitForElementDisplayed(CorporatePageLink);
        CorporatePageLink.click();
        try {
            return new CorporateMessagesPage(this.Driver);
        } catch (IllegalAccessException e) {
            LOG.error(e.getMessage());
            e.printStackTrace();
        }
        return null;

    }

    private void waitForHeader() {
        (new WebDriverWait(this.Driver.getInstance(), 20)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.findElements(By.cssSelector(".dropdown-toggle")).size()>0;
            }
        });
    }

}

