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


    public int charger_capacity = 4;
    public int shootted_bullet = 0;
    public Shot[] charger;
    public threadBullet[] chargerThread;
    public boolean inGame = true;

    public int player_level = 1;

    private ATD_Frame frame;

    public  int tank_hitted = 0;
    public  int missed_shot = 0;
    public  int used_charger = 0;
    public  int used_bullet = 0;



    private Image game_panel_img;

    Game_Panel(ATD_Frame pframe) {

        frame = pframe;

        statusGamechange(inGame);
    }


    /**
     *     Override metodo paintComponent
     *     Disegnare immagini di gioco
     */
    @Override
    protected void paintComponent(Graphics g) {

        g.drawImage(game_panel_img, 0, 0, 800, 800, null);

        frame.active_level.repaint();

        //PLAYER
        g.drawImage(player.tank_img, player.x, player.y, player.getWidth(), player.getHeight(), null);

        //cascata

        g.drawImage(charger[3].bullet_img, charger[3].x_shot, charger[3].y_shot, charger[3].width_shot, charger[3].height_shot, null);

        g.drawImage(charger[2].bullet_img, charger[2].x_shot, charger[2].y_shot, charger[2].width_shot, charger[2].height_shot, null);

        g.drawImage(charger[1].bullet_img, charger[1].x_shot, charger[1].y_shot, charger[1].width_shot, charger[1].height_shot, null);

        g.drawImage(charger[0].bullet_img, charger[0].x_shot, charger[0].y_shot, charger[0].width_shot, charger[0].height_shot, null);

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

                if (shootted_bullet < charger_capacity) {

                    chargerThread[shootted_bullet] = new threadBullet();

                    chargerThread[shootted_bullet].start();

                    System.out.println(shootted_bullet);

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
     *          Thread colpo
     */
    private class threadBullet extends Thread implements Runnable{

        @Override
        public void run() {

            Thread ricaricaThread = new RicaricaThread();

            if (shootted_bullet < charger_capacity){

                int active_shot = shootted_bullet;

                shootted_bullet++;

                used_bullet++;

                System.out.println("Colpi utilizzati" + used_bullet);

                System.out.println(shootted_bullet + ";" + active_shot );

                if (shootted_bullet >= charger_capacity){
                    ricaricaThread.start();
                }

                charger[active_shot] = new Shot(player.x, player.y, player.getWidth(), player.getHeight());

                while(!charger[active_shot].shotted(true) && (tank_hitted < frame.active_level.enemiesNumber) && !charger[active_shot].hit) {

                    frame.game_panel.repaint();

                    int i = 0;

                    while (i < frame.active_level.enemiesNumber && !charger[active_shot].hit) {

                        if (frame.active_level.levels[frame.active_level.activeLevel - 1].enemies[i].isalive && enemy_hitted(frame.active_level.levels[frame.active_level.activeLevel - 1].enemies[i], charger[active_shot])) {

                            tank_hitted++;

                            missed_shot --;

                            frame.active_level.levels[frame.active_level.activeLevel - 1].enemies[i].isHitted(true);

                            charger[active_shot].shotHit();



                            if (tank_hitted == frame.active_level.enemiesNumber) {

                                tank_hitted = 0;

                                for (int j = 0; j < charger_capacity; j++){

                                    charger[j].shotHit();
                                }

                                JOptionPane.showMessageDialog(frame, "You hit all enemy tanks!");

                                statusGamechange(false);

                                player.levelUp();

                                frame.active_level.nextLevel();

                            }
                        } else {


                            System.out.println("colpi mancati: " + missed_shot);
                        }

                        i++;



                    }

                    try {

                            Thread.sleep(50);

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }

                missed_shot ++;

            }


        }
    }




    /**
     *     Metodo RicaricaThread caricatore
     */
    public class RicaricaThread extends Thread implements Runnable{

        @Override
        public void run() {

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            shootted_bullet = 0;

            used_charger++;

            System.out.println("Caricatori usati: " + used_charger);

            //scritta ricarica
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



    /**
     *     Metodo nemico colpito
     */

    public void statusGamechange (boolean inGame){

        if (inGame){

            player = new Player();

            this.addKeyListener(new Keyboard_Input());

            this.setSize(800, 800);

            this.game_panel_img = Resources.getImage("/Resources/First_Level_Background.png");

            charger = new Shot[charger_capacity];

            chargerThread = new threadBullet[charger_capacity];

            for (int i = 0; i < charger_capacity; i++){

                charger[i] = new Shot(800,800,0,0);
                chargerThread[i] = new threadBullet();
            }

            tank_hitted = 0;

        }else {

            this.frame.inGame(false);

        }
    }
}


