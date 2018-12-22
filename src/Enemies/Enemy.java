package Enemies;

import GUI.Game_Panel;
import GameLogic.Enemy_Shot;
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
    public int movement_speed = 20;


    private static int up = 0;
    private static int down = 1;
    private static int left = 2;
    private static int right = 3;
    public Thread threadEnemylogic = new threadEnemyAI();

    int up_collider = 3;
    int down_collider = 700;
    int left_collider = 410;
    int right_collider = 710;


    public int shootted_bullet = 0;
    public Enemy_Shot[] enemyCharger;
    public int charger_capacity = 6;



    public void isHitted(boolean isHitted) {

        if (isHitted) {
            isalive = false;
            tank_img = null;
            x = 800;
            y = 800;
            height = 0;
            width = 0;
        }


//        threadEnemylogic.start();

        System.out.println("start AI : " + level);

    }


    /**
     * Thread Intelligenza artificiale nemici
     */
    private class threadEnemyAI extends Thread implements Runnable {

        @Override
        public void run() {

            enemyCharger = new Enemy_Shot[charger_capacity];


            for (int i = 0; i < charger_capacity; i++){

                enemyCharger[i] = new Enemy_Shot(x , y, width, height);
            }

//            Thread threadEnemyshot = new threadEnemyBullet();
//
//            threadEnemyshot.start();


            while (isalive) {

                movementLogic();

                if (shootted_bullet < charger_capacity){


                   // enemyCharger[shootted_bullet].shotted(true);


                    Thread threadEnemyshot = new threadEnemyBullet();

                    threadEnemyshot.start();

                    shootted_bullet++;

                    System.out.println(shootted_bullet);

                }

                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                }


            }

        }



    private void movementLogic() {

        Random random_direction = new Random();
        int direction;

        int coeffcient;

        direction = random_direction.nextInt(4);

        // coeffcient = random_direction.nextInt(4);


        if (direction == up && y > up_collider) {

            y -= movement_speed;

            if (direction > 1) {
                y -= movement_speed;
                direction = right;

            }
        }


        if (direction == down && y < down_collider) {

            y += movement_speed;


            if (direction < 2) {
                y += movement_speed;

                direction = left;
            }
        }


        if (direction == left && x >= left_collider) {

            x -= movement_speed;
        }


        if (direction == right && x < right_collider) {

            x += movement_speed;
        }
    }

    public class threadEnemyBullet extends Thread implements Runnable {

        @Override
        public void run() {

           // enemyCharger = new Enemy_Shot[charger_capacity];

            enemyCharger[shootted_bullet] = new Enemy_Shot(x, y, width, height);

            while (enemyCharger[shootted_bullet].shotted(true)){


                System.out.println(enemyCharger[shootted_bullet].x_shot);


                    try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


        }
    }
}







