package userInterface;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.zinternaltools.DateVetoPolicyMinimumMaximumDate;
import userInterface.CRUDPanels.*;
import userInterface.searchPanels.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.util.Locale;

public class MainWindow extends JFrame {
    private Container frameContainer;
    private JMenuBar menuBar;
    private JMenu toolMenu, manipulationMenu, searchMenu, helpMenu;
    private JMenuItem leaveItemMenu, createItemMenu, editItemMenu, deleteItemMenu, searchItemMenu, search1ItemMenu, search2ItemMenu, search3ItemMenu , helpItemMenu;
    private JPanel welcomePanel, graphicWestPanel, graphicEastPanel;
    private JLabel welcomeLabel;
    private DatePicker datePicker;

    public MainWindow(){
        super("\"Pas vu, pas pris\" spy agency");
        setBounds(50, 50, 1200, 750);
        this.addWindowListener(new ClosingListener());

        //Barre de menu
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        //Menu "Outils"
        toolMenu = new JMenu("Outils");
        toolMenu.setMnemonic('T');

        leaveItemMenu = new JMenuItem("Quitter");
        leaveItemMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
        toolMenu.add(leaveItemMenu);

        menuBar.add(toolMenu);

        //Menu "Manipulation"
        manipulationMenu = new JMenu("Manipulation");
        manipulationMenu.setMnemonic('M');

        createItemMenu = new JMenuItem("Créer");
        createItemMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
        manipulationMenu.add(createItemMenu);

        editItemMenu = new JMenuItem("Modifier");
        editItemMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK));
        manipulationMenu.add(editItemMenu);

        deleteItemMenu = new JMenuItem("Supprimer");
        deleteItemMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_MASK));
        manipulationMenu.add(deleteItemMenu);

        searchItemMenu = new JMenuItem("Rechercher");
        searchItemMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
        manipulationMenu.add(searchItemMenu);

        menuBar.add(manipulationMenu);

        //Menu "Recherche"
        searchMenu = new JMenu("Recherche");
        searchMenu.setMnemonic('S');

        search1ItemMenu = new JMenuItem("Langues d'une cellule");
        searchMenu.add(search1ItemMenu);

        search2ItemMenu = new JMenuItem("Missions d'un agents");
        searchMenu.add(search2ItemMenu);

        search3ItemMenu = new JMenuItem("Contacts d'une mission");
        searchMenu.add(search3ItemMenu);

        menuBar.add(searchMenu);

        //Menu "Aide"
        helpMenu = new JMenu("Aide");
        helpMenu.setMnemonic('H');

        helpItemMenu = new JMenuItem("Contacts");
        helpItemMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.CTRL_MASK));
        helpMenu.add(helpItemMenu);

        menuBar.add(helpMenu);

        //Contenu de la page
        frameContainer = this.getContentPane();
        frameContainer.setLayout(new BorderLayout());

        welcomePanel = new JPanel();
        welcomePanel.setLayout(new FlowLayout());
        welcomePanel.setBackground(Color.WHITE);

        welcomeLabel = new JLabel("<html><h1 text-align : center;\">Bienvenu sur la page d'accueil !</h1><p style=\"font-size: 20px;\" text-align : center;>Merci de nous faire confiance : Jerem et Thoams 😏</p><p style=\"font-size: 16px;\" text-align : center;>Nos agents sont disponibles 24/7 pour vous assister.</p><html>");
        welcomePanel.add(welcomeLabel);

        graphicWestPanel = new GraphicPanel();
        graphicEastPanel = new GraphicPanel();

        frameContainer.add(graphicWestPanel, BorderLayout.WEST);
        frameContainer.add(graphicEastPanel, BorderLayout.EAST);
        //En dernier et pas en premier pour pouvoir le supprimer via l'indice (car = tableau) quand on change le pannel, sans toucher aux éléments graphiques
        frameContainer.add(welcomePanel, BorderLayout.CENTER);


        MenuBarActionListener menuBarActionListener = new MenuBarActionListener();
        leaveItemMenu.addActionListener(menuBarActionListener);

        createItemMenu.addActionListener(menuBarActionListener);
        editItemMenu.addActionListener(menuBarActionListener);
        deleteItemMenu.addActionListener(menuBarActionListener);
        searchItemMenu.addActionListener(menuBarActionListener);

        search1ItemMenu.addActionListener(menuBarActionListener);
        search2ItemMenu.addActionListener(menuBarActionListener);
        search3ItemMenu.addActionListener(menuBarActionListener);

        helpItemMenu.addActionListener(menuBarActionListener);

        setVisible(true);
    }

    private class ClosingListener extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
            System.exit(0);
        }
    }

    private class MenuBarActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == leaveItemMenu){
                System.exit(0);
            }
            else if(e.getSource() == helpItemMenu){
                HelpWindow helpWindow = new HelpWindow();
            }
            else {
                frameContainer.remove(frameContainer.getComponentCount() - 1);

                if(e.getSource() == createItemMenu){
                    frameContainer.add(new CreatePanel());
                }
                else if(e.getSource() == editItemMenu){
                    frameContainer.add(new EditPanel());
                }
                else if(e.getSource() == deleteItemMenu){
                    frameContainer.add(new DeletePanel());
                }
                else if(e.getSource() == searchItemMenu){
                    frameContainer.add(new SearchPanel());
                }
                else if (e.getSource() == search1ItemMenu) {
                    frameContainer.add(new FirstSeachPanel());
                } else if (e.getSource() == search2ItemMenu) {
                    frameContainer.add(new SecondSearchPanel());
                } else if (e.getSource() == search3ItemMenu) {
                    frameContainer.add(new ThirdSearchPanel());
                }
                frameContainer.getComponent(frameContainer.getComponentCount() - 1).setBackground(Color.WHITE);
                repaint();
                setVisible(true);
            }
        }
    }
}
