package com.controller;

import com.View.RandomInput;
import com.View.StandardInput;
import com.model.PuzzleState;

public class Main {
    PuzzleState puzzleState;

    RandomInput ri;
    StandardInput si;

    public static void main(String[] args) {
        HFunc1 h1 = new HFunc1();
        HFunc2 h2 = new HFunc2();
        PuzzleState testPuzzle = new PuzzleState("018652374");


        System.out.println("Is solvable? " + testPuzzle.isSolvable());
        System.out.println("HF1? " + h1.heuristicVal(testPuzzle.getTiles()));
        System.out.println("HF2? " + h2.heuristicVal(testPuzzle.getTiles()));


        PuzzleState test2 = new PuzzleState("518602374");
        System.out.println(test2.isSolvable());
    }
}
