import com.thoughtworks.selenium.Selenium;
import java.util.Arrays;
import java.util.Collection;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;


@RunWith(Parameterized.class)
public class LogginTest2 {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  
  private final String userMail;
  private final String userPwd;
  
  public LogginTest2(String email, String pwd)
  {
      this.userMail=email;
      this.userPwd=pwd;
  }

  @Before
  public void setUp() throws Exception {
    System.setProperty("webdriver.chrome.driver", "c:/temp/chromedriver.exe");
    driver = new ChromeDriver();
    baseUrl = "http://localhost:8080/Mummy/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);   
  }
  
    @Parameters
    public static Collection<Object[]> params() {
        return Arrays.asList(
                new Object[]{"newUser@mail.com", "pass"},
                new Object[]{"wrongUser@mail.com", "faux"}
        );
    }

  @Test
  public void testLogginTest2() throws Exception {
    driver.get(baseUrl);
    driver.findElement(By.xpath("(//a[contains(text(),'Login')])[3]")).click();
    driver.findElement(By.id("email")).clear();
    //driver.findElement(By.id("email")).sendKeys("newUser@mail.com");
    driver.findElement(By.id("email")).sendKeys(this.userMail);
    driver.findElement(By.id("password")).clear();
    //driver.findElement(By.id("password")).sendKeys("pass");
    driver.findElement(By.id("password")).sendKeys(this.userPwd);

    driver.findElement(By.cssSelector("input.waves-button-input")).click();   
    Thread.sleep(1000);
   
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
