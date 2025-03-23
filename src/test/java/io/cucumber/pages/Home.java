package io.cucumber.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Home extends Page {

  @FindBy(css = "h1")
  private WebElement title;
  @FindBy(tagName = "a")
  List<WebElement> allLinks;
  @FindBy(linkText = "Basic Auth")
  private WebElement credentialLink;
  private By successMessage = By.tagName("p");
  private By tableRows = By.xpath("//table[@id='table1']/tbody/tr");
  private By tableHeaders = By.xpath("//table[@id='table1']/thead/tr/th");

  public Home(ChromeDriver driver) {
    super(driver);
    System.out.println("Homepage title is : " + getTitle().getText());
  }

  public WebElement getTitle() {
    return title;
  }
  public String getHomeTitle() {
    return driver.getTitle();
  }

  public void refresh() {
    driver.navigate().refresh();
    System.out.println("Refreshed page");
  }

  public List<String> getAllLinksText() {
    List<String> linkTexts = new ArrayList<>();
    for (WebElement link : allLinks) {
      linkTexts.add(link.getText());
    }
    return linkTexts;
  }
  public void clickLink(String baseLink){
    credentialLink.click();

  }
  public void clickSortableLink(String sortLink){
    driver.findElement(By.linkText("Sortable Data Tables")).click();

  }
  public String getSuccessMessage() {
    return driver.findElement(successMessage).getText();
  }
  public void openBasicAuthPage(String url, String username, String password) {
    String authUrl = "https://" + username + ":" + password + "@" + url.replace("https://", "");
    System.out.println("authUrl:"+authUrl);
    driver.navigate().to(authUrl);
  }

  public List<Map<String, String>> getTableData() {
    List<WebElement> rows = driver.findElements(tableRows);
    List<WebElement> headers = driver.findElements(tableHeaders);
    List<Map<String, String>> tableData = new ArrayList<>();

    for (WebElement row : rows) {
      List<WebElement> cells = row.findElements(By.tagName("td"));
      Map<String, String> rowData = new java.util.HashMap<>();
      for (int i = 0; i < headers.size()-1; i++) {
        String header = headers.get(i).getText().trim();
        String cellValue = cells.get(i).getText().trim();
        rowData.put(header, cellValue);
      }
      tableData.add(rowData);
    }
    return tableData;
  }
}

