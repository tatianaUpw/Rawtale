package tests.core;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Driver {
    final WebDriver instance;

    protected static final CustomLogger LOG = new CustomLogger();

    public Driver() {
       String pathToCromeDriver = System.getProperty("user.dir")+"\\cromedriver\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", pathToCromeDriver);
        instance = new ChromeDriver();
        LOG.info("Web Driver initializes");
        instance.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        instance.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        LOG.info("Web Driver sets up timeouts");
        instance.manage().deleteAllCookies();
        LOG.info("Web Driver deletes cokies");
        instance.manage().window().maximize();
        LOG.info("Web Driver maximizes window");
    }

    public WebDriver getInstance()
    {
        if (instance ==null)
        {
            new Driver();
        }
        return instance;
    }

    public void safeQuit() {

        LOG.info( "Web Driver closes");
        if (instance == null) {
            return;
        }
        instance.close();
        instance.quit();
    }
    public boolean waitForJSandJQueryToLoad() {

        WebDriverWait wait = new WebDriverWait(this.instance,120);

        // wait for jQuery to load
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((Long)((JavascriptExecutor)driver).executeScript("return jQuery.active") == 0);
                }
                catch (Exception e) {
                    // no jQuery present
                    return true;
                }
            }
        };

        // wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor)driver).executeScript("return document.readyState")
                        .toString().equals("complete");
            }
        };

        return wait.until(jQueryLoad) && wait.until(jsLoad);
    }




}
