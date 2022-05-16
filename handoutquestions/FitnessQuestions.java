package assignment2021.handoutquestions;

import assignment2021.codeprovided.fitnesstracker.Participant;
import assignment2021.codeprovided.fitnesstracker.Tracker;
import assignment2021.codeprovided.fitnesstracker.measurements.Measurement;
import assignment2021.codeprovided.fitnesstracker.measurements.MeasurementType;
import assignment2021.codeprovided.handoutquestions.AbstractFitnessQuestions;

import java.util.*;


public class FitnessQuestions extends AbstractFitnessQuestions {

    public FitnessQuestions(Collection<Participant> participants) {
        super(participants);
    }

    private boolean hasMeasurementType(String trackerName,
                                       Participant participant,
                                       MeasurementType type) {
        try {
            Tracker tracker =
                    participant.getTracker(trackerName);
            return tracker.getMeasurementsMap().containsKey(type);
        } catch (NoSuchElementException e) {
            e.printStackTrace(); // if it doesn't have a tracker, return false
            return false;
        }


    }

    @Override
    public String toString() {
        // TODO Implement
        System.out.println("Total participants: " + getTotalParticipants());
        System.out.println("Participants with HRM: " + getParticipantsNumberWithHRM());
        System.out.println("Participants with steps: " + getParticipantsNumberWithStepsM());
        return "TODO: FitnessQuestions' toString() method should return the answers to the questions as specified in the handout.\n";
    }

    @Override
    public int getTotalParticipants() {
        // TODO Auto-generated method stub
        // get total number of participants
        return this.getParticipants().size();
    }

    @Override
    public int getParticipantsNumberWithHRM() {
        // TODO Auto-generated method stub
        int counter = 0;
        for (Participant participant : this.getParticipants()) {// for each participant
            for (String trackerName : participant.getAllTrackerNames()) {  // for each tracker
                // if it has a heart rate measurement, increment counter
                if (hasMeasurementType(trackerName, participant,
                        MeasurementType.HEART_RATE)) counter++;
                break;

            }
        }
        return counter;
    }

    @Override
    public int getParticipantsNumberWithStepsM() {
        int counter = 0;
        for (Participant participant : this.getParticipants()) {// for each participant
            for (String trackerName : participant.getAllTrackerNames()) {  // for each tracker
                // if it has steps measurement, increment counter
                if (hasMeasurementType(trackerName, participant, MeasurementType.STEPS))
                    counter++;
                break;
            }
        }
        return counter;
    }

    @Override
    public int getParticipantsNumberWithDistanceM() {
        int counter = 0;
        for (Participant participant : this.getParticipants()) {// for each participant
            for (String trackerName : participant.getAllTrackerNames()) {  // for each tracker
                // if it has a distance measurement, increment counter
                if (hasMeasurementType(trackerName, participant, MeasurementType.DISTANCE))
                    counter++;
                break;
            }
        }
        return counter;
    }

    @Override
    public int getParticipantsNumberWithEEM() {
        int counter = 0;
        for (Participant participant : this.getParticipants()) {// for each participant
            for (String trackerName : participant.getAllTrackerNames()) {  // for each tracker
                // if it has energy expenditure measurement, increment counter
                if (hasMeasurementType(trackerName, participant, MeasurementType.ENERGY_EXPENDITURE))
                    counter++;
                break;
            }
        }
        return counter;
    }

    @Override
    public int getTotalHRMCount() {
        // TODO Auto-generated method stub
        int counter = 0;

        // for each participant
        // get total count of heart rate measurements for each tracker
        // add them to counter
        for (Participant participant : this.getParticipants()) {
            for (String trackerName : participant.getAllTrackerNames()) {
                Tracker tracker = participant.getTracker(trackerName);
                counter += tracker.getMeasurementsForType(MeasurementType.HEART_RATE).size();

            }
        }
        return counter;
    }

    @Override
    public int getTotalStepsCount() {
        int counter = 0;
        for (Participant participant : this.getParticipants()) {// for each participant
            for (String trackerName : participant.getAllTrackerNames()) {  // for each tracker
                Tracker tracker = participant.getTracker(trackerName);
                counter += tracker.getMeasurementsForType(MeasurementType.STEPS).size();

            }

        }
        return counter;
    }

    @Override
    public int getTotalDistanceCount() {
        int counter = 0;
        for (Participant participant : this.getParticipants()) {// for each participant
            for (String trackerName : participant.getAllTrackerNames()) {  // for each tracker
                Tracker tracker = participant.getTracker(trackerName);
                counter += tracker.getMeasurementsForType(MeasurementType.DISTANCE).size();
            }

        }
        return counter;
    }


