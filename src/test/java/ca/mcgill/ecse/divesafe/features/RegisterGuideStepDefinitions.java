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
    * Initializes the DiveSafe system
    * @author danielmakhlin
    */
    @Given("the following DiveSafe system exists: \\(p3)")
    public void the_following_dive_safe_system_exists_p3(io.cucumber.datatable.DataTable dataTable) throws ParseException {
        List<List<String>> row = dataTable.asList(List.class);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date converted = formatter.parse(row.get(1).get(0));
        java.sql.Date convertedStartDate = new java.sql.Date(converted.getTime());

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

    /**
    * Initializes and adds the guides in the system
    * @author radupetrescu
    */
    @Given("the following guides exist in the system: \\(p3)")
    public void the_following_guides_exist_in_the_system_p3(io.cucumber.datatable.DataTable dataTable) {
        List<List<String>> rows = dataTable.asList(List.class);
        for(int i = 1; i < rows.size(); i++) {
            String email = rows.get(i).get(0);
            String password = rows.get(i).get(1);
            String name = rows.get(i).get(2);
            String emergencyContact = rows.get(i).get(3);

            Guide newGuide = new Guide(email, password, name, emergencyContact, DiveSafeApplication.getDiveSafe());
            DiveSafeApplication.getDiveSafe().addGuide(newGuide);
        }
    }

    /**
    * Initializes and adds members in the system
    * @author radupetrescu
    */
    @Given("the following members exist in the system: \\(p3)")
    public void the_following_members_exist_in_the_system_p3(io.cucumber.datatable.DataTable dataTable) throws ParseException {
        List<List<String>> rows = dataTable.asList(List.class);
        for(int i = 1; i < rows.size(); i++) {
            String email = rows.get(i).get(0);
            String password = rows.get(i).get(1);
            String name = rows.get(i).get(2);
            String emergencyContact = rows.get(i).get(3);
            int numDays = Integer.parseInt(rows.get(i).get(4));
            boolean guideRequired = Boolean.parseBoolean(rows.get(i).get(5));
            boolean hotelRequired = Boolean.parseBoolean(rows.get(i).get(6));
            Member newMember = new Member(email, password, name,emergencyContact,numDays,guideRequired,hotelRequired, DiveSafeApplication.getDiveSafe());
      }
    }

    /**
    * Attempts to register a guide
    * @author AlecTufenkjian
    */
    @When("a new guide attempts to register with {string}, {string}, {string}, and {string} \\(p3)")
    public void a_new_guide_attempts_to_register_with_and_p3(String email, String password, String name, String emergencyContact) {
        try {
            System.out.println("Tested Guide: " + email);
            responseFound = GuideController.registerGuide(email, password, name, emergencyContact);
        } catch (Exception e) {
            responseFound = "Error";
            e.printStackTrace();
        }
    }

    /**
    * Compares the expected response with the found response
    * @author AlecTufenkjian
    */
    @Then("the following {string} shall be raised \\(p3)")
    public void the_following_shall_be_raised_p3(String responseExpected) {
        assertEquals(responseExpected, responseFound);
    }
}