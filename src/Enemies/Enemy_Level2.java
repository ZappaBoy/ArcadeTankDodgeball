package Enemies;

import Utility.Resources;

import java.awt.*;

public class Enemy_Level2 extends Enemy {






    public Enemy_Level2(){


        x = 720;
        y = 160;
        width = 80;
        height = 80;
        level = 2;


        this.tank_img = Resources.getImage("/Resources/Enemy_Tank_Level2_Blue_img.png" );



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
}
