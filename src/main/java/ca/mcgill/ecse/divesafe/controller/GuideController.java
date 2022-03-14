package ca.mcgill.ecse.divesafe.controller;
import ca.mcgill.ecse.divesafe.application.DiveSafeApplication;
import ca.mcgill.ecse.divesafe.model.Guide;
import ca.mcgill.ecse.divesafe.model.Member;

public class GuideController {

	/**
	 * @author danielmakhlin, radupetrescu, shadyguindi, AlecTufenkjian, androwabdelmalak, yassinemeliani
	 * @param email - email string
	 * @param password - password string
	 * @param name - name string
	 * @param emergencyContact - emergency contact string
	 * @return error message if registration fails, null if registration succeeds
	 * @throws Exception
	 */
	public static String registerGuide(String email, String password, String name,
		String emergencyContact) throws Exception {

		//Verifies for empty parameters
		if (email == null || email.equals("")) return "Email cannot be empty";
		if (password == null || password.equals("")) return "Password cannot be empty";
		if (name == null || name.equals("")) return "Name cannot be empty";
		if (emergencyContact == null || emergencyContact.equals("")) return "Emergency contact cannot be empty";

		int numberOfAts = getCharacterFrequency(email, '@');
		int numberOfSpaces = getCharacterFrequency(email, ' ');
		int numberOfDots = getCharacterFrequency(email, '.');

		//Verifies character counts
		if(numberOfSpaces != 0) return "Email must not contain any spaces";
		if(numberOfAts != 1 || numberOfDots != 1) return "Invalid email";


		int positionOfAt = getCharacterPosition(email, '@');
		int positionOfDot = getCharacterPosition(email, '.');

		//Verifies range of characters
		for (int i = 0; i < email.length(); i++) {
			if (!((email.charAt(i)>= 97 || email.charAt(i)<=122) || (email.charAt(i)=='@') || (email.charAt(i)=='.'))) {
				return "Invalid email";
			}
		}

		//Verifies email is not reserved
		if (email.equals("admin@nmc.nt")) return "Email cannot be admin@nmc.nt";

		String[] splitEmailWithDot = email.split("\\.");
		String[] splitEmailWithAt = email.split("@");

		//Verifies position of '.' and '@' in email
		if(splitEmailWithAt.length != 2 || positionOfAt == 0) return "Invalid email";
		if(splitEmailWithDot.length != 2 || positionOfDot == 0) return "Invalid email";
		if(Math.abs(positionOfDot - positionOfAt) == 1) return "Invalid email";
		if(positionOfDot < positionOfAt) return "Invalid email";

		//check if the email is NOT linked to an existing member account
		if (Member.hasWithEmail(email)) return "Email already linked to a member account";

		//check if the email is NOT linked to an existing guide account
		if (Guide.hasWithEmail(email)) return "Email already linked to a guide account";

		//Registering and adding the new guide
		Guide newGuide = new Guide(email, password, name, emergencyContact, DiveSafeApplication.getDiveSafe());
		DiveSafeApplication.getDiveSafe().addGuide(newGuide);

		return null;
	}

	/**
	 * returns number of target character found in given string
	 * @author AlecTufenkjian
	 * @param email - email string
	 * @param character - target character
	 * @return number of target character found
	 */
	private static int getCharacterFrequency(String email, char character){
	  int counter = 0;

	  for(int i = 0; i < email.length(); i++){
		  if(email.charAt(i) == character) counter++;
	  }

	  return counter;
	}

	/**
	 * returns position of first occurring target character in given string
	 * @author AlecTufenkjian
	 * @param email - email string
	 * @param character - target character
	 * @return position of first occurring target character
	 */
	private static int getCharacterPosition(String email, char character){

		for(int i = 0; i < email.length(); i++){
			if(email.charAt(i) == character) return i;
		}

		return -1;
	}

	/**
	 * Updates Guide with new information
	 * @param email - email string
	 * @param newPassword - new password string
	 * @param newName - new name string
	 * @param newEmergencyContact - new emergency contact string
	 * @return response failure/success
	 */
	public static String updateGuide(String email, String newPassword, String newName,
	  String newEmergencyContact) {
		return null;
	}

	/**
	 * Deletes guide based on email
	 * @param email
	 */
  	public static void deleteGuide(String email) {}
}
