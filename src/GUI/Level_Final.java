package GUI;


import Enemies.Enemy;
import Enemies.Final_Boss;


public class Level_Final extends Level {

    Level_Final() {
        gameLevel = 4;

      //  enemyLevel1_number = 1;
        //enemyLevel2_number = 1;
      //  enemyLevel3_number = 1;
        finalBoss_number = 1;

        enemies = new Enemy[getEnemies_number()];

        enemies[0] = new Final_Boss();


      //  enemies[0] = new Enemy_Level1();
//        enemies[1] = new Enemy_Level2();
//        enemies[2] = new Enemy_Level3();
    }

}
