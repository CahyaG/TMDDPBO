/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import viewmodel.KeyInputs;
import javax.swing.JFrame;
import viewmodel.Game;
import viewmodel.Sound;

/**
 *	Filename	= GameWindow.java
 *	Author		= Cahya Gumilang
 *      Email           = cahya.gumilang@upi.edu
 *	Date		= 2022-06-10 
 *	Deskripsi 	= Frame/Window dari game
 */
public class GameWindow extends JFrame{
    Game game;
    Menu menu;
    Sound music;
    public GameWindow(String username, Menu menu){
        super("The Survive Hop");
        this.menu = menu;
        // membuat panel yang menampilkan jalannya game
        game = new Game(username, this);
        addKeyListener(new KeyInputs(game));
        add(game);
        
        // mengatur ukuran dan lokasi frame game
        setSize(1280, 720);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // menjalankan musik Stage (Game Dimulai)
        music = new Sound(Sound.STAGE);
        music.play();
    }
    
    public void destroy(){
        // Menghapus Frame Game dan menampilkan kembali menu
        menu.setVisible(true);
        menu.updateTable();
        menu.playMusic();
        this.dispose();
    }
    
    public void stopMusic(){
        // menghentikan musik pada frame game
        music.stop();
    }
}
