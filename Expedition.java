/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.util.*;
import java.sql.Date;

// line 97 "model.ump"
// line 118 "model.ump"
public class Expedition
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Expedition Attributes
  private int numberOfExpeditionDays;

  //Expedition Associations
  private List<HotelRental> hotelRentals;
  private List<Rental> rentals;
  private Member booker;
  private List<ExpeditionDay> listOfDays;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Expedition(int aNumberOfExpeditionDays, Member aBooker)
  {
    numberOfExpeditionDays = aNumberOfExpeditionDays;
    hotelRentals = new ArrayList<HotelRental>();
    rentals = new ArrayList<Rental>();
    boolean didAddBooker = setBooker(aBooker);
    if (!didAddBooker)
    {
      throw new RuntimeException("Unable to create expedition due to booker. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    listOfDays = new ArrayList<ExpeditionDay>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setNumberOfExpeditionDays(int aNumberOfExpeditionDays)
  {
    boolean wasSet = false;
    numberOfExpeditionDays = aNumberOfExpeditionDays;
    wasSet = true;
    return wasSet;
  }

  public int getNumberOfExpeditionDays()
  {
    return numberOfExpeditionDays;
  }
  /* Code from template association_GetMany */
  public HotelRental getHotelRental(int index)
  {
    HotelRental aHotelRental = hotelRentals.get(index);
    return aHotelRental;
  }

  public List<HotelRental> getHotelRentals()
  {
    List<HotelRental> newHotelRentals = Collections.unmodifiableList(hotelRentals);
    return newHotelRentals;
  }

  public int numberOfHotelRentals()
  {
    int number = hotelRentals.size();
    return number;
  }

  public boolean hasHotelRentals()
  {
    boolean has = hotelRentals.size() > 0;
    return has;
  }

  public int indexOfHotelRental(HotelRental aHotelRental)
  {
    int index = hotelRentals.indexOf(aHotelRental);
    return index;
  }
  /* Code from template association_GetMany */
  public Rental getRental(int index)
  {
    Rental aRental = rentals.get(index);
    return aRental;
  }

  public List<Rental> getRentals()
  {
    List<Rental> newRentals = Collections.unmodifiableList(rentals);
    return newRentals;
  }

  public int numberOfRentals()
  {
    int number = rentals.size();
    return number;
  }

  public boolean hasRentals()
  {
    boolean has = rentals.size() > 0;
    return has;
  }

  public int indexOfRental(Rental aRental)
  {
    int index = rentals.indexOf(aRental);
    return index;
  }
  /* Code from template association_GetOne */
  public Member getBooker()
  {
    return booker;
  }
  /* Code from template association_GetMany */
  public ExpeditionDay getListOfDay(int index)
  {
    ExpeditionDay aListOfDay = listOfDays.get(index);
    return aListOfDay;
  }

  public List<ExpeditionDay> getListOfDays()
  {
    List<ExpeditionDay> newListOfDays = Collections.unmodifiableList(listOfDays);
    return newListOfDays;
  }

  public int numberOfListOfDays()
  {
    int number = listOfDays.size();
    return number;
  }

  public boolean hasListOfDays()
  {
    boolean has = listOfDays.size() > 0;
    return has;
  }

  public int indexOfListOfDay(ExpeditionDay aListOfDay)
  {
    int index = listOfDays.indexOf(aListOfDay);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfHotelRentals()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addHotelRental(HotelRental aHotelRental)
  {
    boolean wasAdded = false;
    if (hotelRentals.contains(aHotelRental)) { return false; }
    hotelRentals.add(aHotelRental);
    if (aHotelRental.indexOfExpedition(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aHotelRental.addExpedition(this);
      if (!wasAdded)
      {
        hotelRentals.remove(aHotelRental);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeHotelRental(HotelRental aHotelRental)
  {
    boolean wasRemoved = false;
    if (!hotelRentals.contains(aHotelRental))
    {
      return wasRemoved;
    }

    int oldIndex = hotelRentals.indexOf(aHotelRental);
    hotelRentals.remove(oldIndex);
    if (aHotelRental.indexOfExpedition(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aHotelRental.removeExpedition(this);
      if (!wasRemoved)
      {
        hotelRentals.add(oldIndex,aHotelRental);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addHotelRentalAt(HotelRental aHotelRental, int index)
  {  
    boolean wasAdded = false;
    if(addHotelRental(aHotelRental))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfHotelRentals()) { index = numberOfHotelRentals() - 1; }
      hotelRentals.remove(aHotelRental);
      hotelRentals.add(index, aHotelRental);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveHotelRentalAt(HotelRental aHotelRental, int index)
  {
    boolean wasAdded = false;
    if(hotelRentals.contains(aHotelRental))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfHotelRentals()) { index = numberOfHotelRentals() - 1; }
      hotelRentals.remove(aHotelRental);
      hotelRentals.add(index, aHotelRental);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addHotelRentalAt(aHotelRental, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfRentals()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Rental addRental(Date aStartDay, Date aReturnDay, Equipment aEquipment, Bundle aRentedBundle)
  {
    return new Rental(aStartDay, aReturnDay, aEquipment, aRentedBundle, this);
  }

  public boolean addRental(Rental aRental)
  {
    boolean wasAdded = false;
    if (rentals.contains(aRental)) { return false; }
    Expedition existingExpedition = aRental.getExpedition();
    boolean isNewExpedition = existingExpedition != null && !this.equals(existingExpedition);
    if (isNewExpedition)
    {
      aRental.setExpedition(this);
    }
    else
    {
      rentals.add(aRental);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeRental(Rental aRental)
  {
    boolean wasRemoved = false;
    //Unable to remove aRental, as it must always have a expedition
    if (!this.equals(aRental.getExpedition()))
    {
      rentals.remove(aRental);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addRentalAt(Rental aRental, int index)
  {  
    boolean wasAdded = false;
    if(addRental(aRental))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfRentals()) { index = numberOfRentals() - 1; }
      rentals.remove(aRental);
      rentals.add(index, aRental);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveRentalAt(Rental aRental, int index)
  {
    boolean wasAdded = false;
    if(rentals.contains(aRental))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfRentals()) { index = numberOfRentals() - 1; }
      rentals.remove(aRental);
      rentals.add(index, aRental);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addRentalAt(aRental, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOneToMany */
  public boolean setBooker(Member aBooker)
  {
    boolean wasSet = false;
    if (aBooker == null)
    {
      return wasSet;
    }

    Member existingBooker = booker;
    booker = aBooker;
    if (existingBooker != null && !existingBooker.equals(aBooker))
    {
      existingBooker.removeExpedition(this);
    }
    booker.addExpedition(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfListOfDays()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public ExpeditionDay addListOfDay(boolean aHasGuide, SwimDay aSwimmingDay)
  {
    return new ExpeditionDay(aHasGuide, aSwimmingDay, this);
  }

  public boolean addListOfDay(ExpeditionDay aListOfDay)
  {
    boolean wasAdded = false;
    if (listOfDays.contains(aListOfDay)) { return false; }
    Expedition existingExpedition = aListOfDay.getExpedition();
    boolean isNewExpedition = existingExpedition != null && !this.equals(existingExpedition);
    if (isNewExpedition)
    {
      aListOfDay.setExpedition(this);
    }
    else
    {
      listOfDays.add(aListOfDay);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeListOfDay(ExpeditionDay aListOfDay)
  {
    boolean wasRemoved = false;
    //Unable to remove aListOfDay, as it must always have a Expedition
    if (!this.equals(aListOfDay.getExpedition()))
    {
      listOfDays.remove(aListOfDay);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addListOfDayAt(ExpeditionDay aListOfDay, int index)
  {  
    boolean wasAdded = false;
    if(addListOfDay(aListOfDay))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfListOfDays()) { index = numberOfListOfDays() - 1; }
      listOfDays.remove(aListOfDay);
      listOfDays.add(index, aListOfDay);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveListOfDayAt(ExpeditionDay aListOfDay, int index)
  {
    boolean wasAdded = false;
    if(listOfDays.contains(aListOfDay))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfListOfDays()) { index = numberOfListOfDays() - 1; }
      listOfDays.remove(aListOfDay);
      listOfDays.add(index, aListOfDay);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addListOfDayAt(aListOfDay, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    ArrayList<HotelRental> copyOfHotelRentals = new ArrayList<HotelRental>(hotelRentals);
    hotelRentals.clear();
    for(HotelRental aHotelRental : copyOfHotelRentals)
    {
      aHotelRental.removeExpedition(this);
    }
    for(int i=rentals.size(); i > 0; i--)
    {
      Rental aRental = rentals.get(i - 1);
      aRental.delete();
    }
    Member placeholderBooker = booker;
    this.booker = null;
    if(placeholderBooker != null)
    {
      placeholderBooker.removeExpedition(this);
    }
    for(int i=listOfDays.size(); i > 0; i--)
    {
      ExpeditionDay aListOfDay = listOfDays.get(i - 1);
      aListOfDay.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "numberOfExpeditionDays" + ":" + getNumberOfExpeditionDays()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "booker = "+(getBooker()!=null?Integer.toHexString(System.identityHashCode(getBooker())):"null");
  }
}