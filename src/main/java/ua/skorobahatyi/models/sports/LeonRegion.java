package ua.skorobahatyi.models.sports;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LeonRegion {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;
      @JsonProperty("leagues")
    private List<LeonLeague> leagues;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public List<LeonLeague> getLeagues() {
        return leagues;
    }
}