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

public class RawTaleTests extends BaseTest {


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




}
