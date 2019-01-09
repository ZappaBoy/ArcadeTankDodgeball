package GUI;

import javax.swing.*;


public class ATD_Frame extends JFrame {


    private static final int default_width = 800;
    private static final int default_height = 800;

    Start_Panel start_panel;
    Game_Panel game_panel;
    Active_Level active_level;
    Credits_Panel credits_panel;

    private boolean inGame = false;



    public ATD_Frame() {


        this.setSize(default_width, default_height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setLayout(null);

        start_panel = new Start_Panel(this);
        game_panel = new Game_Panel(this);
        active_level = new Active_Level(this);
        credits_panel = new Credits_Panel(this);

        start_panel.setLocation(0, 0);
        game_panel.setLocation(0, 0);
        active_level.setLocation(0,0);
        credits_panel.setLocation(0, 0);

        this.getContentPane().add(active_level);
        this.getContentPane().add(start_panel);
        this.getContentPane().add(game_panel);
        this.getContentPane().add(credits_panel);


        start_panel.setVisible(true);
        game_panel.setVisible(false);
        active_level.setVisible(false);
        credits_panel.setVisible(false);


    }

    public void inGame(boolean ingame){


        if (ingame){

            this.start_panel.setVisible(false);
            this.game_panel.setVisible(true);
            this.active_level.setVisible(true);
            this.game_panel.setFocusable(true);
            this.game_panel.requestFocus();

            inGame = true;


            //startare delay con il pannello di gioco
            if (active_level.activeLevel == 1 && !game_panel.lose){
                game_panel.thread_Delay.start();

            }

            this.game_panel.playerisHitted = false;

            active_level.enemiesLogicStart(inGame);

            game_panel.statusGamechange(inGame);

            Thread threadGameRepaint = new repaintThread();
            threadGameRepaint.start();

        }else{

            this.start_panel.setVisible(true);
            this.game_panel.setVisible(false);
            this.active_level.setVisible(false);

            inGame = false;

            active_level.enemiesLogicStart(inGame);

        }
    }


    private class repaintThread extends Thread implements Runnable{

        @Override
        public void run() {

            while (true){

                game_panel.repaint();

                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

}