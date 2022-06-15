/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.awt.Color;
import java.util.Random;

/**
 *	Filename	= Obstacle.java
 *	Author		= Cahya Gumilang
 *      Email           = cahya.gumilang@upi.edu
 *	Date		= 2022-06-11 
 *	Deskripsi 	= model untuk menampung data obstacle 
 */
public class Obstacle extends GameObject{
    // boolean point dari obstacle ini sudah dihitung
    private boolean pointCounted = false;
    // point yang didapat saat melewati/berpijak di obstacle ini
    /**
     * point = 0  : Safe Zone
     * point < 0  : Lantai (Fall)
     * point > 0  : Balok (Adapt)
     */
    private final int point;
    
    Random rand = new Random();
    // warna obstacle
    Color color;
    
    public Obstacle(float x, float y, int width, int height, int point) {
        super(x, y, width, height);
        this.point = point;
        // membuat warna obstacle
        setColorObstacle();
    }
    
    /**
     * membuat warna obstacle berdasarkan point
     * point = 0 merupakan safezone (berwarna hijau)
     * point < 0 merupakan Lantai (berwarna merah)
     * point > 0 merupakan Balok (berwarna random dari biru sampai ungu)
     */
    private void setColorObstacle(){
        if(point == 0) color = Color.GREEN;
        else if(point  < 0) color = Color.RED;
        else{
            int r =  rand.nextInt(0, 255);
            color = new Color(r, 0, 255);
        }
    }

    public boolean isPointCounted() {
        // mengembalikan boolean apakah obstacle sudah dihitung pointnya
        return pointCounted;
    }

    public void setPointCounted(boolean pointCounted) {
        this.pointCounted = pointCounted;
    }

    public int getPoint() {
        // mengembalikan point obstacle
        return point;
    }
    
    public Color getColor(){
        // mengembalikan warna obstacle
        return this.color;
    }
    
    
}
