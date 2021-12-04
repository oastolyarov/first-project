package Practicum2;

import java.util.Random;
import java.util.Scanner;

import static Practicum2.TicTacToe.*;

public class Game {
    static char sign_x = 'x';
    static char sign_o = 'o';
    static char sign_empty = '.';
    static char[][] table;
    static Random random;
    static Scanner scanner;

    public static void main(String[] args) {
        random = new Random();
        scanner = new Scanner(System.in);
        table = new char[3][3];

        initTable();

        turnHuman();
        System.out.println("Конец игры");

        printTable();
    }
}