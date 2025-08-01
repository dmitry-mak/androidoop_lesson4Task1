package ru.netology.android.exceptions;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        PasswordChecker checker = new PasswordChecker();
        System.out.print("Введите минимальную длину пароля: ");
        int passwordMinLength = scanner.nextInt();
        checker.setMinLength(passwordMinLength);
        System.out.print("Введите макс. допустимое количество повторений символа подряд: ");
        int passwordMaxRepeats = scanner.nextInt();
        checker.setMaxRepeats(passwordMaxRepeats);
        scanner.nextLine();
        while (true) {
            System.out.print("Введите пароль или end:");
            String password = scanner.nextLine();
            if (password.equals("end")) {
                break;
            }
            if (checker.verify(password)) {
                System.out.println("Подходит!");
            } else {
                System.out.println("Не подходит!");
            }
        }

    }
}
