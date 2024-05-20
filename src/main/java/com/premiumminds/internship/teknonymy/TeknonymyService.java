package com.premiumminds.internship.teknonymy;

import com.premiumminds.internship.teknonymy.Person;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;

class TeknonymyService implements ITeknonymyService {

  private static String[] _maleNames = { "father", "grandfather" };
  private static String[] _femaleNames = { "mother", "grandmother" };

  private static String _prefix = "great-";
  private static Integer _maxGen = -1;
  private final Integer _multiplier = 2;

  private ArrayList<Person> descendants;

  /*
   * NOTE: opting for BFS because it's easier to segment processed nodes into the
   * layer they're on
   */
  private Queue<Person> _queue = new LinkedList<Person>(); // this will store the nodes to be processed

  /**
   * Method to get a Person Teknonymy Name
   * 
   * @param Person person
   * @return String which is the Teknonymy Name
   */
  public String getTeknonymy(Person person) {
    // FIXME: will this even happen?
    if (person == null) {
      throw new IllegalArgumentException("Person cannot be null");
    }

    // if person doesn't have children, the teknonymy is empty
    if (person.children() == null) {
      return "";
    }

    // add the root to the queue
    _queue.add(person);

    while (!_queue.isEmpty()) {
      Person currentPerson = _queue.poll();
      Person[] children = currentPerson.children();

      descendants = new ArrayList<Person>();

      if (children == null) {
        continue;
      }

      for (Person child : children) {
        _queue.add(child);
        descendants.add(child);
      }
      _maxGen++;
    }

    // filter the male descendants
    ArrayList<Person> maleDescendants = descendants.stream().filter(person -> person.sex() == 'M')
        .toArray(Person[]::new);
    // create a list of femalte descendants
    ArrayList<Person> femaleDescendants = descendants.stream().filter(person -> person.sex() == 'F')
        .toArray(Person[]::new);

    // get the oldest of the male descendants
    Optional<Person> oldestMaleDescendant = maleDescendants.sort((a, b) -> a.dateOfBirth().compareTo(b.dateOfBirth()))
        .get(0);

    // form the teknonymy
    String teknonymy = "";

    int index = _maxGen;
    for (int i = _multiplier; i < _maxGen; i++) {
      teknonymy += _prefix;
      index--;
    }

    // TODO: what if it's a female descendant?
    teknonymy += _maleNames[index - 1] + " of " + oldestMaleDescendant.name();

    return teknonymy;
  };
}
