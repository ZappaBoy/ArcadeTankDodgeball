package GUI;

import Utility.Resources;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Settings_Panel extends JPanel {

    private Image settings_panel_img;

    private ATD_Frame frame;

    private HotArea color_button_orange = new HotArea();

    private HotArea color_button_pink = new HotArea();

    private HotArea color_button_yellow = new HotArea();

    private HotArea return_button = new HotArea();

    private HotArea boundary_rect = new HotArea();



    private static final int orange = 1;

    private static final int pink = 2;

    private static final int yellow = 3;

    public int color = 1;


    Settings_Panel(ATD_Frame pframe) {

        frame = pframe;

        this.setSize(800, 800);

        this.addMouseListener(new MyMouseListener());

        this.settings_panel_img = Resources.getImage("/Resources/Settings_Panel.png");

        //Pulsante Colore Arancione
        this.color_button_orange.x = 160; //Asse x
        this.color_button_orange.y = 350; //Asse y
        this.color_button_orange.width = 80; //Lunghezza
        this.color_button_orange.height = 80; //Altezza

        //Pulsante Colore Rosa
        this.color_button_pink.x = 360; //Asse x
        this.color_button_pink.y = 350; //Asse y
        this.color_button_pink.width = 80; //Lunghezza
        this.color_button_pink.height = 80; //Altezza

        //Pulsante Colore Giallo
        this.color_button_yellow.x = 560; //Asse x
        this.color_button_yellow.y = 350; //Asse y
        this.color_button_yellow.width = 80; //Lunghezza
        this.color_button_yellow.height = 80; //Altezza

        //Return Button
        this.return_button.x = 230; //Asse x
        this.return_button.y = 580; //Asse y
        this.return_button.width = 350; //Lunghezza
        this.return_button.height = 100; //Altezza

        //Boundary rectangle
        this.boundary_rect.x = 800; //Asse x
        this.boundary_rect.y = 800; //Asse y
        this.boundary_rect.width = 100; //Lunghezza
        this.boundary_rect.height = 100; //Altezza


    }

    @Override
    protected void paintComponent(Graphics g) {

        g.drawImage(settings_panel_img, 0, 0, 800, 800, null);

        g.setColor(Color.black);
        //g.fillRect(0, 0, 800, 800);

        g.fillRect(color_button_orange.x - 5 , color_button_orange.y - 5, color_button_orange.width + 10, color_button_orange.height + 10);
        g.fillRect(color_button_pink.x - 5, color_button_pink.y - 5, color_button_pink.width + 10, color_button_pink.height + 10);
        g.fillRect(color_button_yellow.x - 5, color_button_yellow.y - 5, color_button_yellow.width + 10, color_button_yellow.height + 10);

        g.setColor(Color.white);
        g.fillRect(boundary_rect.x, boundary_rect.y, boundary_rect.width, boundary_rect.height);

        g.setColor(Color.orange);
        g.fillRect(color_button_orange.x, color_button_orange.y, color_button_orange.width, color_button_orange.height);

        g.setColor(Color.pink);
        g.fillRect(color_button_pink.x, color_button_pink.y, color_button_pink.width, color_button_pink.height);

        g.setColor(Color.yellow);
        g.fillRect(color_button_yellow.x, color_button_yellow.y, color_button_yellow.width, color_button_yellow.height);


       // g.setColor(Color.blue);
        //g.fillRect(return_button.x, return_button.y, return_button.width, return_button.height);

    }


    private class MyMouseListener extends MouseAdapter {

        @Override
        public void mouseReleased(MouseEvent e) {

            if (return_button.contains(e.getPoint())) {

                frame.settings_panel.setVisible(false);
                frame.start_panel.setVisible(true);
                frame.start_panel.setFocusable(true);

            }else {

                if (color_button_orange.contains(e.getPoint())) {

                    color = orange;
                    boundary_rect.x = color_button_orange.x;
                    boundary_rect.y = color_button_orange.y;
                    boundary_rect.x -= 10;
                    boundary_rect.y -= 10;
                }

                if (color_button_pink.contains(e.getPoint())) {

                    color = pink;
                    boundary_rect.x = color_button_pink.x;
                    boundary_rect.y = color_button_pink.y;
                    boundary_rect.x -= 10;
                    boundary_rect.y -= 10;
                }

                if (color_button_yellow.contains(e.getPoint())) {

                    color = yellow;
                    boundary_rect.x = color_button_yellow.x;
                    boundary_rect.y = color_button_yellow.y;
                    boundary_rect.x -= 10;
                    boundary_rect.y -= 10;
                }
            }

            repaint();
        }
    }
}