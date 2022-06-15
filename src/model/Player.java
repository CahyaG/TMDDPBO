package model;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *	Filename	= Player.java
 *	Author		= Cahya Gumilang
 *      Email           = cahya.gumilang@upi.edu
 *	Date		= 2022-06-10 
 *	Deskripsi 	= model untuk menampung data player
 */
public class Player extends GameObject{
    public static Image skin;
    
    public static final float PLAYER_SPEED = 5.0f;
    public static final float JUMP_STRENGTH = 20.0f;
    public static final float GRAVITY = 0.3f;
    
    /* Movement State */
    // boolean untuk menentukan arah pergerakan player (bisa bergerak lebih dari 1 arah dalam 1 waktu)
    private boolean moveLeft;
    private boolean moveUp;
    private boolean moveRight;
    /* Position State*/
    // state player sedang di udara
    private boolean inAir = true;
    // state player sedang di ujung layar kiri
    private boolean inCorner = false;
    
    
    public Player(int x, int y) {
        super(x, y, 50, 50);
        // membuat Image skin dari file yang ditentukan
        skin = Toolkit.getDefaultToolkit().getImage("resources/image/happy.png");
    }
    
    /* 
    *   bound adalah hitbox untuk mendeteksi collision dan dibedakan menjadi 4
    */
    // hitbox bawah
    public Rectangle getBoundBottom(){
        return new Rectangle((int) (x+(width/2)-(width/4)), (int) (y+(height/2)), width/2, height/2);
    }
    // hitbox atas
    public Rectangle getBoundTop(){
        return new Rectangle((int) (x+(width/2)-(width/4)), (int) (y), width/2, height/2);
    }
    // hitbox kanan
    public Rectangle getBoundRight(){
        return new Rectangle((int) x+width-5, (int)y + 5, 5, height-10);
    }
    // hitbox kiri
    public Rectangle getBoundLeft(){
        return new Rectangle((int) x, (int)y + 5, 5, height-10);
    }
    
    /* Getter dan Setter State Movement*/
    public boolean isMoveLeft() {
        return moveLeft;
    }

    public void setMoveLeft(boolean moveLeft) {
        this.moveLeft = moveLeft;
    }

    public boolean isMoveUp() {
        return moveUp;
    }

    public void setMoveUp(boolean moveUp) {
        this.moveUp = moveUp;
    }

    public boolean isMoveRight() {
        return moveRight;
    }

    public void setMoveRight(boolean moveRight) {
        this.moveRight = moveRight;
    }
    
    public boolean isInCorner() {
        return inCorner;
    }

    public void setInCorner(boolean inCorner) {
        this.inCorner = inCorner;
    }

    public boolean isInAir() {
        return inAir;
    }

    public void setInAir(boolean inAir) {
        this.inAir = inAir;
    }
    
}
