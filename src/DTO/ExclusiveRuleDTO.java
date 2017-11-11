/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author DLT
 */
public class ExclusiveRuleDTO {
    private int indexOfTuple;
    private int exclusiveTuple;
    /**
     * @return the indexOfTuple
     */
    public int getIndexOfTuple() {
        return indexOfTuple;
    }

    /**
     * @param indexOfTuple the indexOfTuple to set
     */
    public void setIndexOfTuple(int indexOfTuple) {
        this.indexOfTuple = indexOfTuple;
    }

    /**
     * @return the exclusiveTuple
     */
    public int getExclusiveTuple() {
        return exclusiveTuple;
    }

    /**
     * @param exclusiveTuple the exclusiveTuple to set
     */
    public void setExclusiveTuple(int exclusiveTuple) {
        this.exclusiveTuple = exclusiveTuple;
    }
}
