package tests.tests;

import model.pages.*;
import org.apache.logging.log4j.LogManager;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.core.*;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ProfilePageTest {
    Driver driver;
    public TestResult testResult=new TestResult();
    protected static final CustomLogger LOG = new CustomLogger();

    @BeforeMethod
    public void SetUp(Method method) {
        TestResult.ErrCount=0;
        String testName = method.getName();
        String date = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        System.setProperty("logfilename", String.format("%1$s %2$s",testName,date));
        org.apache.logging.log4j.core.LoggerContext ctx =
                (org.apache.logging.log4j.core.LoggerContext) LogManager.getContext(false);
        ctx.reconfigure();
        driver = new Driver();
    }

    @Test
    public void updateUserInfoTest()
    {
        String homePageUrl ="https://rawtale.com/";
        String userName = "t.s.smirnova@gmail.com";
        String pwd = "987456";
        UserData userData = Factory.GenerateUserData();
        this.driver.getInstance().navigate().to(homePageUrl);
        System.out.println(String.format("Go to %1$s",homePageUrl));
        RTHomePage homePage =null;

        try {
            homePage = new RTHomePage(driver);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        homePage.header.signIn(userName,pwd);
        PersonalSettingsPage settingsPage =homePage.header.goToSettings();
        EditProfilePage editProfilePage =settingsPage.ClickEditProfileLink();
        editProfilePage.EditProfileInfo(userData);
        String message = editProfilePage.getResultMessageOnSaveClick();
        Assert.assertEquals(message,"Data has been successfully saved!");
        UserData dataFromPage= editProfilePage.getUserProfileInfoFromPage();
        editProfilePage.updateExperience(userData);
        editProfilePage.changeCoverImage(userData.CoverImgSrc);
        editProfilePage.changeProfileImage(userData.ProfileImgSrc);
        dataFromPage= editProfilePage.getUserProfileInfoFromPage();
        verifyProfileData(userData, dataFromPage);
        assertIntValues(editProfilePage.getExperienceInstancesCount(),1);
        verifyExperienceData(userData, dataFromPage);
        verifyImagesData(userData, editProfilePage, dataFromPage);
        homePage =editProfilePage.header.logOut();
        homePage.header.signIn(userName,pwd);
        settingsPage =homePage.header.goToSettings();
        editProfilePage =settingsPage.ClickEditProfileLink();
        dataFromPage= editProfilePage.getUserProfileInfoFromPage();
        verifyProfileData(userData, dataFromPage);
        assertIntValues(editProfilePage.getExperienceInstancesCount(),1);
        verifyExperienceData(userData, dataFromPage);
        verifyImagesData(userData, editProfilePage, dataFromPage);

        LogResult();

    }
    @Test
    public void sendMessageTest() {

        String homePageUrl = "https://52.20.133.109/proposal/9";
        String normalUserName = "kojiro.hirate@rawtale.com";
        String normalUserpwd = "kojiro";

        String corporateUserName = "orderboxrawtale@gmail.com";
        String corporateUserpwd = "orderbox123";

        MessageData message = Factory.GenerateMessage("Kojiro Hirate");
        this.driver.getInstance().navigate().to(homePageUrl);
        ProposalPage proposalPage = null;
        try {
            proposalPage = new ProposalPage(driver);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        proposalPage.header.signIn(normalUserName,normalUserpwd);
        proposalPage.sendMessage(message);
        assertStringValues("Message has been sent!",proposalPage.getResultMessage());
        proposalPage.closeSendMessageDialog();
        RTHomePage homePage=proposalPage.header.logOut();
        homePage.header.signIn(corporateUserName,corporateUserpwd);
        CorporateMessagesPage corporateMessagesPage = homePage.header.goToCorporatePage();
        corporateMessagesPage.clickMessageByText(message.MessageText);
        MessageData messageFromPage= corporateMessagesPage.getMessage();
        assertStringValues(message.MessageFrom,message.MessageFrom);
        assertStringValues(message.MessageText,message.MessageText);
        LogResult();

    }

    private void verifyImagesData(UserData userData, EditProfilePage editProfilePage, UserData dataFromPage) {
        assertStringValues(dataFromPage.CoverImgSrc.substring(dataFromPage.CoverImgSrc.length()-4),userData.CoverImgSrc.substring(userData.CoverImgSrc.length()-4));
        assertStringValues(dataFromPage.ProfileImgSrc.substring(dataFromPage.ProfileImgSrc.length()-4),userData.ProfileImgSrc.substring(userData.ProfileImgSrc.length()-4));
        assertTrue(editProfilePage.isProfileImgVisible());
        assertTrue(editProfilePage.isCoverImgVisible());
    }

    private void verifyExperienceData(UserData userData, UserData dataFromPage) {
        assertStringValues(dataFromPage.CompanyName,userData.CompanyName);
        assertStringValues(dataFromPage.Role,userData.Role);
        assertIntValues(dataFromPage.StartDate.get(dataFromPage.StartDate.YEAR),userData.StartDate.get(userData.StartDate.YEAR));
        assertIntValues(dataFromPage.StartDate.get(dataFromPage.StartDate.MONTH),userData.StartDate.get(userData.StartDate.MONTH));
        assertIntValues(dataFromPage.EndDate.get(dataFromPage.EndDate.YEAR),userData.EndDate.get(userData.EndDate.YEAR));
        assertIntValues(dataFromPage.EndDate.get(dataFromPage.EndDate.MONTH),userData.EndDate.get(userData.EndDate.MONTH));
        assertStringValues(dataFromPage.ExpDescription,userData.ExpDescription);
    }

    private void verifyProfileData(UserData userData, UserData dataFromPage) {
        assertStringValues(dataFromPage.FirstName,userData.FirstName);
        assertStringValues(dataFromPage.LastName,userData.LastName);
        assertStringValues(dataFromPage.Description,userData.Description);
        assertStringValues(dataFromPage.Location,userData.Location);
        assertStringValues(dataFromPage.Industry,userData.Industry);
        assertStringValues(dataFromPage.Facebook,userData.Facebook);
        assertStringValues(dataFromPage.LinkedIn,userData.LinkedIn);
        assertStringValues(dataFromPage.Angellist,userData.Angellist);
        assertStringValues(dataFromPage.Twitter,userData.Twitter);
        assertStringValues(dataFromPage.Google,userData.Google);
    }
    void assertStringValues(String actual, String expected)
    {
        if (!expected.equals(actual))
        {
            LOG.error(String.format("Expected '%1$s', but found '%2$s'",expected,actual));
        }
    }
    void assertIntValues(int actual, int expected)
    {
        if (expected!=actual)
        {
            LOG.error(String.format("Expected '%1$d', but found '%2$d'",expected,actual));
        }
    }
    void assertTrue(boolean actual)
    {
        if (!actual)
        {
            LOG.error(String.format("Expected 'True', but found 'False'"));
        }
    }

    @AfterMethod
    public void TearDown()
    {

        driver.safeQuit();
    }

    private void LogResult() {
        if (TestResult.ErrCount==0)
        {
            LOG.info("Test PASSED");

        }
        else { LOG.info("Test FAILED");}
        Assert.assertEquals(0,TestResult.ErrCount);
    }


}
