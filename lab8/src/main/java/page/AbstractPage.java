package page;

import org.openqa.selenium.WebDriver;

public abstract class AbstractPage {

    protected WebDriver driver;

    protected final int TIMEOUT_IN_SECONDS = 20;

    protected abstract AbstractPage openPage();

    protected AbstractPage(WebDriver driver)
    {
        this.driver = driver;
    }

}
