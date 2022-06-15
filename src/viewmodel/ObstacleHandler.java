/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewmodel;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import model.Obstacle;
import static model.GameOption.*;

/**
 *	Filename	= ObstacleHandler.java
 *	Author		= Cahya Gumilang
 *      Email           = cahya.gumilang@upi.edu
 *	Date		= 2022-06-13 
 *	Deskripsi 	= viewmodel untuk pemrosesan Obstacle pada Game
 */
public class ObstacleHandler {
    // jumlah maks obstacle yang ada di game
    private final int MAX_OBSTACLE = 20;
    
    private final int GAP_WIDTH = 52; // minimal lebar celah
    private final int MIN_Y = 100; // minimal y dari balok (menentukan maksimal tinggi balok)
    private final int WIDTH = 50; // lebar balok
    private final int Y_HEIGHT_LIMIT = GAME_HEIGHT - 50; // posisi alas balok
    
    private int obstacleNumber = 0; // jumlah obstacle yang ada
    
    private final ArrayList<Obstacle> obstacles = new ArrayList<>(); // Obstacle yang ada
    
    private final Random rand = new Random();
    
    public ObstacleHandler() {
        // membuat lantai nya saat game dimulai
        makeFloor();
    }
    
    private void makeFloor(){
        // Fall zone (jika kena akan mengurangi point)
        Obstacle floor = new Obstacle(0-GAME_WIDTH/2, GAME_HEIGHT - 50, GAME_WIDTH, 50, FALL_POINT);
        // Safe zone (sebagai starting zone dari player)
        Obstacle floor_safe = new Obstacle(GAME_WIDTH/2, GAME_HEIGHT - 50, GAME_WIDTH/2, 50, 0);
        obstacles.add(floor);
        obstacles.add(floor_safe);
        obstacleNumber += 2;
    }
    
    public void updateObstacle(){
        // update data obstacle
        Iterator<Obstacle> itr = obstacles.iterator();
        while(itr.hasNext()){
            Obstacle ob = itr.next();
            // menghapus obstacle yang keluar ujung layar kanan
            if(ob.getX() > GAME_WIDTH){
                itr.remove();
                obstacleNumber--;
            }else{
                // update posisi obstacle
                updatePos(ob);
                ob.updateHitbox();
            }
        }
    }
    
    public void renderObstacle(Graphics g){
        // menampilkan semua obstacle
        for (Obstacle ob : obstacles) {
            render(g, ob);
        }
    }
     
    public void addObstacle(){
        // menambah obstacle jika masih kurang dari jumlah maksimal
        if(obstacleNumber < MAX_OBSTACLE){
            float x = 0;
            // merandom tinggi dari obstacle
            float y = getRandomNumberUsingNextInt(MIN_Y, Y_HEIGHT_LIMIT - 50);
            if(obstacleNumber > 1){
                // menentukan luar celah
                float gapNumber = getRandomNumberUsingNextInt(1, 4);
                if(obstacleNumber == 2) gapNumber = 1;
                // menentukan posisi x
                x =  obstacles.get(obstacles.size() - 1).getX() - (gapNumber*GAP_WIDTH);
            }
            // tambahkan obstacle
            Obstacle obstacle = new Obstacle(x, y, WIDTH, (int) (Y_HEIGHT_LIMIT - y), ADAPT_POINT);
            obstacles.add(obstacle);
            obstacleNumber++;
        }
    }
    
    private void render(Graphics g, Obstacle o) {
        // menampilkan obstacle
        g.setColor(o.getColor());
        g.fillRect((int)o.getX(), (int)o.getY(), o.getWidth(), o.getHeight());
    }
    
    private void updatePos(Obstacle o){
        // mengupdate posisi obstacle
        // akan bergeser ke kanan secepat GAME_SPEED 
        // untuk floor tidak akan ikut bergerak kecuali safe zone masih ada di layar
        if(o.getPoint() > 0 || o.getPoint() == 0 || (o.getPoint() < 0 && o.getX()<0))
            o.setX(o.getX() + GAME_SPEED);
    }
    
    private float getRandomNumberUsingNextInt(int min, int max) {
        return rand.nextInt(max - min) + min;
    }

    public ArrayList<Obstacle> getObstacles() {
        return obstacles;
    }
    
}
