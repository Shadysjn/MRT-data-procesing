/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.util.*;

// line 29 "model.ump"
// line 169 "model.ump"
public class Member extends Account
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Member Attributes
  private String emergencyContact;

  //Member Associations
  private Season season;
  private Admin admin;
  private List<Expedition> expeditions;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Member(String aName, String aEmail, String aPassword, String aEmergencyContact, Season aSeason, Admin aAdmin)
  {
    super(aName, aEmail, aPassword);
    emergencyContact = aEmergencyContact;
    if (!setSeason(aSeason))
    {
      throw new RuntimeException("Unable to create Member due to aSeason. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddAdmin = setAdmin(aAdmin);
    if (!didAddAdmin)
    {
      throw new RuntimeException("Unable to create member due to admin. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    expeditions = new ArrayList<Expedition>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setEmergencyContact(String aEmergencyContact)
  {
    boolean wasSet = false;
    emergencyContact = aEmergencyContact;
    wasSet = true;
    return wasSet;
  }

  public String getEmergencyContact()
  {
    return emergencyContact;
  }
  /* Code from template association_GetOne */
  public Season getSeason()
  {
    return season;
  }
  /* Code from template association_GetOne */
  public Admin getAdmin()
  {
    return admin;
  }
  /* Code from template association_GetMany */
  public Expedition getExpedition(int index)
  {
    Expedition aExpedition = expeditions.get(index);
    return aExpedition;
  }

  public List<Expedition> getExpeditions()
  {
    List<Expedition> newExpeditions = Collections.unmodifiableList(expeditions);
    return newExpeditions;
  }

  public int numberOfExpeditions()
  {
    int number = expeditions.size();
    return number;
  }

  public boolean hasExpeditions()
  {
    boolean has = expeditions.size() > 0;
    return has;
  }

  public int indexOfExpedition(Expedition aExpedition)
  {
    int index = expeditions.indexOf(aExpedition);
    return index;
  }
  /* Code from template association_SetUnidirectionalOne */
  public boolean setSeason(Season aNewSeason)
  {
    boolean wasSet = false;
    if (aNewSeason != null)
    {
      season = aNewSeason;
      wasSet = true;
    }
    return wasSet;
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
      existingAdmin.removeMember(this);
    }
    admin.addMember(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfExpeditions()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Expedition addExpedition(int aNumberOfExpeditionDays)
  {
    return new Expedition(aNumberOfExpeditionDays, this);
  }

  public boolean addExpedition(Expedition aExpedition)
  {
    boolean wasAdded = false;
    if (expeditions.contains(aExpedition)) { return false; }
    Member existingBooker = aExpedition.getBooker();
    boolean isNewBooker = existingBooker != null && !this.equals(existingBooker);
    if (isNewBooker)
    {
      aExpedition.setBooker(this);
    }
    else
    {
      expeditions.add(aExpedition);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeExpedition(Expedition aExpedition)
  {
    boolean wasRemoved = false;
    //Unable to remove aExpedition, as it must always have a booker
    if (!this.equals(aExpedition.getBooker()))
    {
      expeditions.remove(aExpedition);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addExpeditionAt(Expedition aExpedition, int index)
  {  
    boolean wasAdded = false;
    if(addExpedition(aExpedition))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfExpeditions()) { index = numberOfExpeditions() - 1; }
      expeditions.remove(aExpedition);
      expeditions.add(index, aExpedition);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveExpeditionAt(Expedition aExpedition, int index)
  {
    boolean wasAdded = false;
    if(expeditions.contains(aExpedition))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfExpeditions()) { index = numberOfExpeditions() - 1; }
      expeditions.remove(aExpedition);
      expeditions.add(index, aExpedition);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addExpeditionAt(aExpedition, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    season = null;
    Admin placeholderAdmin = admin;
    this.admin = null;
    if(placeholderAdmin != null)
    {
      placeholderAdmin.removeMember(this);
    }
    for(int i=expeditions.size(); i > 0; i--)
    {
      Expedition aExpedition = expeditions.get(i - 1);
      aExpedition.delete();
    }
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "emergencyContact" + ":" + getEmergencyContact()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "season = "+(getSeason()!=null?Integer.toHexString(System.identityHashCode(getSeason())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "admin = "+(getAdmin()!=null?Integer.toHexString(System.identityHashCode(getAdmin())):"null");
  }
}