package org.example;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class NumbersProcessorTest {

    private final String TEST_FILE = "data/input.txt";

    @Test
    public void testFindMin() throws FileNotFoundException {
        int[] numbers = NumbersProcessor.readNumbersFromFile(TEST_FILE);
        assertEquals(1, NumbersProcessor._min(numbers));
    }

    @Test
    public void testFindMax() throws FileNotFoundException {
        int[] numbers = NumbersProcessor.readNumbersFromFile(TEST_FILE);
        assertEquals(8, NumbersProcessor._max(numbers));
    }

    @Test
    public void testCalculateSum() throws FileNotFoundException {
        int[] numbers = NumbersProcessor.readNumbersFromFile(TEST_FILE);
        assertEquals(36, NumbersProcessor._sum(numbers));
    }

    @Test
    public void testCalculateMult() throws FileNotFoundException {
        int[] numbers = NumbersProcessor.readNumbersFromFile(TEST_FILE);
        assertEquals(40320, NumbersProcessor._mult(numbers));
    }

    @Test
    public void testReadSingleNumberFromFile() {
        try {
            int singleNumber = 42;
            File file = createFile(singleNumber);
            int[] numbers = NumbersProcessor.readNumbersFromFile(TEST_FILE_TIME);
            assertThat(new int[]{singleNumber}).isEqualTo(numbers);
            file.delete();
        } catch (IOException e) {
            System.out.println("Ошибка при создании тестового файла: " + e.getMessage());
        }
    }

    private File createFile(int number) throws IOException {
        File file = new File(TEST_FILE_TIME);
        FileWriter writer = new FileWriter(file);
        writer.write(String.valueOf(number));
        writer.close();
        return file;
    }

    //тесты на время
    private final String TEST_FILE_TIME = "data/test_input.txt";

    @Test
    public void testPerformanceFindMin() {
        measureTimeForFunction("_min");
    }

    @Test
    public void testPerformanceFindMax() {
        measureTimeForFunction("_max");
    }

    @Test
    public void testPerformanceCalculateSum() {
        measureTimeForFunction("_sum");
    }

    @Test
    public void testPerformanceCalculateMult() {
        measureTimeForFunction("_mult");
    }

    private void measureTimeForFunction(String functionName) {
        try {
            File file = createTestFile(1000000);
            int[] numbers = NumbersProcessor.readNumbersFromFile(TEST_FILE_TIME);
            long startTime = System.currentTimeMillis();
            switch (functionName) {
                case "_min":
                    NumbersProcessor._min(numbers);
                    break;
                case "_max":
                    NumbersProcessor._max(numbers);
                    break;
                case "_sum":
                    NumbersProcessor._sum(numbers);
                    break;
                case "_mult":
                    NumbersProcessor._mult(numbers);
                    break;
                default:
                    throw new IllegalArgumentException("Неизвестная функция: " + functionName);
            }
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;
            System.out.println("Время выполнения функции " + functionName + ": " + duration + " мс");
            file.delete();
        } catch (IOException e) {
            System.out.println("Ошибка при создании тестового файла: " + e.getMessage());
        }
    }

    private File createTestFile(int count) throws IOException {
        File file = new File(TEST_FILE_TIME);
        FileWriter writer = new FileWriter(file);
        for (int i = 0; i < count; i++) {
            writer.write(i + " ");
        }
        writer.close();
        return file;
    }
}
