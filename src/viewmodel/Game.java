/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewmodel;

import java.awt.Font;
import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import static model.GameOption.*;
import view.GameWindow;

/**
 *	Filename	= Game.java
 *	Author		= Cahya Gumilang
 *      Email           = cahya.gumilang@upi.edu
 *	Date		= 2022-06-10 
 *	Deskripsi 	= viewmodel untuk Pemrosesan jalannya Game
 */
public class Game extends JPanel implements Runnable{
    private Thread gameThread;
    private String username;
    
    private boolean running = false;
    private boolean started = false;
    private boolean gameOver = false;
    
    private final ObstacleHandler obs_handler;
    private final PlayerHandler player_handler;
    private final PointHandler point_handler;
    
    private GameWindow window;
    
    public Game(String username, GameWindow window){
        this.window = window;
        this.username = username;
        // instansiasi handler player
        this.player_handler = new PlayerHandler(this);
        // instansiasi handler Obstacle
        this.obs_handler = new ObstacleHandler();
        // instansiasi handler Point
        this.point_handler = new PointHandler();
    }
    
    public synchronized void StartGame(){
        // Menjalankan Thread Game
        gameThread = new Thread(this);
        gameThread.start();
    }
    
    public synchronized void stopGame(){
        // Menghentikan Game
        try {
            window.stopMusic();
            running = false;
            // upload Score
            point_handler.uploadPoint(username);
            JOptionPane.showMessageDialog(null, "Username: " + this.username + "\n" + "Adapt: " + this.point_handler.adapt + "\n" + "Fail: " + this.point_handler.fall, "GAME OVER", JOptionPane.INFORMATION_MESSAGE);
            gameOver = true;
            // menghancurkan window game
            window.destroy();
            // menunggu dulu sampai thread dari game ini mati
            gameThread.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void paint(Graphics g){
        // fungsi untuk menampilkan Graphics ke Game
        super.paint(g); // hapus component yang telah di gambar sebelumnya
        if(!running && !started){
            g.setFont(new Font("TimesRoman", Font.PLAIN, 20)); 
            g.drawString("Press Any Key To Start", GAME_WIDTH/2 - 120, GAME_HEIGHT/2 - 20);
        }
        // menampilkan objek player
        player_handler.renderPlayer(g);
        // menampilkan objek Obstacle
        obs_handler.renderObstacle(g);
        // menapilkan score
        point_handler.render(g);
    }
    
    public void updateGame(){
        // mengupdate objek-objek pada game
        obs_handler.addObstacle();
        player_handler.updatePlayer(obs_handler.getObstacles());
        obs_handler.updateObstacle();
    }
    
    
    @Override
    public void run() {
        // main game loop
        while(running){
            try {
                // update game dan tampilkan lagi ke layar
                updateGame();
                repaint();
                Thread.sleep(1000L/60L); // sleep agar game loop tidak terlalu cepat
            } catch (InterruptedException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public PlayerHandler getPlayerHandler(){
        return this.player_handler;
    }

    public PointHandler getPointHandler() {
        return point_handler;
    }
    
    public boolean isStarted(){
        return this.started;
    }
    
    public void setStarted(boolean started){
        this.started = started;
    }
    
    public boolean isRunning(){
        return this.running;
    }
    
    public void setRunning(boolean running){
        this.running = running;
    }

    public boolean isGameOver() {
        return gameOver;
    }
    
    
}
