package Enemies;

import Utility.Resources;

import java.awt.*;

public class Enemy_Level3 extends Enemy {



    public Enemy_Level3(){

        x = 720;
        y = 560;
        width = 80;
        height = 80;
        level =3;

        up_collider = 3 + 260 + 260;
        down_collider = 700 ;


        this.tank_img = Resources.getImage("/Resources/Enemy_Tank_Level3_Green_img.png" );
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