    @Override
    public int getTotalEECount() {
        // for each participant
        // get total count of energy expenditure measurements for each tracker
        // add them to counter
        int counter = 0;
        for (Participant participant : this.getParticipants()) {
            for (String trackerName : participant.getAllTrackerNames()) {
                Tracker tracker = participant.getTracker(trackerName);
                counter += tracker.getMeasurementsForType(MeasurementType.ENERGY_EXPENDITURE).size();
            }
        }

        return counter;
    }

    @Override
    public List<Integer> getHRMCountPerFT() {
        // save all the tracker names to a set
        // iterate through the participants and for each tracker name
        // get the count of heart rate measurements for that tracker
        Set<String> trackerNames = new HashSet<>();
        for (Participant participant : this.getParticipants()) {
            trackerNames.addAll(participant.getAllTrackerNames());
        }
        List<Integer> counts = new ArrayList<>();
        for (String trackerName : trackerNames) {
            int count = 0;
            for (Participant participant : this.getParticipants()) {
                Tracker tracker = participant.getTracker(trackerName);
                count += tracker.getMeasurementsForType(MeasurementType.HEART_RATE).size();
            }
            counts.add(count);
        }
        // return a map of tracker names to counts

        return counts;
    }

    @Override
    public int getEEMCountForFT1() {
        // return the count of energy expenditure measurements for the tracker with name FT1
        int count = 0;
        for (Participant participant : this.getParticipants()) {
            Tracker tracker = participant.getTracker("FT1");
            count += tracker.getMeasurementsForType(MeasurementType.ENERGY_EXPENDITURE).size();
        }
        return count;
    }

    @Override
    public List<Integer> getStepsMCountForFT234() {
        // return the counts of steps measurements for the trackers with names FT2, FT3, and FT4
        // save all the tracker names to a set
        // iterate through the participants and for each tracker name
        // get the count of steps measurements for that tracker
        Set<String> trackerNames = new HashSet<>();
        for (Participant participant : this.getParticipants()) {
            trackerNames.addAll(participant.getAllTrackerNames());
        }
        List<Integer> counts = new ArrayList<>();
        for (String trackerName : trackerNames) {
            int count = 0;
            for (Participant participant : this.getParticipants()) {
                Tracker tracker = participant.getTracker(trackerName);
                count += tracker.getMeasurementsForType(MeasurementType.STEPS).size();

            }
            counts.add(count);

        }
        return counts;
    }

    @Override
    public int getDistanceMCountForFT5() {
        // get the count of distance measurements for the tracker with name FT5
        int count = 0;
        for (Participant participant : this.getParticipants()) {
            Tracker tracker = participant.getTracker("FT5");
            count += tracker.getMeasurementsForType(MeasurementType.DISTANCE).size();
        }
        return count;
    }

    // method overloading in order to get a count for a specific tracker
    private Set<String> getParticipantsWithMaxValueForType(MeasurementType type) {
        return getParticipantsWithMaxValueForType(type, null);
    }
    // method to get the participants with the max value for a specific type
    private Set<String> getParticipantsWithMaxValueForType(MeasurementType type,
                                                           Tracker tracker) {
        Set<String> trackerNames = new HashSet<>();

        // gets the tracker names for all the participants
        if (tracker == null) {
            for (Participant participant : this.getParticipants()) {
                trackerNames.addAll(participant.getAllTrackerNames());
            }
        } else {
            trackerNames.add(tracker.getName());
        }

        // iterate through participants and for each tracker name get the
        // highest measurement for that type
        // for that participant
        Map<String, Object> maxMap = new HashMap<>();
        float globalMax = 0;
        for (Participant participant : this.getParticipants()) {
            float participantMax = 0;

            for (String trackerName : trackerNames) {

                // get the list of measurements for that tracker
                // cast the measurement to a double and get the max
                for (Measurement measurement :
                        participant.getTracker(trackerName).getMeasurementsForType(type)) {
                    Number number = measurement.getValue();
                    float value = (float) number;
                    if (value > participantMax) {
                        if (value > globalMax) {
                            globalMax = value;
                        }
                        participantMax = value;
                    }

                }
            }
            maxMap.put(participant.getName(), participantMax);
        }

        // get the participants with the max value by comparing the max values
        // to the global max if they are equal then add the participant to the
        // set
        Set<String> maxParticipants = new HashSet<>();
        for (String participant : maxMap.keySet()) {
            if ((float) maxMap.get(participant) == globalMax) {
                maxParticipants.add(participant);
                    }
        }
        return maxParticipants;


    }

