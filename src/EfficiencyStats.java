import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
class EfficiencyStats {
    public static StatsResult computeStats(ArrayList<Car> carList) {

        if (carList.isEmpty()) {
            return new StatsResult(0.0, 0.0, 0.0);
        }

        List<Double> fuelEfficiencies = new ArrayList<>();
        for (Car car : carList) {
            fuelEfficiencies.add(car.getFuelEfficiency());
        }

        double mean = calculateMean(fuelEfficiencies);
        double median = calculateMedian(fuelEfficiencies);
        double mode = calculateMode(fuelEfficiencies);

        return new StatsResult(mean, median, mode);
    }

    private static double calculateMean(List<Double> fuelEfficiencies) {
        double sum = 0.0;
        for (Double efficiency : fuelEfficiencies) {
            sum += efficiency;
        }
        return sum / fuelEfficiencies.size();
    }

    private static double calculateMedian(List<Double> fuelEfficiencies) {
        Collections.sort(fuelEfficiencies);
        int size = fuelEfficiencies.size();
        if (size % 2 == 0) {
            int middle1 = size / 2;
            int middle2 = middle1 - 1;
            return (fuelEfficiencies.get(middle1) + fuelEfficiencies.get(middle2)) / 2;
        } else {
            int middle = size / 2;
            return fuelEfficiencies.get(middle);
        }
    }

    private static double calculateMode(List<Double> fuelEfficiencies) {
        Map<Double, Integer> frequencyMap = new HashMap<>();
        double mode = 0;
        int maxFrequency = 0;
        for (Double efficiency : fuelEfficiencies) {
            int frequency = frequencyMap.getOrDefault(efficiency, 0) + 1;
            frequencyMap.put(efficiency, frequency);
            if (frequency > maxFrequency) {
                maxFrequency = frequency;
                mode = efficiency;
            }
        }
        return mode;
    }

    static class StatsResult {
        public double mean;
        public double median;
        public double mode;

        public StatsResult(double mean, double median, double mode) {
            this.mean = mean;
            this.median = median;
            this.mode = mode;
        }
    }
}