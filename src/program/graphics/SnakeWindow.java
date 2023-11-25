package program.graphics;

import program.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SnakeWindow extends JFrame {

    //menu
    private String currentPlayer;
    private GridLayout menuGrid = new GridLayout(3,3);
    private GridLayout rangletraGrid = new GridLayout(7,3);
    private GridLayout nevMegadGrid = new GridLayout(1,3);

    private JFrame menuFrame = new JFrame("Snake menu");
    private JFrame rangletraFrame = new JFrame("Snake ranglétra");

    private JFrame nevMegadFrame = new JFrame("Name");

    private JPanel mainMenu = new JPanel(menuGrid);
    private JPanel rangletraPanel = new JPanel(rangletraGrid);
    private  JPanel nevMegadPanel = new JPanel(nevMegadGrid);

    private JButton jatek = new JButton("Jatek");
    private JButton rangletra = new JButton("Rangletra");
    private JButton kilepes = new JButton("Kilepes");
    private JButton kilepes2 = new JButton("Kilepes");
    private JButton vissza = new JButton("Vissza");
    private JButton nevMegadas = new JButton("Játék");

    private JLabel helyezes = new JLabel("Helyezés");
    private JLabel nev = new JLabel("Név");
    private JLabel pontszam = new JLabel("Pontszám");
    private JLabel nevMegadLabel = new JLabel("Név:");
    private JTextField nevMegadField = new JTextField();

    private JFrame ujJatekFrame = new JFrame();

    private JPanel ujJatekPanel = new JPanel();
    private JButton ujJatekButton = new JButton("Menü");

    private Data highScores = new Data();
    private Integer points =0;
    private JLabel poiontsLabel = null;

    private JFrame gameFrame ;

    public static Snake snake = null;

    private JMenuBar menuBar = new JMenuBar();
    private JMenu fileMenu = new JMenu("File");
    private JMenuItem exitMenuItem = new JMenuItem("Exit");

    //for tests
    public Data getRangletra(){
        return this.highScores;
    }
    public boolean isRangletraVisible(){
        return rangletraFrame.isVisible();
    }
    public boolean isNevMegadVisible(){
        return nevMegadFrame.isVisible();
    }
    public boolean isUjJatekVisible(){
        return ujJatekFrame.isVisible();
    }
    public boolean isGameVisible(){
        return gameFrame.isVisible();
    }
    public boolean isMenuVisible(){
        return menuFrame.isVisible();
    }


    //end


    public void endOfGame(Rangletra elem){
        this.points = elem.pontszam;
        highScores.addIfNeded(elem);
        this.UpdatehighScores(this.highScores);
        Main.setHighscores(highScores);
        showEndGamePanel();
    }

    public void showEndGamePanel(){
        if (gameFrame.isVisible()){
            gameFrame.setVisible(false);
        }

        poiontsLabel.setText("Ponszám: " + this.points);

        ujJatekFrame.pack();
        ujJatekFrame.setVisible(true);
    }


    public void showMenu(){
        if (rangletraFrame.isVisible()){
            rangletraFrame.setVisible(false);
        }else if (ujJatekFrame.isVisible()){
            ujJatekFrame.setVisible(false);
        }
        menuFrame.setVisible(true);

    }

    public void showRangletra() {
        if (menuFrame.isVisible()){
            menuFrame.setVisible(false);
        }
        rangletraFrame.setVisible(true);

    }

    public void showSetName(){
        if (menuFrame.isVisible()){
            menuFrame.setVisible(false);
        }
        nevMegadFrame.setVisible(true);
    }
    public void showGame(){
        if (nevMegadFrame.isVisible()){
            nevMegadFrame.setVisible(false);
        }
        gameFrame = new JFrame("Game");
        gameFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        gameFrame.setSize(1000,750);
        gameFrame.setResizable(false);
        gameFrame.getRootPane().setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));
        gameFrame.setVisible(true);
        snake = new Snake(currentPlayer,this);
        gameFrame.add(snake);
    }



    public void UpdatehighScores(Data highScores){
        this.highScores = highScores;
        rangletraPanel.removeAll();

        rangletraPanel.add(helyezes);
        rangletraPanel.add(nev);
        rangletraPanel.add(pontszam);
        for (Integer i = 1; i < 6; i++) {//kezeld a nullokat
            try{
            JLabel label = new JLabel(i.toString());
            rangletraPanel.add(label); //index
            String tempnev = highScores.getAtIndexString(i-1);
            Integer temppontszam = highScores.getAtIndexInteger(i-1);
            JLabel labelszoveg = new JLabel(tempnev);
            JLabel labelponszam = new JLabel(temppontszam.toString());
            rangletraPanel.add(labelszoveg);
            rangletraPanel.add(labelponszam);
            }catch (Exception e){
                //nullPtr exep, valodi hibát nem okoz csak teszteléskor dobja játéknál nem
            }
        }
        rangletraPanel.add(vissza);
        rangletraFrame.pack();
    }
    public SnakeWindow(String s, Data highScores){
        super(s);
        exitMenuItem.addActionListener(exitButtonListener);
        fileMenu.add(exitMenuItem);
        menuBar.add(fileMenu);
        jatek.setBackground(Color.cyan);
        rangletra.setBackground(Color.cyan);
        kilepes.setBackground(Color.cyan);

        mainMenu.add(jatek);
        mainMenu.add(rangletra);
        mainMenu.add(kilepes);

        jatek.addActionListener(jatekButtonListener);
        rangletra.addActionListener(menuButtonListener);
        vissza.addActionListener(visszaButtonListener);
        kilepes.addActionListener(exitButtonListener);

        menuFrame.add(mainMenu);
        menuFrame.setJMenuBar(menuBar);
        menuFrame.pack();
        menuFrame.setMinimumSize(new Dimension(200, 300));
        menuFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        UpdatehighScores(highScores);

        rangletraFrame.add(rangletraPanel);
        rangletraFrame.pack();
        rangletraFrame.setMinimumSize(new Dimension(200, 300));
        rangletraFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        nevMegadFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        nevMegadPanel.add(nevMegadLabel);
        nevMegadField.setEditable(true);
        nevMegadPanel.add(nevMegadField);
        nevMegadas.addActionListener(nameSetListener);
        nevMegadPanel.add(nevMegadas);

        nevMegadFrame.add(nevMegadPanel);
        nevMegadFrame.pack();

        ujJatekButton.addActionListener(ujJatekListener);
        kilepes2.addActionListener(exitButtonListener);
        ujJatekPanel.add(ujJatekButton);
        ujJatekPanel.add(kilepes2);
        ujJatekFrame.add(ujJatekPanel);
        ujJatekFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        poiontsLabel = new JLabel("");
        ujJatekPanel.add(poiontsLabel);
        ujJatekFrame.pack();

        //alap menu
        showMenu();
    }
    private ActionListener visszaButtonListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            showMenu();
        }
    };
    private ActionListener menuButtonListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            showRangletra();
        }
    };
    private ActionListener exitButtonListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Main.exit();
        }
    };
    private ActionListener jatekButtonListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            showSetName();
        }
    };

    private ActionListener nameSetListener = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            currentPlayer = nevMegadField.getText();
            showGame();
        }
    };
    private ActionListener ujJatekListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            showMenu();
        }
    };

}
