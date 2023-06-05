package ua.skorobahatyi.models.sports;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LeonLeague {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("top")
    private Boolean top;

    public Long getId() {
        return id;
    }

    public Boolean getTop() {
        return top;
    }
}