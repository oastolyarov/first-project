package Practicum2;


public class TicTacToe extends Game {
    static void printTable() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                System.out.print(table[row][col] + " ");
            }
            System.out.println();
        }
    }

    static void turnHuman() {
        int x;
        int y;
        while (true) {
            do {
                System.out.println("Введите кординату X (1..3):");
                x = scanner.nextInt() - 1;
                System.out.println("Введите кординату Y (1..3):");
                y = scanner.nextInt() - 1;
            } while (!isCellValid(x, y));
            table[y][x] = sign_x;
            //
            if (checkWin(sign_x)) {
                System.out.println("Вы выйграли!");
                break;
            }
            if (isTableFull()) {
                System.out.println("Ничья!");
                break;
            }

            if (turnAI()) break;
        }
    }

    static void initTable() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                table[row][col] = sign_empty;
            }
        }
    }

    static boolean turnAI() {
        int x;
        int y;
        do {
            x = random.nextInt(3);
            y = random.nextInt(3);
        } while (!isCellValid(x, y));
        table[y][x] = sign_o;

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                System.out.print(table[row][col] + "\t");
            }
            System.out.println();
        }
        if (checkWin(sign_o)) {
            System.out.println("Компьютер выйграл!");
            return true;
        }
        if (isTableFull()) {
            System.out.println("Ничья!");
            return true;
        }
        return false;
    }

    static boolean isCellValid(int x, int y) {
        if (x < 0 || y < 0 || x >= 3|| y >= 3) {
            System.out.println("Введено не верное значение координат");
            return false;
        }
        return table[y][x] == sign_empty;
    }

    static boolean checkWin(char dot) {
        for (int i = 0; i < 3; i++) {
            if ((table[i][0] == dot && table[i][1] == dot &&
                    table[i][2] == dot) ||
                    (table[0][i] == dot && table[1][i] == dot &&
                            table[2][i] == dot)) {
                return true;
            }
        }
        if ((table[0][0] == dot && table[1][1] == dot &&
                table[2][2] == dot) ||
                (table[2][0] == dot && table[1][1] == dot &&
                        table[0][2] == dot)) {
            return true;
        }
        return false;
    }

    static boolean isTableFull() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (table[row][col] == sign_empty) {
                    return false;
                }
            }
        }
        return true;
    }
}
