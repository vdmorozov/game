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
	private JButton startButton;
	private JFrame frame;

	private ScriptExpression expr = null;

	public GameFrame(Game game){
		super("Game");
		frame = this;

		scriptPanel = new JPanel();
		scriptPanel.setLayout(new BorderLayout());

		startButton = new JButton("Start");
		startButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String commands;
				for(int i=0; i<game.getBallNumber(); i++) {
                    commands=editors.get(i).getText();
					//System.out.println(i + ": " + commands);
                    new Thread(new RunTask(i,commands)).start();
                }
			}
			
		});
		
		scriptPanel.add(startButton, BorderLayout.PAGE_END);
		scriptPanel.setPreferredSize(new Dimension(200,400));
		this.add(scriptPanel, BorderLayout.LINE_END);

		setLevel(game);

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

        this.tabs = new JTabbedPane();
        for(int i=0;i<game.getBallNumber();i++) {
            editors.add(new JEditorPane());
            tabs.addTab("Ball"+i,editors.get(i));
        }
        this.scriptPanel.add(tabs, BorderLayout.CENTER);
        this.pack();
    }
}
