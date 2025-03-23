package io.cucumber.pages;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class Page {

  protected ChromeDriver driver;

  public Page(ChromeDriver driver) {
    this.driver = driver;
    PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(5)), this);
    waitForPageLoad();
  }

  public void waitForPageLoad() {
    try {
      Duration duration = Duration.ofSeconds(10);

      Thread.sleep(duration.toMillis());
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}