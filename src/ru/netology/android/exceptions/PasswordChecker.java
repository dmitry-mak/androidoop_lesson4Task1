package ru.netology.android.exceptions;

/**
 * предстоит написать класс PasswordChecker, объект которого умеет проверять надёжность пароля.
 * <p>
 * У этого класса должны быть методы-настройки:
 * <p>
 * setMinLength — сеттер установки минимально допустимой длины пароля. Если в сеттер передали
 * недопустимое значение (отрицательное число), то должно выкинуться исключение IllegalArgumentException,
 * т. е. исключение недопустимого аргумента, с соответствующим сообщением;
 * setMaxRepeats — сеттер установки максимально допустимого количества повторений символа подряд.
 * Если в сеттер передали недопустимое значение (отрицательное число или 0), то должно выкинуться
 * исключение IllegalArgumentException, т. е. исключение недопустимого аргумента, с соответствующим сообщением.
 * И метод проверки boolean verify(String password), который проверяет переданный пароль на эти два критерия.
 * Если пароль проходит, возвращает true, не проходит — false. Если до вызова метода verify хотя бы одна из двух
 * вышеперечисленных настроек не была выставлена чекеру, то в нём кидается исключение IllegalStateException,
 * т. е. исключение недопустимого состояния, с соответствующим сообщением.
 * <p>
 * В main спросите у пользователя настройки для чекера, создайте объект чекера и в бесконечном цикле — до ввода end — спрашивайте у пользователя пароли, которые затем проверяйте чекером и выводите на экран результат. Если пользователь ввёл недопустимые настройки, то программа не должна вылетать с исключением. Поймайте его, выведите на экран сообщение об ошибке и завершите программу.
 */
public class PasswordChecker {

    private int minLength;
    private int maxRepeats;
    private boolean isLengthSet = false;
    private boolean isRepeatsSet = false;

    public void setMinLength(int minLength) {
        if (minLength < 0) {
            throw new IllegalArgumentException("Min length must be positive");
        }
        this.minLength = minLength;
        this.isLengthSet = true;
    }

    public void setMaxRepeats(int maxRepeats) {
        if (maxRepeats <= 0) {
            throw new IllegalArgumentException("Max repeats must be positive");
        }
        this.maxRepeats = maxRepeats;
        this.isRepeatsSet = true;
    }

    public boolean verify(String password) {
        if (!isLengthSet || !isRepeatsSet) {
            throw new IllegalStateException("Одна из необходимых настроек не была выставлена");
        }
        if (!isTooFrequent(password, maxRepeats) && !isTooShort(password, minLength)) {
            return true;
        } else
            return false;
    }

    public boolean isTooFrequent(String password, int maxRepeats) {
        for (int i = 0; i < password.length(); i++) {
            if (password.contains(String.valueOf(password.charAt(i)).repeat(maxRepeats + 1))) {
                return true;
            }
        }
        return false;
    }

    public boolean isTooShort(String password, int minLength) {
        return (password.length() < minLength);
    }

    boolean verifyFromLecturer(String password) {
        if (this.minLength == -1 && this.maxRepeats == -1) {
            throw new IllegalStateException("Не все настройки пароля выставлены!");
        }
        if (password.length() < minLength) {
            return false;
        }
        int count = 0;
        char previousLatter = password.charAt(0);
        for (char latter : password.toCharArray()) {
            if (latter == previousLatter) {
                count++;
            } else {
                count = 1;
            }

            if (count > maxRepeats) {
                return false;
            }
            previousLatter = latter;
        }
        return true;
    }
}

