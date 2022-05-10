package assignment2021.dataloading;

import assignment2021.codeprovided.dataloading.AbstractDataLoader;
import assignment2021.codeprovided.dataloading.DataParsingException;
import assignment2021.codeprovided.fitnesstracker.Participant;
import assignment2021.codeprovided.fitnesstracker.Tracker;
import assignment2021.codeprovided.fitnesstracker.measurements.MeasurementFactory;
import assignment2021.codeprovided.fitnesstracker.measurements.MeasurementType;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataLoader extends AbstractDataLoader {


    @Override
    public Participant loadDataLines(List<String> lines) throws DataParsingException {


        String attributes = lines.get(0);
        lines.remove(0);

        // if tracker name make new tracker with new info


        String currentTracker = "";
        Map<String, Tracker> trackerMap = new HashMap<>();
        MeasurementType type = null;

        for (String line : lines) {

            // sets which tracker the data is coming from
            switch (line) {
                case "Heart Rate\n" -> {
                    currentTracker = "Heart Rate";
                    type = MeasurementType.HEART_RATE;
                }
                case "Steps\n" -> {
                    currentTracker = "Steps";
                    type = MeasurementType.STEPS;
                }
                case "Energy expenditure\n" -> {
                    currentTracker = "Energy expenditure";
                    type = MeasurementType.ENERGY_EXPENDITURE;
                }
                case "Distance\n" -> {
                    currentTracker = "Distance";
                    type = MeasurementType.DISTANCE;
                }
            }


            List<String> measurements = Arrays.asList(line.split(CELL_SEPARATOR));
            int count = Integer.parseInt(measurements.get(0));
            measurements.remove(0);
            measurements.forEach(measurement -> {
				assert type != null;
				MeasurementFactory.createMeasurement(type, count, measurement);
			})


        })

		return null;
    }


}
