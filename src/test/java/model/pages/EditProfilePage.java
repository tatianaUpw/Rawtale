package model.pages;

import tests.core.UserData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import tests.core.Visibility;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class EditProfilePage extends PageBase {
    protected EditProfilePage(tests.core.Driver driver) throws IllegalAccessException {
        super(driver, "RawTale | The Leading Platform for Business Partnership");
        LocationSelect=new Select(LocationInput);
        IndustrySelect=new Select(IndustryInput);
        this.header = new Header(driver);
    }
    Select LocationSelect;
    Select IndustrySelect;

    @FindBy(id = "fName")
    @Visibility(VisibilityOnPage= Visibility.VisibilityOptions.REQUIRED)
    public WebElement FirstNameInput;

    @FindBy(id = "lName")
    @Visibility(VisibilityOnPage= Visibility.VisibilityOptions.REQUIRED)
    public WebElement LastNameInput;

    @FindBy(id = "domainDescription")
    @Visibility(VisibilityOnPage= Visibility.VisibilityOptions.REQUIRED)
    public WebElement DescriptionInput;

    @FindBy(id = "countryId")
    @Visibility(VisibilityOnPage= Visibility.VisibilityOptions.REQUIRED)
    public WebElement LocationInput;

    @FindBy(id = "industryId")
    @Visibility(VisibilityOnPage= Visibility.VisibilityOptions.REQUIRED)
    public WebElement IndustryInput;

    @FindBy(id = "facebookLink")
    @Visibility(VisibilityOnPage= Visibility.VisibilityOptions.REQUIRED)
    public WebElement FacebookInput;

    @FindBy(id = "linkedinLink")
    @Visibility(VisibilityOnPage= Visibility.VisibilityOptions.REQUIRED)
    public WebElement LinkedinInput;

    @FindBy(id = "angelListLink")
    @Visibility(VisibilityOnPage= Visibility.VisibilityOptions.REQUIRED)
    public WebElement AngelListInput;

    @FindBy(id = "twitterLink")
    @Visibility(VisibilityOnPage= Visibility.VisibilityOptions.REQUIRED)
    public WebElement TwitterInput;

    @FindBy(id = "googlePlusLink")
    @Visibility(VisibilityOnPage= Visibility.VisibilityOptions.REQUIRED)
    public WebElement GooglePlusInput;

    @FindBy(id = "saveEditProfile")
    @Visibility(VisibilityOnPage= Visibility.VisibilityOptions.REQUIRED)
    public WebElement SaveButton;

    @FindBy(id = "settings-experience-add")
    @Visibility(VisibilityOnPage= Visibility.VisibilityOptions.REQUIRED)
    public WebElement AddExperienceButton;

    @FindBy(id = "companyName")
    @Visibility(VisibilityOnPage= Visibility.VisibilityOptions.DYNAMIC)
    public WebElement CompanyNameInput;

    @FindBy(css = "[id*='companyName']")
    @Visibility(VisibilityOnPage= Visibility.VisibilityOptions.DYNAMIC)
    public WebElement SavedCompanyName;

    @FindBy(id = "role")
    @Visibility(VisibilityOnPage= Visibility.VisibilityOptions.DYNAMIC)
    public WebElement RoleInput;

    @FindBy(css = "[id*='role']")
    @Visibility(VisibilityOnPage= Visibility.VisibilityOptions.DYNAMIC)
    public WebElement SavedRole;

    @FindBy(id = "startDate")
    @Visibility(VisibilityOnPage= Visibility.VisibilityOptions.DYNAMIC)
    public WebElement StartDateInput;

    @FindBy(css = "[id*='startDate']")
    @Visibility(VisibilityOnPage= Visibility.VisibilityOptions.DYNAMIC)
    public WebElement SavedStartDate;

    @FindBy(xpath ="//div[contains(@id,'monthpicker') and not(contains(@style,'display: none'))]")
    @Visibility(VisibilityOnPage= Visibility.VisibilityOptions.DYNAMIC)
    public WebElement ActiveDatePicker;

    @FindBy(id = "endDate")
    @Visibility(VisibilityOnPage= Visibility.VisibilityOptions.DYNAMIC)
    public WebElement EndDateInput;

    @FindBy(css = "[id*='endDate']")
    @Visibility(VisibilityOnPage= Visibility.VisibilityOptions.DYNAMIC)
    public WebElement SavedEndDate;

    @FindBy(id = "iscurrent")
    @Visibility(VisibilityOnPage= Visibility.VisibilityOptions.DYNAMIC)
    public WebElement IsCurrentCheckBox;

    @FindBy(id = "description")
    @Visibility(VisibilityOnPage= Visibility.VisibilityOptions.DYNAMIC)
    public WebElement ExperienceDescriptionInput;

    @FindBy(css = "[id*='description']")
    @Visibility(VisibilityOnPage= Visibility.VisibilityOptions.DYNAMIC)
    public WebElement SavedExperienceDescription;

    @FindBy(className = "add")
    @Visibility(VisibilityOnPage= Visibility.VisibilityOptions.DYNAMIC)
    public WebElement SaveExperienceLink;

    @FindBy(className = "remove")
    @Visibility(VisibilityOnPage= Visibility.VisibilityOptions.DYNAMIC)
    public WebElement RemoveExperienceLink;


    @FindBy(id = "upload_per_cover_name")
    @Visibility(VisibilityOnPage= Visibility.VisibilityOptions.DYNAMIC)
    public WebElement CoverImgInput;

    @FindBy(css = ".settings-coverimg .settings-button")
    @Visibility(VisibilityOnPage= Visibility.VisibilityOptions.REQUIRED)
    public WebElement ChangeCoverImgButton;

    @FindBy(id = "upload_per_cover_modal")
    @Visibility(VisibilityOnPage= Visibility.VisibilityOptions.DYNAMIC)
    public WebElement CoverImgSaveButton;

    @FindBy(id = "coverImgsrc")
    @Visibility(VisibilityOnPage= Visibility.VisibilityOptions.REQUIRED)
    public WebElement CoverImgSrc;

    @FindBy(id = "profileImgsrc")
    @Visibility(VisibilityOnPage= Visibility.VisibilityOptions.REQUIRED)
    public WebElement ProfileImgSrc;

    @FindBy(id ="upload_profile_name")
    @Visibility(VisibilityOnPage= Visibility.VisibilityOptions.DYNAMIC)
    public WebElement  ProfileImgInput;

    @FindBy(id = "upload_profile_modal")
    @Visibility(VisibilityOnPage= Visibility.VisibilityOptions.DYNAMIC)
    public WebElement ProfileImgSaveButton;

    @FindBy(css = ".settings-profileimg .settings-button")
    @Visibility(VisibilityOnPage= Visibility.VisibilityOptions.REQUIRED)
    public WebElement ChangeProfileImgButton;

    @FindBy(className = "successmsg")
    @Visibility(VisibilityOnPage= Visibility.VisibilityOptions.DYNAMIC)
    public WebElement SuccessMessage;

    @FindBy(className = "errormsg")
    @Visibility(VisibilityOnPage= Visibility.VisibilityOptions.DYNAMIC)
    public WebElement ErrorMessage;

    @FindBy(className = "successwe")
    @Visibility(VisibilityOnPage= Visibility.VisibilityOptions.DYNAMIC)
    public WebElement ExpSuccessMessage;

    @FindBy(className = "errorwe")
    @Visibility(VisibilityOnPage= Visibility.VisibilityOptions.DYNAMIC)
    public WebElement ExpErrorMessage;

    public Header header;



    public void EditProfileInfo(UserData userData)
    {
        LOG.info("Setting value int \"First Name\" field");
        setValueToField(FirstNameInput,userData.FirstName);
        LOG.info("Setting value int \"Last Name\" field");
        setValueToField(LastNameInput,userData.LastName);
        LOG.info("Setting value int \"Description\" field");
        setValueToField(DescriptionInput,userData.Description);
        LOG.info(String.format("Setting value '%1$s' int \"Location\" field",userData.Location));
        LocationSelect.selectByVisibleText(userData.Location);
        LOG.info(String.format("Setting value '%1$s' int \"Industry\" field",userData.Industry));
        IndustrySelect.selectByVisibleText(userData.Industry);
        LOG.info("Setting value int \"Facebook\" field");
        setValueToField(FacebookInput,userData.Facebook);
        LOG.info("Setting value int \"LinkedIn\" field");
        setValueToField(LinkedinInput,userData.LinkedIn);
        LOG.info("Setting value int \"LinkedIn\" field");
        setValueToField(TwitterInput,userData.Twitter);
        LOG.info("Setting value int \"LinkedIn\" field");
        setValueToField(GooglePlusInput,userData.Google);
        LOG.info("Setting value int \"LinkedIn\" field");
        setValueToField(AngelListInput,userData.Angellist);
    }



    public void updateExperience(UserData userData)
    {
        deleteExistingExperience();
        addExperience(userData);
    }

    public void changeCoverImage(String imgSrc)
    {
        waitForElementDisplayed(ChangeCoverImgButton);
        ChangeCoverImgButton.click();
        waitForElementDisplayed(CoverImgInput);
        CoverImgInput.sendKeys(imgSrc);
        CoverImgSaveButton.click();
        LOG.info(String.format("Cover image changed to '%1$s'",imgSrc));
        refreshPage();

    }

    public void changeProfileImage(String imgSrc)
    {
        waitForElementDisplayed(ChangeProfileImgButton);
        ChangeProfileImgButton.click();
        waitForElementDisplayed(ProfileImgInput);
        ProfileImgInput.sendKeys(imgSrc);
        ProfileImgSaveButton.click();
        LOG.info(String.format("Profile image changed to '%1$s'",imgSrc));
        waitForElementDisplayed(ChangeProfileImgButton);
    }

    public String getResultMessageOnSaveClick()
    {
        String message = "";
        SaveButton.click();
        LOG.info("Save button was clicked");
        LOG.info("Getting result message text after click on Save");
        (new WebDriverWait(this.Driver.getInstance(), 20)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return SuccessMessage.isDisplayed()||ErrorMessage.isDisplayed();
            }
        });

        if(ErrorMessage.isDisplayed())
        {
            message=ErrorMessage.getText();
            LOG.error(String.format("Error message is displayed: '%1$s'",message));
            return message;
        }
        else
        {
            LOG.info("Success message is displayed");
            message=SuccessMessage.getText();

        }
        return message;
    }


    private  void deleteExistingExperience()
    {
        List<WebElement> removeLinks =this.Driver.getInstance().findElements(By.className("remove"));
        int size = removeLinks.size();
        LOG.info(String.format("'%1$d experience instances is displayed",size));
        int initialSize = removeLinks.size();
        for (int i=0;i<size;i++)
        {
            removeLinks.get(i).click();
            LOG.info("Remove link was clicked");
            initialSize-=1;
            final int finalInitialsize = initialSize;
            (new WebDriverWait(this.Driver.getInstance(), 20)).until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver d) {
                    return d.findElements(By.className("remove")).size() == finalInitialsize;
                }
            });
        }

        removeLinks =this.Driver.getInstance().findElements(By.className("remove"));
        if (removeLinks.size()>0)
        {
            LOG.error(String.format("Not all experiences were deleted. There is still: '%1$d'",removeLinks.size()));

        }
        if (ExpErrorMessage.isDisplayed())
        {
            LOG.error(String.format("Error message is displayed: '%1$s'",ExpErrorMessage.getText()));
        }


    }
    public int getExperienceInstancesCount()
    {
        LOG.info("Getting count of Experience Instances");
        return this.Driver.getInstance().findElements(By.className("remove")).size();

    }

    private void addExperience(UserData userData) {
        AddExperienceButton.click();
        LOG.info("Setting value int \"Company Name\" field");
        setValueToField(CompanyNameInput,userData.CompanyName);
        LOG.info("Setting value int \"Role\" field");
        setValueToField(RoleInput,userData.Role);
        LOG.info("Setting value int \"Start Date\" field");
        SetDate(userData.StartDate,StartDateInput);
        LOG.info("Setting value int \"End Date\" field");
        SetDate(userData.EndDate,EndDateInput);
        LOG.info("Setting value int \"Experience Description\" field");
        setValueToField(ExperienceDescriptionInput,userData.ExpDescription);
        SaveExperienceLink.click();
        waitForElementDisplayed(ChangeCoverImgButton);
        if (ExpErrorMessage.isDisplayed())
        {
            LOG.error(String.format("Error message is displayed: '%1$s'",ExpErrorMessage.getText()));
        }
    }

    private void SetDate(GregorianCalendar date, WebElement dateInput) {
        dateInput.click();
        waitForElementDisplayed(ActiveDatePicker);
        Select dateSelect = new Select(ActiveDatePicker.findElement(By.className("mtz-monthpicker-year")));
        int year =date.get(date.YEAR);
        dateSelect.selectByVisibleText(String.valueOf(year));

        LOG.info(String.format("Value '%1$d' is set as Year",year));
        int month =date.get(date.MONTH)+1;
        String monthCssSelector = String.format("[data-month='%1$d']",month);
        ActiveDatePicker.findElement(By.cssSelector(monthCssSelector)).click();
        LOG.info(String.format("Value '%1$d' is set as Month",month));
    }

    public boolean isCoverImgVisible()
    {
        LOG.info("Checking if Cover Img is visible");
        return  IsImageVisible(CoverImgSrc);
    }

    public boolean isProfileImgVisible()
    {
        LOG.info("Checking if Profile Img is visible");
        return  IsImageVisible(ProfileImgSrc);
    }

    public UserData getUserProfileInfoFromPage()
    {
        refreshPage();
        UserData dataFromPage = new UserData();
        LOG.info("Getting User Data from page");
        dataFromPage.FirstName= getValueAttribute(FirstNameInput);
        dataFromPage.LastName =getValueAttribute(LastNameInput);
        dataFromPage.Description = DescriptionInput.getText();
        dataFromPage.Location =LocationSelect.getFirstSelectedOption().getText();
        dataFromPage.Industry = IndustrySelect.getFirstSelectedOption().getText();
        dataFromPage.Facebook =getValueAttribute(FacebookInput);
        dataFromPage.LinkedIn =getValueAttribute(LinkedinInput);
        dataFromPage.Angellist =getValueAttribute(AngelListInput);
        dataFromPage.Twitter =getValueAttribute(TwitterInput);
        dataFromPage.Google =getValueAttribute(GooglePlusInput);
        dataFromPage.CompanyName =getValueAttribute(SavedCompanyName);
        dataFromPage.Role =getValueAttribute(SavedRole);
        String startDate =getValueAttribute(SavedStartDate);
        String endDate =getValueAttribute(SavedEndDate);
        dataFromPage.StartDate = ParseDate(startDate);
        dataFromPage.EndDate =ParseDate(endDate);
        dataFromPage.ExpDescription =SavedExperienceDescription.getText();
        dataFromPage.CoverImgSrc=CoverImgSrc.getAttribute("src");
        dataFromPage.ProfileImgSrc =ProfileImgSrc.getAttribute("src");
        return dataFromPage;
    }


    private GregorianCalendar ParseDate(String date) {
        DateFormat dateFormat = new SimpleDateFormat("MM/yyyy");
        try {
            Date start = dateFormat.parse(date);
            GregorianCalendar cal = new GregorianCalendar();
            cal.setTime(start);
            return cal;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


}

