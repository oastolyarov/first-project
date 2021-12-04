package Practicum1;

import java.util.Random;
import java.util.Scanner;

public class StartStepTracker {
    static int stepsPerMonth[] = new int[30];
    static int dayNumber = 0;
    static StepTracker practicum1 = new StepTracker();
    static fillMonth fillMonth = new fillMonth();
    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();

    public static void main(String[] args) {

        System.out.println("Сколько шагов в день вы хотите проходить?");
        int stepGoal = scanner.nextInt();

        fillMonth.fillMonth(); // заполнить месяц данными
        practicum1.menu(practicum1, scanner, stepGoal); // вызов меню
    }
}
