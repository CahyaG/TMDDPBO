/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *	Filename	= DB.java
 *	Author		= Cahya Gumilang
 *      Email           = cahya.gumilang@upi.edu
 *	Date		= 2022-06-14 
 *	Deskripsi 	= model untuk mengakses basis data
 */
public class DB {
    private String ConAddress = "jdbc:mysql://localhost/db_tmd_cahya?user=root&password=";
    private Statement stmt = null;
    private ResultSet rs = null;
    private Connection conn = null;
    
    public DB() throws Exception, SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(ConAddress);
            conn.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
        } catch (SQLException es) {
            throw es;
        }
    }
    
    public void createQuery(String Query) throws Exception, SQLException {
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(Query);
            if(stmt.execute(Query)) {
                rs = stmt.getResultSet();
            }
        } catch (SQLException es) {
            throw es;
        }
    }
    
    public int createUpdate(String Query)throws Exception, SQLException {
        try {
            stmt = conn.createStatement();
            return stmt.executeUpdate(Query);
        } catch (SQLException es) {
            throw es;
        }
    }
    
    public ResultSet getResult() throws Exception {
        ResultSet Temp = null;
        try {
            return rs;
        } catch (Exception e) {
            return Temp;
        }
    }
    
    public void closeResult() throws Exception, SQLException {
        if(rs != null) {
            try {
                rs.close();
            }
            catch(SQLException es){
                rs = null;
                throw es;
            }
        }
        if(stmt != null) {
            try {
                stmt.close();
            } catch (SQLException es) {
                stmt = null;
                throw es;
            }
        }
    }
    
    public void closeConnection() throws Exception, SQLException {
        if(conn != null) {
            try {
                conn.close();
            }
            catch(SQLException es){
                conn = null;
            }
        }
    }
    
}
