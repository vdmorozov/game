package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GameFrame extends JFrame{

    private final static String START="Start";
    private final static String END="End";

    private Game game;
	private GamePanel gamePanel;
    private JTabbedPane tabs;
    private ArrayList<JEditorPane> editors;
	private JPanel scriptPanel;
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

        startButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent arg0) {
                String commands;
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

        this.setStartButtons();
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
            startButton.setEnabled(false);

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

			startButton.setEnabled(true);
        }
    }
	
	public void setLevel(Game g){

        this.game=g;
        this.getContentPane().removeAll();

        this.gamePanel = new GamePanel(game);
        this.getContentPane().add(this.gamePanel, BorderLayout.CENTER);

        this.gamePanel.setPreferredSize(new Dimension(400, 400));

        this.editors = new ArrayList<JEditorPane>();

        this.tabs = new JTabbedPane();
        this.scriptPanel.add(tabs, BorderLayout.CENTER);


        for(int i=0;i<game.getBallNumber();i++) {
            editors.add(new JEditorPane());
            tabs.addTab("Ball "+i,editors.get(i));
        }

        scriptPanel.add(cards, BorderLayout.PAGE_END);
        scriptPanel.setPreferredSize(new Dimension(200,400));
        this.add(scriptPanel, BorderLayout.LINE_END);

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
    }
}
