/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.KhoaLuanDAL;
import DTO.KhoaLuanDTO;
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
    
    public void InsertExclusiveTable(int indexOfTuple, String exclusiveTuples) throws Exception
    {
        dal.InsertExclusiveTable(indexOfTuple, exclusiveTuples);
    }
    
    public String GetExclusiveTupleString(int indexOfTuple) throws Exception
    {
        return dal.GetExclusiveTupleString(indexOfTuple);
    }
    
    public void DeleteExclusiveTable () throws Exception
    {
        dal.DeleteExclusiveTable();
    }
    
    public void InsertInclusiveTable(int indexOfTuple, String inclusiveTuples) throws Exception
    {
        dal.InsertInclusiveTable(indexOfTuple, inclusiveTuples);
    }
    
    public String GetInclusiveTupleString(int indexOfTuple) throws Exception
    {
        return dal.GetInclusiveTupleString(indexOfTuple);
    }
    
    public void DeleteInclusiveTable () throws Exception
    {
        dal.DeleteInclusiveTable();
    }
    
}
