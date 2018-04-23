package com.controller;

/**
 * Created by mayalake on 4/10/18.
 */
public class HFunc1 extends HFunc{

    public int heuristicVal(String state){
        int heuristicVal = 0;
        for (int i = 0; i < state.length(); i++){
            char c = state.charAt(i);
//            if ((Character.getNumericValue(state.charAt(i)) != i) && (state.charAt(i)!='0')){
            if ((Integer.parseInt(state.substring(i, i+1)) !=i) && state.charAt(i) !=0){

                heuristicVal++;
            }
        }
        return heuristicVal;
    }
}
