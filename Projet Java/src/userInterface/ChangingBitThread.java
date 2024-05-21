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
                for(Integer iLine = 0; iLine < GraphicPanel.ROWS_NB; iLine++){
                    for(Integer iCol = 0;iCol < GraphicPanel.COLS_NB;iCol++){
                        JLabel label = (JLabel)graphicPanel.getComponent(iLine*GraphicPanel.COLS_NB+iCol);
                        label.setText(graphicPanel.randomCharacter());
                        label.setFont(new Font("Consolas",Font.PLAIN,18));
                    }
                }
                sleep(50);
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

}
