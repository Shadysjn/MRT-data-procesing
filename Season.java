/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.sql.Date;
import java.util.*;

// line 79 "model.ump"
// line 210 "model.ump"
public class Season
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Season Attributes
  private Date seasonBegin;
  private Date seasonEnd;
  private int length;

  //Season Associations
  private List<SwimDay> openSwimDays;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Season(Date aSeasonBegin, Date aSeasonEnd, int aLength)
  {
    seasonBegin = aSeasonBegin;
    seasonEnd = aSeasonEnd;
    length = aLength;
    openSwimDays = new ArrayList<SwimDay>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setSeasonBegin(Date aSeasonBegin)
  {
    boolean wasSet = false;
    seasonBegin = aSeasonBegin;
    wasSet = true;
    return wasSet;
  }

  public boolean setSeasonEnd(Date aSeasonEnd)
  {
    boolean wasSet = false;
    seasonEnd = aSeasonEnd;
    wasSet = true;
    return wasSet;
  }

  public boolean setLength(int aLength)
  {
    boolean wasSet = false;
    length = aLength;
    wasSet = true;
    return wasSet;
  }

  public Date getSeasonBegin()
  {
    return seasonBegin;
  }

  public Date getSeasonEnd()
  {
    return seasonEnd;
  }

  public int getLength()
  {
    return length;
  }
  /* Code from template association_GetMany */
  public SwimDay getOpenSwimDay(int index)
  {
    SwimDay aOpenSwimDay = openSwimDays.get(index);
    return aOpenSwimDay;
  }

  public List<SwimDay> getOpenSwimDays()
  {
    List<SwimDay> newOpenSwimDays = Collections.unmodifiableList(openSwimDays);
    return newOpenSwimDays;
  }

  public int numberOfOpenSwimDays()
  {
    int number = openSwimDays.size();
    return number;
  }

  public boolean hasOpenSwimDays()
  {
    boolean has = openSwimDays.size() > 0;
    return has;
  }

  public int indexOfOpenSwimDay(SwimDay aOpenSwimDay)
  {
    int index = openSwimDays.indexOf(aOpenSwimDay);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfOpenSwimDays()
  {
    return 0;
  }
  /* Code from template association_AddUnidirectionalMany */
  public boolean addOpenSwimDay(SwimDay aOpenSwimDay)
  {
    boolean wasAdded = false;
    if (openSwimDays.contains(aOpenSwimDay)) { return false; }
    openSwimDays.add(aOpenSwimDay);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeOpenSwimDay(SwimDay aOpenSwimDay)
  {
    boolean wasRemoved = false;
    if (openSwimDays.contains(aOpenSwimDay))
    {
      openSwimDays.remove(aOpenSwimDay);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addOpenSwimDayAt(SwimDay aOpenSwimDay, int index)
  {  
    boolean wasAdded = false;
    if(addOpenSwimDay(aOpenSwimDay))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOpenSwimDays()) { index = numberOfOpenSwimDays() - 1; }
      openSwimDays.remove(aOpenSwimDay);
      openSwimDays.add(index, aOpenSwimDay);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveOpenSwimDayAt(SwimDay aOpenSwimDay, int index)
  {
    boolean wasAdded = false;
    if(openSwimDays.contains(aOpenSwimDay))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOpenSwimDays()) { index = numberOfOpenSwimDays() - 1; }
      openSwimDays.remove(aOpenSwimDay);
      openSwimDays.add(index, aOpenSwimDay);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addOpenSwimDayAt(aOpenSwimDay, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    openSwimDays.clear();
  }


  public String toString()
  {
    return super.toString() + "["+
            "length" + ":" + getLength()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "seasonBegin" + "=" + (getSeasonBegin() != null ? !getSeasonBegin().equals(this)  ? getSeasonBegin().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "seasonEnd" + "=" + (getSeasonEnd() != null ? !getSeasonEnd().equals(this)  ? getSeasonEnd().toString().replaceAll("  ","    ") : "this" : "null");
  }
}