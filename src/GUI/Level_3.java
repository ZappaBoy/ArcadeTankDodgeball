package GUI;


import Enemies.Enemy;
import Enemies.Enemy_Level1;
import Enemies.Enemy_Level2;
import Enemies.Enemy_Level3;


public class Level_3 extends Level{

    Level_3() {
        gameLevel = 3;

        enemyLevel1_number = 1;
        enemyLevel2_number = 1;
        enemyLevel3_number = 1;

        enemies = new Enemy[getEnemies_number()];

        enemies[0] = new Enemy_Level1();
        enemies[1] = new Enemy_Level2();
        enemies[2] = new Enemy_Level3();
    }
}
