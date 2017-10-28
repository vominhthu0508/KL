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
public class KhoaLuanDTO {
    private int index;
    private float score;
    private float pro;
    private float topk;
    /**
     * @return the score
     */
    public float getScore() {
        return score;
    }

    /**
     * @param score the score to set
     */
    public void setScore(float score) {
        this.score = score;
    }

    /**
     * @return the pro
     */
    public float getPro() {
        return pro;
    }

    /**
     * @param pro the pro to set
     */
    public void setPro(float pro) {
        this.pro = pro;
    }

    /**
     * @return the topk
     */
    public float getTopk() {
        return topk;
    }

    /**
     * @param topk the topk to set
     */
    public void setTopk(float topk) {
        this.topk = topk;
    }

    /**
     * @return the index
     */
    public int getIndex() {
        return index;
    }

    /**
     * @param index the index to set
     */
    public void setIndex(int index) {
        this.index = index;
    }
}
