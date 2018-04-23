package com.controller;

import com.model.Frontier;
import com.model.PuzzleState;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by mayalake on 4/22/18.
 */
public class FrontierController {
    private static final HashMap<Integer, Integer[]> MAP_TO_IDXS_FOR_0 = new HashMap<>();
    private static final int GOAL_STATE_H_VAL = 0;

    public static void instantiateExpandIdxMap(){
        MAP_TO_IDXS_FOR_0.put(0, new Integer[]{1,3});
        MAP_TO_IDXS_FOR_0.put(1, new Integer[]{0,2,4});
        MAP_TO_IDXS_FOR_0.put(2, new Integer[]{1,5});
        MAP_TO_IDXS_FOR_0.put(3, new Integer[]{0,4,6});
        MAP_TO_IDXS_FOR_0.put(4, new Integer[]{1,3,5,7});
        MAP_TO_IDXS_FOR_0.put(5, new Integer[]{2,4,8});
        MAP_TO_IDXS_FOR_0.put(6, new Integer[]{3,7});
        MAP_TO_IDXS_FOR_0.put(7, new Integer[]{4,6,8});
        MAP_TO_IDXS_FOR_0.put(8, new Integer[]{5,7});
    }

    public static PuzzleState aStarAlgorithm(PuzzleState temp){
//        Comparator comparator = new PuzzleComparator();
        Queue<PuzzleState> frontierStates = new PriorityQueue<PuzzleState>();
        HashMap<String, Boolean> visitedStates = new HashMap();
        String tiles;
        PuzzleState childPuzzle;

        //add root to frontier
        temp.setDepth(0);
        frontierStates.add(temp);

        //expand frontier
        while ((temp = frontierStates.poll()) != null){

            //goal state reached
            if (temp.getHeuristicVal() == GOAL_STATE_H_VAL){
                return temp;
            }

            tiles = temp.getTiles();
            visitedStates.put(tiles, false);
            Frontier.incrementNumberOfStates();

            //for the number of tiles that 0 can switch with, add each new puzzle state to the frontier
            for (int i : MAP_TO_IDXS_FOR_0.get(temp.indexOf0)){
                if ((temp.getDepth()== 0) || (i != temp.getParent().indexOf0)) {
                    String childPuzzleString = puzzleWithSwappedTilesString(tiles, temp.indexOf0, i);

                    if (!visitedStates.containsKey(childPuzzleString)){
                        childPuzzle = PuzzleStateController.getChildPuzzle(temp, childPuzzleString);

                        frontierStates.add(childPuzzle);
                        Frontier.incrementNumberOfStates();
                    }
                }
            }
        }
        return null;
    }

    private static String puzzleWithSwappedTilesString(String tiles, int tile1Idx, int tile2Idx){
        int lowerIdx = ((tile1Idx < tile2Idx) ? tile1Idx : tile2Idx);
        int higherIdx = ((lowerIdx == tile1Idx) ? tile2Idx : tile1Idx);

        return tiles.substring(0, lowerIdx)
                + tiles.charAt(higherIdx)
                + tiles.substring(lowerIdx+1, higherIdx)
                + tiles.charAt(lowerIdx)
                + tiles.substring(higherIdx+1);
    }
}
