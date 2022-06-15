/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewmodel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import model.Obstacle;
import model.Player;
import model.TableExperience;

/**
 *	Filename	= PointHandler.java
 *	Author		= Cahya Gumilang
 *      Email           = cahya.gumilang@upi.edu
 *	Date		= 2022-06-14 
 *	Deskripsi 	= viewmodel untuk Pemrosesan Point pada Game
 */
public class PointHandler {
    int adapt = 0;
    int fall = 0;
    
    public PointHandler() {
    }
    
    public void render(Graphics g){
        // menampilkan point di layar
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 125, 75);
        g.setColor(Color.BLACK);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 20)); 
        g.drawString("Adapt: " + adapt, 20, 30);
        g.drawString("Fall: " + fall, 20, 60);
    }
    
    public void countPoint(Player player, ArrayList<Obstacle> obstacles, boolean isFall){
        // mengitung point dari obstacle yang telah dilewati
        for(Obstacle obstacle: obstacles){
            if(obstacle.getPoint() <= 0){
                if(isFall) fall += obstacle.getPoint();
                continue;
            }
            if(obstacle.getX()+obstacle.getWidth() >= player.getX()){
                // point dihitung dari obstacle yang sisi sebelah kanannya di lewati oleh player
                if(!isFall && !obstacle.isPointCounted())
                    // jika player fall, maka point tidak dihitung
                    // tapi tetap tandai agar tidak dihitung ke depannya
                    adapt += obstacle.getPoint();
                obstacle.setPointCounted(true);
            }else{
                return;
            }
        }
    }
    
    public void uploadPoint(String username) throws Exception{
        // upload point ke database
        TableExperience tp = new TableExperience();
        tp.insertUpdatePoint(username, adapt, fall);
    }

    public int getAdapt() {
        return adapt;
    }

    public int getFall() {
        return fall;
    }
    
    
}
