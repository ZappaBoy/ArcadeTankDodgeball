package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;

import Enemies.Enemy;
import GameLogic.Shot;
import Player.Player;
import Utility.Resources;


public class Game_Panel extends JPanel {

    public Player player;


    public int charger_capacity = 3;
    public int shotted_bullet = 0;
    public Shot[] charger;
    public threadPlayerBullet[] chargerThread;
    public boolean inGame = true;

    public int player_level = 1;

    private ATD_Frame frame;

    public int enemy_tank_hitted = 0;
    public int missed_shot = 0;
    public int used_charger = 0;
    public int used_bullet = 0;
    public boolean readyToshot = true;
    public boolean firstShot = true;


    public Thread thread_Delay = new threadDelay();

    public boolean playerisHitted = false;

    public Thread thread_PlayerHitted = new player_hitted();

    public boolean lose = false;






    private Image game_panel_img;

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

        //PLAYER
        g.drawImage(player.tank_img, player.x, player.y, player.getWidth(), player.getHeight(), null);

        //cascata

        for (int i = 0; i < charger_capacity; i++){

        g.drawImage(charger[i].bullet_img, charger[i].x_shot, charger[i].y_shot, charger[i].width_shot, charger[i].height_shot, null);

        }

        frame.active_level.paintComponents(g);



