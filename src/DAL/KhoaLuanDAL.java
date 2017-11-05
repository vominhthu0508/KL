/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;
import DAL.MyConnectUnit;
import DTO.KhoaLuanDTO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author DLT
 */
public class KhoaLuanDAL {
    //MyConnectUnit connect = new MyConnectUnit("C:/Users/DLT/Documents/NetBeansProjects/KhoaLuan/src/Database/database_khoaluan.db");
    MyConnectUnit connect = new MyConnectUnit("C:/Users/DLT/Documents/NetBeansProjects/KhoaLuan/src/Database/sample2.db");
    public ArrayList<KhoaLuanDTO> getData(ResultSet rs) throws SQLException, Exception
    {
        ArrayList<KhoaLuanDTO> dataList = new ArrayList<KhoaLuanDTO>();
        while(rs.next())
        {
            KhoaLuanDTO data= new KhoaLuanDTO();
            data.setIndex(rs.getInt("indexOfTuple"));
            data.setId(rs.getString("ProductID"));
            data.setScore(rs.getFloat("Score"));
            data.setPro(rs.getFloat("Pro"));
            dataList.add(data);
        }
        connect.Close();
        return dataList;
    }
    
    public ArrayList<KhoaLuanDTO> getAllData() throws Exception
    {
        ResultSet rs = connect.Select("Top_k");
        return getData(rs);
    }
    
    public void UpdateIndexOfTuple(int index)throws Exception
    {
        HashMap<String, Object> map = new HashMap<String,Object>();
        map.put("indexOfTuple", index);
        connect.Update("Top_k", map, "[index] = "+ index);
        connect.Close();
    }
    
    public void InsertExclusiveTable(int indexOfTuple, String exclusiveTuples) throws Exception
    {
        HashMap<String, Object>map = new HashMap<String,Object>();
        map.put("indexOfTuple", indexOfTuple);
        map.put("exclusiveTuple", exclusiveTuples);
        connect.Insert("ExclusiveRule", map);
        connect.Close();
    }
    
    public String GetExclusiveTupleString(int indexOfTuple) throws Exception
    {
        String exclusiveTuples = "";
        ResultSet rs = connect.Select("ExclusiveRule", "indexOfTuple = " + indexOfTuple);
        if(rs.next())
        {            
            exclusiveTuples = (rs.getString("exclusiveTuple"));
        }
        connect.Close();
        return exclusiveTuples;  
    }
    
    public void DeleteExclusiveTable() throws Exception
    {
        connect.Delete("ExclusiveRule",null);
        connect.Close();
    }
    
    public void InsertInclusiveTable(int indexOfTuple, String inclusiveTuples) throws Exception
    {
        HashMap<String, Object>map = new HashMap<String,Object>();
        map.put("indexOfTuple", indexOfTuple);
        map.put("inclusiveTuple", inclusiveTuples);
        connect.Insert("InclusiveRule", map);
        connect.Close();
    }
    
    public String GetInclusiveTupleString(int indexOfTuple) throws Exception
    {
        String exclusiveTuples = "";
        ResultSet rs = connect.Select("InclusiveRule", "indexOfTuple = " + indexOfTuple);
        if(rs.next())
        {            
            exclusiveTuples = (rs.getString("inclusiveTuple"));
        }
        connect.Close();
        return exclusiveTuples;  
    }
    
    public void DeleteInclusiveTable() throws Exception
    {
        connect.Delete("InclusiveRule",null);
        connect.Close();
    }
}
   
