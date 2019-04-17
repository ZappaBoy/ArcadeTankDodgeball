package GUI;

import Enemies.Enemy;
import Enemies.Final_Boss;

public class Level_Final extends Level {

    Level_Final() {
        gameLevel = 4;

        finalBoss_number = 1;

        enemies = new Enemy[getEnemies_number()];

        enemies[0] = new Final_Boss();
    }
}
