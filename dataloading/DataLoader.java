package assignment2021.dataloading;

import assignment2021.codeprovided.dataloading.AbstractDataLoader;
import assignment2021.codeprovided.dataloading.DataParsingException;
import assignment2021.codeprovided.fitnesstracker.Participant;
import assignment2021.codeprovided.fitnesstracker.Tracker;
import assignment2021.codeprovided.fitnesstracker.measurements.Measurement;
import assignment2021.codeprovided.fitnesstracker.measurements.MeasurementFactory;
import assignment2021.codeprovided.fitnesstracker.measurements.MeasurementType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class DataLoader extends AbstractDataLoader {


    @Override
    public Participant loadDataLines(List<String> lines)
            throws DataParsingException {

        // get participants attributes
        String attributes = lines.remove(0);

        // initialise the participant
        List<String> attributesArray = Arrays.asList(
                attributes.split(","));
        Participant currentParticipant = new Participant(attributesArray.get(0),
                Integer.parseInt(attributesArray.get(1)),
                attributesArray.get(2));

        MeasurementType type = null;
        boolean headers = false;
        ArrayList<String> headersArray = new ArrayList<String>();
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            List<String> measurements =
                    new LinkedList<String>(Arrays.asList(line.split(CELL_SEPARATOR)));
            // sets which tracker the data is coming from


            // after each line dictating the tracker the line after contains
            // headers by using the skip boolean we can pass over this line


            // TODO: reduce code repetition
            switch (measurements.get(0)) {
                case "Heart Rate" -> {
                    type = MeasurementType.HEART_RATE;
                    headers = true;
                    continue; // and this is
                }
                case "Steps" -> {
                    type = MeasurementType.STEPS;
                    headers = true;
                    continue;
                }
                case "Energy expenditure" -> {
                    type = MeasurementType.ENERGY_EXPENDITURE;
                    headers = true;
                    continue;
                }
                case "Distance" -> {
                    type = MeasurementType.DISTANCE;
                    headers = true;
                    continue;
                }
            }

            // takes the line and splits to list for each measurement
            if (headers) {
                headersArray.clear();  // j = 1 because first header is count
                for (int j = 1; j < measurements.size(); j++) {
                    headersArray.add(measurements.get(j));

                }
                headers = false;

            } else {
                line = lines.get(i);
                measurements =
                        new LinkedList<String>(Arrays.asList(line.split(CELL_SEPARATOR)));
                int k = 1;
                for (String header : headersArray) {
                    Measurement measurement =
                            MeasurementFactory.createMeasurement(type,
                                    Integer.parseInt(measurements.get(0)), //
                                    // count is always first
                                    measurements.get(k));

                    currentParticipant.addMeasurementToTracker(header, measurement);
                    k++;
                }
            }

        }
        return currentParticipant;
    }
}