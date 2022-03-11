package ca.mcgill.ecse.divesafe.features;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import ca.mcgill.ecse.divesafe.application.DiveSafeApplication;
import ca.mcgill.ecse.divesafe.model.DiveSafe;
import ca.mcgill.ecse.divesafe.model.Guide;
import ca.mcgill.ecse.divesafe.model.Member;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;



public class RegisterGuideStepDefinitions {

    /**
     * @author danielmakhlin
     */

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
  }

  @Given("the following guides exist in the system: \\(p3)")
  public void the_following_guides_exist_in_the_system_p3(io.cucumber.datatable.DataTable dataTable) {
      List<List<String>> rows = dataTable.asList(List.class);
      List<Guide> guides = new ArrayList<Guide>();
      for (List<String> columns : rows) {
          String email = columns.get(0);
          String password = columns.get(1);
          String name = columns.get(2);
          String emergencyContact = columns.get(3);
          guides.add(new Guide(email, password, name, emergencyContact, DiveSafeApplication.getDiveSafe()));
          //Guide newGuide = new Guide(email, password, name, emergencyContact, DiveSafeApplication.getDiveSafe());
      }
      System.out.println(guides);

  }

    /**
     * @author radupetrescu
     */

  @Given("the following members exist in the system: \\(p3)")
  public void the_following_members_exist_in_the_system_p3(io.cucumber.datatable.DataTable dataTable) throws ParseException {
      List<List<String>> rows = dataTable.asList(List.class);
      for (List<String> columns : rows) {
          String email = columns.get(0);
          String password = columns.get(1);
          String name = columns.get(2);
          String emergencyContact = columns.get(3);
          int numDays = Integer.parseInt(columns.get(4));
          boolean guideRequired = Boolean.parseBoolean(columns.get(5));
          boolean hotelRequired = Boolean.parseBoolean(columns.get(6));
          String items = columns.get(7);
          int quantity = Integer.parseInt(columns.get(8));
          Member newMember = new Member(email, password, name,emergencyContact,numDays,guideRequired,hotelRequired, DiveSafeApplication.getDiveSafe());

      }

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

