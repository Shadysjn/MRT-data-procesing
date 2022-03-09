package ca.mcgill.ecse.divesafe.controller;
import ca.mcgill.ecse.divesafe.application.DiveSafeApplication;
import ca.mcgill.ecse.divesafe.model.Guide;
import ca.mcgill.ecse.divesafe.model.Member;


public class GuideController {
  public static String registerGuide(String email, String password, String name,
      String emergencyContact) throws Exception {
	  //check if the email is valid 
	  
	  	email.toLowerCase();
	  	if (email=="") {
	  		return "Eamil cannot be empty");
	  	}
	  
	  	//check that there is one @
	  	int amountOfAts = 0; 
	  	int amountOfSpaces = 0; 
	  	boolean validSplit = true; 
	  	for (int i = 0; i<email.length();i++) {
	  		if (email.charAt(i)=='@') {
	  			amountOfAts++;
	  		}
	  		if (email.charAt(i)==' ') {
	  			amountOfSpaces++;
	  		}
	  		
	  		//check if we should remove this 
	  		if (!((email.charAt(i)>= 97 || email.charAt(i)<=122)||(email.charAt(i)=='@') || (email.charAt(i)=='.'))) {
	  			validSplit = false; 
	  		}
	  	}
	  	
	  	String[] splitString = email.split(".");
	  	if (splitString.length>2) {
	  		//cannot have more than 1 dot
	  		validSplit = false; 
	  	}else if (splitString[1]=="com" && splitString[0].charAt(splitString[0].length()-1)=='@') {
	  		// cannot have the situation in which @.com
	  		validSplit = false; 
	  	}else if (splitString[1]!="com") {
	  		//make sure 
	  		validSplit = false;
	  	}else if (amountOfAts!=1 ) {
	  		//makes sure there is exactly 1 @ in the string 
	  		validSplit = false; 
	  	}
	  	

	  	if (amountOfSpaces>0) {
	  		//cannot contain spaces
	  		return "Email must not contain any spaces";
	  	}
	  	if (validSplit == false) {
	  		//check if the email syntax is valid 
	  		return "invalid email";
	  	}
	  	

	  
	  //check if the email is NOT the admin email 
	  if (email.equals("admin@nmc.nt")) {
		  return "Email cannot be admin@nmc.nt";
	  }
	  
	  //check if the email is NOT linked to an existing guide account 
	  if (!Guide.hasWithEmail(email)) {
		  return "Email already linked to a guide account"; 
	  }
	  
	  //check if the email is NOT linked to an existing member account 
	  if (!Member.hasWithEmail(email)) {
		  return "Email already linked to a member account";
	  
	  }
	  //password is not empty 
	  if (password.equals("") || password.equals(null)) {
		  return "Password cannot be empty";
	  }
	  
	  //name is not empty 
	  if (name.equals("") || password.equals(null)) {
		  return "Name cannot be empty";  
	  }
	  
	  //emergency contact cannot be empty 
	  if (emergencyContact.equals("") || emergencyContact.equals(null)) {
		  return "Emergency contact cannot be empty";
	  }
		 
    
    //registering the new guide 
    Guide newGuide = new Guide(email, password, name, emergencyContact, DiveSafeApplication.getDiveSafe());
    
    return null; 
  }

  public static String updateGuide(String email, String newPassword, String newName,
      String newEmergencyContact) {
    return null;
  }

  public static void deleteGuide(String email) {}

}
