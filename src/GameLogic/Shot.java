package GameLogic;


import Utility.Resources;
import java.awt.*;



public class Shot extends Rectangle {

    public Image bullet_img;

    public int x_shot;
    public int y_shot;
    public int width_shot = 30;
    public int height_shot = 30;

    private int x_frame = 800;
    private int y_frame = 800;
    public boolean hit = false;

    public Shot(int x_Tank, int y_Tank, int width_Tank, int height_Tank) {

        x_shot = x_Tank + (width_Tank/2);
        y_shot = y_Tank + (height_Tank/3);


        bullet_img = Resources.getImage("/Resources/bullet_img.png");
    }

     public boolean shotted(boolean isShotted){

         boolean end_shot;
         end_shot = false;

        if (isShotted && !hit) {

            if (x_shot < x_frame) {

                this.x_shot += 20;

            } else {
                end_shot = true;

            }
        }

         return end_shot;
    }

    public void shotHit(){

      bullet_img = null;

      x_shot = 0;
      y_shot = 0;
      width_shot = 0;
      height_shot = 0;

      x_frame = 0;
      y_frame = 0;
      hit = true;
    }

}



