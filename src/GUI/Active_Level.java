package GUI;

import GameLogic.Enemy_Shot;

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


    public void paintComponents(Graphics g) {

        //fa schifo senza un cristo di for!!!!!!!


        for (int i = 0; i < enemiesNumber; i++){


            g.drawImage(levels[gameLevel - 1].enemies[i].tank_img, levels[gameLevel - 1].enemies[i].x, levels[gameLevel - 1].enemies[i].y, levels[gameLevel - 1].enemies[i].width, levels[gameLevel - 1].enemies[i].height, null );

            for (int j = 0; j < levels[gameLevel - 1].enemies[i].charger_capacity; j++){


                g.drawImage(levels[gameLevel - 1].enemies[i].enemyCharger[j].bullet_img, levels[gameLevel - 1].enemies[i].enemyCharger[j].x_shot, levels[gameLevel - 1].enemies[i].enemyCharger[j].y_shot, levels[gameLevel - 1].enemies[i].enemyCharger[j].width_shot, levels[gameLevel - 1].enemies[i].enemyCharger[j].height_shot, null );


            }
        }


//       if (gameLevel == 1 && levels[0].enemies[0].isalive ){
//
//           g.drawImage(levels[gameLevel - 1].enemies[0].tank_img, levels[gameLevel - 1].enemies[0].x, levels[gameLevel - 1].enemies[0].y, levels[gameLevel - 1].enemies[0].width, levels[gameLevel - 1].enemies[0].height, null );
//
//       }if (gameLevel == 2){
//
//           if (levels[gameLevel - 1].enemies[0].isalive){
//
//               g.drawImage(levels[1].enemies[0].tank_img, levels[1].enemies[0].x, levels[1].enemies[0].y, levels[1].enemies[0].width, levels[1].enemies[0].height, null );
//
//           }
//
//           if (levels[gameLevel - 1].enemies[1].isalive){
//
//               g.drawImage(levels[gameLevel - 1].enemies[1].tank_img, levels[gameLevel - 1].enemies[1].x, levels[gameLevel - 1].enemies[1].y, levels[gameLevel - 1].enemies[1].width, levels[gameLevel - 1].enemies[1].height, null );
//
//            }
//
//       }if (gameLevel == 3){
//
//           if (levels[gameLevel - 1].enemies[0].isalive){
//
//               g.drawImage(levels[gameLevel - 1].enemies[0].tank_img, levels[gameLevel - 1].enemies[0].x, levels[gameLevel - 1].enemies[0].y, levels[2].enemies[0].width, levels[gameLevel - 1].enemies[0].height, null );
//
//           }
//
//            if (levels[gameLevel - 1].enemies[1].isalive){
//
//                g.drawImage(levels[gameLevel - 1].enemies[1].tank_img, levels[gameLevel - 1].enemies[1].x, levels[gameLevel - 1].enemies[1].y, levels[gameLevel - 1].enemies[1].width, levels[gameLevel - 1].enemies[1].height, null );
//            }
//
//            if (levels[gameLevel - 1].enemies[2].isalive){
//
//                g.drawImage(levels[gameLevel - 1].enemies[2].tank_img, levels[gameLevel - 1].enemies[2].x, levels[gameLevel - 1].enemies[2].y, levels[gameLevel - 1].enemies[2].width, levels[gameLevel - 1].enemies[2].height, null );
//
//            }
//        }
    }

    public void nextLevel() {

        if (gameLevel < levels_numbers) {


            gameLevel++;
            activeLevel++;
            enemiesNumber = levels[activeLevel - 1].getEnemies_number();

            for (int i = 0; i < frame.active_level.enemiesNumber; i++){

                frame.active_level.levels[frame.active_level.activeLevel - 1].enemies[i].threadEnemylogic.start();

//                for (int j = 0; j < levels[activeLevel - 1].enemies[i].charger_capacity; j++){
//
//                   // levels[activeLevel - 1].enemies[i].enemyCharger[j] = new Enemy_Shot(0,0,0,0);
//                }


            }




        }else{

            JOptionPane.showMessageDialog(frame, "You complete all levels!");
        }
    }
}