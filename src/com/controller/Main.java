package com.controller;

import com.View.RandomInput;
import com.View.ReadTestFile;
import com.View.StandardInput;
import com.model.Frontier;
import com.model.PuzzleState;

public class Main {
    PuzzleState puzzleState;

    RandomInput ri;
    StandardInput si;

    public static void main(String[] args) {

//        RandomInput ri = new RandomInput();
        HFunc1 h1 = new HFunc1();
        HFunc2 h2 = new HFunc2();
        Frontier frontier;

        try {
            ReadTestFile rtf = new ReadTestFile();

//            while (rtf.populatePuzzle() != null) {

                PuzzleState testPuzzle = new PuzzleState("158634072", h1);

                if (testPuzzle.isSolvable()) {
                    frontier = new Frontier(testPuzzle);

                }
//            }

        } catch (Exception e){
            e.printStackTrace();
        }




    }
}
