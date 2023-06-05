package ua.skorobahatyi.models.response;

import java.util.List;

public class MarketResponse {
    private String marketName;
    private List<RunnerResponse> runnerResponses;

    public MarketResponse() {
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public List<RunnerResponse> getRunnerResponses() {
        return runnerResponses;
    }

    public void setRunnerResponses(List<RunnerResponse> runnerResponses) {
        this.runnerResponses = runnerResponses;
    }
}
