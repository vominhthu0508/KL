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
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author DLT
 */
public class KhoaLuanDAL {
    MyConnectUnit connect = new MyConnectUnit("C:/Users/DLT/Documents/NetBeansProjects/KhoaLuan/src/Database/database_khoaluan.db");
    
    public ArrayList<KhoaLuanDTO> getData(ResultSet rs) throws SQLException, Exception
    {
        ArrayList<KhoaLuanDTO> dataList = new ArrayList<KhoaLuanDTO>();
        while(rs.next())
        {
            KhoaLuanDTO data= new KhoaLuanDTO();
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
    
}
   
