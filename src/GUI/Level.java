package GUI;

import Enemies.Enemy;

public class Level {


    public int gameLevel = 0;
    public int enemies_number = 0;
    public int enemyLevel1_number = 0;
    public int enemyLevel2_number = 0;
    public int enemyLevel3_number = 0;

    public Enemy[] enemies;


    public int getEnemies_number() {

        enemies_number = enemyLevel1_number + enemyLevel2_number + enemyLevel3_number;

        return enemies_number;
    }
}


