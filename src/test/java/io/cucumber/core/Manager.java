package io.cucumber.core;

import org.openqa.selenium.chrome.ChromeDriver;

import java.util.HashMap;

public class Manager {

  //Used as a stash for any data that needs shared across steps
  final HashMap<String, Object> stash;

  private ChromeDriver driver;

  public Manager() {
    this.stash = new HashMap<>();
  }

  public ChromeDriver getDriver() {
    return driver;
  }

  public void setDriver(ChromeDriver driver) {
    this.driver = driver;
  }
}