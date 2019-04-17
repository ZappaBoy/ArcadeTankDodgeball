package Enemies;

import Utility.Resources;

public class Enemy_Level1 extends Enemy {

    public Enemy_Level1() {

        x = 720;
        y = 360;
        width = 80;
        height = 80;
        level = 1;

        up_collider = 3 + 260;
        down_collider = 700 - 260;

        this.tank_img = Resources.getImage("/Resources/Enemy_Tank_Level1_Red_img.png");

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




