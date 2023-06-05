package ua.skorobahatyi.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ua.skorobahatyi.models.events.LeonEvent;
import ua.skorobahatyi.models.response.EventResponse;

import java.io.FileReader;
import java.io.IOException;

public class ReportingTest  {
    public LeonMapper leonMapper = new LeonMapper();
    static Reporting reporting = new Reporting();
    public ObjectMapper mapper = new ObjectMapper();


    @Test
    @DisplayName("Tennis match, ATP French Open, Fognini, F - Ofner, S")
    @Disabled
    public void testPrintConsoleTennisMatch() throws IOException {
        LeonEvent leonEvent = mapper.readValue(new FileReader("src/test/resources/leon_tennis_event.json"), LeonEvent.class);
        EventResponse response = leonMapper.convertToResponse(leonEvent);

        reporting.printConsole(response);
    }

    @Test
    @DisplayName("Football match, Europe UEFA Champions League , Manchester City - Inter Milan")
    @Disabled
    public void testPrintConsoleFootballMatch() throws IOException {
        LeonEvent leonEvent = mapper.readValue(new FileReader("src/test/resources/leon_football_even.json"), LeonEvent.class);
        EventResponse response = leonMapper.convertToResponse(leonEvent);

        reporting.printConsole(response);
    }
}