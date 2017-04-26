import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class CreatTask {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    System.setProperty("webdriver.chrome.driver", "c:/temp/chromedriver.exe");
    driver = new ChromeDriver();
    baseUrl = "http://localhost:8080/Mummy/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testCreatTask() throws Exception {
    driver.get(baseUrl);
    driver.findElement(By.xpath("(//a[contains(text(),'My tasks')])[3]")).click();
    driver.findElement(By.cssSelector("i.material-icons.left")).click();
    driver.findElement(By.id("j_idt54:title")).click();
    driver.findElement(By.id("j_idt54:title")).clear();
    driver.findElement(By.id("j_idt54:title")).sendKeys("etetetet");
    driver.findElement(By.id("j_idt54:description")).click();
    driver.findElement(By.id("j_idt54:description")).clear();
    driver.findElement(By.id("j_idt54:description")).sendKeys("tetetete");
    driver.findElement(By.xpath("//table[@id='j_idt54:begin_table']/tbody/tr[5]/td[5]/div")).click();
    driver.findElement(By.cssSelector("button.btn-flat.picker__close")).click();
    driver.findElement(By.xpath("//div[@id='j_idt54:end_root']/div")).click();
    driver.findElement(By.xpath("//div[@id='j_idt54:end_root']/div")).click();
    driver.findElement(By.xpath("//table[@id='j_idt54:end_table']/tbody/tr[5]/td[5]/div")).click();
    driver.findElement(By.id("j_idt54:end")).click();
    driver.findElement(By.xpath("//table[@id='j_idt54:end_table']/tbody/tr[5]/td[5]/div")).click();
    driver.findElement(By.xpath("(//button[@type='button'])[6]")).click();
    driver.findElement(By.id("j_idt54:category")).click();
    driver.findElement(By.id("j_idt54:category")).clear();
    driver.findElement(By.id("j_idt54:category")).sendKeys("dfdff");
    driver.findElement(By.id("j_idt54:progression")).click();
    driver.findElement(By.xpath("(//input[@value='---'])[2]")).click();
    new Select(driver.findElement(By.name("j_idt54:fkPriority"))).selectByVisibleText("standard");
    driver.findElement(By.xpath("(//input[@value='---'])[3]")).click();
    new Select(driver.findElement(By.name("j_idt54:fkStatus"))).selectByVisibleText("todo");
    driver.findElement(By.xpath("//a[@onclick=\"mojarra.jsfcljs(document.getElementById('j_idt54'),{'j_idt54:j_idt77':'j_idt54:j_idt77'},'');return false\"]")).click();
    driver.findElement(By.linkText("Mummy")).click();
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
