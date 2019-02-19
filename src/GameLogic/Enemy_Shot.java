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
    public int speed_movement = 15;
    private int x = 800;
    private int y = 800;
    private boolean neverShotted = true;
    public boolean hit = false;


    public Enemy_Shot(int xTank, int yTank, int width, int height){

        bullet_img = Resources.getImage("/Resources/bullet_img.png");

        x = xTank - (width/2);
        y = yTank + (height/3);


    }


    public boolean shotted(){

        if (neverShotted){
            x_shot = x;
            y_shot = y;

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
