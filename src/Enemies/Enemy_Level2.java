package Enemies;

import Utility.Resources;


public class Enemy_Level2 extends Enemy {

    public Enemy_Level2() {

        x = 720;
        y = 160;
        width = 80;
        height = 80;
        level = 2;

        up_collider = 3;
        down_collider = 700 - 260 - 260;

        this.tank_img = Resources.getImage("/Resources/Enemy_Tank_Level2_Blue_img.png");
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
