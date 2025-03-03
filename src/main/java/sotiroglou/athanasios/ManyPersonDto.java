package sotiroglou.athanasios;

import java.util.List;
import java.util.Objects;

public class ManyPersonDto {
    List<Person> persons;

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ManyPersonDto that = (ManyPersonDto) o;
        return Objects.equals(persons, that.persons);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(persons);
    }

    @Override
    public String toString() {
        return "MultiPersonDto{" +
                "persons=" + persons +
                '}';
    }
}
