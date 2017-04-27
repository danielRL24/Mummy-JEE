package com.example.tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class EditParticipantTest {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://localhost:8080/Mummy/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testEditParticipant() throws Exception {
    driver.get(baseUrl + "/Mummy/task/List.xhtml");
    driver.navigate().refresh();
    for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { if (isElementPresent(By.cssSelector("div.collapsible-header"))) break; } catch (Exception e) {}
    	Thread.sleep(1000);
    }

    driver.findElement(By.cssSelector("div.collapsible-header")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | null | ]]
    for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { if (isElementPresent(By.xpath("//a[@onclick=\"mojarra.jsfcljs(document.getElementById('j_idt60'),{'j_idt60:j_idt65:0:j_idt100':'j_idt60:j_idt65:0:j_idt100'},'');return false\"]"))) break; } catch (Exception e) {}
    	Thread.sleep(1000);
    }

    driver.findElement(By.xpath("//a[@onclick=\"mojarra.jsfcljs(document.getElementById('j_idt60'),{'j_idt60:j_idt65:0:j_idt100':'j_idt60:j_idt65:0:j_idt100'},'');return false\"]")).click();
    driver.findElement(By.id("j_idt54:description")).click();
    driver.findElement(By.id("j_idt54:description")).clear();
    driver.findElement(By.id("j_idt54:description")).sendKeys("Test login and task creation");
    driver.findElement(By.xpath("//table[@id='j_idt54:end_table']/tbody/tr[5]/td[7]/div")).click();
    driver.findElement(By.xpath("(//button[@type='button'])[6]")).click();
    driver.findElement(By.id("j_idt54:progression")).click();
    driver.findElement(By.id("j_idt54:progression")).click();
    driver.findElement(By.id("j_idt54:progression")).clear();
    driver.findElement(By.id("j_idt54:progression")).sendKeys("10");
    driver.findElement(By.cssSelector("div.card-content")).click();
    driver.findElement(By.xpath("//a[@onclick=\"mojarra.jsfcljs(document.getElementById('j_idt54'),{'j_idt54:j_idt77':'j_idt54:j_idt77'},'');return false\"]")).click();
    driver.findElement(By.cssSelector("i.material-icons.left")).click();
    driver.findElement(By.xpath("(//input[@value='---'])[1]")).click();
    new Select(driver.findElement(By.name("j_idt53:idUser"))).selectByVisibleText("user2");
    driver.findElement(By.xpath("//form[@id='j_idt53']/a/i")).click();
    driver.findElement(By.linkText("My tasks")).click();
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
