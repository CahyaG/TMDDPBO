/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewmodel;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import model.TableExperience;

/**
 *	Filename	= PlayerHandler.java
 *	Author		= Cahya Gumilang
 *      Email           = cahya.gumilang@upi.edu
 *	Date		= 2022-06-14 
 *	Deskripsi 	= viewmodel untuk Pemrosesan Table Experience
 */
public class TableProcess {
    TableExperience tableExp;

    public TableProcess() {
        try {
            this.tableExp = new TableExperience();
        } catch (Exception ex) {
            Logger.getLogger(TableProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public DefaultTableModel getPlayerTable(){
        // mengambil semua data expperience mengembalikannya sebagai DefaultTableModel
        DefaultTableModel dataTabel = null;
        try{
            Object[] column = {"Username", "Adapt", "Fall"};
            dataTabel = new DefaultTableModel(null, column);
            
            this.tableExp.getAll();
            while(this.tableExp.getResult().next()){
                Object[] row = new Object[3];
                row[0] = this.tableExp.getResult().getString(2);
                row[1] = this.tableExp.getResult().getString(3);
                row[2] = this.tableExp.getResult().getString(4);
                dataTabel.addRow(row);
            }
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
        return dataTabel;
    }
}
