package GUI;

import Enemies.Enemy;
import GameLogic.Enemy_Shot;
import Player.Player;

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


        for (int i = 0; i < enemiesNumber; i++){


            g.drawImage(levels[gameLevel - 1].enemies[i].tank_img, levels[gameLevel - 1].enemies[i].x, levels[gameLevel - 1].enemies[i].y, levels[gameLevel - 1].enemies[i].width, levels[gameLevel - 1].enemies[i].height, null );

            for (int j = 0; j < levels[activeLevel - 1].enemies[i].charger_capacity; j++){

                g.drawImage(levels[gameLevel - 1].enemies[i].enemyCharger[j].bullet_img, levels[gameLevel - 1].enemies[i].enemyCharger[j].x_shot, levels[gameLevel - 1].enemies[i].enemyCharger[j].y_shot, levels[gameLevel - 1].enemies[i].enemyCharger[j].width_shot, levels[gameLevel - 1].enemies[i].enemyCharger[j].height_shot, null );

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


    public void enemiesLogicStart(boolean ingame){

        if (ingame){

            for (int i = 0; i < frame.active_level.enemiesNumber; i++){

                frame.active_level.levels[activeLevel - 1].enemies[i].isalive = true;

                frame.active_level.levels[activeLevel - 1].enemies[i].initEnemyLogic();                     //inizializzare di nuovo il thread della logica

                frame.active_level.levels[activeLevel - 1].enemies[i].threadEnemylogic.start();

            }
        }else {


            for (int i = 0; i < frame.active_level.enemiesNumber; i++){

                frame.active_level.levels[activeLevel - 1].enemies[i].isalive = false;

            }

        }
    }



}