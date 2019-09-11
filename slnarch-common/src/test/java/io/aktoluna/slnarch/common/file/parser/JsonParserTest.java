package io.aktoluna.slnarch.common.file.parser;

import static org.hamcrest.Matchers.is;

import org.junit.Assert;
import org.junit.Test;

public class JsonParserTest {

  @Test
  public void parseFile() throws Exception {
    JsonParser peopleParser = new JsonParser();
    People expected = new People();
    expected.setSurname("ali");
    expected.setLastName("aktolun");
    People actual = peopleParser.parseFile("{'surname':'ali','lastName':'aktolun'}", People.class);
    Assert.assertThat(actual.getSurname(), is(expected.getSurname()));
  }



  private static class People {

    String surname;
    String lastName;

    public String getSurname() {
      return surname;
    }

    public void setSurname(String surname) {
      this.surname = surname;
    }

    public String getLastName() {
      return lastName;
    }

    public void setLastName(String lastName) {
      this.lastName = lastName;
    }
  }



}