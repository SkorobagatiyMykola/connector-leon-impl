package ua.skorobahatyi.models.events;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LeonEvent {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("kickoff")
    private Long kickoff;
    @JsonProperty("league")
    private LeonLeagueInfo league;
    @JsonProperty("markets")
    private List<LeonMarket> markets;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getKickoff() {
        return kickoff;
    }

    public LeonLeagueInfo getLeague() {
        return league;
    }

    public List<LeonMarket> getMarkets() {
        return markets;
    }
}
