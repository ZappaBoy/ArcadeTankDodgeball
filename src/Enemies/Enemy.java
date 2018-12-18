package Enemies;

import java.awt.*;

public class Enemy {

    public int x = 0;
    public int y = 0;
    public int height = 0;
    public int width = 0;
    public boolean isalive = true;
    public int level = 0;
    public Image tank_img;


    public void isHitted(boolean isHitted){

        if (isHitted){
            isalive = false;
            tank_img = null;
            x = 0;
            y = 0;
            height = 0;
            width =0;
            level = 0;

        }
    }

}






