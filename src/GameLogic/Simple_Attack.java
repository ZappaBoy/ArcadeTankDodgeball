package GameLogic;

import Utility.Resources;

import java.awt.*;

public class Simple_Attack implements Runnable{

    private  Image ball;

    public Simple_Attack (){

        Resources.getImage("/Resources/Ball_Blue.png");




    }


    @Override
    public void run(){


        //colpo



    }


    public Image getBall() {
        return ball;
    }
}
