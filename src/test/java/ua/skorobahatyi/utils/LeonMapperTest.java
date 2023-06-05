package ua.skorobahatyi.utils;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ua.skorobahatyi.models.events.LeonLeagueEvent;
import ua.skorobahatyi.models.sports.LeonSport;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class LeonMapperTest {
    public LeonMapper leonMapper = new LeonMapper();
    public ObjectMapper mapper = new ObjectMapper();

    @Test
    @DisplayName("Find All Top Leagues Id, for Football, Ice Hockey, Tennis, Basketball")
    public void testFindAllTopLeaguesId() throws IOException {
        List<LeonSport> leonSports = mapper.readValue(new FileReader("src/test/resources/leon_sports.json"),
                new TypeReference<List<LeonSport>>() {
                });
        List<String> listSports = List.of("Football", "Ice Hockey", "Tennis", "Basketball");
        List<Long> listTopLeaguesId = leonMapper.findAllTopLeaguesId(leonSports, listSports);

        List<Long> result = List.of(1970324836975366L, 1970324836980123L, 1970324836991678L, 1970324836980121L,
                1970324836975649L, 1970324836975652L, 1970324836978008L, 1970324836975689L,
                1970324836980046L, 1970324836981706L, 1970324836987012L, 1970324836987014L,
                1970324836975913L, 1970324836975930L, 1970324836976160L, 1970324836975937L,
                1970324836976109L, 1970324836974725L);

        Assertions.assertTrue(listTopLeaguesId.containsAll(result));
    }

    @Test
    @DisplayName("All Top Leagues Id for Football")
    public void testFindFootballTopLeaguesId() throws IOException {
        List<LeonSport> leonSports = mapper.readValue(new FileReader("src/test/resources/leon_sports.json"),
                new TypeReference<List<LeonSport>>() {
                });
        List<String> listSports = List.of("Football");
        List<Long> listTopLeaguesId = leonMapper.findAllTopLeaguesId(leonSports, listSports);

        List<Long> result = List.of(1970324836975366L, 1970324836980123L, 1970324836991678L, 1970324836980121L,
                1970324836975649L, 1970324836975652L, 1970324836978008L, 1970324836975689L, 1970324836980046L);

        Assertions.assertTrue(listTopLeaguesId.containsAll(result));
    }

    @Test
    @DisplayName("Failed, All Top Leagues Id for Football")
    public void testFindFootballTopLeaguesId_Failed() throws IOException {
        List<LeonSport> leonSports = mapper.readValue(new FileReader("src/test/resources/leon_sports.json"),
                new TypeReference<List<LeonSport>>() {
                });
        List<String> listSports = List.of("Football");
        List<Long> listTopLeaguesId = leonMapper.findAllTopLeaguesId(leonSports, listSports);

        List<Long> result = List.of(100000000001L, 1970324836980123L, 1970324836991678L, 1970324836980121L,
                1970324836975649L, 1970324836975652L, 1970324836978008L, 1970324836975689L, 1970324836980046L);

        Assertions.assertFalse(listTopLeaguesId.containsAll(result));
    }

    @Test
    @DisplayName("First Event Id for Football League")
    public void testFindFootballFirstEventId() throws IOException {
        LeonLeagueEvent leonLeagueEvent = mapper.readValue(
                new FileReader("src/test/resources/leon_football_league.json"), LeonLeagueEvent.class);

        Long leagueId = leonMapper.findFirstEventId(leonLeagueEvent);

        Assertions.assertEquals(1970324841906411L, leagueId);
        Assertions.assertNotEquals(19703248420000L, leagueId);
    }

    @Test
    @DisplayName("First Event Id for Tennis League")
    public void testFindTennisFirstEventId() throws IOException {
        LeonLeagueEvent leonLeagueEvent = mapper.readValue(
                new FileReader("src/test/resources/leon_tennis_league.json"), LeonLeagueEvent.class);

        Long leagueId = leonMapper.findFirstEventId(leonLeagueEvent);

        Assertions.assertEquals(1970324842072117L, leagueId);
        Assertions.assertNotEquals(19703248420000L, leagueId);
    }
}