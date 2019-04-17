package Enemies;

import GameLogic.Enemy_Shot;

import java.awt.*;
import java.util.Random;

public class Enemy {

    public int x = 0;
    public int y = 0;
    public int height = 80;
    public int width = 80;
    public boolean isalive = true;
    public int level = 0;
    public Image tank_img;
    public int movement_speed = 20;


    public static int up = 0;
    public static int down = 1;
    public static int left = 2;
    public static int right = 3;
    public Thread threadEnemylogic = new threadEnemyAI();

    int up_collider = 3;
    int down_collider = 700;
    int left_collider = 410;
    int right_collider = 710;


    public int shotted_bullet = 0;
    public int life = 0;
    public Enemy_Shot[] enemyCharger;
    public Thread[] threadEnemybullett;
    public int charger_capacity = 3;
    public boolean firstShot = true;


    public void isHitted(boolean isHitted) {

        if (isHitted) {

            if (life == 0) {

                isalive = false;
                x = 800;
                y = 800;

            } else {

                life--;
            }
        }
    }

    public void initEnemyLogic() {

        isalive = true;
        shotted_bullet = 0;

        enemyCharger = new Enemy_Shot[charger_capacity];
        threadEnemybullett = new threadEnemyShot[charger_capacity];


        for (int i = 0; i < charger_capacity; i++) {

            enemyCharger[i] = new Enemy_Shot(x, y, width, height);
            threadEnemybullett[i] = new threadEnemyShot();
        }

        threadEnemylogic = new threadEnemyAI();
    }


    /**
     * Thread Intelligenza artificiale nemici
     */
    public class threadEnemyAI extends Thread implements Runnable {

        @Override
        public void run() {

            while (isalive) {

                movementLogic();

                if (firstShot) {

                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    firstShot = false;

                    movementLogic();
                }

                if (shotted_bullet < charger_capacity) {

                    threadEnemybullett[shotted_bullet] = new threadEnemyShot();

                    threadEnemybullett[shotted_bullet].start();

                } else {

                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    shotted_bullet = 0;
                }

                movementLogic();

                try {
                    Thread.sleep(1800);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }


    public class threadEnemyShot extends Thread implements Runnable {

        @Override
        public void run() {


            shotted_bullet++;

            enemyCharger[shotted_bullet - 1] = new Enemy_Shot(x, y, width, height);

            while (!enemyCharger[shotted_bullet - 1].shotted() && !enemyCharger[shotted_bullet - 1].hit) {

                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            enemyCharger[shotted_bullet - 1].bullet_img = null;

        }
    }

    public void movementLogic() {

        Random random_direction = new Random();
        int direction;

        direction = random_direction.nextInt(4);

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







