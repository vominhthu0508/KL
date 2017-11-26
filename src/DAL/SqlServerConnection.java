/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.microsoft.sqlserver.jdbc.*;
/**
 *
 * @author DLT
 */
public class SqlServerConnection {
    Connection connect = null;
    Statement statement = null;
    ResultSet result =null;  
    String databaseName = null;
    String URL = null;
    public SqlServerConnection (String databaseName){
        this.databaseName = databaseName;
    }
    
    public Connection getConnect() throws Exception{
        try{
            SQLServerDataSource ds = new SQLServerDataSource();
            ds.setIntegratedSecurity(true);
            ds.setServerName("localhost");
            //ds.setPortNumber(1433); 
            ds.setDatabaseName("KhoaLuan");
            connect = ds.getConnection();
            }catch(java.sql.SQLException e){
                throw new Exception("Không thể kết nối đến database");
        }
        return this.connect;
    }
    protected Statement getStatement() throws Exception{
        if(this.statement == null ? true :this.statement.isClosed()){
            this.statement = this.getConnect().createStatement();
        }
        return this.statement;
    }
    public ResultSet excuteQuery(String Query) throws Exception{
        try{
            // Thực thi câu lệnh.
            this.result = getStatement().executeQuery(Query);
        }
        // Nếu không thành công ném lỗi ra ngoài.
        catch(Exception e){
            throw new Exception("Error: " +e.getMessage() +" - "+Query);
        }
        // Trả kết quả ra ngoài.
        return this.result;
    }
    public int executeUpdate(String Query) throws Exception{
        //Khai báo biến int lưu trữ kết quả tình trạng thực thi câu lệnh Query.
        int res =Integer.MIN_VALUE;
        try{
            //Thực thi câu lệnh.
            res = getStatement().executeUpdate(Query);
        }
        //Nếu không thành công ném lỗi ra ngoài.
        catch(Exception e){
            throw new Exception("Error: " +e.getMessage() +" - "+Query);}
        finally{
            //Đóng kết nối.
            this.getClose();
        }
        //Trả kết quả ra ngoài.
        return res;
    }
    public void getClose() throws SQLException
    {
        if(this.result!=null && ! this.result.isClosed()){
            this.result.close();
            this.result = null;
        }
        // Nếu statement chưa đóng. Đóng statement.
        if(this.statement!=null && !this.statement.isClosed()){
            this.statement.close();
            this.statement = null;
        }
        // Nếu connection chưa đóng. Đóng connection.
        if(this.connect!=null && !this.connect.isClosed()){
            this.connect.close();
            this.connect =null;
        }
    }
}
