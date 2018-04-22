package com.controller;

import com.View.RandomInput;
import com.View.ReadTestFile;
import com.View.WriteSampleOutput;
import com.model.Frontier;
import com.model.PuzzleState;

import java.util.Scanner;

public class Main {
    private static final HFunc1 HEURISTIC_FUNC_1 = new HFunc1();
    private static final HFunc2 HEURISTIC_FUNC_2 = new HFunc2();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PuzzleState puzzleState;

        while(true) {

            printWelcomeMsg();

            int num;
            do {
                System.out.print("Enter a number: ");
                num = Integer.parseInt(sc.nextLine());
            } while (!((num >= 1) && (num <= 3)));

            switch (num) {
                case 1:
                    System.exit(0);
                    break;
                case 2:
                    String enteredPuzzle;
                    String formattedPuzzle;

                    //ask for puzzle input
                    do {
                        System.out.print("Enter a puzzle: ");
                        enteredPuzzle = sc.nextLine();
                        formattedPuzzle = correctInput(enteredPuzzle);
                    } while (formattedPuzzle == null);

                    //solve puzzle and print results
                    puzzleState = new PuzzleState(formattedPuzzle, HEURISTIC_FUNC_1);
                    printResults(puzzleState);

                    break;
                case 3:
                    RandomInput randomInput = new RandomInput();

                    //generate puzzle input
                    String randomPuzzle;

                    for (int i = 0; i < RandomInput.NUMBER_OF_RANDOM_PUZZLES; i++) {
                        randomPuzzle = randomInput.populatePuzzle();

                        puzzleState = new PuzzleState(randomPuzzle, HEURISTIC_FUNC_1);
                        printResults(puzzleState);
                    }

                    break;
                default:
                    break;
            }
        }
    }

    private static void printResults(PuzzleState puzzleState){
        Frontier frontier;
        if (puzzleState.isSolvable()) {
            frontier = new Frontier(puzzleState);
            System.out.println(frontier.toString());
        } else {
            System.out.println("Puzzle was not solvable");
        }
    }

    private static String correctInput(String puzzle){
        String finalPuzzle = "";
        String correctPuzzle = "012345678";

        for (char c : puzzle.toCharArray()){
            String cString = String.valueOf(c);
            if ((correctPuzzle.contains(cString)) && (cString != " ") && (!finalPuzzle.contains(cString))){
                finalPuzzle +=c;
            }
        }

        if (finalPuzzle.length() == 9){
            return finalPuzzle;
        }
        return null;
    }

    private static void printWelcomeMsg(){
        System.out.printf("Welcome to the 8 piece puzzle solver.\n\n" +
                "Main menu:\n" +
                "\t(1)Exit\n" +
                "\t(2)Enter a puzzle in the format:# # # # # # # # #.\n\tThere cannot be any repeating numbers, and the numbers must be between 0-8\n"+
                "\t(3)Generate and solve 10 random puzzles\n");
    }

    private static void runTests(){
        Frontier frontier;

        try {
            for (String filePath : ReadTestFile.INPUT_FILE_PATHS) {
                ReadTestFile rtf = new ReadTestFile(filePath);
                String newPuzzle;

                while ((newPuzzle = rtf.populatePuzzle()) != null) {
                    PuzzleState testPuzzle = new PuzzleState(newPuzzle, HEURISTIC_FUNC_1);

                    if (testPuzzle.isSolvable()) {
                        frontier = new Frontier(testPuzzle);
                    }
                }
            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void populateSampleOutput(){
        try {
            WriteSampleOutput writeSampleOutput = new WriteSampleOutput();
            RandomInput randomInput = new RandomInput();
            String puzzle;

            for (int i = 0; i < WriteSampleOutput.NUMBER_OF_SAMPLE_PUZZLES; i++) {
                puzzle = randomInput.populatePuzzle();
                writeSampleOutput.write(puzzle, HEURISTIC_FUNC_1);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
