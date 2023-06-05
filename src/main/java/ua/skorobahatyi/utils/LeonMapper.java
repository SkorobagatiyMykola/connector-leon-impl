package ua.skorobahatyi.utils;

import ua.skorobahatyi.models.events.*;
import ua.skorobahatyi.models.response.EventResponse;
import ua.skorobahatyi.models.response.MarketResponse;
import ua.skorobahatyi.models.response.RunnerResponse;
import ua.skorobahatyi.models.sports.LeonLeague;
import ua.skorobahatyi.models.sports.LeonSport;
import ua.skorobahatyi.models.sports.LeonRegion;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

public class LeonMapper {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public EventResponse convertToResponse(LeonEvent leonEvent) {
        EventResponse response = new EventResponse();

        LeonLeagueInfo league = leonEvent.getLeague();
        String sportName = league.getSport().getName();
        String regionName = league.getRegion().getName();
        String leagueName = league.getName();
        LocalDateTime ld = LocalDateTime.ofInstant(Instant.ofEpochMilli(leonEvent.getKickoff()), TimeZone.getDefault().toZoneId());
        String dateEvent = ld.format(formatter);

        response.setSport(sportName);
        response.setRegion(regionName);
        response.setLeague(leagueName);

        response.setDate(dateEvent);
        response.setMatch(leonEvent.getName());
        response.setMatchId(leonEvent.getId());

        List<LeonMarket> marketList = leonEvent.getMarkets();
        List<MarketResponse> marketResponses = new ArrayList<>();
        for (LeonMarket leonMarket : marketList) {
            String marketName = leonMarket.getName();
            List<LeonRunner> runnerList = leonMarket.getRunners();

            MarketResponse marketResponse = new MarketResponse();
            List<RunnerResponse> runnerResponseList = new ArrayList<>();
            for (LeonRunner leonRunner : runnerList) {
                RunnerResponse runnerResponse = new RunnerResponse();
                runnerResponse.setId(leonRunner.getId());
                runnerResponse.setName(leonRunner.getName());
                runnerResponse.setPrice(leonRunner.getPriceStr());
                runnerResponseList.add(runnerResponse);
            }

            marketResponse.setMarketName(marketName);
            marketResponse.setRunnerResponses(runnerResponseList);
            marketResponses.add(marketResponse);
        }
        response.setMarkets(marketResponses);

        return response;
    }

    public List<Long> findAllTopLeaguesId(List<LeonSport> leonSportList, List<String> requiredSportsList) {

        int count = requiredSportsList.size();
        int convertSports = 0;
        List<Long> result = new ArrayList<>();
        for (LeonSport leonSport : leonSportList) {
            if (requiredSportsList.contains(leonSport.getName())) {
                List<LeonRegion> regions = leonSport.getRegions();
                findTopLeaguesHandlerRegions(result, regions);
                convertSports++;
            }
            if (count == convertSports) {
                break;
            }
        }
        return result;
    }

    private void findTopLeaguesHandlerRegions(List<Long> topLeagues, List<LeonRegion> regions) {
        for (LeonRegion leonRegion : regions) {
            findTopLeaguesFromRegion(topLeagues, leonRegion);
        }
    }

    private void findTopLeaguesFromRegion(List<Long> topLeagues, LeonRegion region) {
        List<LeonLeague> leagues = region.getLeagues();
        findTopLeaguesId(topLeagues, leagues);
    }

    private void findTopLeaguesId(List<Long> topLeagues, List<LeonLeague> leagues) {
        for (LeonLeague league : leagues) {
            if (league.getTop()) {
                topLeagues.add(league.getId());
            }
        }
    }

    public Long findFirstEventId(LeonLeagueEvent event) {
        return event.getFirstEventId();
    }
}
