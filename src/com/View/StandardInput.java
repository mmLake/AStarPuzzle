package com.View;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by mayalake on 4/10/18.
 */
public class StandardInput {

    public String populatePuzzle(){
        Scanner sc = new Scanner(System.in);
        String puzzleInput = sc.nextLine();
        return puzzleInput.split(" ").toString();

    }
}
