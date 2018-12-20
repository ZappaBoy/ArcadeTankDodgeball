package GUI;

import javax.swing.*;
import java.awt.*;

public class Active_Level extends JPanel {

    private int gameLevel = 1;

    public int enemiesNumber = 1;

    private ATD_Frame frame;

    public Level[] levels;

    public int levels_numbers = 3;

    public int activeLevel = 1;


    Active_Level(ATD_Frame pframe){

        this.setSize(800, 800);

        frame = pframe;
        levels = new Level[levels_numbers];
        levels[0] = new Level_1();
        levels[1] = new Level_2();
        levels[2] = new Level_3();
    }

    @Override
    protected void paintComponent(Graphics g) {

        //fa schifo senza un cristo di for!!!!!!!

       if (gameLevel == 1 && levels[0].enemies[0].isalive ){

           g.drawImage(levels[gameLevel - 1].enemies[0].tank_img, levels[gameLevel - 1].enemies[0].x, levels[gameLevel - 1].enemies[0].y, levels[gameLevel - 1].enemies[0].width, levels[gameLevel - 1].enemies[0].height, null );

       }if (gameLevel == 2){

           if (levels[gameLevel - 1].enemies[0].isalive){

               g.drawImage(levels[1].enemies[0].tank_img, levels[1].enemies[0].x, levels[1].enemies[0].y, levels[1].enemies[0].width, levels[1].enemies[0].height, null );

           }

           if (levels[gameLevel - 1].enemies[1].isalive){

               g.drawImage(levels[gameLevel - 1].enemies[1].tank_img, levels[gameLevel - 1].enemies[1].x, levels[gameLevel - 1].enemies[1].y, levels[gameLevel - 1].enemies[1].width, levels[gameLevel - 1].enemies[1].height, null );

            }

       }if (gameLevel == 3){

           if (levels[gameLevel - 1].enemies[0].isalive){

               g.drawImage(levels[gameLevel - 1].enemies[0].tank_img, levels[gameLevel - 1].enemies[0].x, levels[gameLevel - 1].enemies[0].y, levels[2].enemies[0].width, levels[gameLevel - 1].enemies[0].height, null );

           }

            if (levels[gameLevel - 1].enemies[1].isalive){

                g.drawImage(levels[gameLevel - 1].enemies[1].tank_img, levels[gameLevel - 1].enemies[1].x, levels[gameLevel - 1].enemies[1].y, levels[gameLevel - 1].enemies[1].width, levels[gameLevel - 1].enemies[1].height, null );
            }

            if (levels[gameLevel - 1].enemies[2].isalive){

                g.drawImage(levels[gameLevel - 1].enemies[2].tank_img, levels[gameLevel - 1].enemies[2].x, levels[gameLevel - 1].enemies[2].y, levels[gameLevel - 1].enemies[2].width, levels[gameLevel - 1].enemies[2].height, null );

            }
        }
    }

    public void nextLevel() {

        if (gameLevel < levels_numbers) {


            gameLevel++;
            activeLevel++;
            enemiesNumber = levels[activeLevel - 1].getEnemies_number();
        }else{

            JOptionPane.showMessageDialog(frame, "You complete all levels!");
        }
    }
}