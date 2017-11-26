/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;
import DAL.MyConnectUnit;
import DTO.*;
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
    MyConnectUnit connect = new MyConnectUnit("KhoaLuan");
    public ArrayList<KhoaLuanDTO> getData(ResultSet rs) throws SQLException, Exception
    {
        ArrayList<KhoaLuanDTO> dataList = new ArrayList<KhoaLuanDTO>();
        while(rs.next())
        {
            KhoaLuanDTO data= new KhoaLuanDTO();
            data.setIndex(rs.getInt("index"));
            data.setIndexOfTuple(rs.getInt("indexOfTuple"));
            data.setProductId(rs.getString("productID"));
            data.setScore(rs.getInt("score"));
            data.setPro(rs.getFloat("pro"));
            dataList.add(data);
        }
        connect.Close();
        return dataList;
    }
    
    public ArrayList<KhoaLuanDTO> getAllData() throws Exception
    {
        ResultSet rs = connect.Select("ProData");
        return getData(rs);
    }
    
    public void UpdateIndexOfTuple(KhoaLuanDTO tuple)throws Exception
    {
        HashMap<String, Object> map = new HashMap<String,Object>();
        map.put("indexOfTuple", tuple.getIndexOfTuple());
        connect.Update("ProData", map, "[index] = " + tuple.getIndex());
        connect.Close();
    }
    
    public void InsertExclusiveTable(int indexOfTuple, int exclusiveTuple) throws Exception
    {
        HashMap<String, Object>map = new HashMap<String,Object>();
        map.put("indexOfTuple", indexOfTuple);
        map.put("exclusiveTuple", exclusiveTuple);
        connect.Insert("ExclusiveRule", map);
        connect.Close();
    }
    
    public ArrayList<ExclusiveRuleDTO> GetExclusiveRulesByIndex(int indexOfTuple) throws Exception
    {
        ResultSet rs = connect.Select("ExclusiveRule", "indexOfTuple = " + indexOfTuple);
        ArrayList<ExclusiveRuleDTO> exclusiveRules = new ArrayList<ExclusiveRuleDTO>();
        while(rs.next())
        {
            ExclusiveRuleDTO data= new ExclusiveRuleDTO();
            data.setIndexOfTuple(rs.getInt("indexOfTuple"));
            data.setExclusiveTuple(rs.getInt("exclusiveTuple"));
            exclusiveRules.add(data);
        }
        connect.Close();
        return exclusiveRules;  
    }
    
    public void DeleteExclusiveTable() throws Exception
    {
        connect.Delete("ExclusiveRule",null);
        connect.Close();
    }
    
    public void InsertInclusiveTable(int indexOfTuple, int inclusiveTuple) throws Exception
    {
        HashMap<String, Object>map = new HashMap<String,Object>();
        map.put("indexOfTuple", indexOfTuple);
        map.put("inclusiveTuple", inclusiveTuple);
        connect.Insert("InclusiveRule", map);
        connect.Close();
    }
    
    public ArrayList<InclusiveRuleDTO> GetInclusiveRulesByIndex(int indexOfTuple) throws Exception
    {
        ResultSet rs = connect.Select("InclusiveRule", "indexOfTuple = " + indexOfTuple);
        ArrayList<InclusiveRuleDTO> inclusiveRules = new ArrayList<InclusiveRuleDTO>();
        while(rs.next())
        {
            InclusiveRuleDTO data= new InclusiveRuleDTO();
            data.setIndexOfTuple(rs.getInt("indexOfTuple"));
            data.setInclusiveTuple(rs.getInt("inclusiveTuple"));
            inclusiveRules.add(data);
        }
        connect.Close();
        return inclusiveRules;  
    }
    
    public void DeleteInclusiveTable() throws Exception
    {
        connect.Delete("InclusiveRule",null);
        connect.Close();
    }
}
   
