package Practicum1;

public class fillMonth extends StartStepTracker {
     void fillMonth() {
        for (int i = 0; i < stepsPerMonth.length - 1; i++) {
            stepsPerMonth[i] = random.nextInt(15000);
            dayNumber++;
        }
    }
}