    @Override
    public Set<String> getHighestNumberOfStepsParticipants() {
        return getParticipantsWithMaxValueForType(MeasurementType.STEPS);
    }

    @Override
    public Set<String> getHighestWalkedDistanceParticipants() {
        return getParticipantsWithMaxValueForType(MeasurementType.DISTANCE);
    }

    @Override
    public double getGlobalAverageStepsForFT1() {
        // iterate through all the participants looking at their last value for
        // the steps as this will be the highest value for FT1 add to the total
        // then divide by the number of participants
        float total = 0;
        for (Participant participant : this.getParticipants()) {
            // get the last value for the steps for FT1
            // get the tracker for the participant
            Tracker tracker = participant.getTracker("FT1");
            // get the measurement list for the tracker
            List<Measurement> measurementList =
                    tracker.getMeasurementsForType(MeasurementType.STEPS);
            // get the last measurement
            Measurement measurement = measurementList.get(measurementList.size() - 1);
            // get the value from the measurement
            Number number = measurement.getValue();
            // cast the value to a float and add to the total
            total += (float) number;

        }
        // divide the total by the number of participants
        return total / this.getParticipants().size();
    }

    @Override
    public List<String> getAvgStepsAboveGlobalParticipantsForFT1() {
        // get the global average steps for FT1
        // iterate through all the participants looking at their last value for
        // the steps
        // if the value is greater than the global average steps for FT1 add the
        // participant to the list
        float globalAverageSteps = (float) getGlobalAverageStepsForFT1();

        List<String> participants = new ArrayList<>();

        for (Participant participant1 : this.getParticipants()) {
            // get the last value for the steps for FT1
            // get the tracker for the participant
            Tracker tracker = participant1.getTracker("FT1");
            List<Measurement> measurementList = tracker.getMeasurementsForType(MeasurementType.STEPS);
            // get the last measurement
            Measurement measurement =
                    measurementList.get(measurementList.size() - 1);
            if ((float) measurement.getValue() > globalAverageSteps) {  // if the value is greater than the global average steps for FT1
                participants.add(participant1.getName()); // add the
                // participant to the list
            }
        }
        return participants;
    }

    @Override
    public List<String> getAvgStepsBelowGlobalParticipantsForFT1() {
        // get the global average steps for FT1
        // iterate through all the participants looking at their last value for
        // the steps
        // if the value is less than the global average steps for FT1 add the
        // participant to the list
        float globalAverageSteps = (float) getGlobalAverageStepsForFT1();

        List<String> participants = new ArrayList<>();
        for (Participant participant1 : this.getParticipants()) {
            // get the last value for the steps for FT1
            // get the tracker for the participant
            Tracker tracker = participant1.getTracker("FT1");
            List<Measurement> measurementList = tracker.getMeasurementsForType(MeasurementType.STEPS);
            // get the last measurement
            Measurement measurement =
                    measurementList.get(measurementList.size() - 1);
            if ((float) measurement.getValue() < globalAverageSteps) {  // if the value is less than the global average steps for FT1
                participants.add(participant1.getName());
        }
        }
        return participants;
    }

    @Override
    public double getGlobalAverageEEForFT2FT3() {
        // get the global average EE for FT2 and FT3
        // iterate through all the participants looking at their last value
        // for both FT2 and FT3 and add the value to the total
        // divide the total by the number of participants * 2 (because there are
        // two measurements)
        double total = 0;
        for (Participant participant1 : this.getParticipants()) {
            // get the last value for the EE for FT2 and FT3
            // get the tracker for the participant
            Tracker FT2 = participant1.getTracker("FT2");
            List<Measurement> measurementList =
                    FT2.getMeasurementsForType(MeasurementType.ENERGY_EXPENDITURE);
            // get the last measurement
            Tracker FT3 = participant1.getTracker("FT3");
            List<Measurement> measurementList2 = FT3.getMeasurementsForType(MeasurementType.ENERGY_EXPENDITURE);
            Measurement measurement =
                    measurementList.get(measurementList2.size() - 1);

        }

        return 0;
    }

    @Override
    public List<String> getAvgEEAboveGlobalParticipantsForFT2FT3() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<String> getAvgEEBelowGlobalParticipantsForFT2FT3() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public double getGlobalAverageHR() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List<String> getAvgHRAboveGlobalParticipants() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<String> getAvgHRBelowGlobalParticipants() {
        // TODO Auto-generated method stub
        return null;
    }


}
