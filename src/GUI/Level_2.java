package GUI;


import Enemies.Enemy;
import Enemies.Enemy_Level1;
import Enemies.Enemy_Level2;

public class Level_2 extends Level{

    Level_2() {
        gameLevel = 2;

        enemyLevel1_number = 1;
        enemyLevel2_number = 1;
        enemyLevel3_number = 0;

        enemies_number = getEnemies_number();

        enemies = new Enemy[enemies_number];

        enemies[0] = new Enemy_Level1();
        enemies[1] = new Enemy_Level2();


    }
}
