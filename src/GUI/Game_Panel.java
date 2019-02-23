package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import Enemies.Enemy;
import GameLogic.Enemy_Shot;
import GameLogic.Shot;
import Player.Player;
import Utility.Resources;


public class Game_Panel extends JPanel {

    public Player player;


    public int charger_capacity = 4;
    public int shotted_bullet = 0;
    public Shot[] charger;
    public threadPlayerBullet[] chargerThread;
    public boolean inGame = true;

    private boolean firstGame = true;

    private ATD_Frame frame;

    public int enemy_tank_hitted = 0;
    public int missed_shot = 0;
    public int used_charger = 0;
    public int used_bullet = 0;
    public boolean readyToshot = true;
    public boolean firstShot = true;


    public Thread thread_ShotDelay = new shotDelay();

    public boolean playerisHitted = false;

    public Thread thread_PlayerHitted = new player_hitted();

    public boolean lose = false;
    public boolean win = false;

    private Image game_panel_img;

    private Image ricarica_img;

    private boolean ricarica = false;

    private int ricarica_img_x = 800;
    private int ricarica_img_y = 800;
    private int ricarica_img_width = 0;
    private int ricarica_img_height = 0;

    private Active_Level restore;

    Game_Panel(ATD_Frame pframe) {

        frame = pframe;
    }


    /**
     *     Override metodo paintComponent
     *     Disegnare immagini di gioco
     */
    @Override
    protected void paintComponent(Graphics g) {

        g.drawImage(game_panel_img, 0, 0, 800, 800, null);

        g.drawImage(player.tank_img, player.x, player.y, player.getWidth(), player.getHeight(), null);

        for (int i = 0; i < charger_capacity; i++){

        g.drawImage(charger[i].bullet_img, charger[i].x_shot, charger[i].y_shot, charger[i].width_shot, charger[i].height_shot, null);

        }

        frame.active_level.paintComponents(g);

        g.drawImage(ricarica_img, ricarica_img_x, ricarica_img_y, ricarica_img_width, ricarica_img_height, null);




    }


    /**
     * Comandi Tastiera
     */
    public class Keyboard_Input extends KeyAdapter {




        @Override
        public void keyPressed(KeyEvent e) {

            int x ;
            int y ;
            int left_collider;
            int right_collider;
            int up_collider;
            int down_collider;

            left_collider = 0;
            right_collider = 330;
            up_collider = 0;
            down_collider = 700;

            if (e.getKeyCode() == KeyEvent.VK_LEFT) {


                x = player.x - player.speed;

                if (x > left_collider) {

                    player.leftMove();
                }
            }

            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {


                win = true;

                inGame = false;

                statusGamechange(false);




                x = player.x + player.speed;

                if (x < right_collider) {

                    player.rightMove();
                }
            }

            if (e.getKeyCode() == KeyEvent.VK_UP) {


                y = player.y + player.speed;

                if (y > up_collider) {

                    player.upMove();
                }
            }

            if (e.getKeyCode() == KeyEvent.VK_DOWN) {


                y = player.y + player.speed;

                if (y < down_collider) {

                    player.downMove();
                }
            }

            if (e.getKeyCode() == KeyEvent.VK_SPACE) {


                if (firstShot){

                    thread_ShotDelay.start();

                    firstShot = false;


                }

                if (readyToshot) {

                    readyToshot = false;

                    if (shotted_bullet < charger_capacity) {

                        chargerThread[shotted_bullet] = new threadPlayerBullet();

                        chargerThread[shotted_bullet].start();




                    }
                }
            }

            repaint();
        }


        @Override
        public void keyReleased(KeyEvent e) {

        }

        @Override
        public void keyTyped(KeyEvent e) {

            // Funzione tasto Escape
            if (e.getKeyChar() == 27) {

                System.exit(0);
            }
        }
    }

    /**
     *          Thread colpo Player
     */
    private class threadPlayerBullet extends Thread implements Runnable{

