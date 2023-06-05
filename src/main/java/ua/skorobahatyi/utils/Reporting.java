package ua.skorobahatyi.utils;

import ua.skorobahatyi.models.response.MarketResponse;
import ua.skorobahatyi.models.response.EventResponse;
import ua.skorobahatyi.models.response.RunnerResponse;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class Reporting {
    private static final String titleLine = "%s, %s %s\n";
    private static final String matchLine = "\t %s, %s UTC, %d\n";
    private static final String marketLine = "\t\t %s\n";
    private static final String riskEventLine = "\t\t\t %s, %s, %d\n";
    private static String fileName;
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH_mm_ss");

    static {
        Locale.setDefault(Locale.US);
        PropertyManager config = new PropertyManager();
        String fileNameConf = config.getProperty("reporting.file.name");
        String dateNow = DATE_TIME_FORMATTER.format(LocalDateTime.now());
        fileName = fileNameConf.replace("{date}", dateNow);
    }

    public  void printConsole(EventResponse response) {
        System.out.printf(titleLine, response.getSport(), response.getRegion(), response.getLeague());
        System.out.printf(matchLine, response.getMatch(), response.getDate(), response.getMatchId());
        List<MarketResponse> marketResponseList = response.getMarkets();
        for (MarketResponse market : marketResponseList) {
            System.out.printf(marketLine, market.getMarketName());
            List<RunnerResponse> riskEventList = market.getRunnerResponses();
            for (RunnerResponse riskEvent : riskEventList) {
                System.out.printf(riskEventLine, riskEvent.getName(), riskEvent.getPrice(), riskEvent.getId());
            }
        }
    }

    public  void printFile(EventResponse response) throws IOException {
        Path filePath = Path.of(fileName);

        try (FileWriter fileWriter = new FileWriter(filePath.toFile(), true);
             PrintWriter printWriter = new PrintWriter(fileWriter);) {
            printWriter.printf(titleLine, response.getSport(), response.getRegion(), response.getLeague());
            printWriter.printf(matchLine, response.getMatch(), response.getDate(), response.getMatchId());
            List<MarketResponse> marketResponseList = response.getMarkets();
            for (MarketResponse market : marketResponseList) {
                printWriter.printf(marketLine, market.getMarketName());
                List<RunnerResponse> riskEventList = market.getRunnerResponses();
                for (RunnerResponse riskEvent : riskEventList) {
                    printWriter.printf(riskEventLine, riskEvent.getName(), riskEvent.getPrice(), riskEvent.getId());
                }
            }
        }
    }
}
