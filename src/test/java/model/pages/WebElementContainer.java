package model.pages;

import tests.core.CustomLogger;
import tests.core.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebElementContainer {

    protected final tests.core.Driver Driver;

    protected static final CustomLogger LOG = new CustomLogger();

    protected WebElementContainer(Driver driver) throws IllegalAccessException {
        Driver = driver;
        PageFactory.initElements(this.Driver.getInstance(), this);
        this.Driver.waitForJSandJQueryToLoad();
    }

    protected void waitForElementDisplayed(WebElement element) {
        WebDriverWait wait = new WebDriverWait(this.Driver.getInstance(),120);
        wait.until(ExpectedConditions.visibilityOf(element));
        this.Driver.waitForJSandJQueryToLoad();
    }


    protected void setValueToField(WebElement element, String value) {
        element.clear();
        element.sendKeys(value);
        LOG.info(String.format("Value '%1$s' is set ",value));
    }
}
