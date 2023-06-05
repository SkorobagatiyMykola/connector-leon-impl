package ua.skorobahatyi.models.events;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LeonMarket {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("runners")
    private List<LeonRunner> runners;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<LeonRunner> getRunners() {
        return runners;
    }
}
