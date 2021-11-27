import org.junit.jupiter.api.*;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.ParameterizedTest;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class DataServiceTest {
    DataService dataService;
    List<TolkienCharacter> fellowship;

    @BeforeEach
    void initDataService() {
        dataService = new DataService();
        fellowship = dataService.getFellowship();
    }

    @Test
    @DisplayName("Ensure that initialization of Tolkein characters works")
    void ensureThatInitializationOfTolkeinCharactersWorks() {
        TolkienCharacter frodo = new TolkienCharacter("Frodo", 33, Race.HOBBIT);

        assertAll(() -> {
            assertEquals("Frodo", frodo.getName());
            assertEquals(33, frodo.getAge());
            assertNotEquals("Frodon", frodo.getName());
        });
    }

    @Test
    @DisplayName("Ensure that equals method works for characters")
    void ensureThatEqualsMethodWorksForCharacters() {
        Object jake = new TolkienCharacter("Jake", 43, Race.HOBBIT);
        Object sameJake = jake;
        Object jakeClone = new TolkienCharacter("Jake", 12, Race.HOBBIT);

        assertAll(() -> {
            assertEquals(jake, sameJake);
            assertNotEquals(jake, jakeClone);
        });
    }

    @Test
    @DisplayName("Check inheritance")
    void checkInheritance() {
        TolkienCharacter tolkienCharacter = dataService.getFellowship().get(0);

        assertFalse(Movie.class.isAssignableFrom(tolkienCharacter.getClass()));
        assertTrue(TolkienCharacter.class.isAssignableFrom(tolkienCharacter.getClass()));
    }

    @ParameterizedTest
    @ValueSource(strings = "Larson")
    @DisplayName("Ensure fellowship character access by name returns Null for unknown character")
    void ensureFellowshipCharacterAccessByNameReturnsNullForUnknownCharacter(String name) {
        assertNull(dataService.getFellowshipCharacter(name));
    }

    @Test
    @DisplayName("Ensure fellowship character access by name works given correctly given right name")
    void ensureFellowshipCharacterAccessByNameWorksGivenCorrectlyGivenRightName() {
        assertAll(() -> {
            assertEquals(dataService.getFellowshipCharacter("Frodo"), dataService.frodo);
            assertEquals(dataService.getFellowshipCharacter("Legolas"), dataService.legolas);
            assertEquals(dataService.getFellowshipCharacter("Aragorn"), dataService.aragorn);
        });
    }

    @Test
    @DisplayName("Ensure that Frodo and Gandalf are part of the Fellowship")
    void ensureThatFrodoAndGandalfArePartOfTheFellowship() {
        assertAll(() -> {
            Assertions.assertTrue(fellowship.contains(new TolkienCharacter("Frodo", 33, Race.HOBBIT)));
            Assertions.assertTrue(fellowship.contains(new TolkienCharacter("Gandalf", 2020, Race.MAIA)));
        });
    }

    @Test
    @DisplayName("Ensure that one ring bearer is part of the fellowship")
    void ensureThatOneRingBearerIsPartOfTheFellowship() {
        Map<Ring, TolkienCharacter> ringBearers = dataService.getRingBearers();
        assertTrue(ringBearers.values().stream().anyMatch(ringBearer ->
                fellowship.contains(ringBearer)));
    }

    @RepeatedTest(1000)
    @Tag("slow")
    @DisplayName("Minimal stress testing: run this test 1000 times to")
    void ensureWeCanRetrieveFellowshipMultipleTimes() {
        dataService = new DataService();
        assertNotNull(dataService.getFellowship());
    }

    @Test
    @DisplayName("Ensure ordering")
    void ensureOrdering() {
        assertAll(() -> {
            assertEquals(fellowship.get(0), dataService.frodo);
            assertEquals(fellowship.get(1), dataService.sam);
            assertEquals(fellowship.get(2), dataService.merry);
            assertEquals(fellowship.get(3), dataService.pippin);
            assertEquals(fellowship.get(4), dataService.gandalf);
            assertEquals(fellowship.get(5), dataService.legolas);
            assertEquals(fellowship.get(6), dataService.gimli);
            assertEquals(fellowship.get(7), dataService.aragorn);
            assertEquals(fellowship.get(8), dataService.boromir);
        });
    }

    @Test
    @DisplayName("Ensure all hobbits and men are younger 100 years; the elfs, dwarfs and maia are all older than 100")
    void ensureAge() {
        List<TolkienCharacter> menAndHobbits = fellowship.stream().filter(
                tolkienCharacter -> tolkienCharacter.getRace() == Race.HOBBIT ||
                        tolkienCharacter.getRace() == Race.MAN).collect(Collectors.toList());

        List<TolkienCharacter> elfsDwarfsMaia = fellowship.stream().filter(
                tolkienCharacter -> tolkienCharacter.getRace() == Race.ELF ||
                        tolkienCharacter.getRace() == Race.DWARF ||
                        tolkienCharacter.getRace() == Race.MAIA).collect(Collectors.toList());
        assertAll(() -> {
            menAndHobbits.forEach(x -> assertTrue(x.getAge() < 100));
            elfsDwarfsMaia.forEach(x -> assertTrue(x.getAge() > 100));
        });
    }

    @Test
    @DisplayName("Ensure that fellows stay a small group")
    void ensureThatFellowsStayASmallGroup() {
        assertThrows(IndexOutOfBoundsException.class, () -> fellowship.get(20));
    }

    @Test
    @DisplayName("Ensures that update does not run longer than 3 seconds")
    void ensuresThatUpdateDoesNotRunLongerThan3Seconds() {
        assertTimeout(Duration.ofSeconds(3), () -> dataService.update());
    }
}