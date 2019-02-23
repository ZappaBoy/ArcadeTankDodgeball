package GameLogic;

import Enemies.Enemy;
import Utility.Resources;

import java.awt.*;

public class Enemy_Shot {


    public Image bullet_img;

    public int x_shot = 800;
    public int y_shot = 800;
    public int width_shot = 30;
    public int height_shot = 30;
    public int speed_movement = 13; //15
    private boolean neverShotted = true;
    public boolean hit = false;


    public Enemy_Shot(int xTank, int yTank, int width, int height){

        x_shot = xTank - (width/2);
        y_shot = yTank + (height/3);

        width_shot = width /5*2;
        height_shot = height/5*2;

    }


    public boolean shotted(){

        if (neverShotted){

            bullet_img = Resources.getImage("/Resources/Bullet_Img_Enemy.png");

            neverShotted = false;
        }


        boolean end_shot;
        end_shot = false;

        if (!hit) {

            if (x_shot > 0) {

                x_shot -= speed_movement;

            } else {
                end_shot = true;

            }
        }

        return end_shot;
    }

}
