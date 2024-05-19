package com.premiumminds.internship.teknonymy;

import com.premiumminds.internship.teknonymy.Person;

class TeknonymyService implements ITeknonymyService {

  private static String _firstGenMale     = "father";
  private static String _firstGenFemale   = "mother";
  private static String _secondGenMale    = "grandfather";
  private static String _secondGenFemale  = "grandmother";
  private static String _prefix           = "great-";

  /* opting for a BFS since we don't know the full depth */
  private Queue<Person> _queue = new LinkedList<Person>();

  /**
   * Method to get a Person Teknonymy Name
   * 
   * @param Person person
   * @return String which is the Teknonymy Name 
   */
  public String getTeknonymy(Person person) {
    throw new RuntimeException("Not Implemented Yet");
  };
}
