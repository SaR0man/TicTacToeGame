import java.util.Scanner;

public class Homework {

    /*
    Игра "Крестики-нолики"
     */

    public static int coordinateX;
    public static int coordinateY;
    public static int fieldSize;  // размер игрового поля
    public static char[] mainArr;  // массив содержимого клеток
    public static char playerTurn = 'X';  // игрок, которому принадлежит ход (игрок-1 = X, игрок-2 = O, компьютер = 0)
    public static boolean gameType;  // true - игра с человеком, false игра с компьютером
    public static boolean win = false; // условие выполнения победы: должно стать true


    ////// Установка размера игрового поля
    public static void fieldSize() {
        System.out.println("Вас приветствует игра \"Крестики-нолики\"!");
        System.out.println("Установим размер игрового поля. Обычно оно размером 3 на 3.");
        System.out.print("Желаете сыграть на таком поле? (введите цифру 3), или введите значение нового размера поля (введите число от 4 до 9): ");
        boolean event = true;  // пока event = true, крутим ввод координат
        while (event) {
            Scanner scanner = new Scanner(System.in);
            if (!scanner.hasNextInt()) {  // введено ли целое число?
                System.out.print("Будьте внимательны! Введите число от 3 до 9: ");
                continue;
            }
            fieldSize = scanner.nextInt();  // размер игрового поля
            mainArr = new char[fieldSize * fieldSize];  // размер массива содержимого клеток
            if (fieldSize < 3 || fieldSize > 9)
                System.out.print("Будьте внимательны! Введите число от 3 до 9: ");
            else event = false;
        }
    }

    ////// Выбираем игру с человеком или компьютером
    public static void gameType() {
        System.out.print("Игра с человеком или компьютером? 1 - человек, 2 - компьютер: ");
        while (true) {
            Scanner scanner = new Scanner(System.in);
            if (!scanner.hasNextInt()) {  // введено ли целое число?
                System.out.print("Будьте внимательны! Введите 1 или 2: ");
                continue;
            }
            int temp = scanner.nextInt();

            if (temp < 1 || temp > 2)
                System.out.print("Будьте внимательны! Введите 1 или 2: ");
            else {
                gameType = temp == 1;  // если введена 1, то gameType = true; иначе - false
                break;
            }
        }
    }

    ////// Заливка игрового поля пробелами
    public static void fillField() {
        for (int i = 0; i < mainArr.length; i++) {
            mainArr[i] = ' ';
        }
    }

    ////// Метод для отображения игрового поля
    public static void display() {
        System.out.print("\n================\n\n    ");
        for (int i = 1; i <= fieldSize; i++) {
            System.out.print("X" + i + ": ");
        }
        System.out.println("");
        for (int i = 0; i < fieldSize; i++) {
            System.out.print("Y" + (i + 1) + ": ");
            for (int j = 0; j < fieldSize; j++) {
                System.out.print(" " + mainArr[(i * fieldSize) + j] + " ");
                if (j < fieldSize - 1) {
                    System.out.print("|");
                }
            }
            System.out.println("");
            if (i < fieldSize - 1) {
                System.out.print("    ");
                for (int k = 0; k < fieldSize; k++) {
                    System.out.print("---");
                    if (k < fieldSize - 1)
                        System.out.print("-");
                }
                System.out.println("");
            }
        }
    }

    ////// Диалог с игроком для хода
    public static void turn() {
        if (playerTurn == '0')  // если ход компьютера, идем в compTurn()
            compTurn();
            // если ход игрока-человека:
        else {
            boolean event = true;  // пока event = true, крутим диалог
            while (event) {
                System.out.print("Ход игрока " + playerTurn + ". Введите координату по оси Х от 1 до " + fieldSize + ": ");
                coordinateX = coordinate();
                System.out.print("Ход игрока " + playerTurn + ". Введите координату по оси Y от 1 до " + fieldSize + ": ");
                coordinateY = coordinate();
                if (mainArr[(coordinateY - 1) * fieldSize + (coordinateX - 1)] != ' ') {
                    System.out.println("Будьте внимательны! Клетка X" + coordinateX + "*Y" + coordinateY + " занята, повторите ввод!");
                    continue;
                } else
                    event = false;
                mainArr[(coordinateY - 1) * fieldSize + (coordinateX - 1)] = playerTurn;
                display();
            }
        }
    }

