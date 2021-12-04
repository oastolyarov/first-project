package Practicum1;

import java.util.Scanner;

public class StepTracker extends StartStepTracker {
    void menu(StepTracker practicum1, Scanner scanner, int stepGoal) {
        while (true) {
            System.out.println("Что вы хотите сделать? ");
            System.out.println("1 - Введите количество шагов за день");
            System.out.println("2 - Напечатать статистику");
            System.out.println("0 - Выход");

            int command = scanner.nextInt();

            if (command == 1) {
                System.out.println("Введите количество шагов:");
                practicum1.inputSteps(stepsPerMonth, dayNumber, scanner); // ввод количества шагов
            } else if (command == 2) {
                practicum1.printStaticsics(stepsPerMonth, dayNumber, stepGoal); // показ статистики за месяц
            } else if (command == 0) {
                break;
            } else System.out.println("Извините, такой команды пока нет.");
        }
    }

    void inputSteps(int[] stepsPerMonth, int dayNumber, Scanner scanner) {

        int steps = scanner.nextInt();
        if (dayNumber < stepsPerMonth.length) {
            stepsPerMonth[dayNumber] = steps;
            dayNumber++;
            this.dayNumber = dayNumber;
        } else System.out.println("Месяц закончился, данные не записаны.");
    }

    void printStaticsics(int[] stepsPerMonth, int dayNumber, int stepGoal) {
        System.out.println("Статистика за " + dayNumber + " дней.");
        for (int i = 0; i < stepsPerMonth.length; i++) {
            System.out.print(stepsPerMonth[i] + " ");
        }
        System.out.println();
        int result = 0;
        for (int i = 0; i < stepsPerMonth.length; i++) {
            if (stepsPerMonth[i] > result) {
                result = stepsPerMonth[i];
            }
        }
        System.out.println("Максимальное количество шагов: " + result);
        int sum = 0;

        for (int i = 0; i < stepsPerMonth.length; i++) {
            sum += stepsPerMonth[i];
        }
        System.out.println("Среднее количество шагов: " + sum / stepsPerMonth.length);
        int maxStrike = 0;
        int currentStrike = 0; //количество дней с достигнутой целью по шагам

        for (int i = 0; i < stepsPerMonth.length; i++) {
            if (stepsPerMonth[i] >= stepGoal) {
                currentStrike++;
            } else {
                if (currentStrike > maxStrike) {
                    maxStrike = currentStrike;
                }
                currentStrike = 0;
            }
        }
        System.out.println("Лучшая серия: " + maxStrike);
    }

}

