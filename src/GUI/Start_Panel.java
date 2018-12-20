package GUI;



        import Utility.Resources;

        import javax.swing.*;
        import java.awt.*;
        import java.awt.event.MouseAdapter;
        import java.awt.event.MouseEvent;
        import java.awt.Graphics;


public class Start_Panel extends JPanel {

    //Immagine di sfondo
    private Image start_panel_img;

    //Pulsanti

    //Pulsante Start
    private HotArea start_button = new HotArea();
    //Pulsante Credits
    private HotArea credits_button = new HotArea();
    //Pulsante Exit
    private HotArea exit_button = new HotArea();

    private ATD_Frame frame;

    Start_Panel(ATD_Frame pframe){

        //Inizializzazione Frame
        this.setSize(800,800);

        frame = pframe;

        //Definizione posizione e dimensione pulsanti
        //Pulsante Start
        this.start_button.x = 280; //Asse x
        this.start_button.y = 340; //Asse y
        this.start_button.width = 250; //Lunghezza
        this.start_button.height = 70; //Altezza

        //Pulsante Credits
        this.credits_button.x = 280; //Asse x
        this.credits_button.y = 450; //Asse y
        this.credits_button.width = 250; //Lunghezza
        this.credits_button.height = 70; //Altezza

        //Pulsante Exit
        this.exit_button.x = 280; //Asse x
        this.exit_button.y = 560; //Asse y
        this.exit_button.width = 250; //Lunghezza
        this.exit_button.height = 70; //Altezza

        this.addMouseListener(new MyMouseListener());

        this.start_panel_img = Resources.getImage("/Resources/Start_Panel.png");
    }

    @Override
    protected void paintComponent(Graphics g) {

        g.setColor(Color.BLACK);
        g.drawImage(start_panel_img, 0, 0, 800, 800, null );
    }




    private class MyMouseListener extends MouseAdapter {

        @Override
        public void mouseReleased(MouseEvent e) {


            if (start_button.contains(e.getPoint())) {

                frame.start_panel.setVisible(false);
                frame.game_panel.setVisible(true);
                frame.active_level.setVisible(true);
                frame.game_panel.setFocusable(true);
                frame.game_panel.requestFocus();

                frame.active_level.nextLevel();

              //  frame.game_panel.tank_hitted = 0;
            }

            if (credits_button.contains(e.getPoint())) {

                frame.start_panel.setVisible(false);
                //       frame.credits_panel.setVisible(true);

            }

            if (exit_button.contains(e.getPoint())) {

                System.exit(0);

            }
        }

    }



}

