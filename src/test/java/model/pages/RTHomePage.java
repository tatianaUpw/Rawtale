package model.pages;

public class RTHomePage extends PageBase {

    public Header header;


    public RTHomePage(tests.core.Driver driver) throws IllegalAccessException {
        super(driver, "RawTale | The Leading Platform for Business Partnership");
        header = new Header(driver);
    }
}
