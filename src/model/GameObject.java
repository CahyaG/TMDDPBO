/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.awt.Rectangle;

/**
 *	Filename	= GameObject.java
 *	Author		= Cahya Gumilang
 *      Email           = cahya.gumilang@upi.edu
 *	Date		= 2022-06-11 
 *	Deskripsi 	= model abstract untuk menampung data objek game
 */
public abstract class GameObject {

    // koordinat object
    protected float x;
    protected float y;
    // luas objek
    protected int width;
    protected int height;
    
    // hitbox berbentuk kotak sebagai pendeteksi collision
    protected Rectangle hitbox;
    
    public GameObject(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        
        // buat hitbox dari koordinat dan 
        hitbox = new Rectangle((int)x, (int)y, width, height);
    }
    
    public void updateHitbox(){
        hitbox.x = (int) x;
        hitbox.y = (int) y;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
