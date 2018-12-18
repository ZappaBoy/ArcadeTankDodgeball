package GUI;

import javax.swing.*;


public class ATD_Frame extends JFrame {


    private static final int default_width = 800;
    private static final int default_height = 800;

    Start_Panel start_panel;
    Game_Panel game_panel;
    Active_Level active_level;



    public ATD_Frame() {


        this.setSize(default_width, default_height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setLayout(null);

        start_panel = new Start_Panel(this);
        game_panel = new Game_Panel(this);
        active_level = new Active_Level(this);

        start_panel.setLocation(0, 0);
        game_panel.setLocation(0, 0);
        active_level.setLocation(0,0);

        this.getContentPane().add(active_level);
        this.getContentPane().add(start_panel);
        this.getContentPane().add(game_panel);


        start_panel.setVisible(true);
        game_panel.setVisible(false);
        active_level.setVisible(false);


    }



}