package com.model;

import java.util.*;

/**
 * Created by mayalake on 4/15/18.
 */
public class Frontier {
    private static final HashMap<Integer, List<Integer>> MAP_TO_IDXS_FOR_0 = new HashMap<>();
    private static final int GOAL_STATE_H_VAL = 0;

    private Comparator comparator = new PuzzleComparator();
    private int numberOfStates = 0;
    private boolean errorSolvingPuzzle = false;
    private int finalDepth;

    private static void instantiateExpandIdxMap(){
        MAP_TO_IDXS_FOR_0.put(0, Arrays.asList(1,3));
        MAP_TO_IDXS_FOR_0.put(1, Arrays.asList(0,2,4));
        MAP_TO_IDXS_FOR_0.put(2, Arrays.asList(1,5));
        MAP_TO_IDXS_FOR_0.put(3, Arrays.asList(0,4,6));
        MAP_TO_IDXS_FOR_0.put(4, Arrays.asList(1,3,5,7));
        MAP_TO_IDXS_FOR_0.put(5, Arrays.asList(2,4,8));
        MAP_TO_IDXS_FOR_0.put(6, Arrays.asList(3,7));
        MAP_TO_IDXS_FOR_0.put(7, Arrays.asList(4,6,8));
        MAP_TO_IDXS_FOR_0.put(8, Arrays.asList(5,7));
    }

    public Frontier(PuzzleState root){
        instantiateExpandIdxMap();

        PuzzleState finalState = aStarAlgorithm(root);

        errorSolvingPuzzle = (finalState == null);

        finalDepth = (errorSolvingPuzzle? null: finalState.getDepth());
    }

    private PuzzleState aStarAlgorithm(PuzzleState temp){
        Queue<PuzzleState> frontierStates = new PriorityQueue<PuzzleState>(comparator);
        HashMap<String, Boolean> visitedStates = new HashMap();
        int indexOf0;

        //add root to frontier
        frontierStates.add(temp);
        numberOfStates++;

        while ((temp = frontierStates.poll()) != null){
            if (temp.getHeuristicVal() == GOAL_STATE_H_VAL){
                return temp;
            }

            visitedStates.put(temp.getTiles(), false);

            indexOf0 = temp.getTiles().indexOf('0');

            //for the number of tiles that 0 can switch with, add each new puzzle state to the frontier
            for (int i : MAP_TO_IDXS_FOR_0.get(indexOf0)){
                PuzzleState childPuzzle = temp.puzzleWithSwappedTilesOld(indexOf0, i);

                if ((!visitedStates.containsKey(childPuzzle.getTiles())) && (!frontierStates.contains(childPuzzle))){
                    frontierStates.add(childPuzzle);
                    numberOfStates++;
                }
            }
        }
        return null;
    }

    //Comparator for puzzle state object
    private class PuzzleComparator implements Comparator<PuzzleState> {
        @Override
        public int compare(PuzzleState p1, PuzzleState p2) {
            int val = (p1.getHeuristicVal() + p1.getDepth()) - (p2.getHeuristicVal() + p2.getDepth());
            if (val == 0){
                return p1.getDepth() - p2.getDepth();
            }
            return val;
        }
    }

    public String toString(){
        if (errorSolvingPuzzle){
            return "ERROR SOLVING PUZZLE";
        }else {
            return "Depth " + finalDepth + "\n"
                    +"Total # of states " + numberOfStates + "\n"
                    ;
        }
    }
}