        @Override
        public void run() {





            if (shotted_bullet < charger_capacity){

                int active_shot = shotted_bullet;

                shotted_bullet++;

                used_bullet++;



                if (shotted_bullet >= charger_capacity){
                    Thread ricaricaThread = new reload();

                    ricaricaThread.start();
                }

                charger[active_shot] = new Shot(player);

                if (inGame){



                }

                while(!charger[active_shot].shotted(true) && (enemy_tank_hitted < frame.active_level.enemiesNumber) && !charger[active_shot].hit && inGame) {


                    frame.game_panel.repaint();

                    int i = 0;

                    while (i < frame.active_level.enemiesNumber && !charger[active_shot].hit ) {

                        if (frame.active_level.levels[frame.active_level.activeLevel].enemies[i].isalive && enemyHit(frame.active_level.levels[frame.active_level.activeLevel].enemies[i], charger[active_shot])) {



                            missed_shot --;

                            charger[active_shot].shotHit();

                            if (frame.active_level.levels[frame.active_level.activeLevel].enemies[i].life >= 0){

                                frame.active_level.levels[frame.active_level.activeLevel].enemies[i].isHitted(true);



                            }

                            if (frame.active_level.levels[frame.active_level.activeLevel].enemies[i].life == 0){

                                enemy_tank_hitted++;

                            }


                                if (enemy_tank_hitted == frame.active_level.enemiesNumber  ) {


                                    win = true;


                                    inGame = false;

                                    statusGamechange(false);

                                    JOptionPane.showMessageDialog(frame, "You hit all enemy tanks!");

                                }

                        } else {

                            i++;
                        }



                    }

                    try {
                            Thread.sleep(40);

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }

                missed_shot ++;


            }
        }
    }




    /**
     *     Thread ricarica colpi caricatore
     */
    public class reload extends Thread implements Runnable{

        @Override
        public void run() {


            ricarica = true;
            ricarica_img_y = 10;
            ricarica_img_height = 75;
            ricarica_img_width = 320;
            ricarica_img_x = 400 - ricarica_img_width/2;


            Thread changeRechargeImageThread = new changeReloadImage();

            changeRechargeImageThread.start();

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            shotted_bullet = 0;

            used_charger++;


            ricarica_img_x = 800;
            ricarica_img_y = 800;
            ricarica_img_height = 0;
            ricarica_img_width = 0;

            ricarica = false;


        }
    }

    /**
     *     Thread ricarica colpi caricatore
     */
    public class changeReloadImage extends Thread implements Runnable{

