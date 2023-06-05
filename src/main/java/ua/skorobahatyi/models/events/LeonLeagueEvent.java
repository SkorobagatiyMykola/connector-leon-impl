package ua.skorobahatyi.models.events;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LeonLeagueEvent {
    @JsonProperty("enabled")
    private Boolean enabled;
    @JsonProperty("totalCount")
    private Integer totalCount;
    @JsonProperty("data")
    private List<LeagueEventBrief> data;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public  static class LeagueEventBrief {
        @JsonProperty("id")
        private Long id;

        public Long getId() {
            return id;
        }
    }
    public Boolean getEnabled() {
        return enabled;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public List<LeagueEventBrief> getData() {
        return data;
    }
    public Long getFirstEventId() {
        List<LeagueEventBrief> data= getData();
        return data.isEmpty() ? null : data.get(0).getId();
    }
}
