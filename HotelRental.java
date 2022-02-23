/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.sql.Date;
import java.util.*;

// line 36 "model.ump"
// line 176 "model.ump"
public class HotelRental
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //HotelRental Attributes
  private Date checkIn;
  private Date checkOut;
  private int lengthOfStay;

  //HotelRental Associations
  private Hotel rentedHotel;
  private List<Expedition> expeditions;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public HotelRental(Date aCheckIn, Date aCheckOut, int aLengthOfStay, Hotel aRentedHotel)
  {
    checkIn = aCheckIn;
    checkOut = aCheckOut;
    lengthOfStay = aLengthOfStay;
    boolean didAddRentedHotel = setRentedHotel(aRentedHotel);
    if (!didAddRentedHotel)
    {
      throw new RuntimeException("Unable to create hotelRental due to rentedHotel. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    expeditions = new ArrayList<Expedition>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setCheckIn(Date aCheckIn)
  {
    boolean wasSet = false;
    checkIn = aCheckIn;
    wasSet = true;
    return wasSet;
  }

  public boolean setCheckOut(Date aCheckOut)
  {
    boolean wasSet = false;
    checkOut = aCheckOut;
    wasSet = true;
    return wasSet;
  }

  public boolean setLengthOfStay(int aLengthOfStay)
  {
    boolean wasSet = false;
    lengthOfStay = aLengthOfStay;
    wasSet = true;
    return wasSet;
  }

  public Date getCheckIn()
  {
    return checkIn;
  }

  public Date getCheckOut()
  {
    return checkOut;
  }

  public int getLengthOfStay()
  {
    return lengthOfStay;
  }
  /* Code from template association_GetOne */
  public Hotel getRentedHotel()
  {
    return rentedHotel;
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
  /* Code from template association_SetOneToMany */
  public boolean setRentedHotel(Hotel aRentedHotel)
  {
    boolean wasSet = false;
    if (aRentedHotel == null)
    {
      return wasSet;
    }

    Hotel existingRentedHotel = rentedHotel;
    rentedHotel = aRentedHotel;
    if (existingRentedHotel != null && !existingRentedHotel.equals(aRentedHotel))
    {
      existingRentedHotel.removeHotelRental(this);
    }
    rentedHotel.addHotelRental(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfExpeditions()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addExpedition(Expedition aExpedition)
  {
    boolean wasAdded = false;
    if (expeditions.contains(aExpedition)) { return false; }
    expeditions.add(aExpedition);
    if (aExpedition.indexOfHotelRental(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aExpedition.addHotelRental(this);
      if (!wasAdded)
      {
        expeditions.remove(aExpedition);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeExpedition(Expedition aExpedition)
  {
    boolean wasRemoved = false;
    if (!expeditions.contains(aExpedition))
    {
      return wasRemoved;
    }

    int oldIndex = expeditions.indexOf(aExpedition);
    expeditions.remove(oldIndex);
    if (aExpedition.indexOfHotelRental(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aExpedition.removeHotelRental(this);
      if (!wasRemoved)
      {
        expeditions.add(oldIndex,aExpedition);
      }
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
    Hotel placeholderRentedHotel = rentedHotel;
    this.rentedHotel = null;
    if(placeholderRentedHotel != null)
    {
      placeholderRentedHotel.removeHotelRental(this);
    }
    ArrayList<Expedition> copyOfExpeditions = new ArrayList<Expedition>(expeditions);
    expeditions.clear();
    for(Expedition aExpedition : copyOfExpeditions)
    {
      aExpedition.removeHotelRental(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "lengthOfStay" + ":" + getLengthOfStay()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "checkIn" + "=" + (getCheckIn() != null ? !getCheckIn().equals(this)  ? getCheckIn().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "checkOut" + "=" + (getCheckOut() != null ? !getCheckOut().equals(this)  ? getCheckOut().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "rentedHotel = "+(getRentedHotel()!=null?Integer.toHexString(System.identityHashCode(getRentedHotel())):"null");
  }
}