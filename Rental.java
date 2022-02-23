/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.sql.Date;

// line 52 "model.ump"
// line 187 "model.ump"
public class Rental
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Rental Attributes
  private Date startDay;
  private Date returnDay;

  //Rental Associations
  private Equipment equipment;
  private Bundle rentedBundle;
  private Expedition expedition;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Rental(Date aStartDay, Date aReturnDay, Equipment aEquipment, Bundle aRentedBundle, Expedition aExpedition)
  {
    startDay = aStartDay;
    returnDay = aReturnDay;
    if (!setEquipment(aEquipment))
    {
      throw new RuntimeException("Unable to create Rental due to aEquipment. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    if (aRentedBundle == null || aRentedBundle.getAssociatedRental() != null)
    {
      throw new RuntimeException("Unable to create Rental due to aRentedBundle. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    rentedBundle = aRentedBundle;
    boolean didAddExpedition = setExpedition(aExpedition);
    if (!didAddExpedition)
    {
      throw new RuntimeException("Unable to create rental due to expedition. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  public Rental(Date aStartDay, Date aReturnDay, Equipment aEquipment, int aTotalNonDiscountPriceForRentedBundle, int aTotalDiscountPriceForRentedBundle, Expedition aExpedition)
  {
    startDay = aStartDay;
    returnDay = aReturnDay;
    equipment = new Equipment(null);
    rentedBundle = new Bundle(aTotalNonDiscountPriceForRentedBundle, aTotalDiscountPriceForRentedBundle, this);
    boolean didAddExpedition = setExpedition(aExpedition);
    if (!didAddExpedition)
    {
      throw new RuntimeException("Unable to create rental due to expedition. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setStartDay(Date aStartDay)
  {
    boolean wasSet = false;
    startDay = aStartDay;
    wasSet = true;
    return wasSet;
  }

  public boolean setReturnDay(Date aReturnDay)
  {
    boolean wasSet = false;
    returnDay = aReturnDay;
    wasSet = true;
    return wasSet;
  }

  public Date getStartDay()
  {
    return startDay;
  }

  public Date getReturnDay()
  {
    return returnDay;
  }
  /* Code from template association_GetOne */
  public Equipment getEquipment()
  {
    return equipment;
  }
  /* Code from template association_GetOne */
  public Bundle getRentedBundle()
  {
    return rentedBundle;
  }
  /* Code from template association_GetOne */
  public Expedition getExpedition()
  {
    return expedition;
  }
  /* Code from template association_SetUnidirectionalOne */
  public boolean setEquipment(Equipment aNewEquipment)
  {
    boolean wasSet = false;
    if (aNewEquipment != null)
    {
      equipment = aNewEquipment;
      wasSet = true;
    }
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setExpedition(Expedition aExpedition)
  {
    boolean wasSet = false;
    if (aExpedition == null)
    {
      return wasSet;
    }

    Expedition existingExpedition = expedition;
    expedition = aExpedition;
    if (existingExpedition != null && !existingExpedition.equals(aExpedition))
    {
      existingExpedition.removeRental(this);
    }
    expedition.addRental(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    equipment = null;
    Bundle existingRentedBundle = rentedBundle;
    rentedBundle = null;
    if (existingRentedBundle != null)
    {
      existingRentedBundle.delete();
    }
    Expedition placeholderExpedition = expedition;
    this.expedition = null;
    if(placeholderExpedition != null)
    {
      placeholderExpedition.removeRental(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "startDay" + "=" + (getStartDay() != null ? !getStartDay().equals(this)  ? getStartDay().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "returnDay" + "=" + (getReturnDay() != null ? !getReturnDay().equals(this)  ? getReturnDay().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "equipment = "+(getEquipment()!=null?Integer.toHexString(System.identityHashCode(getEquipment())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "rentedBundle = "+(getRentedBundle()!=null?Integer.toHexString(System.identityHashCode(getRentedBundle())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "expedition = "+(getExpedition()!=null?Integer.toHexString(System.identityHashCode(getExpedition())):"null");
  }
}