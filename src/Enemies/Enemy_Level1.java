package Enemies;

import Utility.Resources;

import java.awt.*;

public class Enemy_Level1 extends Enemy{





    public Enemy_Level1(){


        x = 720;
        y = 360;
        width = 80;
        height = 80;
        level = 1;


        this.tank_img = Resources.getImage("/Resources/Enemy_Tank_Level1_img.png" );



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



