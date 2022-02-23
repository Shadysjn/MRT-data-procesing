/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.util.*;
import java.sql.Date;

// line 43 "model.ump"
// line 181 "model.ump"
public class Hotel
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Hotel Attributes
  private String name;
  private int address;
  private String type;
  private R rating;

  //Hotel Associations
  private List<HotelRental> hotelRental;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Hotel(String aName, int aAddress, String aType, R aRating)
  {
    name = aName;
    address = aAddress;
    type = aType;
    rating = aRating;
    hotelRental = new ArrayList<HotelRental>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setAddress(int aAddress)
  {
    boolean wasSet = false;
    address = aAddress;
    wasSet = true;
    return wasSet;
  }

  public boolean setType(String aType)
  {
    boolean wasSet = false;
    type = aType;
    wasSet = true;
    return wasSet;
  }

  public boolean setRating(R aRating)
  {
    boolean wasSet = false;
    rating = aRating;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public int getAddress()
  {
    return address;
  }

  public String getType()
  {
    return type;
  }

  public R getRating()
  {
    return rating;
  }
  /* Code from template association_GetMany */
  public HotelRental getHotelRental(int index)
  {
    HotelRental aHotelRental = hotelRental.get(index);
    return aHotelRental;
  }

  public List<HotelRental> getHotelRental()
  {
    List<HotelRental> newHotelRental = Collections.unmodifiableList(hotelRental);
    return newHotelRental;
  }

  public int numberOfHotelRental()
  {
    int number = hotelRental.size();
    return number;
  }

  public boolean hasHotelRental()
  {
    boolean has = hotelRental.size() > 0;
    return has;
  }

  public int indexOfHotelRental(HotelRental aHotelRental)
  {
    int index = hotelRental.indexOf(aHotelRental);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfHotelRental()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public HotelRental addHotelRental(Date aCheckIn, Date aCheckOut, int aLengthOfStay)
  {
    return new HotelRental(aCheckIn, aCheckOut, aLengthOfStay, this);
  }

  public boolean addHotelRental(HotelRental aHotelRental)
  {
    boolean wasAdded = false;
    if (hotelRental.contains(aHotelRental)) { return false; }
    Hotel existingRentedHotel = aHotelRental.getRentedHotel();
    boolean isNewRentedHotel = existingRentedHotel != null && !this.equals(existingRentedHotel);
    if (isNewRentedHotel)
    {
      aHotelRental.setRentedHotel(this);
    }
    else
    {
      hotelRental.add(aHotelRental);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeHotelRental(HotelRental aHotelRental)
  {
    boolean wasRemoved = false;
    //Unable to remove aHotelRental, as it must always have a rentedHotel
    if (!this.equals(aHotelRental.getRentedHotel()))
    {
      hotelRental.remove(aHotelRental);
      wasRemoved = true;
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
      if(index > numberOfHotelRental()) { index = numberOfHotelRental() - 1; }
      hotelRental.remove(aHotelRental);
      hotelRental.add(index, aHotelRental);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveHotelRentalAt(HotelRental aHotelRental, int index)
  {
    boolean wasAdded = false;
    if(hotelRental.contains(aHotelRental))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfHotelRental()) { index = numberOfHotelRental() - 1; }
      hotelRental.remove(aHotelRental);
      hotelRental.add(index, aHotelRental);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addHotelRentalAt(aHotelRental, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=hotelRental.size(); i > 0; i--)
    {
      HotelRental aHotelRental = hotelRental.get(i - 1);
      aHotelRental.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "address" + ":" + getAddress()+ "," +
            "type" + ":" + getType()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "rating" + "=" + (getRating() != null ? !getRating().equals(this)  ? getRating().toString().replaceAll("  ","    ") : "this" : "null");
  }
}