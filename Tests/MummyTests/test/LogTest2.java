import java.util.Arrays;
import java.util.Collection;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;


@RunWith(Parameterized.class)
public class LogTest2 {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  private String inputName;
  //private String expectedResult;
  
  @Before
  public void setUp() throws Exception {
    System.setProperty("webdriver.chrome.driver", "c:/temp/chromedriver.exe");
    driver = new ChromeDriver();
    baseUrl = "http://localhost:8080/Mummy/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  public LogTest2(String inputName/*, String expectedResult*/)
  {
      this.inputName=inputName;
     // this.expectedResult=expectedResult;
  }
  
  @Parameterized.Parameters
  public static Collection names(){
    return Arrays.asList(new Object[][] {
        {"user2mail@mail.com"},
        {"fake@mail.com"}
    });
  }
  
  @Test
  public void testLogTest2() throws InterruptedException {
    driver.get(baseUrl);
    Thread.sleep(1000);
    driver.findElement(By.xpath("(//a[contains(text(),'Login')])[3]")).click();
    driver.findElement(By.id("email")).clear();
    driver.findElement(By.id("email")).sendKeys(inputName);
    Thread.sleep(1000);
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("pass");
    Thread.sleep(1000);
    driver.findElement(By.cssSelector("input.waves-button-input")).click();
    Thread.sleep(1000);
        
    //Assert.assertEquals(driver, baseUrl);
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
