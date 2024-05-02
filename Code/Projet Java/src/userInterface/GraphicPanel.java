package userInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.print.PrinterGraphics;
import java.util.Random;

public class GraphicPanel extends JPanel {

    public static final int ROWS_NB = 50;
    public static final int COLS_NB = 10;
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
        for(int iLine = 0; iLine < ROWS_NB; iLine++){
            for(int iCol = 0;iCol < COLS_NB;iCol++){
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
