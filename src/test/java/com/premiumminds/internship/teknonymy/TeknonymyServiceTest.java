package com.premiumminds.internship.teknonymy;

import com.premiumminds.internship.teknonymy.TeknonymyService;
import com.premiumminds.internship.teknonymy.Person;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class TeknonymyServiceTest {

  /**
   * The corresponding implementations to test.
   *
   * If you want, you can make others :)
   *
   */
  public TeknonymyServiceTest() {
  };

  @Test
  public void PersonNoChildrenTest() {
    Person person = new Person("John", 'M', null, LocalDateTime.of(1046, 1, 1, 0, 0));
    String result = new TeknonymyService().getTeknonymy(person);
    String expected = "";
    assertEquals(expected, result);
  }

  @Test
  public void PersonOneChildTest() {
    Person person = new Person(
        "John",
        'M',
        new Person[] { new Person("Holy", 'F', null, LocalDateTime.of(1046, 1, 1, 0, 0)) },
        LocalDateTime.of(1046, 1, 1, 0, 0));
    String result = new TeknonymyService().getTeknonymy(person);
    String expected = "father of Holy";
    assertEquals(expected, result);
  }

  @Test
  public void PersonOneGreatGreatGrandchildTest() {
    Person greatGreatGrandchild = new Person("Marie", 'F', null, LocalDateTime.of(1046, 1, 1, 0, 0));
    Person greatGrandchild = new Person("Jason", 'M', new Person[] { greatGreatGrandchild },
        LocalDateTime.of(1046, 1, 1, 0, 0));
    Person grandchild = new Person("Charles", 'M', new Person[] { greatGrandchild },
        LocalDateTime.of(1046, 1, 1, 0, 0));
    Person child = new Person("Leo", 'F', new Person[] { grandchild }, LocalDateTime.of(1046, 1, 1, 0, 0));
    Person person = new Person("Holy", 'F', new Person[] { child }, LocalDateTime.of(1046, 1, 1, 0, 0));
    String result = new TeknonymyService().getTeknonymy(person);
    String expected = "great-great-grandmother of Marie";
    assertEquals(expected, result);
  }

  @Test
  public void PersonTwoGrandchildrenTest() {
    Person grandchild1 = new Person("Jason", 'M', null, LocalDateTime.of(1046, 1, 1, 0, 0));
    Person grandchild2 = new Person("May", 'F', null, LocalDateTime.of(1046, 1, 1, 0, 0));
    Person child = new Person("Leo", 'F', new Person[] { grandchild1, grandchild2 },
        LocalDateTime.of(1046, 1, 1, 0, 0));
    Person person = new Person("John", 'M', new Person[] { child }, LocalDateTime.of(1046, 1, 1, 0, 0));
    String result = new TeknonymyService().getTeknonymy(person);
    String expected = "grandfather of Jason";
    assertEquals(expected, result);
  }

  @Test
  public void PersonWithMultipleGrandchildrenTest() {
    Person grandchild1 = new Person("Jason", 'M', null, LocalDateTime.of(1045, 12, 31, 0, 0));
    Person grandchild2 = new Person("Charles", 'M', null, LocalDateTime.of(1046, 1, 1, 0, 0));
    Person child = new Person("Leo", 'F', new Person[] { grandchild1, grandchild2 },
        LocalDateTime.of(1046, 1, 1, 0, 0));
    Person person = new Person("John", 'M', new Person[] { child }, LocalDateTime.of(1046, 1, 1, 0, 0));
    String result = new TeknonymyService().getTeknonymy(person);
    String expected = "grandfather of Jason";
    assertEquals(expected, result);
  }

  @Test
  public void PersonWithMultipleGrandchildrenAndGreatGrandchildrenTest() {
    Person greatGrandchild1 = new Person("Marie", 'F', null, LocalDateTime.of(1046, 1, 1, 0, 0));
    Person greatGrandchild2 = new Person("May", 'F', null, LocalDateTime.of(1046, 1, 1, 11, 11));
    Person grandchild1 = new Person("Jason", 'M', new Person[] { greatGrandchild1 },
        LocalDateTime.of(1046, 1, 1, 0, 0));
    Person grandchild2 = new Person("Charles", 'M', new Person[] { greatGrandchild2 },
        LocalDateTime.of(1046, 1, 1, 0, 0));
    Person child = new Person("Leo", 'F', new Person[] { grandchild1, grandchild2 },
        LocalDateTime.of(1046, 1, 1, 0, 0));
    Person person = new Person("John", 'M', new Person[] { child }, LocalDateTime.of(1046, 1, 1, 0, 0));
    String result = new TeknonymyService().getTeknonymy(person);
    String expected = "great-grandfather of Marie";
    assertEquals(expected, result);
  }
}
