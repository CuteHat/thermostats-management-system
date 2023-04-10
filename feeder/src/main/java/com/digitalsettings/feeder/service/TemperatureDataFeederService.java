package com.digitalsettings.feeder.service;

import com.digitalsettings.feeder.tms.model.TemperatureDataCreateRequest;
import com.digitalsettings.feeder.tms.service.ThermostatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.cli.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
@Slf4j
public class TemperatureDataFeederService implements CommandLineRunner {
    private final ThermostatService thermostatService;

    @Override
    public void run(String... args) throws Exception {
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(getArgumentOptions(), args);

        // default values
        int numDataTemps = cmd.hasOption("n") ? ((Number) cmd.getParsedOptionValue("n")).intValue() : 5;
        int minDegree = cmd.hasOption("min") ? ((Number) cmd.getParsedOptionValue("min")).intValue() : 10;
        int maxDegree = cmd.hasOption("max") ? ((Number) cmd.getParsedOptionValue("max")).intValue() : 30;

        List<Long> thermostatIds = thermostatService.getThermostatIds();
        log.info("Generating {} temperature data for {} thermostats", numDataTemps, thermostatIds.size());
        for (Long thermostatId : thermostatIds) {
            for (int i = 0; i < numDataTemps; i++) {
                BigDecimal temperature = generateRandomTemperature(minDegree, maxDegree);
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                TemperatureDataCreateRequest request = new TemperatureDataCreateRequest(temperature, timestamp);
                thermostatService.registerTemperatureData(thermostatId, request);
            }
        }
    }

    private Options getArgumentOptions() {
        Options options = new Options();

        Option numDataTemps = Option.builder("n")
                .longOpt("num-data-temps")
                .desc("Number of data temperatures per thermostat (default: 10)")
                .hasArg()
                .type(Number.class)
                .build();

        Option minDegree = Option.builder("min")
                .longOpt("min-degree")
                .desc("Minimum temperature degree (default: 10)")
                .hasArg()
                .type(Number.class)
                .build();

        Option maxDegree = Option.builder("max")
                .longOpt("max-degree")
                .desc("Maximum temperature degree (default: 30)")
                .hasArg()
                .type(Number.class)
                .build();

        options.addOption(numDataTemps);
        options.addOption(minDegree);
        options.addOption(maxDegree);

        return options;
    }

    private BigDecimal generateRandomTemperature(int minDegree, int maxDegree) {
        double randomTemperature = ThreadLocalRandom.current().nextDouble(minDegree, maxDegree);
        return BigDecimal.valueOf(randomTemperature).setScale(2, RoundingMode.HALF_UP);
    }


}
