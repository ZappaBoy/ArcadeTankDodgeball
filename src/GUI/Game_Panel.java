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


    public int colpi_caricatore = 4;
    public int colpi_sparati = 0;
    public Shot[] charger;
    public threadBullet[] chargerThread;
    public boolean inGame = true;

    public int player_level = 1;

    private ATD_Frame frame;

    public int tank_hitted = 0;



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

                if (colpi_sparati < colpi_caricatore ) {

                    chargerThread[colpi_sparati] = new threadBullet();

                    chargerThread[colpi_sparati].start();

                    System.out.println(colpi_sparati);

                }

            if (e.getKeyCode() == KeyEvent.VK_ENTER) {


                //new game
               // statusGamechange(true);


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

            if (colpi_sparati < colpi_caricatore ){

                int colpo_attivo = colpi_sparati;

                colpi_sparati++;

                System.out.println(colpi_sparati + ";" + colpo_attivo );

                if (colpi_sparati >= colpi_caricatore){

                    Thread ricaricaThread = new RicaricaThread();

                    ricaricaThread.start();
                }

                charger[colpo_attivo] = new Shot(player.x, player.y, player.getWidth(), player.getHeight());

                while(!charger[colpo_attivo].shotted(true) && (tank_hitted < frame.active_level.enemiesNumber) && !charger[colpo_attivo].hit) {

                    frame.game_panel.repaint();

                    int i = 0;

                    while (i < frame.active_level.enemiesNumber && !charger[colpo_attivo].hit) {

                        if (frame.active_level.levels[frame.active_level.activeLevel - 1].enemies[i].isalive && enemy_hitted(frame.active_level.levels[frame.active_level.activeLevel - 1].enemies[i], charger[colpo_attivo])) {

                            tank_hitted++;

                            frame.active_level.levels[frame.active_level.activeLevel - 1].enemies[i].isHitted(true);

                            charger[colpo_attivo].shotHit();

                            if (tank_hitted == frame.active_level.enemiesNumber) {

                                tank_hitted = 0;

                                for (int j = 0; j < colpi_caricatore; j++){

                                    charger[j].shotHit();
                                }

                                JOptionPane.showMessageDialog(frame, "You hit all enemy tanks!");

                                statusGamechange(false);

                                frame.active_level.nextLevel();
                            }
                        } else {

                            System.out.println("nemico NON COLPITO");
                        }

                        i++;

                    }

                    try {

                            Thread.sleep(50);

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
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

            colpi_sparati = 0;
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

            player = new Player(player_level);

            this.addKeyListener(new Keyboard_Input());

            this.setSize(800, 800);

            this.game_panel_img = Resources.getImage("/Resources/First_Level_Background.png");

            charger = new Shot[colpi_caricatore];

            chargerThread = new threadBullet[colpi_caricatore];

            for (int i = 0; i < colpi_caricatore; i++){

                charger[i] = new Shot(800,800,0,0);
                chargerThread[i] = new threadBullet();
            }

            tank_hitted = 0;

        }else {

            frame.start_panel.setVisible(true);
            frame.game_panel.setVisible(false);
            frame.active_level.setVisible(false);

            inGame = true;

        }
    }
}


