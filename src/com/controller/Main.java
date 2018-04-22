package com.controller;

import com.View.RandomInput;
import com.View.ReadTestFile;
import com.View.StandardInput;
import com.model.Frontier;
import com.model.PuzzleState;

import java.util.Scanner;

public class Main {
    public static final String[] INPUT_FILE_PATHS = {"src/com/input1.txt", "src/com/input2.txt"};
    public static final int NUMBER_OF_RANDOM_PUZZLES = 10;

    PuzzleState puzzleState;

    RandomInput ri;
    StandardInput si;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HFunc1 h1 = new HFunc1();
        HFunc2 h2 = new HFunc2();
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
                    puzzleState = new PuzzleState(formattedPuzzle, h1);
                    printResults(puzzleState);

                    break;
                case 3:
                    RandomInput randomInput = new RandomInput();

                    //generate puzzle input
                    String randomPuzzle;

                    for (int i = 0; i < NUMBER_OF_RANDOM_PUZZLES; i++) {
                        randomPuzzle = randomInput.populatePuzzle();

                        puzzleState = new PuzzleState(randomPuzzle, h1);
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

    public void runTests(){
        HFunc1 h1 = new HFunc1();
        HFunc2 h2 = new HFunc2();
        Frontier frontier;

        try {
            for (String filePath : INPUT_FILE_PATHS) {
                ReadTestFile rtf = new ReadTestFile(filePath);
                String newPuzzle;

                while ((newPuzzle = rtf.populatePuzzle()) != null) {

                    PuzzleState testInputRead = new PuzzleState(newPuzzle, h1);


                    if (testInputRead.isSolvable()) {
                        frontier = new Frontier(testInputRead);
                    }
                }
            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
