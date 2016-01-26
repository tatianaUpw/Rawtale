package model.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import tests.core.Visibility;

public class PersonalSettingsPage extends PageBase {
    protected PersonalSettingsPage(tests.core.Driver driver) throws IllegalAccessException {
        super(driver, "RawTale | The Leading Platform for Business Partnership");
    }

    @FindBy(linkText = "Edit Profile")
    @Visibility(VisibilityOnPage= Visibility.VisibilityOptions.REQUIRED)
    public WebElement EditProfileLink;

    public EditProfilePage ClickEditProfileLink()
    {
        EditProfileLink.click();
        LOG.info("Click Edit Profile link");
        try {
            return new EditProfilePage(this.Driver);
        } catch (IllegalAccessException e) {
            LOG.error(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}

