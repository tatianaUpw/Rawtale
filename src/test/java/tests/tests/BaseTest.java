package tests.tests;

import model.pages.EditProfilePage;
import org.apache.logging.log4j.LogManager;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import tests.core.CustomLogger;
import tests.core.Driver;
import tests.core.TestResult;
import tests.core.UserData;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseTest {
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
    protected void verifyImagesData(UserData userData, EditProfilePage editProfilePage, UserData dataFromPage) {
        assertStringValues(dataFromPage.CoverImgSrc.substring(dataFromPage.CoverImgSrc.length()-4),userData.CoverImgSrc.substring(userData.CoverImgSrc.length()-4));
        assertStringValues(dataFromPage.ProfileImgSrc.substring(dataFromPage.ProfileImgSrc.length()-4),userData.ProfileImgSrc.substring(userData.ProfileImgSrc.length()-4));
        assertTrue(editProfilePage.isProfileImgVisible());
        assertTrue(editProfilePage.isCoverImgVisible());
    }

    protected void verifyExperienceData(UserData userData, UserData dataFromPage) {
        assertStringValues(dataFromPage.CompanyName,userData.CompanyName);
        assertStringValues(dataFromPage.Role,userData.Role);
        assertIntValues(dataFromPage.StartDate.get(dataFromPage.StartDate.YEAR),userData.StartDate.get(userData.StartDate.YEAR));
        assertIntValues(dataFromPage.StartDate.get(dataFromPage.StartDate.MONTH),userData.StartDate.get(userData.StartDate.MONTH));
        assertIntValues(dataFromPage.EndDate.get(dataFromPage.EndDate.YEAR),userData.EndDate.get(userData.EndDate.YEAR));
        assertIntValues(dataFromPage.EndDate.get(dataFromPage.EndDate.MONTH),userData.EndDate.get(userData.EndDate.MONTH));
        assertStringValues(dataFromPage.ExpDescription,userData.ExpDescription);
    }

    protected void verifyProfileData(UserData userData, UserData dataFromPage) {
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
        String expectedT=expected.trim();
        String actualT =actual.trim();
        if (!expectedT.equals(actualT))
        {
            LOG.error(String.format("Expected '%1$s', but found '%2$s'",expectedT,actualT));
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

    protected void LogResult() {
        if (TestResult.ErrCount==0)
        {
            LOG.info("Test PASSED");

        }
        else { LOG.info("Test FAILED");}
        Assert.assertEquals(0,TestResult.ErrCount);
    }
}
