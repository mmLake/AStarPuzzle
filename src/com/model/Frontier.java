package com.model;

import java.util.*;

/**
 * Created by mayalake on 4/15/18.
 */
public class Frontier {
    private static final HashMap<Integer, List<Integer>> expandIdxMap = new HashMap<>();

    private Comparator comparator = new PuzzleComparator();
    private Queue<PuzzleState> frontierStates = new PriorityQueue(comparator);
    private ArrayList<PuzzleState> visitedStates = new ArrayList<>();
    private int numberOfStates = 0;

    private static void instantiateExpandIdxMap(){
        expandIdxMap.put(0, Arrays.asList(1,3));
        expandIdxMap.put(1, Arrays.asList(0,2,4));
        expandIdxMap.put(2, Arrays.asList(1,5));
        expandIdxMap.put(3, Arrays.asList(0,4,6));
        expandIdxMap.put(4, Arrays.asList(1,3,5,7));
        expandIdxMap.put(5, Arrays.asList(2,4,8));
        expandIdxMap.put(6, Arrays.asList(3,7));
        expandIdxMap.put(7, Arrays.asList(4,6,8));
        expandIdxMap.put(8, Arrays.asList(5,7));
    }


    public Frontier(PuzzleState root){
        instantiateExpandIdxMap();

        frontierStates.add(root);
        numberOfStates++;

        PuzzleState finalState = aStarAlgorithm();

        if (finalState == null){
            System.out.println("ERROR SOLVING PUZZLE");
        } else {
            System.out.println("Depth " + finalState.getDepth() + "Total # of states " + numberOfStates);
        }
    }

    private PuzzleState aStarAlgorithm(){
        PuzzleState temp;
        int indexOf0;

        while ((temp = frontierStates.poll()) != null){
            visitedStates.add(temp);

            indexOf0 = temp.getTiles().indexOf('0');

            System.out.println("CURRENT PUZZLE " + temp.getTiles() + " idx of 0 " + indexOf0);

            //if puzzle is solved, return.
            if (temp.getHeuristicVal() == 0){
                return temp;
            }

            //for the number of tiles that 0 can switch with, add each new puzzle state to the frontier
            for (int i : expandIdxMap.get(indexOf0)){
                PuzzleState childPuzzle = temp.puzzleWithSwappedTilesOld(indexOf0, i);

                if (!visitedStates.contains(childPuzzle) & !frontierStates.contains(childPuzzle)){
                    frontierStates.add(childPuzzle);
                    numberOfStates++;
                }


            }
        }

        return null;
    }

    //Comparator anonymous class implementation
    public class PuzzleComparator implements Comparator<PuzzleState> {
        @Override
        public int compare(PuzzleState p1, PuzzleState p2) {
            return (int) ((p1.getHeuristicVal() + p1.getDepth()) - (p2.getHeuristicVal() + p2.getDepth()));
        }
    }
}
