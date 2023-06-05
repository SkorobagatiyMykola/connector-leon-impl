package ua.skorobahatyi.models.response;

import java.util.List;
import java.util.Objects;

public class EventResponse {
    private Long matchId;
    private String sport;
    private String region;
    private String league;
    private String match;
    private String date;

    private List<MarketResponse> markets;

    public EventResponse() {
    }

    public EventResponse(Long matchId, String match) {
        this.matchId = matchId;
        this.match = match;
    }

    public Long getMatchId() {
        return matchId;
    }

    public void setMatchId(Long matchId) {
        this.matchId = matchId;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public String getMatch() {
        return match;
    }

    public void setMatch(String match) {
        this.match = match;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<MarketResponse> getMarkets() {
        return markets;
    }

    public void setMarkets(List<MarketResponse> markets) {
        this.markets = markets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventResponse response = (EventResponse) o;
        return Objects.equals(matchId, response.matchId) && Objects.equals(sport, response.sport) && Objects.equals(region, response.region) && Objects.equals(league, response.league) && Objects.equals(match, response.match) && Objects.equals(date, response.date) && Objects.equals(markets, response.markets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matchId, sport, region, league, match, date, markets);
    }
}
