package userInterface;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GraphicPanel extends JPanel {

    public static final Integer ROWS_NB = 50;
    public static final Integer COLS_NB = 10;
    public Random rand;

    public GraphicPanel(){
        rand = new Random();
        this.setBackground(Color.BLACK);
        setLayout(new GridLayout(ROWS_NB,COLS_NB));
        init();
        ChangingBitThread changingBitThread = new ChangingBitThread(this);
        changingBitThread.start();
    }

    public void init(){
        for(Integer iLine = 0; iLine < ROWS_NB; iLine++){
            for(Integer iCol = 0;iCol < COLS_NB;iCol++){
                JLabel label = new JLabel(randomCharacter());
                label.setForeground(new Color(20,148,20));
                add(label);
            }
        }
    }

    public String randomCharacter(){
        Integer intASCII  = Integer.valueOf(rand.nextInt((255 - 33) + 1) + 33);

        return "" + (char) intASCII.intValue();
    }

}
