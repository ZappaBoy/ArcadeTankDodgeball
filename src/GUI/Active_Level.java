package GUI;

import javax.swing.*;
import java.awt.*;

public class Active_Level extends JPanel {

    public int gameLevel = 1;

    public int enemiesNumber = 1;

    private ATD_Frame frame;

    public Level[] levels;

    public int levels_number = 4;

    public int activeLevel = 0;


    Active_Level(ATD_Frame pframe){

        this.setSize(800, 800);

        frame = pframe;
        levels = new Level[levels_number];
        levels[0] = new Level_1();
        levels[1] = new Level_2();
        levels[2] = new Level_3();
        levels[3] = new Level_Final();
    }


    public void paintComponents(Graphics g){

//
//        System.out.println("gameLevel: " + gameLevel);
//
//        System.out.println("enemiesNumber: " + enemiesNumber);



        for (int i = 0; i < enemiesNumber; i++){

            g.drawImage(levels[activeLevel].enemies[i].tank_img, levels[activeLevel].enemies[i].x, levels[activeLevel].enemies[i].y, levels[activeLevel].enemies[i].width, levels[activeLevel].enemies[i].height, null );

            for (int j = 0; j < levels[activeLevel].enemies[i].charger_capacity; j++){

                if (!frame.game_panel.lose && frame.game_panel.inGame){

                    if (!levels[activeLevel].enemies[i].enemyCharger[j].hit){

                        g.drawImage(levels[activeLevel].enemies[i].enemyCharger[j].bullet_img, levels[activeLevel].enemies[i].enemyCharger[j].x_shot, levels[activeLevel].enemies[i].enemyCharger[j].y_shot, levels[activeLevel].enemies[i].enemyCharger[j].width_shot, levels[activeLevel].enemies[i].enemyCharger[j].height_shot, null );

                    }
                }
            }
        }
    }

    public void nextLevel() {

        if (gameLevel < levels_number) {


            gameLevel++;
            activeLevel++;
            enemiesNumber = levels[activeLevel].getEnemies_number();

        }



    }


    public void enemiesLogicStart(boolean ingame){

        if (ingame){

            for (int i = 0; i < levels[activeLevel].enemies_number; i++){

                //levels[activeLevel].enemies[i].isalive = true;

                levels[activeLevel].enemies[i].initEnemyLogic();                     //inizializzare di nuovo il thread della logica

                levels[activeLevel].enemies[i].threadEnemylogic.start();

               // System.out.println("enemylogicinit____________");

            }
        }else {


            for (int i = 0; i < frame.active_level.enemiesNumber; i++){


               // levels[activeLevel].enemies[i].initEnemyLogic();                     //inizializzare di nuovo il thread della logica

                levels[activeLevel].enemies[i].isalive = false;

            }

        }
    }



}