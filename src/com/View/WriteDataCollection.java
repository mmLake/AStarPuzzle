package com.View;

import com.controller.HFunc;
import com.controller.HFunc1;
import com.model.DataCollection;
import com.model.Frontier;
import com.model.PuzzleState;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mayalake on 4/22/18.
 */
public class WriteDataCollection {
    public static final int NUMBER_OF_PUZZLES = 450;

    private static final String OUTPUT_FILE_PATH = "src/com/dataCollected.txt";

    private File file;
    private PrintWriter bw;
    private Map<Integer, DataCollection> allDataH1 = new HashMap<>();
    private Map<Integer, DataCollection> allDataH2 = new HashMap<>();

    public WriteDataCollection() throws Exception{
        file = new File(OUTPUT_FILE_PATH);
        bw = new PrintWriter(new FileWriter(file, true));
    }

    private void addDataHelper(PuzzleState puzzle, Map<Integer, DataCollection> allData){
        Frontier frontier = new Frontier(puzzle);

        int currKey = frontier.getFinalDepth();

        if (allData.containsKey(currKey)){
            allData.get(currKey)
                    .addToDataCollection(frontier.getNumberOfStates(), frontier.getTimeCost());
        } else{
            DataCollection data = new DataCollection();
            data.addToDataCollection(frontier.getNumberOfStates(), frontier.getTimeCost());

            allData.put(currKey, data);
        }
    }

    public void addData(String puzzle, HFunc hFunc){
        //add data to appropriate map
        if (hFunc instanceof HFunc1){
            PuzzleState puzzleState = new PuzzleState(puzzle, hFunc);
            addDataHelper(puzzleState, allDataH1);
        } else {
            PuzzleState puzzleState = new PuzzleState(puzzle, hFunc);
            addDataHelper(puzzleState, allDataH2);
        }
    }

    public void writeAll(){
        bw.printf("H1\n");
        bw.flush();
        if (!write(allDataH1)){
            bw.printf("ERROR\n");
            bw.flush();
        }

        bw.printf("H2\n");
        bw.flush();
        if (!write(allDataH2)){
            bw.printf("ERROR\n");
            bw.flush();
        }
    }

    private Boolean write(Map<Integer, DataCollection> allData){

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
