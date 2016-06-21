package Game;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GameFrame extends JFrame{

    private final static String START="Start";
    private final static String END="End";
    private static int ballsFinished;

    private static Game game;
	private GamePanel gamePanel;
    private JTabbedPane tabs;
    private ArrayList<JEditorPane> editors;
	private JPanel scriptPanel;
    private JPanel tabsPanel;
    private JPanel cards;
    private JPanel buttonPanel0;
    private JPanel buttonPanel1;
	private JButton startButton;
    private JButton replayButton;
    private JButton restartButton;
    private JButton nextLevelButton;
	private JFrame frame;
    private Loader loader;

	private ScriptExpression expr = null;

	public GameFrame(Loader loader){

        super("Game");
        this.loader=loader;
		frame = this;
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		scriptPanel = new JPanel();
		scriptPanel.setLayout(new BorderLayout());

        tabsPanel = new JPanel();
        tabsPanel.setLayout(new BorderLayout());

        cards=new JPanel();
        cards.setLayout(new CardLayout());

        buttonPanel0 = new JPanel();
        buttonPanel0.setLayout(new FlowLayout());

        buttonPanel1=new JPanel();
        buttonPanel1.setLayout(new FlowLayout());

        restartButton = new JButton("Restart");
        startButton = new JButton("Start");
        replayButton = new JButton("Replay");
        nextLevelButton = new JButton("Next");

        buttonPanel0.add(startButton);
        buttonPanel0.add(restartButton);

        buttonPanel1.add(nextLevelButton);
        buttonPanel1.add(replayButton);

        cards.add(buttonPanel0,START);
        cards.add(buttonPanel1,END);

        this.editors = new ArrayList<JEditorPane>();

        startButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent arg0) {
                String commands;
                ballsFinished=0;
                System.out.println("Starting...");
                for(int i=0; i<game.getBallNumber(); i++) {
                    commands=editors.get(i).getText();
                    new Thread(new RunTask(i,commands)).start();
                }
            }

        });

        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Reloading level.");
                loader.loadSameLevel();
            }
        });

        replayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Reloading level.");
                loader.loadSameLevel();
            }
        });

        nextLevelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Loading next level.");
                loader.loadNextLevel();
            }
        });

		//this.setResizable(false);
		this.setVisible(true);
	}

    private class RunTask implements Runnable {
        private int ballNum;
        private String commands;
        public RunTask(int n, String com){
            this.ballNum=n;
            this.commands=com;
        }
        @Override
        public void run() {
            startButton(false);

            try {
                game.startBall(ballNum, commands);
            } catch (IndexOutOfBoundsException e) {
                JOptionPane.showMessageDialog(
                        frame,
                        e.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }

            if(startButtonCheck()){
                startButton(true);
            }

        }
    }
	
	public void setLevel(Game g){

        this.game=g;
        this.getContentPane().removeAll();

        this.gamePanel = new GamePanel(game);
        EndGameAdapter adapt = new EndGameAdapter(this.gamePanel);
        this.game.registerObserver(adapt);
        this.getContentPane().add(this.gamePanel, BorderLayout.CENTER);

        this.gamePanel.setPreferredSize(new Dimension(400, 400));

        tabsPanel.removeAll();
        editors.removeAll(editors);

        this.tabs = new JTabbedPane();
        this.tabsPanel.add(tabs, BorderLayout.CENTER);
        this.scriptPanel.add(tabsPanel, BorderLayout.CENTER);

        for(int i=0;i<game.getBallNumber();i++) {
            JEditorPane editor = new JEditorPane();
            editors.add(editor);
            tabs.addTab("Ball "+i,editors.get(i));
        }

        this.scriptPanel.add(cards, BorderLayout.PAGE_END);
        this.scriptPanel.setPreferredSize(new Dimension(200,400));
        this.add(scriptPanel, BorderLayout.LINE_END);
        this.setStartButtons();
        frame.getContentPane().revalidate();
        frame.getContentPane().repaint();

        this.pack();
    }

    public void setEndGameButtons(){
        CardLayout cl = (CardLayout) (cards.getLayout());
        cl.show(cards,END);
    }

    public void setStartButtons(){
        CardLayout cl = (CardLayout) (cards.getLayout());
        cl.show(cards,START);
        startButton(true);
    }

    public void startButton(boolean condition){
        startButton.setEnabled(condition);
    }

    public static synchronized boolean startButtonCheck() {
        if(ballsFinished!=(game.getBallNumber()-1)){
            ballsFinished++;
            return false;
        }
        else {
           return true;
        }
    }
}
