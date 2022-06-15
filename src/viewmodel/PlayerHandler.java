/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewmodel;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import model.Obstacle;
import model.Player;
import static model.GameOption.*;

/**
 *	Filename	= PlayerHandler.java
 *	Author		= Cahya Gumilang
 *      Email           = cahya.gumilang@upi.edu
 *	Date		= 2022-06-14 
 *	Deskripsi 	= viewmodel untuk Pemrosesan Player pada Game
 */
public class PlayerHandler {
    private Player player;
    private Game game;
    
    private float xSpeed = 0;
    private float ySpeed = 0;
    
    public PlayerHandler(Game game) {
        // membuat objek player nya
        this.player = new Player(GAME_WIDTH - 400, 200);
        this.game = game;
    }
    
    public void updatePlayer(ArrayList<Obstacle> AOb){
        // mengupdate player
        updatePos(AOb);
        // mengecek apakah player masih ada di layar
        checkPlayerPosStillInGame();
        player.updateHitbox();
    }
    
    public void renderPlayer(Graphics g){
        // manmpilkan player ke layar
        g.drawImage(Player.skin, (int)player.getX(), (int)player.getY(), player.getWidth(), player.getHeight(), null);
        
//        Rectangle rect = player.getBoundBottom();
//        g.drawRect(rect.x, rect.y, rect.width, rect.height);
//        rect = player.getBoundLeft();
//        g.drawRect(rect.x, rect.y, rect.width, rect.height);
//        rect = player.getBoundRight();
//        g.drawRect(rect.x, rect.y, rect.width, rect.height);
//        rect = player.getBoundTop();
//        g.drawRect(rect.x, rect.y, rect.width, rect.height);
    }
    
    private void updatePos(ArrayList<Obstacle> AOb){
        // update posisi player (menghandle pergerakan player)
        
        // saat tidak digerakan, maka player akan terseret ke kanan
        if(player.isMoveLeft()&& player.isMoveRight()|| !player.isMoveLeft() && !player.isMoveRight())  xSpeed = GAME_SPEED;
        else if(player.isMoveLeft()) xSpeed -= Player.PLAYER_SPEED; // digerakan ke kiri
        else if(player.isMoveRight()) xSpeed += Player.PLAYER_SPEED; // digerakan ke kanan
        
        // membatasi perubahan x per update
        if(xSpeed > 5) xSpeed = 5;
        else if(xSpeed < -5) xSpeed = -5;
        
        // loncat
        if(player.isMoveUp() && !player.isInAir()){
            // hanya bisa dilakukan jika player tidak sedang di udara
            player.setInAir(true);
            ySpeed -= Player.JUMP_STRENGTH;
        }
        
        if(!player.isInAir() && !isOnFloor(AOb)){
             player.setInAir(true);
        }
        
        // menurunkan player saat sedang di udara
        if(player.isInAir()){
            ySpeed += Player.GRAVITY;
        }
        
        // cek collision player untuk setiap obstacle
        for(Obstacle ob : AOb){
            // bottom collision
            if(player.getBoundBottom().intersects(ob.getHitbox())){
                // player tidak berada di udara
                player.setInAir(false);
                ySpeed = 0; // jangan gerakan y
                
                // memindahkan player tepat ke atas obejek yang collided 
                player.setY(ob.getHitbox().y - player.getHeight());
                // jika terjadi bottom collision, update score
                if(ob.getPoint() < 0) this.game.getPointHandler().countPoint(player, AOb, true);
                else this.game.getPointHandler().countPoint(player, AOb, false);
            }
            if(player.getBoundRight().intersects(ob.getHitbox())){
                // roight collision
                xSpeed = GAME_SPEED;
                // update posisi player ke tepat di sebelah kiri objek yang collided
                player.setX(ob.getHitbox().x - player.getWidth() - 1);
            }
            if(player.getBoundLeft().intersects(ob.getHitbox())){
                // left collision
                xSpeed = GAME_SPEED;
                // update posisi player ke tepat di sebelah kanan objek yang collided
                player.setX(ob.getHitbox().x + ob.getHitbox().width + 1);
            }
            if(player.getBoundTop().y < 0){
                // top collision
                player.setY(0);
                ySpeed = 0;
            }
            if(player.getBoundLeft().x < 0){
                // mencegah player keluar dari layar sebelah kiri
                player.setX(0);
            }
        }
        
        // update posisi
        player.setX(player.getX() + xSpeed);
        player.setY(player.getY() + ySpeed);
    }
    
    private boolean isOnFloor(ArrayList<Obstacle> AOb){
        // mengecek apakah player berada di lantai (bottom collision dengan minimal 1 Obstacle)
        Rectangle rect;
        for(Obstacle ob : AOb){
            rect = player.getBoundBottom();
            rect.y += 1;
            if(rect.intersects(ob.getHitbox()))return true;
        }
        return false;
    }
    
    private void checkPlayerPosStillInGame(){
        // mengecek apakah player masih berada di layar
        if(player.getX() > GAME_WIDTH - 13){
            this.game.stopGame();
        }
    }
    
    public void setPlayerMoveUp(boolean moveUp){
        player.setMoveUp(moveUp);
    }
    public void setPlayerMoveLeft(boolean moveLeft){
        player.setMoveLeft(moveLeft);
    }
    public void setPlayerMoveRight(boolean moveRight){
        player.setMoveRight(moveRight);
    }
}
