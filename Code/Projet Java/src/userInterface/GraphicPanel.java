package userInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.print.PrinterGraphics;

public class GraphicPanel extends JPanel {

    public static final int ROWS_NB = 50;
    public static final int COLS_NB = 10;

    public GraphicPanel(){
        this.setBackground(Color.BLACK);
        setLayout(new GridLayout(ROWS_NB,COLS_NB));
        init();
        ChangingBitThread changingBitThread = new ChangingBitThread(this);
        changingBitThread.start();
    }

    public void init(){
        for(int iLine = 0; iLine < ROWS_NB; iLine++){
            for(int iCol = 0;iCol < COLS_NB;iCol++){
                JLabel label = new JLabel(randomNumber().toString());
                label.setForeground(Color.getHSBColor(120, 86, 58));
                add(label);
            }
        }
    }

    public Integer randomNumber(){
        return Math.round((float) Math.random());
    }

}
