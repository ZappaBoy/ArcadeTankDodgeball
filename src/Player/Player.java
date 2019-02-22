package Player;

import Utility.Resources;

import java.awt.*;

//import Game_Logic.Attack;


public class Player {

    //get e set

    public int x = 10;
    public int y = 360;
    public int level = 1;
    public int color = 1;

    private final int height = 80;
    private final int width = 80;

    public int speed = 8;


    public Image tank_img;

    //
   // private Attack attack_type;


 public Player (){


     playerImageinit();


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

    public String playerImageinit(){

     String colore;

     colore = "_Green";

     if (color == 1){

         colore = "_Orange";
       //  this.tank_img = Resources.getImage("/Resources/Player_Tank_Level" + level + colore + "_img.png" );

     }


     if (color == 2){

            colore = "_Pink";
      //   this.tank_img = Resources.getImage("/Resources/Player_Tank_Level" + level + colore + "_img.png" );

     }


     if (color == 3){

            colore = "_Yellow";
       //  this.tank_img = Resources.getImage("/Resources/Player_Tank_Level" + level + colore + "_img.png" );

     }

      if (color == 4){

            colore = "_Green";
           // this.tank_img = Resources.getImage("/Resources/Player_Tank_Level" + level + colore + "_img.png" );

      }


      if (color == 5){

            colore = "_Red";
          //  this.tank_img = Resources.getImage("/Resources/Player_Tank_Level" + level + colore + "_img.png" );

      }


      if (color == 6){

            colore = "_Blue";

      }

        this.tank_img = Resources.getImage("/Resources/Player_Tank_Level" + level + colore + "_img.png" );


        return colore;

    }

    public void levelUp(){

     level++;
     color ++;
     playerImageinit();
    }
}
