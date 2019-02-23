package GUI;

import Enemies.Enemy;
import Utility.Resources;

import java.awt.*;

public class Level {

    public int gameLevel = 0;
    public int enemies_number = 0;
    public int enemyLevel1_number = 0;
    public int enemyLevel2_number = 0;
    public int enemyLevel3_number = 0;
    public int finalBoss_number = 0;

    public Enemy[] enemies;

    private Image level_image;

    public int getEnemies_number() {

        enemies_number = enemyLevel1_number + enemyLevel2_number + enemyLevel3_number + finalBoss_number;

        return enemies_number;
    }

    public Image getlevel_img() {

        level_image = Resources.getImage("/Resources/Level_" + gameLevel + "_Background.png");

        return level_image;
    }
}


