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
    //driver.get(baseUrl + "/task/List.xhtml");
    driver.get(baseUrl);
    
    loggingSequence();
    
    Thread.sleep(1000);
        for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { if (isElementPresent(By.xpath("//form[@id='j_idt60']/div[2]/a/i"))) break; } catch (Exception e) {}
    	Thread.sleep(1000);
    }
    driver.findElement(By.cssSelector("i.material-icons.left")).click();
    Thread.sleep(1000);
    driver.findElement(By.id("j_idt54:title")).click();
    Thread.sleep(1000);
    driver.findElement(By.id("j_idt54:title")).click();
    Thread.sleep(1000);
    driver.findElement(By.id("j_idt54:title")).clear();
    Thread.sleep(1000);
    driver.findElement(By.id("j_idt54:title")).sendKeys("Selenium tests");
    Thread.sleep(1000);
    driver.findElement(By.id("j_idt54:description")).click();
    Thread.sleep(1000);
    driver.findElement(By.id("j_idt54:description")).clear();
    Thread.sleep(1000);
    driver.findElement(By.id("j_idt54:description")).sendKeys("JEE tests");
    Thread.sleep(1000);
   
    driver.findElement(By.id("j_idt54:begin")).click();
    driver.findElement(By.xpath("//table[@id='j_idt54:begin_table']/tbody/tr[5]/td[6]/div")).click();
    Thread.sleep(1000);
    driver.findElement(By.cssSelector("button.btn-flat.picker__close")).click();
    Thread.sleep(1000);

    driver.findElement(By.id("j_idt54:end")).click();
    driver.findElement(By.xpath("//table[@id='j_idt54:end_table']/tbody/tr[5]/td[6]/div")).click();
    Thread.sleep(1000);

    driver.findElement(By.xpath("(//button[@type='button'])[6]")).click();
    Thread.sleep(1000);
    driver.findElement(By.id("j_idt54:category")).click();
    driver.findElement(By.id("j_idt54:category")).click();
    driver.findElement(By.id("j_idt54:category")).clear();
    driver.findElement(By.id("j_idt54:category")).sendKeys("test");
    
    
//    driver.findElement(By.xpath("(//input[@value='---'])[2]")).click();
//    Thread.sleep(1000);
//    //it bug
//    new Select(driver.findElement(By.name("j_idt54:fkPriority"))).selectByVisibleText("standard");   
    Thread.sleep(1000);
   // Select select = new Select(driver.findElement(By.xpath("(//input[@value='---'])[2]")));
    //select.deselectAll();
    //select.selectByVisibleText("standard");

//    driver.findElement(By.xpath("(//input[@value='---'])[3]")).click();
//    Thread.sleep(1000);
//    new Select(driver.findElement(By.name("j_idt54:fkStatus"))).selectByVisibleText("todo");
    
    Thread.sleep(1000);
    
    driver.findElement(By.xpath("//form[@id='j_idt54']/div/div/div/a/i")).click();
    Thread.sleep(1000);
    driver.findElement(By.linkText("Mummy")).click();
    Thread.sleep(1000);
  }
  
   private void loggingSequence() throws Exception{
    driver.findElement(By.xpath("(//a[contains(text(),'Login')])[3]")).click();
    driver.findElement(By.id("email")).clear();
    driver.findElement(By.id("email")).sendKeys("newUser@mail.com");
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("pass");
    

    driver.findElement(By.cssSelector("input.waves-button-input")).click();
    Thread.sleep(500);
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
