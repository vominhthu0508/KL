/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.KhoaLuanDAL;
import DTO.*;
import java.util.ArrayList;

/**
 *
 * @author DLT
 */
public class KhoaLuanBLL {
    KhoaLuanDAL dal = new KhoaLuanDAL();
    public ArrayList<KhoaLuanDTO> getAllData() throws Exception
    {
        return dal.getAllData();
    }
    
    public void UpdateIndexOfTuple(int index) throws Exception
    {
        dal.UpdateIndexOfTuple(index);
    }
    
    public void InsertExclusiveTable(int indexOfTuple, int exclusiveTuple) throws Exception
    {
        dal.InsertExclusiveTable(indexOfTuple, exclusiveTuple);
    }
    
    public ArrayList<ExclusiveRuleDTO> GetExclusiveRulesByIndex(int indexOfTuple) throws Exception
    {
        return dal.GetExclusiveRulesByIndex(indexOfTuple);
    }
    
    public void DeleteExclusiveTable () throws Exception
    {
        dal.DeleteExclusiveTable();
    }
    
    public void InsertInclusiveTable(int indexOfTuple, int inclusiveTuple) throws Exception
    {
        dal.InsertInclusiveTable(indexOfTuple, inclusiveTuple);
    }
    
    public ArrayList<InclusiveRuleDTO> GetInclusiveRulesByIndex(int indexOfTuple) throws Exception
    {
        return dal.GetInclusiveRulesByIndex(indexOfTuple);
    }
    
    public void DeleteInclusiveTable () throws Exception
    {
        dal.DeleteInclusiveTable();
    }
    
}
