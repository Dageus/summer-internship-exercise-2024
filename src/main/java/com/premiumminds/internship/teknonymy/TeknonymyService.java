package com.premiumminds.internship.teknonymy;

import com.premiumminds.internship.teknonymy.Person;
import java.util.HashSet;
import java.util.Set;

class TeknonymyService implements ITeknonymyService {

  private static String _firstGenMale     = "father";
  private static String _firstGenFemale   = "mother";
  private static String _secondGenMale    = "grandfather";
  private static String _secondGenFemale  = "grandmother";
  private static String _prefix           = "great-";

  /* NOTE: this smells like a recursive call function to me */
  /**
   * Method to get a Person Teknonymy Name
   * 
   * @param Person person
   * @return String which is the Teknonymy Name 
   */
  public String getTeknonymy(Person person) {
    if (person == null) {
      throw new IllegalArgumentException("Person cannot be null");
    }

    /* TODO: find a way to not only return the items at the lowest level, but also sort them by gender and age*/

    throw new RuntimeException("Not Implemented Yet");
  };
}
