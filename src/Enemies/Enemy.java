package Enemies;

import GUI.Game_Panel;
import GameLogic.Shot;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Enemy {

    public int x = 0;
    public int y = 0;
    public int height = 0;
    public int width = 0;
    public boolean isalive = true;
    public int level = 0;
    public Image tank_img;
    public int movement_speed = 10;



    private static int up = 0;
    private static int down = 1;
    private static int left = 2;
    private static int right = 3;
    public  Thread threadEnemylogic = new threadEnemyAI();

    int up_collider = 3;
    int down_collider = 700;
    int left_collider = 410;
    int right_collider= 710;

    public void isHitted(boolean isHitted){

        if (isHitted){
            isalive = false;
            tank_img = null;
            x = 800;
            y = 800;
            height = 0;
            width =0;
        }


//        threadEnemylogic.start();

        System.out.println("start AI : " + level);

    }


    /**
     *          Thread Intelligenza artificiale nemici
     */
    private class threadEnemyAI extends Thread implements Runnable {

        @Override
        public void run() {

            while(this.isAlive()){

                Random random_direction = new Random();
                int direction;

                int coeffcient;

                direction = random_direction.nextInt(4);

                coeffcient = random_direction.nextInt(4);

               // System.out.println("direction ai" + direction);

                if (direction == up && y > up_collider){


                    y -= movement_speed;

                  //  System.out.println("direction up" + direction);


                }


                if (direction == down && y < down_collider){

                    y += movement_speed;

                  //  System.out.println("direction down" + direction);

                }


                if (direction == left && x >= left_collider && (coeffcient != direction)){

                    x -= movement_speed;
                   // System.out.println("direction left" + direction);

                }


                if (direction == right && x < right_collider && (coeffcient != direction)){

                    x += movement_speed;
                  //  System.out.println("direction right" + direction);


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






