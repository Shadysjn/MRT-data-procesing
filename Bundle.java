/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.util.*;
import java.sql.Date;

// line 65 "model.ump"
// line 198 "model.ump"
public class Bundle
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Bundle Attributes
  private int totalNonDiscountPrice;
  private int totalDiscountPrice;

  //Bundle Associations
  private Rental associatedRental;
  private List<Equipment> associatedEquipment;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Bundle(int aTotalNonDiscountPrice, int aTotalDiscountPrice, Rental aAssociatedRental)
  {
    totalNonDiscountPrice = aTotalNonDiscountPrice;
    totalDiscountPrice = aTotalDiscountPrice;
    if (aAssociatedRental == null || aAssociatedRental.getRentedBundle() != null)
    {
      throw new RuntimeException("Unable to create Bundle due to aAssociatedRental. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    associatedRental = aAssociatedRental;
    associatedEquipment = new ArrayList<Equipment>();
  }

  public Bundle(int aTotalNonDiscountPrice, int aTotalDiscountPrice, Date aStartDayForAssociatedRental, Date aReturnDayForAssociatedRental, Equipment aEquipmentForAssociatedRental, Expedition aExpeditionForAssociatedRental)
  {
    totalNonDiscountPrice = aTotalNonDiscountPrice;
    totalDiscountPrice = aTotalDiscountPrice;
    associatedRental = new Rental(aStartDayForAssociatedRental, aReturnDayForAssociatedRental, aEquipmentForAssociatedRental, this, aExpeditionForAssociatedRental);
    associatedEquipment = new ArrayList<Equipment>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setTotalNonDiscountPrice(int aTotalNonDiscountPrice)
  {
    boolean wasSet = false;
    totalNonDiscountPrice = aTotalNonDiscountPrice;
    wasSet = true;
    return wasSet;
  }

  public boolean setTotalDiscountPrice(int aTotalDiscountPrice)
  {
    boolean wasSet = false;
    totalDiscountPrice = aTotalDiscountPrice;
    wasSet = true;
    return wasSet;
  }

  public int getTotalNonDiscountPrice()
  {
    return totalNonDiscountPrice;
  }

  public int getTotalDiscountPrice()
  {
    return totalDiscountPrice;
  }
  /* Code from template association_GetOne */
  public Rental getAssociatedRental()
  {
    return associatedRental;
  }
  /* Code from template association_GetMany */
  public Equipment getAssociatedEquipment(int index)
  {
    Equipment aAssociatedEquipment = associatedEquipment.get(index);
    return aAssociatedEquipment;
  }

  public List<Equipment> getAssociatedEquipment()
  {
    List<Equipment> newAssociatedEquipment = Collections.unmodifiableList(associatedEquipment);
    return newAssociatedEquipment;
  }

  public int numberOfAssociatedEquipment()
  {
    int number = associatedEquipment.size();
    return number;
  }

  public boolean hasAssociatedEquipment()
  {
    boolean has = associatedEquipment.size() > 0;
    return has;
  }

  public int indexOfAssociatedEquipment(Equipment aAssociatedEquipment)
  {
    int index = associatedEquipment.indexOf(aAssociatedEquipment);
    return index;
  }
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfAssociatedEquipmentValid()
  {
    boolean isValid = numberOfAssociatedEquipment() >= minimumNumberOfAssociatedEquipment();
    return isValid;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfAssociatedEquipment()
  {
    return 2;
  }
  /* Code from template association_AddMandatoryManyToOne */
  public Equipment addAssociatedEquipment(int aPrice, int aWeight)
  {
    Equipment aNewAssociatedEquipment = new Equipment(aPrice, aWeight, this);
    return aNewAssociatedEquipment;
  }

  public boolean addAssociatedEquipment(Equipment aAssociatedEquipment)
  {
    boolean wasAdded = false;
    if (associatedEquipment.contains(aAssociatedEquipment)) { return false; }
    Bundle existingEquipmentContained = aAssociatedEquipment.getEquipmentContained();
    boolean isNewEquipmentContained = existingEquipmentContained != null && !this.equals(existingEquipmentContained);

    if (isNewEquipmentContained && existingEquipmentContained.numberOfAssociatedEquipment() <= minimumNumberOfAssociatedEquipment())
    {
      return wasAdded;
    }
    if (isNewEquipmentContained)
    {
      aAssociatedEquipment.setEquipmentContained(this);
    }
    else
    {
      associatedEquipment.add(aAssociatedEquipment);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeAssociatedEquipment(Equipment aAssociatedEquipment)
  {
    boolean wasRemoved = false;
    //Unable to remove aAssociatedEquipment, as it must always have a equipmentContained
    if (this.equals(aAssociatedEquipment.getEquipmentContained()))
    {
      return wasRemoved;
    }

    //equipmentContained already at minimum (2)
    if (numberOfAssociatedEquipment() <= minimumNumberOfAssociatedEquipment())
    {
      return wasRemoved;
    }

    associatedEquipment.remove(aAssociatedEquipment);
    wasRemoved = true;
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addAssociatedEquipmentAt(Equipment aAssociatedEquipment, int index)
  {  
    boolean wasAdded = false;
    if(addAssociatedEquipment(aAssociatedEquipment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAssociatedEquipment()) { index = numberOfAssociatedEquipment() - 1; }
      associatedEquipment.remove(aAssociatedEquipment);
      associatedEquipment.add(index, aAssociatedEquipment);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveAssociatedEquipmentAt(Equipment aAssociatedEquipment, int index)
  {
    boolean wasAdded = false;
    if(associatedEquipment.contains(aAssociatedEquipment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAssociatedEquipment()) { index = numberOfAssociatedEquipment() - 1; }
      associatedEquipment.remove(aAssociatedEquipment);
      associatedEquipment.add(index, aAssociatedEquipment);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addAssociatedEquipmentAt(aAssociatedEquipment, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    Rental existingAssociatedRental = associatedRental;
    associatedRental = null;
    if (existingAssociatedRental != null)
    {
      existingAssociatedRental.delete();
    }
    for(int i=associatedEquipment.size(); i > 0; i--)
    {
      Equipment aAssociatedEquipment = associatedEquipment.get(i - 1);
      aAssociatedEquipment.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "totalNonDiscountPrice" + ":" + getTotalNonDiscountPrice()+ "," +
            "totalDiscountPrice" + ":" + getTotalDiscountPrice()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "associatedRental = "+(getAssociatedRental()!=null?Integer.toHexString(System.identityHashCode(getAssociatedRental())):"null");
  }
}