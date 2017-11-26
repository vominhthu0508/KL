/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.util.*;

/**
 *
 * @author DLT
 */
public class KhoaLuanDTO {
    private int index;
    private int indexOfTuple;
    private String productId;
    private int score;
    private float pro;
    private float topk;
    private int status;
    public KhoaLuanDTO (int indexOfTuple, int score, float pro, float topk){
        this.indexOfTuple = indexOfTuple;
        this.score = score;
        this.pro = pro;
        this.topk = topk;
    }
    public KhoaLuanDTO(){
        
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
     * @return the productId
     */
    public String getProductId() {
        return productId;
    }

    /**
     * @param productId the productId to set
     */
    public void setProductId(String productId) {
        this.productId = productId;
    }

    /**
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * @param score the score to set
     */
    public void setScore(int score) {
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
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }
    
}
