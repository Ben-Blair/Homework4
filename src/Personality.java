/**
 * Ben Blair 23 February 2024
 * Should use array
 * Follow Style Guide provided in BB
 * Follow freedom of information and gilligans island rule
 * Used Machine AI - ChatGPT
 **/

import java.io.*;
import java.util.*;

public class Personality {
    public static final int NUM_DIMENSIONS = 4;
    public static final String[][] DIMENSION_OPTIONS = {{"E", "I"}, {"S", "N"}, {"T", "F"}, {"J", "P"}};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        introduceProgram();
        try {
            System.out.print("input file name? ");
            Scanner inputFileScanner = new Scanner(new File(scanner.next()));

            System.out.print("output file name? ");
            PrintStream outputFile = new PrintStream(new File(scanner.next()));

            processInputFile(inputFileScanner, outputFile);

            outputFile.close();
            inputFileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } finally {
            scanner.close();
        }
    }

    // Introduction message
    public static void introduceProgram() {
        System.out.println("This program processes a file of answers to the");
        System.out.println("Keirsey Temperament Sorter.  It converts the");
        System.out.println("various A and B answers for each person into");
        System.out.println("a sequence of B-percentages and then into a");
        System.out.println("four-letter personality type.");
        System.out.println();
    }

    // Process input file and generate output
    public static void processInputFile(Scanner inputFileScanner, PrintStream outputFile) {
        while (inputFileScanner.hasNextLine()) {
            String name = inputFileScanner.nextLine(); // Read person's name
            outputFile.print(name + ": ");
            String answers = inputFileScanner.next(); // Read person's answers
            String[] questionAnswers = breakDownAnswers(answers); // Break down answers into individual questions
            int[] answerCounts = countAnswers(questionAnswers); // Count A and B answers for each question
            int[] bPercentages = calculateBPercentage(answerCounts); // Calculate B percentages for each dimension
            outputFile.println(Arrays.toString(bPercentages) + " = " + determinePersonality(bPercentages)); // Print results
            if (inputFileScanner.hasNextLine()) {
                inputFileScanner.nextLine(); // Move to the next person
            }
        }
    }

    // Break down answers into individual questions
    public static String[] breakDownAnswers(String answers) {
        String[] questionAnswers = new String[NUM_DIMENSIONS];
        int index = 0;
        for (int i = 0; i < questionAnswers.length; i++) {
            questionAnswers[i] = "";
        }
        for (int i = 0; i < answers.length(); i++) {
            char answer = Character.toUpperCase(answers.charAt(i)); // Convert answer to uppercase
            int questionIndex = (int)Math.round((index + 1) / 2); // Calculate question index
            questionAnswers[questionIndex] += answer; // Store answer for the question
            index = (index + 1) % 7; // Move to the next question
        }
        return questionAnswers;
    }

    // Count A and B answers for each question
    public static int[] countAnswers(String[] questionAnswers) {
        int[] answerCounts = new int[2 * NUM_DIMENSIONS];
        int index = 0;
        for (String answer : questionAnswers) {
            for (int j = 0; j < answer.length(); j++) {
                if (answer.charAt(j) == 'A') {
                    answerCounts[index]++; // Count A answer
                } else if (answer.charAt(j) == 'B') {
                    answerCounts[index + 1]++; // Count B answer
                }
            }
            index += 2; // Move to the next dimension
        }
        return answerCounts;
    }

    // Calculate B percentages for each dimension
    public static int[] calculateBPercentage(int[] answerCounts) {
        int[] bPercentages = new int[NUM_DIMENSIONS];
        for (int i = 0; i < NUM_DIMENSIONS; i++) {
            int total = answerCounts[i * 2] + answerCounts[i * 2 + 1]; // Total number of answers for the dimension
            bPercentages[i] = (total == 0) ? 0 : Math.round((answerCounts[i * 2 + 1] * 100.0f) / total); // Calculate B percentage
        }
        return bPercentages;
    }

    // Determine personality type based on B percentages
    public static String determinePersonality(int[] bPercentages) {
        StringBuilder personality = new StringBuilder();
        for (int i = 0; i < NUM_DIMENSIONS; i++) {
            if (bPercentages[i] > 50) {
                personality.append(DIMENSION_OPTIONS[i][1]); // Append second letter for B percentage > 50
            } else if (bPercentages[i] < 50) {
                personality.append(DIMENSION_OPTIONS[i][0]); // Append first letter for B percentage < 50
            } else {
                personality.append("X"); // Append 'X' for B percentage = 50
            }
        }
        return personality.toString();
    }
}