        @Override
        public void run() {

            boolean change = true;

            while (ricarica) {

                if (change) {
                    ricarica_img = Resources.getImage("/Resources/Ricarica_img_0.png");

                }else {


                    ricarica_img = Resources.getImage("/Resources/Ricarica_img_1.png");
                }

                change = !change;
                try {
                    Thread.sleep(400);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }



    /**
     *     thread delay tra colpi caricatore
     */
    public class shotDelay extends Thread implements Runnable{

        @Override
        public void run() {

            while (inGame){


                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                readyToshot = !readyToshot;
            }

        }
    }


    /**
     *     Metodo nemico colpito
     */

    public boolean enemyHit(Enemy enemy, Shot shot) {

        boolean isHitted = false;

        if ((shot.x_shot + shot.width_shot/2  >= enemy.x && shot.x_shot <= enemy.x + enemy.width) && (shot.y_shot + shot.height_shot/2 >= enemy.y && shot.y_shot <= enemy.y + enemy.height - 25 )){

            isHitted = true;
        }

        return isHitted;
    }


    /**
     *     Metodo player colpito
     */

    public boolean playerHit(Player player, Enemy_Shot enemy_shot) {

        boolean isHitted = false;

        if ((enemy_shot.x_shot >= player.x && enemy_shot.x_shot <= player.x + player.getWidth()) && (enemy_shot.y_shot + enemy_shot.height_shot/2 >= player.y && enemy_shot.y_shot <= player.y + player.getHeight() - 25 )){

            isHitted = true;
        }

        return isHitted;
    }


    private void restoreLevel() {


         restore = new Active_Level(frame);

            //opzione gioco riparte dal primo livello
        frame.active_level = restore;

    }



    private class changeFinalImage extends Thread implements Runnable {

        @Override
        public void run() {
            boolean change = true;

            while (frame.active_level.gameLevel == frame.active_level.levels_number && inGame) {

                if (change) {

                    frame.game_panel.game_panel_img = Resources.getImage("/Resources/Level_" + 4 + "_Background_" + 0 + ".png");

                } else {

                    frame.game_panel.game_panel_img = Resources.getImage("/Resources/Level_" + 4 + "_Background_" + 1 + ".png");

                }

                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                change = !change;

            }
        }
    }

    /**
     *     Metodo Player colpito
     */

    public class player_hitted extends Thread implements Runnable {

        @Override
        public void run() {


            while (inGame) {


                try{
                    for (int i = 0; i < frame.active_level.levels[frame.active_level.activeLevel].enemies_number; i++) {

                        try {
                            for (int j = 0; j < frame.active_level.levels[frame.active_level.activeLevel].enemies[i].charger_capacity; j++) {


                                if (!playerisHitted) {


                                    if (!frame.active_level.levels[frame.active_level.activeLevel].enemies[i].enemyCharger[j].hit) {


                                        if (playerHit(player, frame.active_level.levels[frame.active_level.activeLevel].enemies[i].enemyCharger[j])) {



                                            frame.active_level.levels[frame.active_level.activeLevel].enemies[i].enemyCharger[j].hit = true;

                                            playerisHitted = true;

                                            lose = true;

                                            inGame = false;

                                            statusGamechange(false);
                                        }
                                    }
                                }
                            }
                        }catch ( java.lang.ArrayIndexOutOfBoundsException exception){

                        }
                    }
                }catch ( java.lang.ArrayIndexOutOfBoundsException exception){

                }

                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            }

        }
    }
    /**
     *     Metodo cambio stato di gioco
     */

    public void statusGamechange (boolean isGaming){

        if (isGaming){

            inGame = true;

            enemy_tank_hitted = 0;

            firstShot = true;

            readyToshot = true;

            shotted_bullet = 0;

            if (frame.active_level.gameLevel == 1 && firstGame){

                firstGame = false;



                this.addKeyListener(new Keyboard_Input());
                this.setSize(800, 800);



            }

            player = new Player();

            charger = new Shot[charger_capacity];

            chargerThread = new threadPlayerBullet[charger_capacity];

            if (frame.active_level.gameLevel < frame.active_level.levels_number){

                this.game_panel_img = frame.active_level.levels[frame.active_level.activeLevel].getlevel_img();

            } else {

                Thread changeFinalImageThread = new changeFinalImage();

                changeFinalImageThread.start();
            }

            ricarica_img = Resources.getImage("/Resources/Ricarica_img_0.png");

            player.color = frame.settings_panel.color;

            player.playerImageinit();

            lose = false;

            win = false;

            playerisHitted = false;

            frame.active_level.enemiesLogicStart(inGame);

            for (int i = 0; i < charger_capacity; i++){

                charger[i] = new Shot(player);
                chargerThread[i] = new threadPlayerBullet();

            }

            thread_PlayerHitted = new player_hitted();

            thread_PlayerHitted.start();

            thread_ShotDelay = new shotDelay();



        }else {

            inGame = false;

            this.frame.inGame(false);

            if (lose){

                restoreLevel();


                JOptionPane.showMessageDialog(frame, "You lose, try again!");

            } else {

                if (player.level < player.maxLevel){

                    player.levelUp();

                }


                if (frame.active_level.gameLevel >= frame.active_level.levels_number ){

                    JOptionPane.showMessageDialog(frame, "You finish all levels. Congratulation!");

                    restoreLevel();



                }else{

                    frame.active_level.nextLevel();


                }



            }
            frame.active_level.enemiesLogicStart(inGame);

        }
    }
}


