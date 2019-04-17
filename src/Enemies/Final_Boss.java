package Enemies;

import Utility.Resources;


public class Final_Boss extends Enemy {

    public Final_Boss() {

        x = 600;
        y = 400 - height / 2;
        width = 150;
        height = 150;
        level = 4;
        life = 4;

        movement_speed = 30;

        up_collider = 10;
        down_collider = 790 - height;
        right_collider = 680;

        this.tank_img = Resources.getImage("/Resources/Enemy_Tank_Final_Level_img.png");
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


}
