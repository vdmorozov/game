package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GameFrame extends JFrame{
	private Game game;

	private GamePanel gamePanel;
    private JTabbedPane tabs;
    private ArrayList<JEditorPane> editors;
	private JPanel scriptPanel;
    private JPanel buttonPanel;
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

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		scriptPanel = new JPanel();
		scriptPanel.setLayout(new BorderLayout());

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        restartButton = new JButton("Restart");
        startButton = new JButton("Start");
        buttonPanel.add(startButton);
        buttonPanel.add(restartButton);

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

        /*restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loader.loadSameLevel();
            }
        });*/

		scriptPanel.add(buttonPanel, BorderLayout.PAGE_END);
		scriptPanel.setPreferredSize(new Dimension(200,400));
		this.add(scriptPanel, BorderLayout.LINE_END);

		this.setResizable(false);
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
        this.gamePanel = new GamePanel(game);
        this.gamePanel.setPreferredSize(new Dimension(400, 400));
        this.getContentPane().add(this.gamePanel, BorderLayout.CENTER);
        this.editors = new ArrayList<JEditorPane>();

        if(this.tabs==null) {
            this.tabs = new JTabbedPane();
            this.scriptPanel.add(tabs, BorderLayout.CENTER);
        }
        else {
            this.tabs = new JTabbedPane();
        }

        for(int i=0;i<game.getBallNumber();i++) {
            editors.add(new JEditorPane());
            tabs.addTab("Ball "+i,editors.get(i));
        }
        this.pack();
    }
}
