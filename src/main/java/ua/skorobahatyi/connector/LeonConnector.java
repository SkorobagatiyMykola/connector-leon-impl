package ua.skorobahatyi.connector;

import com.fasterxml.jackson.core.type.TypeReference;
import ua.skorobahatyi.models.events.LeonEvent;
import ua.skorobahatyi.models.events.LeonLeagueEvent;
import ua.skorobahatyi.models.response.EventResponse;
import ua.skorobahatyi.models.sports.LeonSport;
import ua.skorobahatyi.utils.ApiClientUtils;
import ua.skorobahatyi.utils.LeonMapper;
import ua.skorobahatyi.utils.PropertyManager;

import java.util.*;

public class LeonConnector {

    private ApiClientUtils httpUtils = new ApiClientUtils();
    private static String leonAllSportUrl;
    private static String leonLeagueUrl;
    private static String leonEventUrl;
    private static String vtag;
    private static List<String> sportList;

    LeonMapper leonMapper = new LeonMapper();

    static {
        PropertyManager config = new PropertyManager();
        leonAllSportUrl = config.getProperty("leon.api.url.sports");
        leonLeagueUrl = config.getProperty("leon.api.url.league");
        leonEventUrl = config.getProperty("leon.api.event");
        sportList = config.getPropertyList("sports.list");
        vtag = config.getProperty("vtag.id");
    }

    public List<Long> getAllTopLeaguesId() throws Exception {

        String url = leonAllSportUrl;

        List<LeonSport> leonSports = httpUtils.httpGet(url, getHeaders(), new TypeReference<List<LeonSport>>() {
        }).getEntity();

        List<Long> listTopLeaguesId = leonMapper.findAllTopLeaguesId(leonSports, sportList);

        return listTopLeaguesId;
    }

    public Long getFirstEventIdFromLeague(Long leagueId) throws Exception {
        String url = leonLeagueUrl.replace("{vtag}", vtag).replace("{league_id}", leagueId.toString());

        LeonLeagueEvent leonLeagueEvent = httpUtils.httpGet(url, getHeaders(), LeonLeagueEvent.class).getEntity();

        Long eventId = leonMapper.findFirstEventId(leonLeagueEvent);

        return eventId;
    }

    public EventResponse getAllMarketForEvent(Long eventId) throws Exception {
        String url = leonEventUrl.replace("{eventId}", eventId.toString());

        LeonEvent leonEvent = httpUtils.httpGet(url, getHeaders(), LeonEvent.class).getEntity();

        EventResponse eventResponse = leonMapper.convertToResponse(leonEvent);

        return eventResponse;
    }

    private static Map<String, String> getHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("User-Agent", "PostmanRuntime/7.32.2");
        headers.put("Accept", "*/*");
        headers.put("Cache-Control", "no-cache");
        headers.put("Postman-Token", UUID.randomUUID().toString());
        headers.put("Host", "leon.bet");
        headers.put("Accept-Encoding", "gzip, deflate, br");
        headers.put("Connection", "keep-alive");
        return headers;
    }
}
