/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.sql.Date;

// line 73 "model.ump"
// line 205 "model.ump"
public class SwimDay
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //SwimDay Attributes
  private int day;
  private Date currentDay;

  //SwimDay Associations
  private Guide guideWorking;
  private ExpeditionDay expeditionDay;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public SwimDay(int aDay, Date aCurrentDay, Guide aGuideWorking, ExpeditionDay aExpeditionDay)
  {
    day = aDay;
    currentDay = aCurrentDay;
    boolean didAddGuideWorking = setGuideWorking(aGuideWorking);
    if (!didAddGuideWorking)
    {
      throw new RuntimeException("Unable to create workingDay due to guideWorking. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    if (aExpeditionDay == null || aExpeditionDay.getSwimmingDay() != null)
    {
      throw new RuntimeException("Unable to create SwimDay due to aExpeditionDay. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    expeditionDay = aExpeditionDay;
  }

  public SwimDay(int aDay, Date aCurrentDay, Guide aGuideWorking, boolean aHasGuideForExpeditionDay, Expedition aExpeditionForExpeditionDay)
  {
    day = aDay;
    currentDay = aCurrentDay;
    boolean didAddGuideWorking = setGuideWorking(aGuideWorking);
    if (!didAddGuideWorking)
    {
      throw new RuntimeException("Unable to create workingDay due to guideWorking. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    expeditionDay = new ExpeditionDay(aHasGuideForExpeditionDay, this, aExpeditionForExpeditionDay);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setDay(int aDay)
  {
    boolean wasSet = false;
    day = aDay;
    wasSet = true;
    return wasSet;
  }

  public boolean setCurrentDay(Date aCurrentDay)
  {
    boolean wasSet = false;
    currentDay = aCurrentDay;
    wasSet = true;
    return wasSet;
  }

  public int getDay()
  {
    return day;
  }

  public Date getCurrentDay()
  {
    return currentDay;
  }
  /* Code from template association_GetOne */
  public Guide getGuideWorking()
  {
    return guideWorking;
  }
  /* Code from template association_GetOne */
  public ExpeditionDay getExpeditionDay()
  {
    return expeditionDay;
  }
  /* Code from template association_SetOneToMandatoryMany */
  public boolean setGuideWorking(Guide aGuideWorking)
  {
    boolean wasSet = false;
    //Must provide guideWorking to workingDay
    if (aGuideWorking == null)
    {
      return wasSet;
    }

    if (guideWorking != null && guideWorking.numberOfWorkingDays() <= Guide.minimumNumberOfWorkingDays())
    {
      return wasSet;
    }

    Guide existingGuideWorking = guideWorking;
    guideWorking = aGuideWorking;
    if (existingGuideWorking != null && !existingGuideWorking.equals(aGuideWorking))
    {
      boolean didRemove = existingGuideWorking.removeWorkingDay(this);
      if (!didRemove)
      {
        guideWorking = existingGuideWorking;
        return wasSet;
      }
    }
    guideWorking.addWorkingDay(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Guide placeholderGuideWorking = guideWorking;
    this.guideWorking = null;
    if(placeholderGuideWorking != null)
    {
      placeholderGuideWorking.removeWorkingDay(this);
    }
    ExpeditionDay existingExpeditionDay = expeditionDay;
    expeditionDay = null;
    if (existingExpeditionDay != null)
    {
      existingExpeditionDay.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "day" + ":" + getDay()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "currentDay" + "=" + (getCurrentDay() != null ? !getCurrentDay().equals(this)  ? getCurrentDay().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "guideWorking = "+(getGuideWorking()!=null?Integer.toHexString(System.identityHashCode(getGuideWorking())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "expeditionDay = "+(getExpeditionDay()!=null?Integer.toHexString(System.identityHashCode(getExpeditionDay())):"null");
  }
}