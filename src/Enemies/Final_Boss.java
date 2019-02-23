package Enemies;

import GameLogic.Enemy_Shot;
import Utility.Resources;

import java.awt.*;
import java.util.Random;

public class Final_Boss extends Enemy{

    public Final_Boss(){

    x = 600;
    y = 400 - height/2;
    width = 150;
    height = 150;
    level = 4;
    life = 4;

    up_collider = 10;
    down_collider = 790 - height ;
    right_collider = 680;





    this.tank_img = Resources.getImage("/Resources/Enemy_Tank_Final_Level_img.png" );
}

    public Image getTank_img() {

        return tank_img;
    }

    public int getX() {

        return x;
    }

    public int getY() {

        return y;
    }
    public int getHeight() {

        return height;
    }
    public int getWidth() {

        return width;
    }


    public void initEnemyLogic(){

        isalive = true;
        shotted_bullet = 0;

        enemyCharger = new Enemy_Shot[charger_capacity];
        threadEnemybullett = new Enemy.threadEnemyShot[charger_capacity];


        for (int i = 0; i < charger_capacity; i++){

            enemyCharger[i] = new Enemy_Shot(x , y, width, height);
            threadEnemybullett[i]= new Enemy.threadEnemyShot();
        }

        threadEnemylogic = new Enemy.threadEnemyAI();
    }


    /**
     * Thread Intelligenza artificiale nemici
     */

    private class threadEnemyAI extends Thread implements Runnable {

        @Override
        public void run() {

            while (isalive) {

                movementLogic();

                if (firstShot){

                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    firstShot = false;

                    movementLogic();
                }

                if (shotted_bullet < charger_capacity){

                    threadEnemybullett[shotted_bullet] = new Enemy.threadEnemyShot();

                    threadEnemybullett[shotted_bullet].start();


                } else{

                    // System.out.println("recharge Caricatore nemico" );

                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    //recharge Cricatore Nemico

                    shotted_bullet = 0;

                }

                movementLogic();

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }




    //PROBLEMA CON VELOCITA THREAD - SHOTTED_BULLETT VIENE INCREMENTATO PRIMA DELLA FINE DEL THREAD

    public class threadEnemyShot extends Thread implements Runnable {

        @Override
        public void run() {

            // enemyCharger = new Enemy_Shot[charger_capacity];

            //System.out.println(shotted_bullet);

            shotted_bullet++;

            enemyCharger[shotted_bullet - 1] = new Enemy_Shot(x, y, width, height);

            while (!enemyCharger[shotted_bullet - 1].shotted() && !enemyCharger[shotted_bullet - 1].hit ){

                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            enemyCharger[shotted_bullet - 1].bullet_img = null;

            // System.out.println("end " + shotted_bullet);
        }
    }

    @Override
    public void movementLogic() {

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

}
