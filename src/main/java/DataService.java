import java.util.*;
import java.util.List;

public class DataService {
    static final String ERROR_EXAMPLE_FOR_ASSERTION = "{} assertion : {}\n";


    final TolkienCharacter frodo = new TolkienCharacter("Frodo", 33, Race.HOBBIT);

    final TolkienCharacter sam = new TolkienCharacter("Sam", 38, Race.HOBBIT);

    final TolkienCharacter merry = new TolkienCharacter("Merry", 36, Race.HOBBIT);

    final TolkienCharacter pippin = new TolkienCharacter("Pippin", 28, Race.HOBBIT);

    final TolkienCharacter gandalf = new TolkienCharacter("Gandalf", 2020, Race.MAIA);

    final TolkienCharacter gimli = new TolkienCharacter("Gimli", 139, Race.DWARF);

    final TolkienCharacter legolas = new TolkienCharacter("Legolas", 1000, Race.ELF);

    final TolkienCharacter aragorn = new TolkienCharacter("Aragorn", 87, Race.MAN);

    final TolkienCharacter boromir = new TolkienCharacter("Boromir", 37, Race.MAN);

    final TolkienCharacter sauron = new TolkienCharacter("Sauron", 50000, Race.MAIA);

    final TolkienCharacter galadriel = new TolkienCharacter("Galadriel", 3000, Race.ELF);

    final TolkienCharacter elrond = new TolkienCharacter("Elrond", 3000, Race.ELF);

    final TolkienCharacter guruk = new TolkienCharacter("Guruk", 20, Race.ORC);


    final Movie theFellowshipOfTheRing = new Movie("the fellowship of the Ring", new Date(), "178 min");

    final Movie theTwoTowers = new Movie("the two Towers", new Date(), "179 min");

    final Movie theReturnOfTheKing = new Movie("the Return of the King", new Date(), "201 min");

    public List<TolkienCharacter> getFellowship() {
        final List<TolkienCharacter> fellowshipOfTheRing = new ArrayList<>();

        fellowshipOfTheRing.add(frodo);
        fellowshipOfTheRing.add(sam);
        fellowshipOfTheRing.add(merry);
        fellowshipOfTheRing.add(pippin);
        fellowshipOfTheRing.add(gandalf);
        fellowshipOfTheRing.add(legolas);
        fellowshipOfTheRing.add(gimli);
        fellowshipOfTheRing.add(aragorn);
        fellowshipOfTheRing.add(boromir);

        return fellowshipOfTheRing;
    }

    public List<TolkienCharacter> getOrcsWithHobbitPrisoners() {
        final List<TolkienCharacter> orcsWithHobbitPrisoners = new ArrayList<>();
        orcsWithHobbitPrisoners.add(guruk);
        orcsWithHobbitPrisoners.add(merry);
        orcsWithHobbitPrisoners.add(pippin);
        return orcsWithHobbitPrisoners;
    }

    public TolkienCharacter getFellowshipCharacter(String name) {
        List<TolkienCharacter> list = getFellowship();
        return list.stream().filter(x -> x.getName().equals(name)).findFirst().orElse(null);
    }

    public Map<Ring, TolkienCharacter> getRingBearers() {
        Map<Ring, TolkienCharacter> ringBearers = new HashMap<>();

        ringBearers.put(Ring.nenya, galadriel);
        ringBearers.put(Ring.narya, gandalf);
        ringBearers.put(Ring.vilya, elrond);
        ringBearers.put(Ring.oneRing, frodo);
        return ringBearers;
    }

    public boolean update() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }
}