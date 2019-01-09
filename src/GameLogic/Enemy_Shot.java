package GameLogic;

import Enemies.Enemy;
import Utility.Resources;

import java.awt.*;

public class Enemy_Shot {


    public Image bullet_img;

    public int x_shot;
    public int y_shot;
    public int width_shot = 30;
    public int height_shot = 30;
    public int speed_movement = 15;

    public boolean hit = false;

    public Enemy_Shot(int xTank, int yTank, int width, int height){

        x_shot = xTank - (width/2);
        y_shot = yTank + (height/3);


    }

    public boolean shotted(boolean isShotted){


        bullet_img = Resources.getImage("/Resources/bullet_img.png");

        boolean end_shot;
        end_shot = false;

        if (isShotted && !hit) {

            if (x_shot > 0) {

                x_shot -= speed_movement;

            } else {
                end_shot = true;

            }
        }

        return end_shot;
    }

}
