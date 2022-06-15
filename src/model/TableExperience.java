/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *	Filename	= TableExperience.java
 *	Author		= Cahya Gumilang
 *      Email           = cahya.gumilang@upi.edu
 *	Date		= 2022-06-14 
 *	Deskripsi 	= model untuk mengakses tabel experienxe 
 */
public class TableExperience extends DB{
    public TableExperience() throws Exception, SQLException{
        super();
    }
    
    public void insertUpdatePoint(String username, int adapt, int fall){
        // Method mengubdate score pada table exprerience atau menambah score baru
        try {
            String query = "UPDATE texperiences SET adapt = adapt + " + adapt + ", fall = fall + " + fall + " WHERE username = '" + username + "'";
            // eksekusi query untuk update data
            if(createUpdate(query) == 0){
                // jika tidak ada yang terupdate, buat data baru
                query = "INSERT INTO texperiences VALUES(NULL, '" + username + "', " + Integer.toString(adapt) + ", " + Integer.toString(fall) + ")";
                createUpdate(query);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    
    public void getAll(){
        // Mengeksekusi query untuk mengambil semua data pada tabel experience
        String query = "SELECT * from texperiences order by (adapt + fall) DESC";
        try {
            createQuery(query);
        } catch (Exception ex) {
            Logger.getLogger(TableExperience.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
