package model.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import tests.core.CustomLogger;
import tests.core.Driver;
import tests.core.Visibility;

import static tests.core.Visibility.*;

abstract class PageBase extends WebElementContainer {

    protected final String PageTitle;


    protected PageBase(Driver driver, String pageTitle) throws IllegalAccessException {
        super(driver);
        PageFactory.initElements(this.Driver.getInstance(), this);
        this.Driver.waitForJSandJQueryToLoad();
        PageTitle = pageTitle;
        verifyPageTitle();
        verifyElementsVisibility();

    }

    private void verifyPageTitle() {
        LOG.info("Verifying page title");
        if (PageTitle.isEmpty()) {
            LOG.error("Page title was not verified because empty expected title provided");
        }
        String actualTitle = this.Driver.getInstance().getTitle();
        if (actualTitle.equals(PageTitle) || actualTitle.contains(PageTitle)) {
            LOG.info(String.format("Page title '%1$s' is verified.", PageTitle));
        } else {
            LOG.error(String.format("Page title '%1$s' is not verified. Actual window title is '%2$s'", PageTitle, actualTitle));
        }
    }
    protected void closeChat() {
        List<WebElement> closeChatLink =this.Driver.getInstance().findElements(By.className("intercom-launcher-hovercard-close-active"));
        if (closeChatLink.size()>0)
        {
            closeChatLink.get(0).click();
            LOG.info("Chat is closed");
        }
    }

    private void verifyElementsVisibility() throws IllegalAccessException {
        String pageName = this.getClass().getName();
        Field[] fields =this.getClass().getDeclaredFields();
        List<Field> elementFields = new ArrayList<Field>();
        for(Field field : fields){
            if (field.getType().getName().equals(WebElement.class.getName()))
            {
                elementFields.add(field);
            }

        }
        for (Field elementfield: elementFields){
            WebElement element = (WebElement) elementfield.get(this);
            String elementName = elementfield.getName();
            Visibility attribute = elementfield.getAnnotation(Visibility.class);
            if (attribute == null)
            {
                LOG.info(String.format("Visibility of element '%1$s' is not specified on page '%2$s'", elementName, pageName ));
                continue;
            }

            VisibilityOptions attributeValue = attribute.VisibilityOnPage();
            switch (attributeValue) {
                case REQUIRED:
                    try {
                        if (element.isDisplayed()) {
                            LOG.info(String.format("Required element '%1$s' is displayed on page '%2$s'", elementName, pageName));
                        } else {
                            LOG.error(String.format("Required element '%1$s' is NOT displayed on page '%2$s'", elementName, pageName));
                        }
                    }
                    catch (NoSuchElementException ex) {
                        LOG.error(String.format("Required element '%1$s' is NOT displayed on page '%2$s'", elementName, pageName));

                    }

                    break;
                case OPTIONAL:
                    LOG.info(String.format("Optional element '%1$s'  displayed: '%2$s' on page '%3$s'", elementName, isElementVisible(elementfield),pageName));
                    break;
                case ABSENT:
                    if (!element.isDisplayed())
                    {
                        LOG.info(String.format("Absent element '%1$s' is not displayed on page '%2$s'", elementName,pageName));
                    }
                    else
                    {
                        LOG.error(String.format("Absent element '%1$s' is displayed on page '%2$s'", elementName,pageName));
                    }
                    break;
            }
        }

    }

    private boolean isElementVisible(Field elementField){
        boolean isVisible;
        FindBy findByAttr = elementField.getAnnotation(FindBy.class);
        List<WebElement> elts;
        By by = null;
        String byValue;
        if (!findByAttr.id().isEmpty() || findByAttr.how()== How.ID)
        {
            byValue= (!findByAttr.id().isEmpty())? findByAttr.id(): findByAttr.using();
            by=By.id(byValue);
        }
        else if (!findByAttr.css().isEmpty() || findByAttr.how()==How.CSS)
        {
            byValue= (!findByAttr.css().isEmpty())? findByAttr.css() :findByAttr.using();
            by = By.cssSelector(byValue);
        }
        else if (!findByAttr.linkText().isEmpty() || findByAttr.how()==How.LINK_TEXT)
        {
            byValue= (!findByAttr.linkText().isEmpty())? findByAttr.linkText() :findByAttr.using();
            by = By.linkText(byValue);
        }
        elts= Driver.getInstance().findElements(by);
        isVisible =elts.size()>0 && elts.get(0).isDisplayed();
        return isVisible;
    }



    public  Boolean IsImageVisible(WebElement image)
    {
        String script = "return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0";
        return (Boolean)((JavascriptExecutor) this.Driver.getInstance()).executeScript(script, image);
    }



    protected void refreshPage()
    {
        LOG.info("Refresh page");
        this.Driver.getInstance().navigate().refresh();
        this.Driver.waitForJSandJQueryToLoad();
    }

    protected String getValueAttribute(WebElement elt) {
        return elt.getAttribute("value");
    }




}
