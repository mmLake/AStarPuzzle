package com.View;

import com.controller.HFunc;
import com.model.DataCollection;
import com.model.Frontier;
import com.model.PuzzleState;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mayalake on 4/22/18.
 */
public class WriteDataCollection {
    public static final int NUMBER_OF_PUZZLES = 450;

    private static final String OUTPUT_FILE_PATH = "src/com/dataCollected.txt";

    private File file;
    private PrintWriter bw;
    private Map<Integer, DataCollection> allData = new HashMap<>();

    public WriteDataCollection() throws Exception{
        file = new File(OUTPUT_FILE_PATH);
        bw = new PrintWriter(new FileWriter(file, true));
    }

    public void addData(String puzzle, HFunc hFunc){
        PuzzleState puzzleState = new PuzzleState(puzzle, hFunc);
        Frontier frontier = new Frontier(puzzleState);

        int currKey = frontier.getFinalDepth();

        if (allData.containsKey(currKey)){
            allData.get(currKey).addToDataCollection(frontier.getNumberOfStates(), frontier.getTimeCost());
        } else{
            DataCollection data = new DataCollection();
            data.addToDataCollection(frontier.getNumberOfStates(), frontier.getTimeCost());

            allData.put(currKey, data);
        }
    }

    public String toString(){
        String dataString = "Depth\t# of puzzles\tAvg solution cost\tAvg time cost\n";
        for (int i: allData.keySet()){
            int totalNumOfPuzzles = allData.get(i).getTotalPuzzles();
            double totalTimeInSecs = allData.get(i).getTotalTimeCost() / 1000000000.0;

            dataString += i + "\t\t"
                    + totalNumOfPuzzles + "\t\t"
                    + allData.get(i).getTotalSolutionCost() / totalNumOfPuzzles + "\t\t"
                    + totalTimeInSecs / totalNumOfPuzzles + "\n";
        }
        return dataString;
    }

    public Boolean write(){

        try {
            //write header
            bw.printf("%10s %15s %20s %20s\n","Depth", "# of puzzles", "Avg solution cost", "Avg time cost");
            bw.flush();

            //write all data
            for (int i: allData.keySet()){
                int totalNumOfPuzzles = allData.get(i).getTotalPuzzles();
                double totalTimeInSecs = allData.get(i).getTotalTimeCost() / 1000000000.0;

                bw.printf("%10d %15d %20d %20f\n",
                        i,
                        totalNumOfPuzzles,
                        allData.get(i).getTotalSolutionCost() / totalNumOfPuzzles,
                        totalTimeInSecs / totalNumOfPuzzles);

                bw.flush();
            }
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
