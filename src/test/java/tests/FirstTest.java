package tests;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static junitparams.JUnitParamsRunner.$;
import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class FirstTest {
    private WebDriver driver;

    @Test
    @Parameters(method = "search")
    public void test1(String searchWord, String expectedPageTitle) throws Exception{
        WebElement search = driver.findElement(By.name("q"));
        search.sendKeys(searchWord);
        driver.findElement(By.name("go")).click();
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().startsWith("test");
            }
        });
        assertEquals(expectedPageTitle, driver.getTitle());
    }

    private Object[] search(){
        return $(
                $("Test","Test - Bing"),
                $("Test1","Test1 - Bing" )
        );
    }

    @Before
    public void setup(){
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://www.bing.com");
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
