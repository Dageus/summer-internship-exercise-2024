package com.premiumminds.internship.teknonymy;

import com.premiumminds.internship.teknonymy.Person;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Comparator;
import java.util.Collections;

class TeknonymyService implements ITeknonymyService {

  private static String[] _maleNames = { "father", "grandfather" };
  private static String[] _femaleNames = { "mother", "grandmother" };

  private final Character MALE = 'M';
  private final Character FEMALE = 'F';

  private final String _prefix = "great-";
  private final Integer _multiplier = 2;

  private ArrayList<Person> descendants;

  /*
   * NOTE: opting for BFS because it's easier to tell which processed nodes are in
   * which generation
   */
  private Queue<Person> _queue = new LinkedList<Person>(); // this will store the nodes to be processed

  private Optional<Person> getOldestDescendant(ArrayList<Person> descendants, Character sex) {
    return descendants.stream()
        .filter(descendant -> descendant.sex() == sex)
        .min(Comparator.comparing(Person::dateOfBirth));

  }

  /**
   * Method to get a Person Teknonymy Name
   * 
   * @param Person person
   * @return String which is the Teknonymy Name
   */
  public String getTeknonymy(Person person) {
    // if person doesn't have children, the teknonymy is empty
    if (person.children() == null) {
      return "";
    }

    // add the root to the queue
    _queue.add(person);

    // initialize the generation counter
    int _maxGen = 0;
    ArrayList<Person> currentLevel = new ArrayList<>();

    while (!_queue.isEmpty()) {
      int levelSize = _queue.size();
      currentLevel.clear();

      for (int i = 0; i < levelSize; i++) {

        Person currentPerson = _queue.poll();
        Person[] children = currentPerson.children();

        currentLevel.add(currentPerson);

        if (children != null) {
          Collections.addAll(_queue, children);
        }
      }
      descendants = new ArrayList<>(currentLevel);

      _maxGen++;
    }

    _maxGen--;

    // filter the male descendants and get the oldest one
    Optional<Person> oldestDescendant = null;
    Person descendant = null;

    oldestDescendant = getOldestDescendant(descendants, MALE);

    if (oldestDescendant.isPresent()) {
      // there is a male descendant
      descendant = oldestDescendant.get();
    } else {
      // there are no male descendants
      // look for female descendants
      oldestDescendant = getOldestDescendant(descendants, FEMALE);
      descendant = oldestDescendant.get();
    }

    // form the teknonymy
    String teknonymy = "";

    int index = _maxGen;
    for (int i = _multiplier; i < _maxGen; i++) {
      teknonymy += _prefix;
      index--;
    }

    teknonymy += (person.sex() == FEMALE ? _femaleNames[index - 1] : _maleNames[index - 1]) + " of "
        + descendant.name();

    return teknonymy;
  };
}
