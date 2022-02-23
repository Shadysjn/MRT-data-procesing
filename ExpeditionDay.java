/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.util.*;
import java.sql.Date;

// line 107 "model.ump"
// line 216 "model.ump"
public class ExpeditionDay
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ExpeditionDay Attributes
  private boolean hasGuide;

  //ExpeditionDay Associations
  private Guide assignedGuide;
  private SwimDay swimmingDay;
  private Expedition Expedition;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ExpeditionDay(boolean aHasGuide, SwimDay aSwimmingDay, Expedition aExpedition)
  {
    hasGuide = aHasGuide;
    if (aSwimmingDay == null || aSwimmingDay.getExpeditionDay() != null)
    {
      throw new RuntimeException("Unable to create ExpeditionDay due to aSwimmingDay. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    swimmingDay = aSwimmingDay;
    boolean didAddExpedition = setExpedition(aExpedition);
    if (!didAddExpedition)
    {
      throw new RuntimeException("Unable to create listOfDay due to Expedition. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  public ExpeditionDay(boolean aHasGuide, int aDayForSwimmingDay, Date aCurrentDayForSwimmingDay, Guide aGuideWorkingForSwimmingDay, Expedition aExpedition)
  {
    hasGuide = aHasGuide;
    swimmingDay = new SwimDay(aDayForSwimmingDay, aCurrentDayForSwimmingDay, aGuideWorkingForSwimmingDay, this);
    boolean didAddExpedition = setExpedition(aExpedition);
    if (!didAddExpedition)
    {
      throw new RuntimeException("Unable to create listOfDay due to Expedition. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setHasGuide(boolean aHasGuide)
  {
    boolean wasSet = false;
    hasGuide = aHasGuide;
    wasSet = true;
    return wasSet;
  }

  public boolean getHasGuide()
  {
    return hasGuide;
  }
  /* Code from template attribute_IsBoolean */
  public boolean isHasGuide()
  {
    return hasGuide;
  }
  /* Code from template association_GetOne */
  public Guide getAssignedGuide()
  {
    return assignedGuide;
  }

  public boolean hasAssignedGuide()
  {
    boolean has = assignedGuide != null;
    return has;
  }
  /* Code from template association_GetOne */
  public SwimDay getSwimmingDay()
  {
    return swimmingDay;
  }
  /* Code from template association_GetOne */
  public Expedition getExpedition()
  {
    return Expedition;
  }
  /* Code from template association_SetOptionalOneToOne */
  public boolean setAssignedGuide(Guide aNewAssignedGuide)
  {
    boolean wasSet = false;
    if (assignedGuide != null && !assignedGuide.equals(aNewAssignedGuide) && equals(assignedGuide.getDay()))
    {
      //Unable to setAssignedGuide, as existing assignedGuide would become an orphan
      return wasSet;
    }

    assignedGuide = aNewAssignedGuide;
    ExpeditionDay anOldDay = aNewAssignedGuide != null ? aNewAssignedGuide.getDay() : null;

    if (!this.equals(anOldDay))
    {
      if (anOldDay != null)
      {
        anOldDay.assignedGuide = null;
      }
      if (assignedGuide != null)
      {
        assignedGuide.setDay(this);
      }
    }
    wasSet = true;
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

    Expedition existingExpedition = Expedition;
    Expedition = aExpedition;
    if (existingExpedition != null && !existingExpedition.equals(aExpedition))
    {
      existingExpedition.removeListOfDay(this);
    }
    Expedition.addListOfDay(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Guide existingAssignedGuide = assignedGuide;
    assignedGuide = null;
    if (existingAssignedGuide != null)
    {
      existingAssignedGuide.delete();
    }
    SwimDay existingSwimmingDay = swimmingDay;
    swimmingDay = null;
    if (existingSwimmingDay != null)
    {
      existingSwimmingDay.delete();
    }
    Expedition placeholderExpedition = Expedition;
    this.Expedition = null;
    if(placeholderExpedition != null)
    {
      placeholderExpedition.removeListOfDay(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "hasGuide" + ":" + getHasGuide()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "assignedGuide = "+(getAssignedGuide()!=null?Integer.toHexString(System.identityHashCode(getAssignedGuide())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "swimmingDay = "+(getSwimmingDay()!=null?Integer.toHexString(System.identityHashCode(getSwimmingDay())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "Expedition = "+(getExpedition()!=null?Integer.toHexString(System.identityHashCode(getExpedition())):"null");
  }
}