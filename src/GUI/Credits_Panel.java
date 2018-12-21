package GUI;

import Utility.Resources;

import javax.swing.*;
import java.awt.*;

public class Credits_Panel extends JPanel {


    public Image credits_panel_img;



    Credits_Panel(ATD_Frame frame){

        this.setSize(800, 800);

        this.credits_panel_img = Resources.getImage("/Resources/Credits_Panel.png");





    }

    @Override
    protected void paintComponent(Graphics g) {

        g.drawImage(credits_panel_img, 100, 100, 600, 80, null);
        //cambiare immagine
    }
}
