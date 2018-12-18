package Player;

import Utility.Resources;

import java.awt.*;

//import Game_Logic.Attack;


public class Player {

    //get e set

    public int x = 10;
    public int y = 360;

    private final int height = 80;
    private final int width = 80;

    public int speed =7;


    public Image tank_img;

   // private Attack attack_type;

 public Player (int level){

     this.tank_img = Resources.getImage("/Resources/Player_Tank_Level" + level + "_img.png" );


    }



    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }



    public void leftMove() {

        this.x -= this.speed;

    }


    public void rightMove() {

        this.x += this.speed;

    }

    public void upMove() {

        this.y -= this.speed;

    }

    public void downMove() {

        this.y += this.speed;

    }

}
