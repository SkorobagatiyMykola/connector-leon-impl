package ua.skorobahatyi.models.events;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LeonLeagueInfo {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;

    @JsonProperty("sport")
    private SportInfo sport;
    @JsonProperty("region")
    private RegionInfo region;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public SportInfo getSport() {
        return sport;
    }

    public RegionInfo getRegion() {
        return region;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public  static class RegionInfo {
        @JsonProperty("name")
        private String name;

        public String getName() {
            return name;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public  static class SportInfo {
        @JsonProperty("name")
        private String name;

        public String getName() {
            return name;
        }
    }

}
