package ca.mcgill.ecse.divesafe.features;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import ca.mcgill.ecse.divesafe.model.DiveSafe;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


/**
 * @author danielmakhlin
 */
public class RegisterGuideStepDefinitions {

	List<DiveSafe> diveSafes = new LinkedList<DiveSafe>();
  @Given("the following DiveSafe system exists: \\(p3)")
  public void the_following_dive_safe_system_exists_p3(io.cucumber.datatable.DataTable dataTable) throws ParseException {



      List<List<String>> row = dataTable.asList(List.class);
      SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
      Date converted = formatter.parse(row.get(1).get(0));
      java.sql.Date convertedStartDate = new java.sql.Date(converted.getTime());
      System.out.println(convertedStartDate);

      int numDays = Integer.parseInt(row.get(1).get(1));
      int priceOfGuidePerDay = Integer.parseInt(row.get(1).get(2));

      DiveSafe divesafe = new DiveSafe(convertedStartDate, numDays, priceOfGuidePerDay);
      System.out.println("bruh");

    // Write code here that turns the phrase above into concrete actions
    // For automatic transformation, change DataTable to one of
    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
    // Double, Byte, Short, Long, BigInteger or BigDecimal.
    //
    // For other transformations you can register a DataTableType.
  }

  @Given("the following guides exist in the system: \\(p3)")
  public void the_following_guides_exist_in_the_system_p3(
      io.cucumber.datatable.DataTable dataTable) {

    // Write code here that turns the phrase above into concrete actions
    // For automatic transformation, change DataTable to one of
    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
    // Double, Byte, Short, Long, BigInteger or BigDecimal.
    //
    // For other transformations you can register a DataTableType.
    throw new io.cucumber.java.PendingException();
  }

  @Given("the following members exist in the system: \\(p3)")
  public void the_following_members_exist_in_the_system_p3(
      io.cucumber.datatable.DataTable dataTable) {
    // Write code here that turns the phrase above into concrete actions
    // For automatic transformation, change DataTable to one of
    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
    // Double, Byte, Short, Long, BigInteger or BigDecimal.
    //
    // For other transformations you can register a DataTableType.
    throw new io.cucumber.java.PendingException();
  }

  @When("a new guide attempts to register with {string}, {string}, {string}, and {string} \\(p3)")
  public void a_new_guide_attempts_to_register_with_and_p3(String string, String string2,
      String string3, String string4) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Then("a new guide account shall exist with {string}, {string}, {string}, and {string} \\(p3)")
  public void a_new_guide_account_shall_exist_with_and_p3(String string, String string2,
      String string3, String string4) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Then("the number of guides in the system is {int} \\(p3)")
  public void the_number_of_guides_in_the_system_is_p3(Integer int1) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Then("the following {string} shall be raised \\(p3)")
  public void the_following_shall_be_raised_p3(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }
}

