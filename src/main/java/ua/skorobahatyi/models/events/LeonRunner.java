package ua.skorobahatyi.models.events;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LeonRunner {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;
/*    @JsonProperty("price") (!) fixme I used String for coefficient
    private String price;*/
    @JsonProperty("priceStr")
    private String priceStr;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPriceStr() {
        return priceStr;
    }
}
