package io.cucumber.glue;

import io.cucumber.core.Context;
import io.cucumber.core.Manager;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class Home extends Context {

  public Home(Manager manager) {
    super(manager);
  }
  private io.cucumber.pages.Home homePage = new io.cucumber.pages.Home(getDriver());

  @When("page contain the title {string}")
  public void page_contain_the_title(String name) {
   String title=homePage.getHomeTitle();
   assertTrue(title.contains(name));

  }
  @Then("expected list should contains actual list")
  public void expectedListShouldContainsActualList(List<String> expectedLinks) {
    List<String> actualLinksText = homePage.getAllLinksText();
    System.out.println("actuals links: "+actualLinksText);
    System.out.println("expectedLinks: "+expectedLinks);

    assertTrue(actualLinksText.containsAll(expectedLinks), "Links do not match!");
  }

  @When("the {string} example is opened")
  public void theExampleIsOpened(String linkText) {
    homePage.clickLink(linkText);
  }

  @When("valid credentials are {string} and {string}")
  public void valid_credentials_are_and(String username, String password) {
    homePage.openBasicAuthPage("the-internet.herokuapp.com/basic_auth", username, password);
  }

  @Then("Congratulations should be displayed")
  public void congratulationsShouldBeDisplayed() {
    String message = homePage.getSuccessMessage();
    assertEquals("Congratulations! You must have the proper credentials.", message);
  }

  @When("click the sortable{string} link")
  public void clickTheSortableLink(String linkText) {
    homePage.clickSortableLink(linkText);
  }

  @Then("Example  table displays the following results")
  public void example_table_displays_the_following_results(DataTable dataTable) {
    List<Map<String, String>> expectedData = dataTable.asMaps(String.class, String.class);
    List<Map<String, String>> actualData = homePage.getTableData();
    assertEquals(expectedData,actualData);
  }
}
