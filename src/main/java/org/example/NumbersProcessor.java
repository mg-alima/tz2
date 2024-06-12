package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class NumbersProcessor {

    public static int _min(int[] numbers) {
        int min = Integer.MAX_VALUE;
        for (int num : numbers) {
            if (num < min) {
                min = num;
            }
        }
        return min;
    }

    public static int _max(int[] numbers) {
        int max = Integer.MIN_VALUE;
        for (int num : numbers) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }

    public static int _sum(int[] numbers) {
        int sum = 0;
        for (int num : numbers) {
            sum += num;
        }
        return sum;
    }

    public static int _mult(int[] numbers) {
        int product = 1;
        for (int num : numbers) {
            product *= num;
        }
        return product;
    }

    public static int[] readNumbersFromFile(String filename) throws FileNotFoundException {
        File file = new File(filename);
        Scanner scanner = new Scanner(file);

        String line = scanner.nextLine();
        String[] numberStrings = line.split(" ");
        int[] numbers = new int[numberStrings.length];
        for (int i = 0; i < numberStrings.length; i++) {
            numbers[i] = Integer.parseInt(numberStrings[i]);
        }

        scanner.close();
        return numbers;
    }

    public static void main(String[] args) {
        try {
            int[] numbers = readNumbersFromFile("data/input.txt");
            int min = _min(numbers);
            int max = _max(numbers);
            int sum = _sum(numbers);
            int product = _mult(numbers);
            System.out.println("Минимальное: " + min);
            System.out.println("Максимальное: " + max);
            System.out.println("Сумма: " + sum);
            System.out.println("Произведение: " + product);
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден: " + e.getMessage());
        }
    }
}