    ////// Ввод пользователем данных с клавиатуры
    public static int coordinate() {
        int coordinate = 0;
        boolean event = true;  // пока event = true, крутим ввод координат
        while (event) {
            Scanner scanner = new Scanner(System.in);
            if (!scanner.hasNextInt()) {  // введено ли целое число?
                System.out.print("Будьте внимательны! Введите корректные координаты - число от 1 до " + fieldSize + ": ");
                continue;
            }
            coordinate = scanner.nextInt();
            if (coordinate < 1 || coordinate > fieldSize)
                System.out.print("Будьте внимательны! Введите корректные координаты - число от 1 до " + fieldSize + ": ");
            else event = false;
        }
        return coordinate;
    }

    ////// Проверка условия победы
    public static boolean win() {
        // сравниваем по всем горизонталям
        for (int j = 0; j <= (fieldSize - 1) * fieldSize; j += fieldSize) {
            char check = mainArr[j];  // переменной check присваиваем первый символ каждой строки игрового поля
            win = true;
            for (int i = j + 1; i < j + fieldSize; i++) {
                if (check == ' ' || check != mainArr[i]) {
                    win = false;
                    break;
                }
            }
            if (win)
                return win;
        }
        // сравниваем по всем вертикалям
        for (int j = 0; j < fieldSize; j++) {
            char check = mainArr[j];  // переменной check присваиваем верхний символ каждого столбца игрового поля
            win = true;
            for (int i = j + fieldSize; i <= ((fieldSize - 1) * fieldSize) + j; i += fieldSize) {
                if (check == ' ' || check != mainArr[i]) {
                    win = false;
                    break;
                }
            }
            if (win)
                return win;
        }
        // сравниваем слева по диагонали
        char check = mainArr[0];  // переменной check присваиваем значение 3верхней левой клетки игрового поля
        win = true;
        for (int i = fieldSize + 1; i <= fieldSize * fieldSize - 1; i += fieldSize + 1) {
            if (check == ' ' || check != mainArr[i]) {
                win = false;
                break;
            }
        }
        if (win)
            return win;
        // сравниваем справа по диагонали
        check = mainArr[fieldSize - 1];  // переменной check присваиваем значение верхней правой клетки игрового поля
        win = true;
        for (int i = fieldSize - 1; i <= (fieldSize - 1) * fieldSize; i += fieldSize - 1) {
            if (check == ' ' || check != mainArr[i]) {
                win = false;
                break;
            }
        }
        return win;
    }

    ////// Проверка условия ничьи
    public static boolean draw() {
        for (char c : mainArr) {
            if (c == ' ') {  // пока в массиве присутствуют пробелы, ходы не исчерпаны
                return false;
            }
        }
        return true;
    }

    ////// ход компьютерного алгоритма в режиме игры 'easy' (случайный ход)
    public static void compTurn() {
        System.out.println("Ход компьютера:");
        // подсчитываем количество пробелов в массиве mainArr
        int spaceCounter = 0;
        for (char c : mainArr) {
            if (c == ' ')
                spaceCounter++;
        }
        // записываем во временный массив spaceList индексы пробелов (в массиве mainArr)
        int[] spaceList = new int[spaceCounter];
        for (int i = 0, j = 0; i < mainArr.length; i++) {
            if (mainArr[i] == ' ') {
                spaceList[j] = i;
                j++;
            }
        }
        // рандомно выбираем индекс в массиве spaceList
        int decision = (int) (Math.random() * spaceList.length);
        // присваиваем нужному элементу массива mainArr значение playerTurn и передаем в display() для отрисовки поля
        mainArr[spaceList[decision]] = playerTurn;
        display();
    }

    public static void main(String[] args) {
        fieldSize();
        fillField();
        gameType();
        display();

        while (!win) {
            turn();
            if (win()) {
                System.out.println("!!!УРА!!! Победил игрок " + playerTurn + "!\nGame Over");
                break;
            }
            if (draw()) {
                System.out.println("Хм... Похоже, ничья!\nGame Over");
                break;
            }
            if (gameType)
                playerTurn = playerTurn == 'X' ? 'O' : 'X';
            else
                playerTurn = playerTurn == 'X' ? '0' : 'X';
        }
    }
}
