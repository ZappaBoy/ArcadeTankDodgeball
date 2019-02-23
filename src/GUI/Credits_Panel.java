package GUI;

import Utility.Resources;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Credits_Panel extends JPanel {

    public Image credits_panel_img;

    private ATD_Frame frame;

    private HotArea return_button = new HotArea();

    Credits_Panel(ATD_Frame pframe) {

        frame = pframe;

        this.setSize(800, 800);

        this.credits_panel_img = Resources.getImage("/Resources/Credits_Panel.png");

        this.addMouseListener(new MyMouseListener());

        //Return Button
        this.return_button.x = 220; //Asse x
        this.return_button.y = 570; //Asse y
        this.return_button.width = 350; //Lunghezza
        this.return_button.height = 90; //Altezza

    }

    @Override
    protected void paintComponent(Graphics g) {

        g.drawImage(credits_panel_img, 0, 0, 800, 800, null);

    }

    private class MyMouseListener extends MouseAdapter {

        @Override
        public void mouseReleased(MouseEvent e) {

            if (return_button.contains(e.getPoint())) {

                frame.credits_panel.setVisible(false);
                frame.start_panel.setVisible(true);
                frame.start_panel.setFocusable(true);
            }
        }
    }
}
