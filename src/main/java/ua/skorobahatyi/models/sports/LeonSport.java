package ua.skorobahatyi.models.sports;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LeonSport {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("regions")
    private List<LeonRegion> regions;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public List<LeonRegion> getRegions() {
        return regions;
    }
}