package userInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class MainWindow extends JFrame {
    private Container frameContainer;
    private JMenuBar menuBar;
    private JMenu toolMenu, manipulationMenu, searchMenu, helpMenu;
    private JMenuItem leaveItemMenu, createItemMenu, modifyItemMenu, deleteItemMenu, searchItemMenu, search1ItemMenu, search2ItemMenu, search3ItemMenu , helpItemMenu;
    private JPanel welcomePanel, graphicWestPanel, graphicEastPanel;
    private JLabel welcomeLabel;

    public MainWindow(){
        super("\"Pas vu, pas pris\" spy agency");
        setBounds(50, 50, 1200, 750);

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

        modifyItemMenu = new JMenuItem("Modifier");
        modifyItemMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.CTRL_MASK));
        manipulationMenu.add(modifyItemMenu);

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

        search1ItemMenu = new JMenuItem("Recherche 1");
        searchMenu.add(search1ItemMenu);

        search2ItemMenu = new JMenuItem("Recherche 2");
        searchMenu.add(search2ItemMenu);

        search3ItemMenu = new JMenuItem("Recherche 3");
        searchMenu.add(search3ItemMenu);

        menuBar.add(searchMenu);

        //Menu "Aide"
        helpMenu = new JMenu("Aide");
        helpMenu.setMnemonic('H');

        helpItemMenu = new JMenuItem("Demander de l'aide");
        helpItemMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.CTRL_MASK));
        helpMenu.add(helpItemMenu);

        menuBar.add(helpMenu);

        //Contenu de la page
        frameContainer = this.getContentPane();
        frameContainer.setLayout(new BorderLayout());

        welcomePanel = new JPanel();
        welcomePanel.setLayout(new FlowLayout());

        welcomeLabel = new JLabel("test");

        welcomePanel.add(welcomeLabel);

        graphicWestPanel = new GraphicPanel();
        graphicEastPanel = new GraphicPanel();

        frameContainer.add(welcomePanel, BorderLayout.CENTER);
        frameContainer.add(graphicWestPanel, BorderLayout.WEST);
        frameContainer.add(graphicEastPanel, BorderLayout.EAST);

        setVisible(true);
    }
}
