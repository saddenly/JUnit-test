import java.util.Objects;

public class TolkienCharacter {
    public int age;
    private String name;
    private final Race race;

    @SuppressWarnings("unused")
    private final long notAccessibleField = System.currentTimeMillis();

    public TolkienCharacter(String name, int age, Race race) {
        this.age = age;
        this.name = name;
        this.race = race;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Race getRace() {
        return race;
    }

    public void setAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Age is not allowed to be smaller than zero");
        }
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TolkienCharacter that = (TolkienCharacter) o;

        if (age != that.age) return false;
        if (!Objects.equals(name, that.name)) return false;
        return race == that.race;
    }

    @Override
    public int hashCode() {
        int result = age;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (race != null ? race.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return name + " " + age + " years old " + race.getName();
    }
}