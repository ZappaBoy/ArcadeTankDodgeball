package GUI;

import javax.swing.*;
import javax.swing.plaf.TableHeaderUI;
import java.awt.*;


import Enemies.Enemy_Level1;


public class First_Level extends JPanel {

    private static final int level = 1;

    private Enemy_Level1 enemy_level1;

    private ATD_Frame frame;


    First_Level(ATD_Frame pframe){

        this.setSize(800, 800);

        frame = pframe;

        enemy_level1 = new Enemy_Level1();


    }




    @Override
    protected void paintComponent(Graphics g) {

        g.drawImage(enemy_level1.getTank_img(), 720, 360, 80, 80, null );

    }


//
//
//    /**
//     * Thread repaint tank nemici
//     */
//
//
//
//    public class EsempioThread extends Thread {
//
//
//
//
//
//        public void run() {
//            while(true) {
//                repaint();
//                System.out.println("sleep thread enemy");
//
//
//
//                try {
//                    Thread.sleep(1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }
//    }



}