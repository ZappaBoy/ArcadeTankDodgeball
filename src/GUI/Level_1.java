package GUI;


import Enemies.Enemy;
import Enemies.Enemy_Level1;

public class Level_1 extends Level{


    Level_1() {
        gameLevel = 1;

        enemyLevel1_number = 1;
        enemyLevel2_number = 0;
        enemyLevel3_number = 0;


        enemies_number = getEnemies_number();

        enemies = new Enemy[enemies_number];

        enemies[0] = new Enemy_Level1();
    }
}
