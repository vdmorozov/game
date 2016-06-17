package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrame extends JFrame{
	private GamePanel gamePanel;
	private JPanel scriptPanel;
	private JEditorPane editor;
	private JButton startButton;
	private JFrame frame;
	
	public GameFrame(GamePanel gamePanel){
		super("Game");
		frame = this;
		this.gamePanel = gamePanel;
		this.gamePanel.setPreferredSize(new Dimension(400, 400));
		this.getContentPane().add(this.gamePanel, BorderLayout.CENTER);
		
		scriptPanel = new JPanel();
		scriptPanel.setLayout(new BorderLayout());
		editor = new JEditorPane();
		
		startButton = new JButton("Start");
		startButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Parser parser = new Parser();
				String commands = editor.getText();
				try{
					parser.parse(commands);
				}catch(IllegalArgumentException e){
					JOptionPane.showMessageDialog(
							frame,
							e.getMessage(),
							"Error",
							JOptionPane.ERROR_MESSAGE
							);
				}
				
				new Thread(){
					@Override
					public void run(){
						startButton.setEnabled(false);
						
						try{
							parser.start(gamePanel.getBall(0));
						}catch(IndexOutOfBoundsException e){
							JOptionPane.showMessageDialog(
									frame,
									e.getMessage(),
									"Error",
									JOptionPane.ERROR_MESSAGE
									);
						}
						
						startButton.setEnabled(true);
					}
				}.start();
				
			}
			
		});
		
		scriptPanel.add(startButton, BorderLayout.PAGE_END);
		scriptPanel.add(editor, BorderLayout.CENTER);
		scriptPanel.setPreferredSize(new Dimension(200,400));
		this.add(scriptPanel, BorderLayout.LINE_END);
		
		this.pack();
		this.setResizable(false);
		this.setVisible(true);
	}
	
	
}
