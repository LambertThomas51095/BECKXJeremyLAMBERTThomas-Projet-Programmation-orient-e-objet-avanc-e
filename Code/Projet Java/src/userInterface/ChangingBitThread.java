package userInterface;

import javax.swing.*;
import java.awt.*;

public class ChangingBitThread extends Thread{
    private GraphicPanel graphicPanel;

    public ChangingBitThread(GraphicPanel graphicPanel){
        this.graphicPanel = graphicPanel;
    }

    public void run(){
        while(true){
            try{
// changer les bits de chaque case du graphicPanel
                for(int iLine = 0; iLine < GraphicPanel.ROWS_NB; iLine++){
                    for(int iCol = 0;iCol < GraphicPanel.COLS_NB;iCol++){
                        JLabel label = (JLabel)graphicPanel.getComponent(iLine*GraphicPanel.COLS_NB+iCol);
                        label.setText(graphicPanel.randomNumber().toString());
                    }
                }
                sleep(30);
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

}
