/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.util.*;
import java.sql.Date;

// line 21 "model.ump"
// line 163 "model.ump"
public class Guide extends Account
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Guide Attributes
  private int price;
  private String emergencyContact;

  //Guide Associations
  private List<SwimDay> workingDays;
  private Admin admin;
  private ExpeditionDay day;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Guide(String aName, String aEmail, String aPassword, int aPrice, String aEmergencyContact, Admin aAdmin, ExpeditionDay aDay)
  {
    super(aName, aEmail, aPassword);
    price = aPrice;
    emergencyContact = aEmergencyContact;
    workingDays = new ArrayList<SwimDay>();
    boolean didAddAdmin = setAdmin(aAdmin);
    if (!didAddAdmin)
    {
      throw new RuntimeException("Unable to create guide due to admin. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddDay = setDay(aDay);
    if (!didAddDay)
    {
      throw new RuntimeException("Unable to create assignedGuide due to day. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setPrice(int aPrice)
  {
    boolean wasSet = false;
    price = aPrice;
    wasSet = true;
    return wasSet;
  }

  public boolean setEmergencyContact(String aEmergencyContact)
  {
    boolean wasSet = false;
    emergencyContact = aEmergencyContact;
    wasSet = true;
    return wasSet;
  }

  public int getPrice()
  {
    return price;
  }

  public String getEmergencyContact()
  {
    return emergencyContact;
  }
  /* Code from template association_GetMany */
  public SwimDay getWorkingDay(int index)
  {
    SwimDay aWorkingDay = workingDays.get(index);
    return aWorkingDay;
  }

  public List<SwimDay> getWorkingDays()
  {
    List<SwimDay> newWorkingDays = Collections.unmodifiableList(workingDays);
    return newWorkingDays;
  }

  public int numberOfWorkingDays()
  {
    int number = workingDays.size();
    return number;
  }

  public boolean hasWorkingDays()
  {
    boolean has = workingDays.size() > 0;
    return has;
  }

  public int indexOfWorkingDay(SwimDay aWorkingDay)
  {
    int index = workingDays.indexOf(aWorkingDay);
    return index;
  }
  /* Code from template association_GetOne */
  public Admin getAdmin()
  {
    return admin;
  }
  /* Code from template association_GetOne */
  public ExpeditionDay getDay()
  {
    return day;
  }
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfWorkingDaysValid()
  {
    boolean isValid = numberOfWorkingDays() >= minimumNumberOfWorkingDays();
    return isValid;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfWorkingDays()
  {
    return 1;
  }
  /* Code from template association_AddMandatoryManyToOne */
  public SwimDay addWorkingDay(int aDay, Date aCurrentDay, ExpeditionDay aExpeditionDay)
  {
    SwimDay aNewWorkingDay = new SwimDay(aDay, aCurrentDay, this, aExpeditionDay);
    return aNewWorkingDay;
  }

  public boolean addWorkingDay(SwimDay aWorkingDay)
  {
    boolean wasAdded = false;
    if (workingDays.contains(aWorkingDay)) { return false; }
    Guide existingGuideWorking = aWorkingDay.getGuideWorking();
    boolean isNewGuideWorking = existingGuideWorking != null && !this.equals(existingGuideWorking);

    if (isNewGuideWorking && existingGuideWorking.numberOfWorkingDays() <= minimumNumberOfWorkingDays())
    {
      return wasAdded;
    }
    if (isNewGuideWorking)
    {
      aWorkingDay.setGuideWorking(this);
    }
    else
    {
      workingDays.add(aWorkingDay);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeWorkingDay(SwimDay aWorkingDay)
  {
    boolean wasRemoved = false;
    //Unable to remove aWorkingDay, as it must always have a guideWorking
    if (this.equals(aWorkingDay.getGuideWorking()))
    {
      return wasRemoved;
    }

    //guideWorking already at minimum (1)
    if (numberOfWorkingDays() <= minimumNumberOfWorkingDays())
    {
      return wasRemoved;
    }

    workingDays.remove(aWorkingDay);
    wasRemoved = true;
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addWorkingDayAt(SwimDay aWorkingDay, int index)
  {  
    boolean wasAdded = false;
    if(addWorkingDay(aWorkingDay))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfWorkingDays()) { index = numberOfWorkingDays() - 1; }
      workingDays.remove(aWorkingDay);
      workingDays.add(index, aWorkingDay);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveWorkingDayAt(SwimDay aWorkingDay, int index)
  {
    boolean wasAdded = false;
    if(workingDays.contains(aWorkingDay))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfWorkingDays()) { index = numberOfWorkingDays() - 1; }
      workingDays.remove(aWorkingDay);
      workingDays.add(index, aWorkingDay);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addWorkingDayAt(aWorkingDay, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOneToMany */
  public boolean setAdmin(Admin aAdmin)
  {
    boolean wasSet = false;
    if (aAdmin == null)
    {
      return wasSet;
    }

    Admin existingAdmin = admin;
    admin = aAdmin;
    if (existingAdmin != null && !existingAdmin.equals(aAdmin))
    {
      existingAdmin.removeGuide(this);
    }
    admin.addGuide(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToOptionalOne */
  public boolean setDay(ExpeditionDay aNewDay)
  {
    boolean wasSet = false;
    if (aNewDay == null)
    {
      //Unable to setDay to null, as assignedGuide must always be associated to a day
      return wasSet;
    }
    
    Guide existingAssignedGuide = aNewDay.getAssignedGuide();
    if (existingAssignedGuide != null && !equals(existingAssignedGuide))
    {
      //Unable to setDay, the current day already has a assignedGuide, which would be orphaned if it were re-assigned
      return wasSet;
    }
    
    ExpeditionDay anOldDay = day;
    day = aNewDay;
    day.setAssignedGuide(this);

    if (anOldDay != null)
    {
      anOldDay.setAssignedGuide(null);
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    for(int i=workingDays.size(); i > 0; i--)
    {
      SwimDay aWorkingDay = workingDays.get(i - 1);
      aWorkingDay.delete();
    }
    Admin placeholderAdmin = admin;
    this.admin = null;
    if(placeholderAdmin != null)
    {
      placeholderAdmin.removeGuide(this);
    }
    ExpeditionDay existingDay = day;
    day = null;
    if (existingDay != null)
    {
      existingDay.setAssignedGuide(null);
    }
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "price" + ":" + getPrice()+ "," +
            "emergencyContact" + ":" + getEmergencyContact()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "admin = "+(getAdmin()!=null?Integer.toHexString(System.identityHashCode(getAdmin())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "day = "+(getDay()!=null?Integer.toHexString(System.identityHashCode(getDay())):"null");
  }
}