        onDraw();

    }


    /**
     * Buffered Strategy
     */
    private void onDraw() {
        BufferStrategy frameStrategy = frame.getBufferStrategy();
        if (frameStrategy == null) {

            frame.createBufferStrategy(1);
            frameStrategy = frame.getBufferStrategy();
        }

        frameStrategy.show();
    }

    /**
     * Comandi Tastiera
     */
    public class Keyboard_Input extends KeyAdapter {

        private boolean leftPressed = false;
        private boolean rightPressed = false;
        private boolean upPressed = false;
        private boolean downPressed = false;
        private boolean firePressed = false;
        private boolean escapePressed = false;


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

                leftPressed = true;

                x = player.x - player.speed;

                if (x > left_collider) {

                    player.leftMove();
                }
            }

            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {

                rightPressed = true;

                x = player.x + player.speed;

                if (x < right_collider) {

                    player.rightMove();
                }
            }

            if (e.getKeyCode() == KeyEvent.VK_UP) {

                upPressed = true;

                y = player.y + player.speed;

                if (y > up_collider) {

                    player.upMove();
                }
            }

            if (e.getKeyCode() == KeyEvent.VK_DOWN) {

                downPressed = true;

                y = player.y + player.speed;

                if (y < down_collider) {

                    player.downMove();
                }
            }

            if (e.getKeyCode() == KeyEvent.VK_SPACE) {

                firePressed = true;

                if (firstShot){

                    thread_Delay.start();

                    firstShot = false;

                    System.out.println("FIRSTSHOT");

                }

                if (readyToshot) {

                    readyToshot = false;

                    if (shotted_bullet < charger_capacity) {

                        chargerThread[shotted_bullet] = new threadPlayerBullet();

                        chargerThread[shotted_bullet].start();

                        System.out.println("ThreadPlayerBulletStart");


                        // System.out.println(shotted_bullet);

                    }
                }
            }

            repaint();
        }


        @Override
        public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                leftPressed = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                rightPressed = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                firePressed = false;
            }


            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                
            }

        }

        @Override
        public void keyTyped(KeyEvent e) {
            // Funzione tasto Escape
            if (e.getKeyChar() == 27) {
                escapePressed = true;

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


            //thread_Delay = new threadDelay();



            if (shotted_bullet < charger_capacity){

                int active_shot = shotted_bullet;

                shotted_bullet++;

                used_bullet++;

                //System.out.println("Colpi utilizzati" + used_bullet);

                //System.out.println(shotted_bullet + ";" + active_shot );

                if (shotted_bullet >= charger_capacity){
                    Thread ricaricaThread = new RicaricaThread();

                    ricaricaThread.start();
                }

                charger[active_shot] = new Shot(player);

                if (inGame){

                   // System.out.println("SB" + shotted_bullet + "; AS" + active_shot );
                    //System.out.println("TH" + enemy_tank_hitted );
                    //System.out.println( );


                }

                while(!charger[active_shot].shotted(true) && (enemy_tank_hitted < frame.active_level.enemiesNumber) && !charger[active_shot].hit && inGame) {

                   // System.out.println("gm");

                    frame.game_panel.repaint();

                    int i = 0;

                    while (i < frame.active_level.enemiesNumber && !charger[active_shot].hit ) {

                        if (frame.active_level.levels[frame.active_level.activeLevel - 1].enemies[i].isalive && enemy_hitted(frame.active_level.levels[frame.active_level.activeLevel - 1].enemies[i], charger[active_shot])) {

                            enemy_tank_hitted++;

                           // System.out.println("ENEMYTANKHitted   "  + enemy_tank_hitted);

                            missed_shot --;

                            frame.active_level.levels[frame.active_level.activeLevel - 1].enemies[i].isHitted(true);

                            charger[active_shot].shotHit();

                            //System.out.println(enemy_tank_hitted);

                            if (enemy_tank_hitted == frame.active_level.enemiesNumber) {

                                //enemy_tank_hitted = 0;

//                                for (int j = 0; j < charger_capacity; j++){
//
//                                    charger[j].shotHit();
//                                }

                                player.levelUp();

                                frame.active_level.nextLevel();

                                System.out.println("NextLVL");

                                inGame = false;

                                statusGamechange(false);

                                JOptionPane.showMessageDialog(frame, "You hit all enemy tanks!");



                            }
                        } else {

                            i++;
                           // System.out.println("colpi mancati: " + missed_shot);
                        }


                    }

                    try {
                            Thread.sleep(50);

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }

                missed_shot ++;

              //  System.out.println("END PLAYER BULLETT THREAD");

            }
        }
    }




    /**
     *     Thread ricarica colpi caricatore
     */
    public class RicaricaThread extends Thread implements Runnable{

        @Override
        public void run() {

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            shotted_bullet = 0;

            used_charger++;

          //  System.out.println("Caricatori usati: " + used_charger);

            //scritta ricarica
        }
    }





    /**
     *     thread delay tra colpi caricatore
     */
    public class threadDelay extends Thread implements Runnable{

        @Override
        public void run() {

            while (inGame){

                readyToshot = !readyToshot;

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                //System.out.println("delay: " + readyToshot);
            }

        }
    }


    /**
     *     Metodo nemico colpito
     */

    public boolean enemy_hitted(Enemy enemy, Shot shot) {

        boolean isHitted = false;

        if ((shot.x_shot >= enemy.x && shot.x_shot <= enemy.x + enemy.width) && (shot.y_shot >= enemy.y && shot.y_shot <= enemy.y + enemy.height)){

            isHitted = true;
        }

        return isHitted;
    }


    private void restoreLevel() {

        for (int i = 0; i < frame.active_level.levels[frame.active_level.activeLevel - 1].enemies_number; i++) {

            Active_Level restore = new Active_Level(frame);

            frame.active_level.levels[frame.active_level.activeLevel - 1].enemies = restore.levels[frame.active_level.activeLevel - 1].enemies;

        }
    }

    /**
     *     Metodo Player colpito
     */

    public class player_hitted extends Thread implements Runnable{

        @Override
        public void run() {

           // playerisHitted = false;

            while (inGame) {

                //System.out.println("ThreadPlayerHitted");

                    for (int i = 0; i < frame.active_level.levels[frame.active_level.activeLevel - 1].enemies_number; i++) {

                        for (int j = 0; j < frame.active_level.levels[frame.active_level.activeLevel - 1].enemies[i].charger_capacity; j++) {

                            // System.out.println("active_level " + frame.active_level.activeLevel + "i: " + i + "j: " + j);

                            //System.out.println("");

                            if (!playerisHitted) {

                                //System.out.println("Enemies: " + i + " EnemyCharger: " + j  + "Hit: " + frame.active_level.levels[frame.active_level.activeLevel - 1].enemies[i].enemyCharger[j].hit );

                                if (!frame.active_level.levels[frame.active_level.activeLevel - 1].enemies[i].enemyCharger[j].hit) {

                                    if ((frame.active_level.levels[frame.active_level.activeLevel - 1].enemies[i].enemyCharger[j].x_shot >= player.x && frame.active_level.levels[frame.active_level.activeLevel - 1].enemies[i].enemyCharger[j].x_shot <= player.x + player.getWidth()) && (frame.active_level.levels[frame.active_level.activeLevel - 1].enemies[i].enemyCharger[j].y_shot >= player.y && frame.active_level.levels[frame.active_level.activeLevel - 1].enemies[i].enemyCharger[j].y_shot <= player.y + player.getHeight())) {

                                        //System.out.println("PlayerHitted");

                                        frame.active_level.levels[frame.active_level.activeLevel - 1].enemies[i].enemyCharger[j].hit = true;

                                        playerisHitted = true;

                                        lose = true;

                                        inGame = false;

                                        statusGamechange(false);

                                    }
                                }
                            }
                        }

                    }
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            }

           // System.out.println("End PlayerHittedThread___________");

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

            lose = false;

            if (frame.active_level.activeLevel == 1 && !playerisHitted){

                player = new Player();

                this.addKeyListener(new Keyboard_Input());
                this.setSize(800, 800);
                this.game_panel_img = Resources.getImage("/Resources/First_Level_Background.png");

                charger = new Shot[charger_capacity];

                chargerThread = new threadPlayerBullet[charger_capacity];

            }

            playerisHitted = false;

            frame.active_level.enemiesLogicStart(inGame);

            for (int i = 0; i < charger_capacity; i++){

                charger[i] = new Shot(player);
                chargerThread[i] = new threadPlayerBullet();

            }

            thread_PlayerHitted = new player_hitted();

            thread_PlayerHitted.start();

            thread_Delay = new threadDelay();

            System.out.println("Status ingame true");

        }else {

            inGame = false;

            this.frame.inGame(false);

            if (lose){
                restoreLevel();

                JOptionPane.showMessageDialog(frame, "You lose, try again!");

            }
            frame.active_level.enemiesLogicStart(inGame);


            System.out.println("Status ingame false");



        }


    }



}


