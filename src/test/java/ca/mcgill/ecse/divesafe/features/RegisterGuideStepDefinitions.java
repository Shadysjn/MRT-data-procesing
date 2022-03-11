package ca.mcgill.ecse.divesafe.features;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import ca.mcgill.ecse.divesafe.application.DiveSafeApplication;
import ca.mcgill.ecse.divesafe.controller.GuideController;
import ca.mcgill.ecse.divesafe.model.DiveSafe;
import ca.mcgill.ecse.divesafe.model.Guide;
import ca.mcgill.ecse.divesafe.model.Member;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.*;


public class RegisterGuideStepDefinitions {

    String responseFound;

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

      //delete the old difesave application to remove the previous tests data
      DiveSafeApplication.getDiveSafe().delete();
      //create a blank new DiveSafe Application
      DiveSafe divesafe = DiveSafeApplication.getDiveSafe();
      //set the attributes of our divesafe system to be the ones that we got from the cucumber table
      divesafe.setStartDate(convertedStartDate);
      divesafe.setNumDays(numDays);
      divesafe.setPriceOfGuidePerDay(priceOfGuidePerDay);
  }

  @Given("the following guides exist in the system: \\(p3)")
  public void the_following_guides_exist_in_the_system_p3(io.cucumber.datatable.DataTable dataTable) {
      List<List<String>> rows = dataTable.asList(List.class);
      for(int i = 1; i < rows.size(); i++) {
          String email = rows.get(i).get(0);
          System.out.println(email);
          String password = rows.get(i).get(1);
          String name = rows.get(i).get(2);
          String emergencyContact = rows.get(i).get(3);

          Guide newGuide = new Guide(email, password, name, emergencyContact, DiveSafeApplication.getDiveSafe());
          DiveSafeApplication.getDiveSafe().addGuide(newGuide);
          //guides.add(new Guide(email, password, name, emergencyContact, DiveSafeApplication.getDiveSafe()));
          //Guide newGuide = new Guide(email, password, name, emergencyContact, DiveSafeApplication.getDiveSafe());
      }
      //System.out.println(guides);
      System.out.println("-------------------------");

  }


    /**
     * @author radupetrescu
     */
  @Given("the following members exist in the system: \\(p3)")
  public void the_following_members_exist_in_the_system_p3(io.cucumber.datatable.DataTable dataTable) throws ParseException {
      List<List<String>> rows = dataTable.asList(List.class);
      for(int i = 1; i < rows.size(); i++) {

          String email = rows.get(i).get(0);
          System.out.println(email);
          String password = rows.get(i).get(1);
          String name = rows.get(i).get(2);
          String emergencyContact = rows.get(i).get(3);
          int numDays = Integer.parseInt(rows.get(i).get(4));
          boolean guideRequired = Boolean.parseBoolean(rows.get(i).get(5));
          boolean hotelRequired = Boolean.parseBoolean(rows.get(i).get(6));
          //String items = rows.get(i).get(7);
          //int quantity = Integer.parseInt(rows.get(i).get(8));
          Member newMember = new Member(email, password, name,emergencyContact,numDays,guideRequired,hotelRequired, DiveSafeApplication.getDiveSafe());

      }

      System.out.println("-------------------------");

  }

    /**
     * @author AlecTufenkjian
     */
  @When("a new guide attempts to register with {string}, {string}, {string}, and {string} \\(p3)")
  public void a_new_guide_attempts_to_register_with_and_p3(String email, String password, String name, String emergencyContact) {

      try {
         responseFound = GuideController.registerGuide(email, password, name, emergencyContact);
      } catch (Exception e) {
          e.printStackTrace();
      }
  }

    /**
     * @author AlecTufenkjian
     */
  @Then("a new guide account shall exist with {string}, {string}, {string}, and {string} \\(p3)")
  public void a_new_guide_account_shall_exist_with_and_p3(String email, String password, String name,String emergencyContact) {
      List<Guide> guides = DiveSafeApplication.getDiveSafe().getGuides();

      boolean guideFound = false;
      for (Guide guide: guides){
          if(guide.getEmail().equals(email) && guide.getPassword().equals(password) && guide.getName().equals(name) && guide.getEmergencyContact().equals(emergencyContact)) {
             guideFound = true;
          }
      }
      assertTrue(guideFound);
  }
    /**
     * @author AlecTufenkjian
     */
  @Then("the number of guides in the system is {int} \\(p3)")
  public void the_number_of_guides_in_the_system_is_p3(Integer numberOfGuidesExpected) {
      Integer numberOfGuidesFound = DiveSafeApplication.getDiveSafe().numberOfGuides();

      assertEquals(numberOfGuidesExpected, numberOfGuidesFound);
  }
    /**
     * @author AlecTufenkjian
     */
  @Then("the following {string} shall be raised \\(p3)")
  public void the_following_shall_be_raised_p3(String responseExpected) {
        assertEquals(responseExpected, responseFound);
  }
}

