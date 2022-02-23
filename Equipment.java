/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/



// line 58 "model.ump"
// line 192 "model.ump"
public class Equipment
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Equipment Attributes
  private int price;
  private int weight;

  //Equipment Associations
  private Bundle equipmentContained;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Equipment(int aPrice, int aWeight, Bundle aEquipmentContained)
  {
    price = aPrice;
    weight = aWeight;
    boolean didAddEquipmentContained = setEquipmentContained(aEquipmentContained);
    if (!didAddEquipmentContained)
    {
      throw new RuntimeException("Unable to create associatedEquipment due to equipmentContained. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
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

  public boolean setWeight(int aWeight)
  {
    boolean wasSet = false;
    weight = aWeight;
    wasSet = true;
    return wasSet;
  }

  public int getPrice()
  {
    return price;
  }

  public int getWeight()
  {
    return weight;
  }
  /* Code from template association_GetOne */
  public Bundle getEquipmentContained()
  {
    return equipmentContained;
  }
  /* Code from template association_SetOneToMandatoryMany */
  public boolean setEquipmentContained(Bundle aEquipmentContained)
  {
    boolean wasSet = false;
    //Must provide equipmentContained to associatedEquipment
    if (aEquipmentContained == null)
    {
      return wasSet;
    }

    if (equipmentContained != null && equipmentContained.numberOfAssociatedEquipment() <= Bundle.minimumNumberOfAssociatedEquipment())
    {
      return wasSet;
    }

    Bundle existingEquipmentContained = equipmentContained;
    equipmentContained = aEquipmentContained;
    if (existingEquipmentContained != null && !existingEquipmentContained.equals(aEquipmentContained))
    {
      boolean didRemove = existingEquipmentContained.removeAssociatedEquipment(this);
      if (!didRemove)
      {
        equipmentContained = existingEquipmentContained;
        return wasSet;
      }
    }
    equipmentContained.addAssociatedEquipment(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Bundle placeholderEquipmentContained = equipmentContained;
    this.equipmentContained = null;
    if(placeholderEquipmentContained != null)
    {
      placeholderEquipmentContained.removeAssociatedEquipment(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "price" + ":" + getPrice()+ "," +
            "weight" + ":" + getWeight()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "equipmentContained = "+(getEquipmentContained()!=null?Integer.toHexString(System.identityHashCode(getEquipmentContained())):"null");
  }
}